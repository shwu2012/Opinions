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
	public User createUser(User user) {
		User persistentUser = null;
		if (userDao.getByEmail(user.getEmail()) == null) {
			persistentUser = userDao.create(user);
		}
		return persistentUser;
	}

	@Override
	public User modifyUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean authenticateUser(String loginEmail, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User getUser(String email) {
		return userDao.getByEmail(email);
	}

}
