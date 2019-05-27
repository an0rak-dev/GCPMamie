#ifndef THREAD_HPP
#define THREAD_HPP

#include <string>
#include "runnable.hpp"

class Thread {
	public:
		Thread();
		virtual ~Thread();
		void start(Runnable* process, std::string params);
};

#endif
