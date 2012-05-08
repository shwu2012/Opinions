package edu.sjsu.students.shuangwu.opinions.service;

import java.util.List;

import edu.sjsu.students.shuangwu.opinions.domain.VoteAction;
import edu.sjsu.students.shuangwu.opinions.domain.VoteTopic;

public interface VoteService {
	/**
	 * Create a new vote topic with its options
	 * 
	 * @param topic
	 *            the new topic
	 * @return the created vote topic with a new assiged id
	 */
	VoteTopic createVoteTopic(VoteTopic topic, String userId);

	VoteAction createVoteAction(VoteAction voteAction,
			String voteOptionEncodedId);

	/**
	 * Get a vote topic by id
	 * 
	 * @param voteTopicId
	 *            the vote topic id
	 * @return the vote topic
	 */
	VoteTopic getVoteTopicById(String voteTopicEncodedId);

	List<VoteTopic> getVoteTopicsByInitiator(String userId);

	VoteTopic getRandomVoteTopic();
}
