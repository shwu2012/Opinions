package edu.sjsu.students.shuangwu.opinions.dao;

import java.util.List;
import java.util.Random;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import org.apache.log4j.Logger;

import edu.sjsu.students.shuangwu.opinions.domain.User;

public class UserDaoGaeImpl implements UserDao {
	private static final Logger LOGGER = Logger.getLogger(UserDaoGaeImpl.class);

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
	public User getById(String id) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Query query = pm.newQuery(User.class);
		query.setFilter("userId == idParam");
		query.declareParameters("String idParam");
		List<User> results = (List<User>) query.execute(id);
		User user = null;
		if (!results.isEmpty()) {
			user = results.get(0);
		}
		query.closeAll();
		return user;
	}

	@Override
	public User getRandomUserHavingVoteTopics() {
		PersistenceManager pm = pmf.getPersistenceManager();
		Query query = pm.newQuery(User.class);
		List<User> results = (List<User>) query.execute();
		User user = null;
		if (!results.isEmpty()) {
			boolean found = false;
			int userCount = results.size();
			while (!found) {
				int randomNumber = new Random().nextInt(userCount);
				user = results.get(randomNumber);
				LOGGER.info("pick random number: " + randomNumber);
				LOGGER.info("pick random user: " + user.getName());
				found = !user.getVoteTopics().isEmpty();
			}
		}
		query.closeAll();
		return user;
	}

}
