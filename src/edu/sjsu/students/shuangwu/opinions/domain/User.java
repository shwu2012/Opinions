package edu.sjsu.students.shuangwu.opinions.domain;

import java.io.Serializable;
import java.util.List;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(detachable = "true")
public class User implements Serializable {
	private static final long serialVersionUID = 201204291626L;

	@PrimaryKey
	@Persistent
	private String userId;

	@Persistent
	private String externalId;

	@Persistent
	private LoginType loginType;

	@Persistent
	private String name;

	@Persistent
	private Gender gender;

	@Persistent
	private String accessToken;

	@Persistent(mappedBy = "user")
	private List<VoteTopic> voteTopics;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public List<VoteTopic> getVoteTopics() {
		return voteTopics;
	}

	public void setVoteTopics(List<VoteTopic> voteTopics) {
		this.voteTopics = voteTopics;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getExternalId() {
		return externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

	public LoginType getLoginType() {
		return loginType;
	}

	public void setLoginType(LoginType loginType) {
		this.loginType = loginType;
	}
}
