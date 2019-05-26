#include "socket.hpp"

#include "thread.hpp"

class TestThread : public Thread {
	public:
		inline TestThread() {}
		virtual ~TestThread() {}
		inline void run() {
			printf("Received Connection.");
		}
};

int main() {
	Socket socket("127.0.0.1", 10022, 1024);
	TestThread testThread;
	socket.attach(&testThread);
	socket.start();
}
