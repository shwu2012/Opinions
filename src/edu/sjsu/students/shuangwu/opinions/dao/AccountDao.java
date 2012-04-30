package edu.sjsu.students.shuangwu.opinions.dao;

import edu.sjsu.students.shuangwu.opinions.domain.Account;

public interface AccountDao {
	Account create(Account account);
	Account getById(long userId);
}
