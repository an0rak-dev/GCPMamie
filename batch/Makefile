EXE_NAME=batch
CC=gcc
CFLAGS=-c -g
LDFLAGS=-lstdc++ -o $(EXE_NAME)

.PHONY: all clean mrproper

all: $(EXE_NAME)

clean:
	rm -f *.o

mrproper: clean
	rm -f $(EXE_NAME)


$(EXE_NAME): socket.o main.o
	$(CC) $^  $(LDFLAGS)

main.o: main.cpp socket.hpp 
	$(CC) $(CFLAGS) -o $@ $<

socket.o: socket.cpp socket.hpp thread.hpp
	$(CC) $(CFLAGS) -o $@ $<
