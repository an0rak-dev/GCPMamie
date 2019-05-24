package com.github.an0rakdev.talkgcpmamie.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.github.an0rakdev.talkgcpmamie.pojos.Product;
import com.github.an0rakdev.talkgcpmamie.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/product")
public class ProductController {
	@Autowired 
	private ProductService productService;

	@GetMapping("")
	public ResponseEntity<List<Product>> getProducts() {
		List<Product> productList = new ArrayList<>();
		productList.addAll(productService.getAllProducts());
		return ResponseEntity.ok().body(productList);
	}

	@GetMapping("/{productId}")
	public ResponseEntity<Product> getProduct(
			@PathVariable String productId) {
		Optional<Product> prdt = productService.getDetailOf(productId);
		if (prdt.isPresent()) {
			return ResponseEntity.ok().body(prdt.get());
		}
		return ResponseEntity.notFound().build();
	}
}
