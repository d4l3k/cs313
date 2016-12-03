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

void db_get (int index, struct db_entry* result, void (*callback)(void*), void* cont) {
  struct db_entry* cur = db_root;
  while (cur && index) {
    cur = cur->next;
    index--;
  }
  *result = *cur;
  callback (cont);
}

void populate_database() {
  add_to_database (1, "Alice", 90);
  add_to_database (2, "Bob",   67);
  add_to_database (3, "Cindy", 85);
  add_to_database (4, "Doug",  94);
  add_to_database (5, "Edith", 72);
}

struct print_highest_callback_cont {
  int finished;
  struct db_entry e[5];
};

void print_highest_callback(void* contv) {
  struct print_highest_callback_cont* cont = contv;
  cont->finished++;

  if (cont->finished == 5) {
    int max = -1;
    for (int i = 0; i < 5; i++) {
      if (max < 0 || cont->e[i].mark > cont->e[max].mark) {
        max = i;
      }
    }
    printf("%s has the highest mark at %d%%\n", cont->e[max].name,
           cont->e[max].mark);
    free(cont);
  }
}

void print_highest() {
  struct print_highest_callback_cont* cont = malloc(sizeof(*cont));
  cont->finished = 0;

  for (int i = 0; i < 5; i++) {
    db_get(i, &cont->e[i], print_highest_callback, cont);
  }
}

int main() {
  populate_database();
  print_highest();
}
