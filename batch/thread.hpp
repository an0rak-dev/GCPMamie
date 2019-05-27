#ifndef THREAD_HPP
#define THREAD_HPP

#include <string>

class Thread {
	public:
		inline Thread() {}
		inline virtual ~Thread() {}
		virtual void run(std::string params)           = 0;
};

#endif
