package com.joved.interfaces;


public class RegisterProduct {
	
	private Integer id_usuario;
	
	private String name;
	
	private String price;
	
	private String address;
	
	private String description;
	
	private int category;
	
	private int active;
	
	public RegisterProduct() {
		
	}

	public Integer getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(Integer id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}
	
	@Override
	public String toString() {
		return String.format("Producto: [name = $s, price = $s , description = %s , category = $s , active = %s ]", name,price, description, category, active);
	}
	
}
