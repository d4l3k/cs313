#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <errno.h>
#include <string.h>
#include <sys/types.h>
#include <stdio.h>
#include <pthread.h>
#include <assert.h>
#include "ipc.h"

int                ipc_s;
short              ipc_rport;
struct sockaddr_in ipc_laddr, ipc_raddr;
pthread_t          ipc_event_thread;

void      p_init    ();
promise_t p_create  ();
void      p_fulfill (promise_t, void*, int);

struct ipc_callback_des {
  void (*callback) (void*);
  void* ibuf;
  int   ilen;
  void* cont;
  struct ipc_callback_des *prev, *next;
};

pthread_mutex_t         ipc_callback_mx;
pthread_cond_t          ipc_callback_empty;
int                     ipc_callback_outstanding_count;
struct ipc_callback_des *ipc_callback_head, *ipc_callback_tail;

void ipc_init (int rport) {
  ipc_s     = socket(AF_INET, SOCK_DGRAM, 0);
  ipc_rport = rport;
  memset (&ipc_laddr, 0, sizeof (ipc_laddr));
  memset (&ipc_raddr, 0, sizeof (ipc_raddr));
  ipc_laddr.sin_family      = AF_INET;
  ipc_laddr.sin_addr.s_addr = htonl(INADDR_ANY);
  ipc_laddr.sin_port        = 0;
  bind (ipc_s, (struct sockaddr *) &ipc_laddr, sizeof (ipc_laddr));
  if (ipc_rport == 0) {
    socklen_t addrlen = sizeof (ipc_laddr);
    getsockname (ipc_s, (struct sockaddr *) &ipc_laddr, &addrlen);
    printf      ("Listening on port %d\n", ntohs (ipc_laddr.sin_port));
  }
}

struct ipc_fulfill_promise_cont {
  promise_t p;
  void*     buf;
  int       len;
};

void* ipc_call_callback (void* cbdv) {
  struct ipc_callback_des* cbd = cbdv;
  cbd->callback (cbd->cont);
  ipc_callback_outstanding_count--;
  free (cbd);
  pthread_mutex_lock (&ipc_callback_mx);
  if (ipc_callback_outstanding_count == 0)
    pthread_cond_signal (&ipc_callback_empty);
  pthread_mutex_unlock (&ipc_callback_mx);
  return 0;
}

void ipc_event_handler() {
  pthread_t cbt;
  char buf[4096];
  while (1) {
    socklen_t addrlen = sizeof (ipc_raddr);
    int rlen = recvfrom (ipc_s, buf, sizeof (buf), 0, (struct sockaddr *) &ipc_raddr, &addrlen);
    pthread_mutex_lock (&ipc_callback_mx);
    struct ipc_callback_des* cbd = ipc_callback_tail;
    assert (cbd);
    ipc_callback_tail = cbd->prev;
    if (ipc_callback_tail == 0)
      ipc_callback_head = 0;
    else
      ipc_callback_tail->next = 0;
    pthread_mutex_unlock (&ipc_callback_mx);
    if (cbd->ibuf) {
      assert (rlen == cbd->ilen);
      memcpy (cbd->ibuf, buf, cbd->ilen);
    } else {
      struct ipc_fulfill_promise_cont* pcont = cbd->cont;
      pcont->buf = malloc (rlen);
      pcont->len = rlen;
      memcpy (pcont->buf, buf, rlen);
    }
    pthread_create (&cbt, 0, ipc_call_callback, cbd);
  }
}

void ipc_enable_callbacks() {
  pthread_mutex_init (&ipc_callback_mx,    0);
  pthread_cond_init  (&ipc_callback_empty, 0);
  pthread_create     (&ipc_event_thread, 0, (void* (*) (void*)) ipc_event_handler, 0);
}

void ipc_enable_promises() {
  p_init();
  ipc_enable_callbacks();
}

void ipc_request (void* buf, int len) {
  ipc_raddr.sin_family = AF_INET;
  ipc_raddr.sin_port   = htons(ipc_rport);
  sendto (ipc_s, buf, len, 0, (struct sockaddr *)&ipc_raddr, sizeof(ipc_raddr));
}

void ipc_request_callback (void* obuf, int olen, void* ibuf, int ilen, void (*callback) (void*), void* cont) {
  struct ipc_callback_des* cbd = malloc (sizeof (struct ipc_callback_des));
  pthread_mutex_lock (&ipc_callback_mx);
  cbd->callback = callback;
  cbd->ibuf     = ibuf;
  cbd->ilen     = ilen;
  cbd->cont     = cont;
  cbd->prev     = 0;
  cbd->next     = ipc_callback_head;
  if (ipc_callback_head)
    ipc_callback_head->prev = cbd;
  ipc_callback_head = cbd;
  if (ipc_callback_tail == 0)
    ipc_callback_tail = cbd;
  ipc_callback_outstanding_count ++;
  pthread_mutex_unlock (&ipc_callback_mx);
  ipc_request (obuf, olen);
}

void ipc_fulfill_promise (void* contv) {
  struct ipc_fulfill_promise_cont* cont = contv;
  p_fulfill (cont->p, cont->buf, cont->len);
  free (cont);
}

promise_t ipc_request_promise (void* obuf, int olen) {
  struct ipc_fulfill_promise_cont* cont = malloc (sizeof (struct ipc_fulfill_promise_cont));
  cont->p = p_create();
  ipc_request_callback (obuf, olen, 0, 0, ipc_fulfill_promise, cont);
  return cont->p;
}

void ipc_recv (void* buf, int len) {
  socklen_t addrlen = sizeof (ipc_raddr);
  int rlen = recvfrom (ipc_s, buf, len, 0, (struct sockaddr *) &ipc_raddr, &addrlen);
  assert (rlen == len);
}

void ipc_respond (void* buf, int len) {
  sendto (ipc_s, buf, len, 0, (struct sockaddr *) &ipc_raddr, sizeof (ipc_raddr));
}

void ipc_finish() {
  pthread_mutex_lock (&ipc_callback_mx);
  while (ipc_callback_outstanding_count)
    pthread_cond_wait (&ipc_callback_empty, &ipc_callback_mx);
  pthread_mutex_unlock (&ipc_callback_mx);
}

struct promise {
  pthread_cond_t fulfilled;
  void*          buf;
  int            len;
};

pthread_mutex_t p_mx;

promise_t p_create() {
  struct promise* p = malloc (sizeof (struct promise));
  pthread_cond_init (&p->fulfilled, 0);
  p->buf = 0;
  return p;
}

void p_fulfill (promise_t p, void* buf, int len) {
  pthread_mutex_lock (&p_mx);
  p->buf = buf;
  p->len = len;
  pthread_cond_signal  (&p->fulfilled);
  pthread_mutex_unlock (&p_mx);
}

void p_get (promise_t p, void* buf, int len) {
  pthread_mutex_lock (&p_mx);
  if (p->buf == 0)
    pthread_cond_wait (&p->fulfilled, &p_mx);
  pthread_mutex_unlock (&p_mx);
  assert (len == p->len);
  memcpy (buf, p->buf, len);
  free (p->buf);
  free (p);
}

void p_init() {
  pthread_mutex_init (&p_mx, 0);
}

