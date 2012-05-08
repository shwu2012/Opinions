package edu.sjsu.students.shuangwu.opinions.service;

import edu.sjsu.students.shuangwu.opinions.domain.User;

public interface UserService {
	User getOrCreateUser(User user);

	User getUser(String id);
}
