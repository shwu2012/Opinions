package edu.sjsu.students.shuangwu.opinions.domain;

import java.util.List;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(detachable = "true")
public class VoteTopic {
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    @Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
    private String encodedKey;

    @Persistent
    @Extension(vendorName="datanucleus", key="gae.pk-id", value="true")
    private Long keyId;

	@Persistent
	private User user;

	@Persistent
	private String text;

	@Persistent
	private String description;
	
	@Persistent
	private Gender gender;
	
	@Persistent
	private boolean friendOnly;
	
	@Persistent
	private VoteStatus status;

	@Persistent(mappedBy = "voteTopic", defaultFetchGroup="true")
	private List<VoteOption> options;

	public String getEncodedKey() {
		return encodedKey;
	}

	public void setEncodedKey(String encodedKey) {
		this.encodedKey = encodedKey;
	}

	public Long getKeyId() {
		return keyId;
	}

	public void setKeyId(Long keyId) {
		this.keyId = keyId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<VoteOption> getOptions() {
		return options;
	}

	public void setOptions(List<VoteOption> options) {
		this.options = options;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public boolean isFriendOnly() {
		return friendOnly;
	}

	public void setFriendOnly(boolean friendOnly) {
		this.friendOnly = friendOnly;
	}

	public VoteStatus getStatus() {
		return status;
	}

	public void setStatus(VoteStatus status) {
		this.status = status;
	}

}
