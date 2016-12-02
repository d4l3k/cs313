all: vm-fifo vm-clock

vm-fifo: vm.c
	gcc -o vm-fifo -DFIFO_REPLACEMENT vm.c

vm-clock: vm.c
	gcc -o vm-clock -DCLOCK_REPLACEMENT vm.c

clean:
	rm -f vm-fifo vm-clock


