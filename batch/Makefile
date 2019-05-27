EXE_NAME=batch
CC=gcc
CFLAGS=-c -g
LDFLAGS=-lstdc++ -o $(EXE_NAME)
WAITING_TIME=-DDUMMY_WORK_TIME_IN_SEC=10

.PHONY: all clean mrproper

all: $(EXE_NAME)

clean:
	rm -f *.o

mrproper: clean
	rm -f $(EXE_NAME)


$(EXE_NAME): supplyworker.o socket.o main.o
	$(CC) $^  $(LDFLAGS)

compile=$(CC) $(CFLAGS) -o $@ $<

main.o: main.cpp socket.hpp 
	$(compile)

socket.o: socket.cpp socket.hpp 
	$(compile)

supplyworker.o: supplyworker.cpp supplyworker.hpp runnable.hpp
	$(compile) $(WAITING_TIME)