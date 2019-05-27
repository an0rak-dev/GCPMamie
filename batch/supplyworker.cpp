#include "supplyworker.hpp"

#include <iostream>
#include <unistd.h>

#ifndef DUMMY_WORK_TIME_IN_SEC
#define DUMMY_WORK_TIME_IN_SEC 60
#endif

SupplyWorker::SupplyWorker() {

}

SupplyWorker::~SupplyWorker() {

}

void SupplyWorker::run(std::string params) {
    int lastIdx = 0;
    int idx = params.find('|', lastIdx);
    std::string commandNumber = params.substr(lastIdx, idx);
    lastIdx = idx +1;
    idx = params.find('|', lastIdx);
    std::string address = params.substr(lastIdx, idx - lastIdx);
    lastIdx = idx +1;
    idx = params.find('|', lastIdx);
    std::string deliverymethod = params.substr(lastIdx);
    std::cout << "Processing order " << commandNumber << "..." << std::endl;
    sleep(DUMMY_WORK_TIME_IN_SEC);
    std::cout << "Shipment " << commandNumber << " ready to be delivered to \"" 
        << address << "\" using : " << deliverymethod << std::endl;
}