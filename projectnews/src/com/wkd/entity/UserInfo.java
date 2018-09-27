package com.wkd.entity;

public class UserInfo {
	private int userId;
	private String username;
	private String password;
	private String realname;
	private boolean gender;
	private String telephone;
	private String email;
	private boolean isAdmin;
	
	public UserInfo() {
	}
	
	public UserInfo(String username, String password, String realname,
			boolean gender, String telephone, String email, boolean isAdmin) {
		super();
		this.username = username;
		this.password = password;
		this.realname = realname;
		this.gender = gender;
		this.telephone = telephone;
		this.email = email;
		this.isAdmin = isAdmin;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public boolean getGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

}
