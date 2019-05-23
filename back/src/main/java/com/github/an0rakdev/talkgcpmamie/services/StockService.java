package com.github.an0rakdev.talkgcpmamie.services;

import com.github.an0rakdev.talkgcpmamie.datas.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockService {

	@Autowired
	private StockRepository stockRepository;

	public boolean checkStock(String code, int quantity) {
		return quantity <= stockRepository.findQuantityOf(code);	
	}

	public void use(String code, int quantity) {
		int initialQuantity = stockRepository.findQuantityOf(code);
		stockRepository.updateQuantity(code, initialQuantity - quantity);
	}
}
