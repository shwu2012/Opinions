package edu.sjsu.students.shuangwu.opinions.domain;

public class Account {
	private long userId;
	private String loginEmail;
	private String password;

	public Account(long userId, String loginEmail, String password) {
		this.userId = userId;
		this.loginEmail = loginEmail;
		this.password = password;
	}

	public long getUserId() {
		return userId;
	}

	public String getLoginEmail() {
		return loginEmail;
	}

	public String getPassword() {
		return password;
	}

}
