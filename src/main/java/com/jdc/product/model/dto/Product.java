package com.jdc.product.model.dto;

public class Product {

	private int id, price;
	private String name;
	private Category category;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	public void setCategoryId(int id) {
		if(category == null) {
			category = new Category();
		}
		
		category.setId(id);
	}
	
	public void setCategoryName(String name) {
		if(category == null) {
			category = new Category();
		}
		category.setName(name);
	}

}
