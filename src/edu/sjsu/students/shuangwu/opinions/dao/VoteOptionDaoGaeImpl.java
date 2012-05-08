package edu.sjsu.students.shuangwu.opinions.dao;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

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
	public VoteOption getById(String voteOptionEncodedId) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Query query = pm.newQuery(VoteOption.class);
		query.setFilter("encodedKey == idParam");
		query.declareParameters("String idParam");
		List<VoteOption> results = (List<VoteOption>) query
				.execute(voteOptionEncodedId);
		VoteOption voteOption = null;
		if (!results.isEmpty()) {
			voteOption = results.get(0);
		}
		query.closeAll();
		return voteOption;
	}

}
