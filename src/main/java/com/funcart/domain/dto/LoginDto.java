package com.funcart.domain.dto;

public class LoginDto {
	
	private String name;
	private String password;

	public String getName() {
		return name;
	}

	public void setName(String username) {
		this.name = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
