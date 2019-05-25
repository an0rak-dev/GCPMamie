package com.github.an0rakdev.talkgcpmamie.services;

import com.github.an0rakdev.talkgcpmamie.datas.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockService {

	@Autowired
	private StockRepository stockRepository;

	public boolean checkStock(String code, int quantity) {
		return quantity <= stockRepository.find(code).getQuantity();	
	}

	public void use(String code, int quantity) {
		double initialQuantity = stockRepository.find(code).getQuantity();
		stockRepository.updateQuantity(code, initialQuantity - quantity);
	}
}
