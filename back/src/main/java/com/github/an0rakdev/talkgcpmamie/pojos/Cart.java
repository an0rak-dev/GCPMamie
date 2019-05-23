package com.github.an0rakdev.talkgcpmamie.pojos;

import java.util.HashMap;
import java.util.Map;

public class Cart {
	private Map<Product, Integer> content;

	public Cart() {
		this.content = new HashMap<>();
	}

	public Map<Product, Integer> getContent() {
		return this.content;
	}
}
