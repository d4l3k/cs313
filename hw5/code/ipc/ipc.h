#ifndef __ipc_h__
#define __ipc_h__

typedef struct promise* promise_t;
void p_get (promise_t p, void* buf, int len);

void      ipc_init             (int rport);
void      ipc_enable_callbacks ();
void      ipc_enable_promises  ();
void      ipc_request          (void* buf, int len);
void      ipc_request_callback (void* obuf, int olen, void* ibuf, int ilen, void (*callback) (void*), void* cont);
promise_t ipc_request_promise  (void* obuf, int olen);
void      ipc_recv             (void* buf, int len);
void      ipc_respond          (void* buf, int len);
void      ipc_finish           ();

#endif