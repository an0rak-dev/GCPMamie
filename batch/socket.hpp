#ifndef SOCKET_HPP
#define SOCKET_HPP

#include <string>
#include <sys/types.h>
#include <sys/socket.h>
#include <arpa/inet.h>

#include "thread.hpp"

class Socket {
	public:
		Socket(std::string addr, unsigned int port);
		virtual ~Socket();
		void attach(Thread* thread);
		void start();

	private:
		struct sockaddr_in socketAddress;
};

#endif
