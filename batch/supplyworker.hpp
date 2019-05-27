#ifndef SUPPLY_WORKER_HPP
#define SUPPLY_WORKER_HPP

#include "runnable.hpp"

class SupplyWorker : public Runnable {
    public: 
        SupplyWorker();
        virtual ~SupplyWorker();
        void run(std::string params);
};

#endif