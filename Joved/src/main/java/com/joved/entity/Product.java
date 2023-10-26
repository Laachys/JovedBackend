package com.joved.entity;

import java.lang.reflect.Array;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="joved_products")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="joved_products_id")
	private Integer id;
	
	@Column(name="joved_products_id_user")
	private String user;
	
	@Column(name="joved_products_name")
	private String name;
	
	@Column(name="joved_products_price")
	private String price;
	
	@Column(name="joved_products_address")
	private String address;
	
	@Column(name="joved_products_description")
	private String description;
	
	@Column(name="joved_products_category")
	private int category;
	
	@Column(name="joved_products_active")
	private String active;
	
	
	
	public Product() {
		
	}

	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getId_user() {
		return user;
	}


	public void setId_user(String id_user) {
		this.user = id_user;
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


	public String getActive() {
		return active;
	}


	public void setActive(String active) {
		this.active = active;
	}


	@Override
	public String toString() {
		return String.format("Producto: [id = "+id+" id_usuario = "+user+ " name = "+name+", price = "+ price+ ", description = "+description+" , category = "+category+" , active = "+active+" ]");
	}


}
