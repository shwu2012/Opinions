package edu.sjsu.students.shuangwu.opinions.domain;

public enum LoginType {
	DEFAULT, FACEBOOK;
	
	public String makeUserKey(String externalUserId) {
		return name() + ":" + externalUserId;
	}
}
