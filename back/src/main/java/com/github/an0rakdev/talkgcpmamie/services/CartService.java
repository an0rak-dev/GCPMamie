package com.github.an0rakdev.talkgcpmamie.services;

import java.util.NoSuchElementException;
import com.github.an0rakdev.talkgcpmamie.datas.CartRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceException;


@Service
public class CartService {
	private Log logger = LogFactory.getLog(CartService.class);
	@Autowired
	private ProductService productService;

	@Autowired
	private StockService stockService;

	@Autowired
	private CartRepository cartRepository;

	public void addProduct(String code, int quantity) throws NoSuchElementException, IllegalStateException {
		productService.getDetailOf(code)
			.orElseThrow(() -> new NoSuchElementException());

		try {
			this.moveProductFromStockToCart(code, quantity);
		} catch (PersistenceException ex) {
			throw new IllegalStateException("Unable to move stock to cart", ex);
		}
	}	

	public int getNbOfProducts() {
		return cartRepository.countAll();
	}

	private void moveProductFromStockToCart(String code, int quantity) throws PersistenceException {
		if (stockService.checkStock(code, quantity)) {
			int initialQuantity = cartRepository.findQuantityOf(code);
			stockService.use(code, quantity);
			if (initialQuantity < 0) {
				cartRepository.insertQuantity(code, quantity);
			} else {
				cartRepository.updateQuantity(code, initialQuantity + quantity);
			}
		}
	}
}
