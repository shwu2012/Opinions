package edu.sjsu.students.shuangwu.opinions.dao;

import edu.sjsu.students.shuangwu.opinions.domain.VoteTopic;

public interface VoteTopicDao {
	VoteTopic create(VoteTopic voteTopic);
	VoteTopic getById(long voteTopicId);
}
