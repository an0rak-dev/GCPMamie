#include "socket.hpp"

#include "thread.hpp"
#include <iostream>

class TestThread : public Thread {
	public:
		inline TestThread() {}
		virtual ~TestThread() {}
		inline void run(std::string params) {
			std::cout << "Received Connection with params : " << params << std::endl;
		}
};

int main() {
	Socket socket("127.0.0.1", 10022, 1024);
	TestThread thread;
	socket.attach(&thread);
    std::cout << "Listening on port 10022..." << std::endl;
	socket.start();
}
