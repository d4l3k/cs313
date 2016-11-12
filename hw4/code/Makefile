CFLAGS = -g -Wall

all: cache-test-correct cache-test-complete cache-test

clean:
	- rm cache-test-complete cache-test cache-test-correct

cache-test-correct: cache-test.o cache_linux.o
cache-test-complete: cache-test-complete.o cache.o
cache-test: cache-test.o cache.o
cache-test.o: cache-test.c cache.h
cache.o: cache.c cache.h

.PHONY: all clean
