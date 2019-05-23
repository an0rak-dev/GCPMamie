package com.github.an0rakdev.talkgcpmamie.services;

import java.util.NoSuchElementException;
import com.github.an0rakdev.talkgcpmamie.datas.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CartService {
	@Autowired
	private ProductService productService;

	@Autowired
	private StockService stockService;

	@Autowired
	private CartRepository cartRepository;

	public void addProduct(String code, int quantity) {
		productService.getDetailOf(code)
			.orElseThrow(() -> new NoSuchElementException());	
		this.moveProductFromStockToCart(code, quantity);
	}	

	private void moveProductFromStockToCart(String code, int quantity) {
		if (stockService.checkStock(code, quantity)) {
			int initialQuantity = cartRepository.findQuantityOf(code);
			stockService.use(code, quantity);
			if (initialQuantity > 0) {
				cartRepository.updateQuantity(code, initialQuantity + quantity);	
			} else {
				cartRepository.insertQuantity(code, quantity);
			}
		}
	}
}
