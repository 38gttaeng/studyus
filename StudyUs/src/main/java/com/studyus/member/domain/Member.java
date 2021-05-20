package com.studyus.member.domain;

public class Member {

	private int mbNo;
	private String mbId;
	private String mbPassword;
	private String mbEmail;
	private String mbNickname;
	private char mbPhone;
	private int mbReputation;
	private int mbPhoto;
	private int mbStatus;
	
	public Member() {}

	public Member(String mbId, String mbPassword) {
		super();
		this.mbId = mbId;
		this.mbPassword = mbPassword;
	}

	public Member(int mbNo, String mbId, String mbPassword, String mbEmail, String mbNickname, char mbPhone,
			int mbReputation, int mbPhoto, int mbStatus) {
		super();
		this.mbNo = mbNo;
		this.mbId = mbId;
		this.mbPassword = mbPassword;
		this.mbEmail = mbEmail;
		this.mbNickname = mbNickname;
		this.mbPhone = mbPhone;
		this.mbReputation = mbReputation;
		this.mbPhoto = mbPhoto;
		this.mbStatus = mbStatus;
	}

	public int getMbNo() {
		return mbNo;
	}

	public void setMbNo(int mbNo) {
		this.mbNo = mbNo;
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

	public char getMbPhone() {
		return mbPhone;
	}

	public void setMbPhone(char mbPhone) {
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

	@Override
	public String toString() {
		return "Member [mbNo=" + mbNo + ", mbId=" + mbId + ", mbPassword=" + mbPassword + ", mbEmail=" + mbEmail
				+ ", mbNickname=" + mbNickname + ", mbPhone=" + mbPhone + ", mbReputation=" + mbReputation
				+ ", mbPhoto=" + mbPhoto + ", mbStatus=" + mbStatus + "]";
	}

}
