package edu.sjsu.students.shuangwu.opinions.service;

import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import edu.sjsu.students.shuangwu.opinions.dao.UserDao;
import edu.sjsu.students.shuangwu.opinions.dao.VoteOptionDao;
import edu.sjsu.students.shuangwu.opinions.dao.VoteTopicDao;
import edu.sjsu.students.shuangwu.opinions.domain.User;
import edu.sjsu.students.shuangwu.opinions.domain.VoteAction;
import edu.sjsu.students.shuangwu.opinions.domain.VoteOption;
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
	public VoteTopic createVoteTopic(VoteTopic topic, String userId) {
		User user = userDao.getById(userId);
		if (user != null) {
			user.getVoteTopics().add(topic);
			return topic;
		}
		return null;
	}

	@Override
	public VoteAction createVoteAction(VoteAction voteAction,
			String voteOptionEncodedId) {
		VoteOption voteOption = voteOptionDao.getById(voteOptionEncodedId);
		if (voteOption != null) {
			voteOption.getActions().add(voteAction);
			return voteAction;
		}
		return null;
	}

	@Override
	public VoteTopic getVoteTopicById(String voteTopicEncodedId) {
		return voteTopicDao.getById(voteTopicEncodedId);
	}

	@Override
	public List<VoteTopic> getVoteTopicsByInitiator(String userId) {
		List<VoteTopic> voteTopics = Lists.newArrayList();
		User user = userDao.getById(userId);
		if (user != null) {
			voteTopics.addAll(user.getVoteTopics());
			LOGGER.info("add vote topics:" + voteTopics.size());
		}
		return voteTopics;
	}

	@Override
	public VoteTopic getRandomVoteTopic() {
		VoteTopic voteTopic = null;
		User user = userDao.getRandomUserHavingVoteTopics();
		if (user != null) {
			int randomNumber = new Random().nextInt(user.getVoteTopics().size());
			voteTopic = user.getVoteTopics().get(randomNumber);
			LOGGER.info("pick random number: " + randomNumber);
			LOGGER.info("pick random vote-topic: " + voteTopic.getText());
		}
		return voteTopic;
	}

}
