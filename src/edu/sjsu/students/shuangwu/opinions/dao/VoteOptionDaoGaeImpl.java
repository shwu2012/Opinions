package edu.sjsu.students.shuangwu.opinions.dao;

import javax.jdo.PersistenceManagerFactory;

import org.apache.log4j.Logger;

import edu.sjsu.students.shuangwu.opinions.domain.VoteOption;

public class VoteOptionDaoGaeImpl implements VoteOptionDao {
	private static final Logger LOGGER = Logger
			.getLogger(VoteOptionDaoGaeImpl.class);

	private PersistenceManagerFactory pmf;

	public void setPersistenceManagerFactory(PersistenceManagerFactory pmf) {
		this.pmf = pmf;
	}

	@Override
	public VoteOption create(VoteOption voteOption) {
		LOGGER.debug("calling VoteOptionDaoGaeImpl.create()");
		return null;
	}

	@Override
	public VoteOption getById(long voteOptionId) {
		// TODO Auto-generated method stub
		return null;
	}

}
