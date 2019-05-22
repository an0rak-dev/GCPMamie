package com.github.an0rakdev.talkgcpmamie.pojos;

public class Product {
	private String code;
	private String name;
	private Double price;

	public Product() {
		this("", "", 0.0);
	}

	public Product(String code, String name, double price) {
		this.code = code;
		this.name = name;
		this.price = price;
	}

	public String getCode() {
		return this.code;
	}

	public String getName() {
		return this.name;
	}

	public Double getPrice() {
		return this.price;
	}
}
