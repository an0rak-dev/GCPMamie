package com.github.an0rakdev.talkgcpmamie.pojos;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="stock")
public class Stock {

	private String code;
	private Double quantity;

	public Stock() {
		this("", 0.0);
	}

	public Stock(String code, double qty) {
		this.code = code;
		this.quantity = qty;
	}

	@Id
	@Column(name = "s_code")
	public String getCode() {
		return this.code;
	}

	@Column(name = "s_quantity")
	public Double getQuantity() {
		return this.quantity;
	}

	public void setCode(String code) { this.code = code; }

	public void setQuantity(Double qty) { this.quantity = qty; }
}
