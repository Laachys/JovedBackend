package com.joved.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="joved_users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="joved_users_id")
	private Integer id;
	
	@Column(name="joved_users_username")
	private String username;
	
	@Column(name="joved_users_name")
	private String name;
	
	@Column(name="joved_users_surname")
	private String surname;
	
	@Column(name="joved_users_email")
	private String email;
	
	@Column(name="joved_users_date")
	private String date;
	
	@Column(name="joved_users_phone")
	private String phone;
	
	@Column(name="joved_users_password")
	private String password;
	
	public User() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	  public String toString() {
	    return String.format("USUARIO: [username = %d, surname = '%s' , name = '%s' ]", username, surname, name );
	  }
	
}
