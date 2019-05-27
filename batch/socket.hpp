#ifndef SOCKET_HPP
#define SOCKET_HPP

#include <string>
#include <sys/types.h>
#include <sys/socket.h>
#include <arpa/inet.h>

#include "runnable.hpp"

class Socket {
	public:
		Socket(std::string addr, unsigned int port, unsigned int msgMaxSize);
		virtual ~Socket();
		void attach(Runnable* process);
		void start();

	private:
		struct sockaddr_in socketAddress;
		unsigned int msgMaxSize;
        Runnable* processToRun;
};

#endif
