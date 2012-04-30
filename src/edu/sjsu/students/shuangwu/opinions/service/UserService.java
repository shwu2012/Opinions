package edu.sjsu.students.shuangwu.opinions.service;

import edu.sjsu.students.shuangwu.opinions.domain.User;

public interface UserService {
	User createUser(User user);

	User modifyUser(User user);

	boolean authenticateUser(String loginEmail, String password);

	User getUser(String email);
}
