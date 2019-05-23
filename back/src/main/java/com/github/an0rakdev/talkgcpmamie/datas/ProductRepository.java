package com.github.an0rakdev.talkgcpmamie.datas;

import java.util.ArrayList;
import java.util.List;
import com.github.an0rakdev.talkgcpmamie.datas.ProductRepository;
import com.github.an0rakdev.talkgcpmamie.pojos.DetailledProduct;
import com.github.an0rakdev.talkgcpmamie.pojos.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductRepository {
	public List<Product> list() {
		final List<Product> result = new ArrayList<Product>();
		result.add(new Product("p01", "Product 1", 42.0));
		result.add(new Product("p02", "Product 2", 2.0));
		result.add(new Product("p03", "Product 3", 9.5));
		result.add(new Product("p04", "Product 4", 12.99));
		result.add(new Product("p05", "Product 5", 1.0));
		result.add(new Product("p06", "Product 6", 1000.2));
		result.add(new Product("p07", "Product 7", 353.0));
		result.add(new Product("p08", "Product 8", 6.8));
		result.add(new Product("p09", "Product 9", 91.12));
		result.add(new Product("p10", "Product 10", 9.2));
		return result;
	}

	public DetailledProduct find(String code) {
		return new DetailledProduct("p01", "Product 1", 42.0,
			"Lorem ipsum sit dolor \n\nEt amet.");
	}
}