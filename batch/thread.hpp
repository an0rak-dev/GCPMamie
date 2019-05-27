#ifndef THREAD_HPP
#define THREAD_HPP

#include <string>
#include <queue>
#include "runnable.hpp"

class Thread {
	public:
		Thread();
		virtual ~Thread();
		void start(Runnable* process, std::string params);
    private:
        std::queue<std::string> queue;
        int currentThreadsUsed;
};

#endif
