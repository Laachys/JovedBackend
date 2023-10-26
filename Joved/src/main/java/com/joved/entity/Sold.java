package com.joved.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="joved_sold")
public class Sold {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="joved_sold_id")
	private Integer id;
	
	@Column(name="joved_sold_id_user_buy")
	private String user;
	
	@Column(name="joved_sold_date")
	private String date;
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Sold() {}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	@Column(name="joved_sold_id_product")
	private String product;
}
