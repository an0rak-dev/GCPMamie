#include <iostream>
#include "socket.hpp"
#include "supplyworker.hpp"

int main() {
	Socket socket("127.0.0.1", 10022, 1024);
	SupplyWorker worker;
	socket.attach(&worker);
    std::cout << "Listening on port 10022..." << std::endl;
	socket.start();
}
