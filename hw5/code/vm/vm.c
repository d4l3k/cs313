#include <stdlib.h>
#include <stdio.h>
#include <fcntl.h>
#include <strings.h>
#include <assert.h>
#include <unistd.h>

/**
 * Parameters
 */
#define PAGE_SIZE           4096
#define PAGE_SIZE_LOG_2     12
#define NUM_VIRTUAL_PAGES   2048
#define NUM_PHYSICAL_FRAMES 32
#define MAX_MAP_ENTRIES     4

/**
 * Performance Counters
 */
int perf_read_count        = 0;
int perf_write_count       = 0;
int perf_page_in_count     = 0;
int perf_zero_fill_count   = 0;
int perf_page_out_count    = 0;
int perf_replacement_count = 0;

/**
 * Physical Memory is Modelled as an Array
 */
unsigned char phys_mem [NUM_PHYSICAL_FRAMES * PAGE_SIZE];

/**
 * Page Table Entry
 */
struct pte_s {
  int is_valid    :1;
  int is_accessed :1;
  int is_dirty    :1;
  int pfn         :20;
};

/**
 * Page Table
 */
struct pte_s page_table [NUM_VIRTUAL_PAGES];

/**
 * Inverse Page Table is Mapping from Physical Frame Number to Page-Table Entry
 */
int inverse_page_table [NUM_PHYSICAL_FRAMES];

#ifdef CLOCK_REPLACEMENT
#define REPLACEMENT_ALGORITHM "clock"

/**
 * Current Position of Clock Replacement Algorithm Hand
 */
int clock_index = 0;

#endif

#ifdef FIFO_REPLACEMENT
#define REPLACEMENT_ALGORITHM "fifo"

/**
 * FIFO Entry
 */
struct fifo_s {
  int pfn;
  struct fifo_s* prev;
  struct fifo_s* next;
};

/**
 * FIFO list of PFN's
 */
struct fifo_s  fifo [NUM_PHYSICAL_FRAMES];
struct fifo_s* fifo_back  = & fifo [0];
struct fifo_s* fifo_front = & fifo [NUM_PHYSICAL_FRAMES -1];


/**
 * Initialize to store list of unallocated PFNs
 */
void fifo_init() {
  int i;

  for (i=0; i<NUM_PHYSICAL_FRAMES; i++) {
    fifo[i] .pfn  = i;
    fifo[i] .prev = & fifo [(i-1 + NUM_PHYSICAL_FRAMES) % NUM_PHYSICAL_FRAMES];
    fifo[i] .next = & fifo [(i+1)                       % NUM_PHYSICAL_FRAMES];
  }
}

/**
 * Front of FIFO is first-in and thus replacement victim for FIFO replacement
 */
int fifo_get_front() {
  return fifo_front-> pfn;
}

/**
 * Move PFN to back (last-in) of FIFO when newly mapped
 */
void fifo_move_to_back (int pfn) {
  struct fifo_s* fe = & fifo [pfn];
  // remove
  fe-> prev-> next        = fe-> next;
  fe-> next-> prev        = fe-> prev;
  if (fe == fifo_front)
    fifo_front            = fe-> prev;
  if (fe == fifo_back)
    fifo_back             = fe-> next;
  // insert
  fe-> prev               = fifo_back-> prev;
  fe-> next               = fifo_back;
  fifo_back-> prev-> next = fe;
  fifo_back-> prev        = fe;
  fifo_back               = fe;
}
#endif

/**
 * Map Entry Maintined by Operating System to Describe Mapping of VM Regions to Files
 */
struct map_entry_s {
  int vpn_start;
  int vpn_end;
  int file_desc;
  int file_offset;
};

/**
 * The Map is a List of Map Entries
 */
struct map_entry_s map [MAX_MAP_ENTRIES];
int                map_length             = 0;

/**
 * Intialize Page Table and Inverse Page Table to Empty
 */
void initialize_vm() {
  int i;

  for (i=0; i<NUM_VIRTUAL_PAGES; i++)
    page_table [i] .is_valid = 0;
  for (i=0; i<NUM_PHYSICAL_FRAMES; i++)
    inverse_page_table [i] = -1;

#ifdef FIFO_REPLACEMENT
  fifo_init();
#endif
}

/**
 * Add File to OS Memory Map
 */
int map_file (char* file_name, int file_offset, unsigned int va, int page_count) {
  int fd;

  fd = open (file_name, O_RDWR);
  if (fd > 0 && map_length < MAX_MAP_ENTRIES) {
    map_length += 1;
    map [map_length-1] .vpn_start   = va >> PAGE_SIZE_LOG_2;
    map [map_length-1] .vpn_end     = map [map_length-1] .vpn_start + page_count - 1;
    map [map_length-1] .file_desc   = fd;
    map [map_length-1] .file_offset = file_offset;
    return 1;
  } else
    return 0;
}


/**
 * Lookup Virtual Address in Page Table
 *    - return physical address if page is in memory and -1 if not
 *    - set access and dirty bits for page-table hits
 */
int pt_lookup (unsigned int va, int is_write) {
  int    vpn, offset, pa;
  struct pte_s* pte;

  // *** TODO
  vpn    = va >> PAGE_SIZE_LOG_2;
  offset = va % PAGE_SIZE;
  pte    = &page_table[vpn];

  assert(vpn < NUM_VIRTUAL_PAGES);
  assert(offset < PAGE_SIZE);
  assert(pte != NULL);
  assert((int)va == ((vpn << PAGE_SIZE_LOG_2) + offset));

  if (pte->is_valid) {
    pte-> is_accessed  = 1;
    pte-> is_dirty    |= is_write;
    pa                 = pte->pfn*PAGE_SIZE + offset; // *** TODO
  } else {
    pa = -1;
  }
  return pa;
}

/**
 * Locate Map Entry for Virtual Page Number
 */
struct map_entry_s* find_map_entry (unsigned int vpn) {
  int i;

  for (i=0; i<map_length; i++)
    if (vpn >= map [i] .vpn_start && vpn <= map [i] .vpn_end)
      return & map [i];
  return 0;
}

/**
 * Write Virtual Page to Disk if Dirty
 */
void page_out_if_dirty (int vpn) {
  struct map_entry_s* map_entry;
  struct pte_s* pte;
  int    ok, file_offset, entry_offset;

  pte = & page_table [vpn];
  if (pte-> is_valid && pte-> is_dirty) {
    map_entry = find_map_entry (vpn);
    assert (map_entry != 0);
    entry_offset = (vpn - map_entry-> vpn_start) << PAGE_SIZE_LOG_2;
    file_offset  = map_entry-> file_offset + entry_offset;
    ok = pwrite (map_entry-> file_desc, & phys_mem [pte-> pfn << PAGE_SIZE_LOG_2], PAGE_SIZE, file_offset);
    assert (ok == PAGE_SIZE);
    pte-> is_dirty = 0;

    perf_page_out_count++;
  }
}

/**
 * Flush All Dirty Pages to Disk
 */
void flush_all_dirty_pages() {
  int vpn;

  for (vpn=0; vpn<NUM_VIRTUAL_PAGES; vpn++)
    page_out_if_dirty (vpn);
}

/**
 * Write Dirty Pages to Disk, Close Mapped Files and Print Performance Counters
 */
void close_and_report () {
  int i;

  flush_all_dirty_pages();

  for (i=0; i<map_length; i++)
    close (map [i] .file_desc);

  printf ("reads:\t\t%d\n",      perf_read_count);
  printf ("writes:\t\t%d\n",     perf_write_count);
  printf ("page ins:\t%d\n",     perf_page_in_count);
  printf ("zero fills:\t%d\n",   perf_zero_fill_count);
  printf ("page outs:\t%d\n",    perf_page_out_count);
  printf ("replacements:\t%d\n", perf_replacement_count);
}


/**
 * Find Replacement Victim
 *    - page-out and invalidate virtual page that currently maps victim
 *    - return victim PFN
 */
int find_replacement_victim() {
  int vpn, pfn;

#ifdef CLOCK_REPLACEMENT
  while ((vpn = inverse_page_table [clock_index]) != -1 && page_table [vpn] .is_accessed) {
    page_table [vpn] .is_accessed = 0;
    clock_index                   = (clock_index + 1) % NUM_PHYSICAL_FRAMES;
  }
  pfn = clock_index;
#endif
#ifdef FIFO_REPLACEMENT
  pfn = fifo_get_front();
  vpn = inverse_page_table [pfn];
#endif

  if (vpn != -1) {
    page_out_if_dirty (vpn);
    page_table [vpn] .is_valid = 0;
    inverse_page_table [pfn]   = -1;
  }

  if (vpn != -1)
    perf_replacement_count++;

  return pfn;
}

/**
 * Handle Page Fault by Demand Paging Virtual Page from Disk into Physical Memory
 *     - returns 1 if successful and 0 if virtual address is invalid
 */
int demand_page_in (unsigned int va) {
  struct map_entry_s* map_entry;
  struct pte_s*       pte;
  int                 pfn, pa, entry_offset, file_offset, vpn, ok;

  // Part 1: This code selects the PTE for the target va
  //         and if it is invalid it gets the page from disk and updates the page table
  vpn = va >> PAGE_SIZE_LOG_2;
  pte = & page_table [vpn];
  if (! pte->is_valid) {

    map_entry = find_map_entry (vpn);
    if (map_entry != 0) {
      pfn          = find_replacement_victim();  // Part 1: this picks the physical page frame for new page
      pa           = pfn << PAGE_SIZE_LOG_2;
      entry_offset = (vpn - map_entry-> vpn_start) << PAGE_SIZE_LOG_2;
      file_offset  = map_entry-> file_offset + entry_offset;
      ok = pread (map_entry-> file_desc, & phys_mem [pa], PAGE_SIZE, file_offset);
      if (ok == 0) {
        bzero (& phys_mem [pfn * PAGE_SIZE], PAGE_SIZE);
        ok = PAGE_SIZE;
        perf_page_in_count--;
        perf_zero_fill_count++;
      }
      // Part 1: This code updates the page table for the new page
      if (ok == PAGE_SIZE) {
        inverse_page_table [pfn] = vpn;
        pte-> is_valid           = 1;
        pte-> is_accessed        = 0;
        pte-> is_dirty           = 0;
        pte-> pfn                = pfn;
#ifdef FIFO_REPLACEMENT
        fifo_move_to_back (pfn);
#endif
      }
    }
  }
  if (pte-> is_valid)
    perf_page_in_count++;
  return pte-> is_valid;
}

/**
 * Get Physical Address for Specificed Virtual Address
 *    - page-in from disk if necessary
 */
int get_pa (unsigned int va, int is_write) {
  int pa, demand_page_success;

  pa = pt_lookup (va, is_write);
  if (pa == -1) {
    demand_page_success = demand_page_in (va);
    if (demand_page_success)
      pa = pt_lookup (va, is_write);
    else
      pa = -1;
  }
  if (pa < 0) {
    printf ("Invalid address issued: 0x%x\n", va);
    exit   (-1);
  }

  if (is_write)
    perf_write_count++;
  else
    perf_read_count++;

  return pa;
}

/**
 * Read value of specificed virtual address byte from physical memory
 *     - handling possible page fault and demand paging from disk if necessary
 */
unsigned char read_byte (int va) {
  return phys_mem [get_pa (va, 0)];
}

/**
 * Write value to specified virtual address by in physical memory
 *     - handling possible page fault and demand paging from disk if necessary
 */
void write_byte (int va, unsigned char value) {
  phys_mem [get_pa (va, 1)] = value;
}

/**
 * Read Value of Integer at Specified Virtual Address
 */
int read_int (int va) {
  int pa, value;

  pa = get_pa (va, 0);

  value = phys_mem [pa+0] << 8*0 |
          phys_mem [pa+1] << 8*1 |
          phys_mem [pa+2] << 8*2 |
          phys_mem [pa+3] << 8*3;
  return value;
}

/**
 * Write Integer Value to Specified Virtual Address
 */
void write_int (int va, unsigned int value) {
  int pa;

  pa = get_pa (va, 1);
  phys_mem [pa+0] = (value >> 8*0) & 0xff;
  phys_mem [pa+1] = (value >> 8*1) & 0xff;
  phys_mem [pa+2] = (value >> 8*2) & 0xff;
  phys_mem [pa+3] = (value >> 8*3) & 0xff;
}

/**
 * Perform sequential read expecting it to contain a contiguous sequence of integers starting with 0
 */
void test_seq_write (int va, int bytes) {
  int i;

  printf ("Test: Sequential Write\n");

  for (i=va; i<va+bytes; i+=4)
    write_int (i, (i-va)/4);
}

/**
 * Perform sequential write to store contiguous sequence of integers start with 0
 */
void test_seq_read (int va, int bytes) {
  int i, v;

  printf ("Test: Sequential Read\n");

  for (i=va; i<va+bytes; i+=4) {
    v = read_int (i);
    if (v != (i-va)/4)
      printf ("read failure va 0x%x value=%d want=%d\n", i, v, (i-va)/4);
  }
}

/**
 * Perform Random Reads
 */
void test_random_read_with_locality (int va, int bytes, int percent_in_working_set, int working_set_size) {
  int i, ra;

  printf ("Test: Random Read with Locality; %d%% of accesses to workset that is %d%% the size of physical memory\n",
          percent_in_working_set, (working_set_size*1000/NUM_PHYSICAL_FRAMES+5)/10);

  for (i=0; i< 10000; i++) {
    if (((rand() % 100) < percent_in_working_set) && (working_set_size * PAGE_SIZE < bytes))
      ra = va + (rand() % (working_set_size * PAGE_SIZE));
    else
      ra = va + (rand() % bytes);
    read_int (ra);
  }
}

/**
 * Main
 */
int main (int argc, char** argv) {
  char* file;
  int   offset, pages, va, test, bytes, ok;

  if (argc != 6) {
    printf ("usage: vm file offset va pages test\n");
    return -1;
  }
  file   = argv [1];
  offset = strtol (argv [2], 0, 0);
  va     = strtol (argv [3], 0, 0);
  pages  = strtol (argv [4], 0, 0);
  test   = strtol (argv [5], 0, 0);
  if ((va & (PAGE_SIZE-1)) != 0) {
    printf ("Virtual address must be page aligned\n");
    return -1;
  }
  if (pages <= 0 ) {
    printf ("Maps size must be non-zero\n");
    return -1;
  }
  if (va < 0 || ((va >> PAGE_SIZE_LOG_2) + pages) > NUM_VIRTUAL_PAGES) {
    printf ("Virtual address region out of range; maximum address is 0x%x\n", (NUM_VIRTUAL_PAGES << PAGE_SIZE_LOG_2)-1);
    return -1;
  }
  printf ("Mapping file '%s' starting at offset %d to virtual addresses 0x%x through 0x%x using %s replacement\n",
          file, offset, va, va + (pages << PAGE_SIZE_LOG_2) -1, REPLACEMENT_ALGORITHM);

  ok = map_file (file, offset, va, pages);
  if (! ok) {
    printf ("Unable to map file\n");
    return -1;
  }
  bytes = pages << PAGE_SIZE_LOG_2;

  initialize_vm();

  switch (test) {
    case 0:
      test_seq_write (va, bytes);
      break;
    case 1:
      test_seq_read  (va, bytes);
      break;
    case 2:
      test_random_read_with_locality (va, bytes, 0, 0);
      break;
    case 3:
      test_random_read_with_locality (va, bytes, 50, (NUM_PHYSICAL_FRAMES*10 * 0.66 + 5)/10);
      break;
    case 4:
      test_random_read_with_locality (va, bytes, 80, (NUM_PHYSICAL_FRAMES*10 * 0.66 + 5)/10);
      break;
    case 5:
      test_random_read_with_locality (va, bytes, 90, (NUM_PHYSICAL_FRAMES*10 * 0.66 + 5)/10);
      break;
    case 6:
      test_random_read_with_locality (va, bytes, 99, (NUM_PHYSICAL_FRAMES*10 * 0.66 + 5)/10);
      break;
  }

  close_and_report();
}
