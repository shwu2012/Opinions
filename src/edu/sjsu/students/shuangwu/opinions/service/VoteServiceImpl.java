package edu.sjsu.students.shuangwu.opinions.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import edu.sjsu.students.shuangwu.opinions.dao.UserDao;
import edu.sjsu.students.shuangwu.opinions.dao.VoteOptionDao;
import edu.sjsu.students.shuangwu.opinions.dao.VoteTopicDao;
import edu.sjsu.students.shuangwu.opinions.domain.User;
import edu.sjsu.students.shuangwu.opinions.domain.VoteTopic;

@Service
public class VoteServiceImpl implements VoteService {
	private static final Logger LOGGER = Logger
			.getLogger(VoteServiceImpl.class);

	private final VoteTopicDao voteTopicDao;
	private final VoteOptionDao voteOptionDao;
	private final UserDao userDao;

	public VoteServiceImpl(VoteTopicDao voteTopicDao,
			VoteOptionDao voteOptionDao, UserDao userDao) {
		this.voteTopicDao = voteTopicDao;
		this.voteOptionDao = voteOptionDao;
		this.userDao = userDao;
	}

	@Override
	public VoteTopic createVote(VoteTopic topic, String userKey) {
		LOGGER.debug("calling VoteServiceImpl.createVote()");
		User user = userDao.getByEmail(userKey);
		user.getVoteTopics().add(topic);
		return null;
	}

	@Override
	public void createVoteAction(VoteTopic topic) {
		// TODO Auto-generated method stub

	}

	@Override
	public VoteTopic getVoteTopicById(long voteTopicId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<VoteTopic> getVoteTopicsByInitiator(String userEmail) {
		List<VoteTopic> voteTopics = Lists.newArrayList();
		User user = userDao.getByEmail(userEmail);
		if (user != null) {
			voteTopics.addAll(user.getVoteTopics());
			LOGGER.info("add vote topics:" + voteTopics.size());
		}
		return voteTopics;
	}

	@Override
	public VoteTopic pickRandomVoteTopicForUser(String userEmail) {
		// TODO Auto-generated method stub
		return null;
	}

}
