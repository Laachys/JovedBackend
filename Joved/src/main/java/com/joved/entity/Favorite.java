package com.joved.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="joved_products_favorites")
public class Favorite {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="joved_products_favorites_id")
	private Integer id;
	
	@Column(name="joved_products_favorites_id_user")
	private String user;
	
	@Column(name="joved_products_favorites_id_product")
	private String product;
	
	
	public Favorite() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String id_user) {
		this.user = id_user;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String id_product) {
		this.product = id_product;
	}
	
}
