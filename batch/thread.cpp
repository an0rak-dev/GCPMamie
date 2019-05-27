#include "thread.hpp"

#include <iostream>
#include <thread>

void doRun(Runnable* process, std::string params) {
    process->run(params);
}

Thread::Thread() {}

Thread::~Thread() {}

void Thread::start(Runnable* process, std::string params) {
    std::cout << "Receiving " << params.substr(0, params.find('|')) << std::endl;
    std::thread thread(doRun, process, params);
    thread.join();
}