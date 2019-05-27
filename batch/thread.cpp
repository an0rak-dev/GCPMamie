#include "thread.hpp"

#include <thread>

void doRun(Runnable* process, std::string params) {
    process->run(params);
}

Thread::Thread() {}

Thread::~Thread() {}

void Thread::start(Runnable* process, std::string params) {
    std::thread thread(doRun, process, params);
    thread.join();
}