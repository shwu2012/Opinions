package edu.sjsu.students.shuangwu.opinions.service;

import java.util.List;

import edu.sjsu.students.shuangwu.opinions.domain.VoteTopic;

public interface VoteService {
	/**
	 * Create a new vote topic with its options
	 * 
	 * @param topic the new topic
	 * @return the created vote topic with a new assiged id
	 */
	VoteTopic createVote(VoteTopic topic, String userKey);

	void createVoteAction(VoteTopic topic);
	
	/**
	 * Get a vote topic by id
	 * 
	 * @param voteTopicId the vote topic id
	 * @return the vote topic
	 */
	VoteTopic getVoteTopicById(long voteTopicId);

	List<VoteTopic> getVoteTopicsByInitiator(long userId);

	List<VoteTopic> getVoteTopicsByVoter(long userId);

	VoteTopic pickRandomVoteTopicForUser(long userId);
	
	
}
