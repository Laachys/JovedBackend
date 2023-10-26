package com.joved.interfaces;

public class RegisterUser {
	
	private String username;

	private String name;
	
	private String surname;

	private String email;

	private String date;

	private String phone;
	
	private String password;
	
	public RegisterUser() {
		
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
	    return String.format("USUARIO: [username = %s, surname = '%s' , name = '%s' ]", username, surname, name );
	  }
}
