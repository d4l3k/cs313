#include <stdlib.h>
#include <stdio.h>
#include <string.h>

struct db_entry {
  int              sid;
  char             name [128];
  int              mark;
  char             info [1024];
  struct db_entry* next;
};

struct db_entry* db_root;

void add_to_database (int sid, char* name, int mark) {
  struct db_entry* dbe = malloc (sizeof (struct db_entry));
  dbe->sid = sid;
  strncpy (dbe->name, name, sizeof (dbe->name));
  dbe->mark = mark;
  dbe->next = db_root;
  db_root   = dbe;
}

void db_get (int index, struct db_entry* result) {
  struct db_entry* cur = db_root;
  while (cur && index) {
    cur = cur->next;
    index--;
  }
  *result = *cur;
}

void populate_database() {
  add_to_database (1, "Alice", 90);
  add_to_database (2, "Bob",   67);
  add_to_database (3, "Cindy", 85);
  add_to_database (4, "Doug",  94);
  add_to_database (5, "Edith", 72);
}

void print_highest() {
  struct db_entry e[5];
  for (int i=0; i<5; i++)
    db_get (i, &e[i]);
  int max = -1;
  for (int i=0; i<5; i++)
    if (max < 0 || e[i].mark > e[max].mark)
      max = i;
  printf ("%s has the highest mark at %d%%\n", e[max].name, e[max].mark);
}

int main() {
  populate_database();
  print_highest();
}