#include "socket.hpp"

#include <unistd.h>
#include <stdlib.h>
#include <iostream>

Socket::Socket(std::string addr, unsigned int port, unsigned int maxSize) 
		: msgMaxSize(maxSize) {
	in_addr_t ip = inet_addr(addr.c_str());
	this->socketAddress.sin_family = AF_INET;
	this->socketAddress.sin_addr.s_addr = ip;
	this->socketAddress.sin_port = htons((u_short) port);
}

Socket::~Socket() {

}

void Socket::attach(Thread* thread) {

}

void Socket::start() {
	int hsocket = socket(PF_INET, SOCK_STREAM, 0);
	if (-1 == hsocket) {
		std::cerr << "Unable to create a new socket." << std::endl;
		return;
	}
	int bindResult = bind(hsocket, (struct sockaddr*) &this->socketAddress, 
		sizeof(this->socketAddress));
	if (-1 == bindResult) {
		std::cerr << "Unable to bind to address." << std::endl;
		return;
	}
	int listResult = listen(hsocket, 5);
	if (-1 == listResult) {
		std::cerr << "Unable to listen the socket." << std::endl;
		return;
	}
	while (true) { // TODO Use a proper way to break the loop.
		int hAccept = accept(hsocket, NULL, NULL);
		char* buffer = (char*) malloc(sizeof(char) * this->msgMaxSize + 1);
		for (int i=0; i < this->msgMaxSize +1; i++) { buffer[i] = '\0'; }
		int cb = recv(hAccept, buffer, sizeof(buffer), 0);
		if (cb <= 0) {
			std::cerr << "Unable to read the connected socket." << std::endl;
			return;
		}
		std::cout << "Msg ! " << std::endl << std::string(buffer) << std::endl;
		free(buffer);
		close(hAccept);
	}
	close(hsocket); // Never Reached.
}
