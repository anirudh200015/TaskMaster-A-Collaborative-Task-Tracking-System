package com.project.Taskmaster.entity;

public class UserDTO {

	private String username;
	private String password;
	
	public UserDTO(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getusername() {
		return username;
	}

	public void setusername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
