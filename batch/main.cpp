#include "socket.hpp"

#include "thread.hpp"
#include "runnable.hpp"
#include <iostream>

class TestRunnable : public Runnable {
	public:
		inline TestRunnable() {}
		virtual ~TestRunnable() {}
		inline void run(std::string params) {
			std::cout << "Received Connection with params : " << params << std::endl;
		}
};

int main() {
	Socket socket("127.0.0.1", 10022, 1024);
	TestRunnable runnable;
	socket.attach(&runnable);
    std::cout << "Listening on port 10022..." << std::endl;
	socket.start();
}
