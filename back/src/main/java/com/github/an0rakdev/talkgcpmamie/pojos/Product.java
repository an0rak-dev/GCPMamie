package com.github.an0rakdev.talkgcpmamie.pojos;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="product")
public class Product {

	private String code;
	private String name;
	private Double price;
	private String description;
	private String img;

	public Product() {
		this("", "", 0.0, "", "");
	}

	public Product(String code, String name, double price, String desc, String imgSrc) {
		this.code = code;
		this.name = name;
		this.price = price;
		this.description = desc;
		this.img = imgSrc;
	}

	@Id
	@Column(name = "p_code")
	public String getCode() {
		return this.code;
	}

	@Column(name = "p_name")
	public String getName() {
		return this.name;
	}

	@Column(name = "p_price")
	public Double getPrice() {
		return this.price;
	}

	@Column(name = "p_desc")
	public String getDescription() {
		return this.description;
	}

	@Column(name="p_img_link")
	public String getImage() {
		return this.img;
	}

	public void setCode(String code) { this.code = code; }

	public void setName(String name) { this.name = name; }

	public void setPrice(Double price) { this.price = price; }

	public void setDescription(String desc) { this.description = desc; }

	public void setImage(String img) { this.img = img; }
}
