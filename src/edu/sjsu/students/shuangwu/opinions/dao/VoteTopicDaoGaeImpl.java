package edu.sjsu.students.shuangwu.opinions.dao;

import java.util.List;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

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
	public VoteTopic getById(String voteTopicEncodedId) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Query query = pm.newQuery(VoteTopic.class);
		query.setFilter("encodedKey == idParam");
		query.declareParameters("String idParam");
		List<VoteTopic> results = (List<VoteTopic>) query
				.execute(voteTopicEncodedId);
		VoteTopic voteTopic = null;
		if (!results.isEmpty()) {
			voteTopic = results.get(0);
		}
		query.closeAll();
		return voteTopic;
	}
}
