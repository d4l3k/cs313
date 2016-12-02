CFLAGS  += -std=gnu11 -g
UNAME = $(shell uname)
ifeq ($(UNAME), Linux)
LDFLAGS += -pthread
endif
EXES = local local_callback_0 rpc_client rpc_callback_client_0 rpc_promise_client rpc_server rpc_delay_server local_callback_1 rpc_callback_client_1 

all: $(EXES)

clean:
	rm -f *.o $(EXES)

local: local.o
local_callback_0: local_callback_0.o
local_callback_1: local_callback_1.o
rpc_client: rpc_client.o ipc.o
rpc_callback_client_0: rpc_callback_client_0.o ipc.o
rpc_callback_client_1: rpc_callback_client_1.o ipc.o
rpc_promise_client: rpc_promise_client.o ipc.o
rpc_server: rpc_server.o ipc.o
rpc_delay_server: rpc_delay_server.o ipc.o
