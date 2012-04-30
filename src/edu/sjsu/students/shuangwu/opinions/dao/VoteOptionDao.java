package edu.sjsu.students.shuangwu.opinions.dao;

import edu.sjsu.students.shuangwu.opinions.domain.VoteOption;

public interface VoteOptionDao {
	VoteOption create(VoteOption voteOption);
	VoteOption getById(long voteOptionId);
}
