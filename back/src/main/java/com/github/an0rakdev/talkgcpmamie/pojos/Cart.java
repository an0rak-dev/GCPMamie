package com.github.an0rakdev.talkgcpmamie.pojos;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="cart")
public class Cart {
	
	private String code;
	private Double quantity;

	public Cart() {
		this("", 0.0);
	}

	public Cart(String code, double qty) {
		this.code = code;
		this.quantity = qty;
	}

	@Id
	@Column(name = "c_code")
	public String getCode() {
		return this.code;
	}

	@Column(name = "c_quantity")
	public Double getQuantity() {
		return this.quantity;
	}

	public void setCode(String code) { this.code = code; }

	public void setQuantity(Double qty) { this.quantity = qty; }
}
