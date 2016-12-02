#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include "ipc.h"

struct db_entry {
  int              sid;
  char             name [128];
  int              mark;
  char             info [1024];
  struct db_entry* next;
};

promise_t db_get (int index) {
  // TODO
  return 0;
}

void print_highest() {

  // This is the code from rpc_client.c
  // TODO: Change it to use promises
//  struct db_entry e[5];
//  for (int i=0; i<5; i++)
//    db_get (i, &e[i]);
//  int max = -1;
//  for (int i=0; i<5; i++)
//    if (max < 0 || e[i].mark > e[max].mark)
//      max = i;
//  printf ("%s has the highest mark at %d%%\n", e[max].name, e[max].mark);
}

int main (int argc, char** argv) {
  if (argc != 2) {
    fprintf (stderr, "usage: xxx remote-port\n");
    return 1;
  }
  ipc_init (atoi (argv[1]));
  ipc_enable_promises();

  print_highest();

  ipc_finish();
}