package com.github.an0rakdev.talkgcpmamie.controllers;

import java.util.ArrayList;
import java.util.List;
import com.github.an0rakdev.talkgcpmamie.pojos.Product;
import com.github.an0rakdev.talkgcpmamie.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/product")
public class ProductController {
	@Autowired 
	private ProductService productService;

	@GetMapping("")
	public List<Product> getProducts() {
		return productService.getAllProducts();
	}
}
