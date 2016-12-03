#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "ipc.h"

struct db_entry {
  int sid;
  char name[128];
  int mark;
  char info[1024];
  struct db_entry* next;
};

void db_get(int index, struct db_entry* result, void (*callback)(void*),
            void* cont) {
  ipc_request_callback(&index, sizeof(index), result, sizeof(*result), callback,
                       cont);
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

int main(int argc, char** argv) {
  if (argc != 2) {
    fprintf(stderr, "usage: xxx remote-port\n");
    return 1;
  }
  ipc_init(atoi(argv[1]));
  ipc_enable_callbacks();

  print_highest();

  ipc_finish();
}
