/*
 * User
 * 16-july-2020
 */

package com.todo.bean;

public class User {
	
	private int userId;
	private String name;
	private String email;
	private String password;

	public User(){
		//default constructor
	}
	
	/**
	 * This is the parametrized constructor to set values of
	 * name, email and password. 
	 */
	public User(String name,String email,String password) {
		this.name=name;
		this.email=email;
		this.password=password;
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
