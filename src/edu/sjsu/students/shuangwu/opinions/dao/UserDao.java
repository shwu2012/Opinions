package edu.sjsu.students.shuangwu.opinions.dao;

import edu.sjsu.students.shuangwu.opinions.domain.User;

public interface UserDao {
	User create(User user);
	User getByEmail(String email);
}
