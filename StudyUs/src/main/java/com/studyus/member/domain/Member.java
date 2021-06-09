package com.studyus.member.domain;

public class Member {

	private int mbNo;
	private int rnum;
	private String mbId;
	private String mbPassword;
	private String mbName;
	private String mbEmail;
	private String mbNickname;
	private String mbPhone;
	private int mbReputation;
	private int mbPhoto;
	private int mbStatus;
	private String authKey;
	
	public Member() {}
	
	public Member(String mbId, String mbPassword) {
		super();
		this.mbId = mbId; 
		this.mbPassword = mbPassword;
	}
	
	public Member(String mbId, int mbStatus) {
		super();
		this.mbId = mbId;
		this.mbStatus = mbStatus;
	}

	public Member(String mbId, String mbName, String mbEmail) {
		super();
		this.mbId = mbId;
		this.mbName = mbName;
		this.mbEmail = mbEmail;
	} 
	
	public Member(String mbId, String mbName, String mbEmail, String mbNickname) {
		super();
		this.mbId = mbId;
		this.mbName = mbName;
		this.mbEmail = mbEmail;
		this.mbNickname = mbNickname;
	}

	public int getMbNo() {
		return mbNo;
	}

	public void setMbNo(int mbNo) {
		this.mbNo = mbNo;
	}
	
	public int getRnum() {
		return rnum;
	}

	public void setRnum(int rnum) {
		this.rnum = rnum;
	}

	public String getMbId() {
		return mbId;
	}

	public void setMbId(String mbId) {
		this.mbId = mbId;
	}

	public String getMbPassword() {
		return mbPassword;
	}

	public void setMbPassword(String mbPassword) {
		this.mbPassword = mbPassword;
	}

	public String getMbName() {
		return mbName;
	}

	public void setMbName(String mbName) {
		this.mbName = mbName;
	}

	public String getMbEmail() {
		return mbEmail;
	}

	public void setMbEmail(String mbEmail) {
		this.mbEmail = mbEmail;
	}

	public String getMbNickname() {
		return mbNickname;
	}

	public void setMbNickname(String mbNickname) {
		this.mbNickname = mbNickname;
	}

	public String getMbPhone() {
		return mbPhone;
	}

	public void setMbPhone(String mbPhone) {
		this.mbPhone = mbPhone;
	}

	public int getMbReputation() {
		return mbReputation;
	}

	public void setMbReputation(int mbReputation) {
		this.mbReputation = mbReputation;
	}

	public int getMbPhoto() {
		return mbPhoto;
	}

	public void setMbPhoto(int mbPhoto) {
		this.mbPhoto = mbPhoto;
	}

	public int getMbStatus() {
		return mbStatus;
	}

	public void setMbStatus(int mbStatus) {
		this.mbStatus = mbStatus;
	}
	
	public String getAuthKey() {
		return authKey;
	}

	public void setAuthKey(String authKey) {
		this.authKey = authKey;
	}

	@Override
	public String toString() {
		return "Member [mbNo=" + mbNo + ", mbId=" + mbId + ", mbPassword=" + mbPassword + ", mbName=" + mbName
				+ ", mbEmail=" + mbEmail + ", mbNickname=" + mbNickname + ", mbPhone=" + mbPhone + ", mbReputation="
				+ mbReputation + ", mbPhoto=" + mbPhoto + ", mbStatus=" + mbStatus + ", authKey=" + authKey + "]";
	}
	
	
}
