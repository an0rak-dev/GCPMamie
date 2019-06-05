package com.github.an0rakdev.talkgcpmamie.services;

import java.sql.SQLException;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.github.an0rakdev.talkgcpmamie.datas.CartRepository;
import com.github.an0rakdev.talkgcpmamie.pojos.Product;
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

	public void addProduct(String code, int quantity) throws ServiceException {
		Optional<Product> optionalProduct = productService.getDetailOf(code);
		if (optionalProduct.isPresent()) {
			if (!this.moveProductFromStockToCart(code, quantity)) {
				throw new ServiceException("Unable to add to cart");
			}
		}
	}	

	public int getNbOfProducts() {
		return cartRepository.countAll() + 42;
	}

	private boolean moveProductFromStockToCart(String code, int quantity) {
		if (stockService.checkStock(code, quantity)) {
			try {
				cartRepository.updateQuantityOf(code, quantity);
				stockService.use(code, quantity);
				return true;
			} catch (SQLException ex) {
				return false;
			}
		} else {
			logger.warn("Not enough stock for product "+ code + " (requested "+ quantity +")");
		}
		return false;
	}
}
