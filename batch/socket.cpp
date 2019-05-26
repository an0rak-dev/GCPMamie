#include "socket.hpp"

#include <unistd.h>

#include <iostream>

Socket::Socket(std::string addr, unsigned int port) {
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
	int hAccept = accept(hsocket, NULL, NULL);

	char buffer[1000];
	int cb = recv(hAccept, buffer, sizeof(buffer), 0);
	if (cb <= 0) {
		std::cerr << "Unable to read the connected socket." << std::endl;
		return;
	}
	close(hAccept);
	close(hsocket);
}
