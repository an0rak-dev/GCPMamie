package com.github.an0rakdev.talkgcpmamie.services;

import java.sql.SQLException;
import java.util.NoSuchElementException;
import com.github.an0rakdev.talkgcpmamie.datas.CartRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.postgresql.util.PSQLException;
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

	public void addProduct(String code, int quantity) throws NoSuchElementException, ServiceException {
		productService.getDetailOf(code)
			.orElseThrow(() -> new NoSuchElementException());

		this.moveProductFromStockToCart(code, quantity);
	}	

	public int getNbOfProducts() {
		return cartRepository.countAll();
	}

	private void moveProductFromStockToCart(String code, int quantity) throws ServiceException {
		if (stockService.checkStock(code, quantity)) {
			try {
				cartRepository.insertQuantity(code, quantity);
				stockService.use(code, quantity);
			} catch (SQLException ex) {
				throw new ServiceException("Unable to add the product " + code + " to the cart");
			}
		} else {
			logger.warn("Not enough stock for product "+ code + " (requested "+ quantity +")");
		}
	}
}
