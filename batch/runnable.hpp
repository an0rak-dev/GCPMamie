#ifndef RUNNABLE_HPP
#define RUNNABLE_HPP

#include <string>

class Runnable {
    public:
        inline Runnable() {}
        inline virtual ~Runnable() {}
        virtual void run(std::string params)                    = 0;
};

#endif