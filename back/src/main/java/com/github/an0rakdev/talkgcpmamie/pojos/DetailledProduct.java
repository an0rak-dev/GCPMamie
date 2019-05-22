package com.github.an0rakdev.talkgcpmamie.pojos;

public class DetailledProduct extends Product {
	private String description;

	public DetailledProduct() {
		this("", "", 0.0, "");
	}

	public DetailledProduct(String code, String name, 
		Double price, String desc) {
		super(code, name, price);
		this.description = desc;
	}

	public String getDescription() {
		return this.description;
	}
}
