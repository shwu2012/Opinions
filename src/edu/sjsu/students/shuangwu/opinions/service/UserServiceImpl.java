package edu.sjsu.students.shuangwu.opinions.service;

import org.springframework.stereotype.Service;

import edu.sjsu.students.shuangwu.opinions.dao.UserDao;
import edu.sjsu.students.shuangwu.opinions.domain.User;

@Service
public class UserServiceImpl implements UserService {

	private final UserDao userDao;
	
	public UserServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public User getOrCreateUser(User user) {
		User persistentUser = userDao.getById(user.getUserId());
		if (persistentUser == null) {
			persistentUser = userDao.create(user);
		} else {
			persistentUser.setAccessToken(user.getAccessToken());
			persistentUser.setName(user.getName());
			persistentUser.setGender(user.getGender());
		}
		return persistentUser;
	}

	@Override
	public User getUser(String id) {
		return userDao.getById(id);
	}

}
