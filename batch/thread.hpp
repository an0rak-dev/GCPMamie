#ifndef THREAD_HPP
#define THREAD_HPP

class Thread {
	public:
		inline Thread() {}
		inline virtual ~Thread() {}
		virtual void run()           = 0;
};

#endif
