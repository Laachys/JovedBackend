package com.joved.entity;

public class Response {

	private String response;
	
	public Response() {
		
	}
	
	public String getResponse() {
		return response;
	}
	
	public void setResponse(String response) {
		this.response = response;
	}
	

	@Override
	  public String toString() {
	    return String.format("response = %d", response);
	  }
}
