package com.github.an0rakdev.talkgcpmamie.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.github.an0rakdev.talkgcpmamie.datas.ProductRepository;
import com.github.an0rakdev.talkgcpmamie.pojos.DetailledProduct;
import com.github.an0rakdev.talkgcpmamie.pojos.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;

	public List<Product> getAllProducts() {
		return this.productRepository.list();	
	}

	public Optional<DetailledProduct> getDetailOf(String code) {
		DetailledProduct product = productRepository.find(code);
		return (null == product) ? Optional.empty() : Optional.of(product);
	}
}
