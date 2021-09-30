package com.devs4j.Rest.Model;

public class User {
	
	private String nickName;
	private String user;
	private String password;
	
	public User() {

	}

	public User(String nickName, String user, String password) {
		super();
		this.nickName = nickName;
		this.user = user;
		this.password = password;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}	

}
