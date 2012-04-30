package edu.sjsu.students.shuangwu.opinions.dao;

import javax.jdo.PersistenceManagerFactory;

import org.apache.log4j.Logger;

import edu.sjsu.students.shuangwu.opinions.domain.VoteTopic;

public class VoteTopicDaoGaeImpl implements VoteTopicDao {
	private static final Logger LOGGER = Logger
			.getLogger(VoteTopicDaoGaeImpl.class);
	
	private PersistenceManagerFactory pmf;

	public void setPersistenceManagerFactory(PersistenceManagerFactory pmf) {
		this.pmf = pmf;
	}

	@Override
	public VoteTopic create(VoteTopic voteTopic) {
		LOGGER.debug("calling VoteTopicDaoGaeImpl.create()");
		return null;
	}

	@Override
	public VoteTopic getById(long voteTopicId) {
		// TODO Auto-generated method stub
		return null;
	}

}
