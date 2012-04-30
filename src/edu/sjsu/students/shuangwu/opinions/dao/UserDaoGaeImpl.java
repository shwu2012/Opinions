package edu.sjsu.students.shuangwu.opinions.dao;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import edu.sjsu.students.shuangwu.opinions.domain.User;

public class UserDaoGaeImpl implements UserDao {

	private PersistenceManagerFactory pmf;

	public void setPersistenceManagerFactory(PersistenceManagerFactory pmf) {
		this.pmf = pmf;
	}

	@Override
	public User create(User user) {
		PersistenceManager pm = pmf.getPersistenceManager();
		User persistedUser = pm.makePersistent(user);
		// return pm.detachCopy(persistedUser);
		return persistedUser;
	}

	@Override
	public User getByEmail(String email) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Query query = pm.newQuery(User.class);
	    query.setFilter("email == emailParam");
	    query.setOrdering("email desc");
	    query.declareParameters("String emailParam");
		List<User> results = (List<User>) query.execute(email);
		User user = null;
		if (!results.isEmpty()) {
			user = results.get(0);
		}
		query.closeAll();
		return user;
	}

}
