package com.iwcn.training3.Models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ModelProduct {
	@Id
	private String code;
	private String name;
	private String description;
	private int price;
	
	public ModelProduct() {
		super();
	}
	
	public ModelProduct(String code, String name, String description, int price) {
		this.code = code;
		this.name = name;
		this.description = description;
		this.price = price;
	}
	
	public String showProdSum() {
		return String.format("Product id:%s, name:%s, description:%s, price:%s",code,name,description,price);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
}