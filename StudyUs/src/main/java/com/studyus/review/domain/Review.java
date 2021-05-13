package com.studyus.review.domain;

public class Review {
	
	private int rvNo;
	private String rvContents;
	private int rvRating;
	private int rvStatus;
	
	public Review() {}

	public Review(int rvNo, String rvContents, int rvRating, int rvStatus) {
		super();
		this.rvNo = rvNo;
		this.rvContents = rvContents;
		this.rvRating = rvRating;
		this.rvStatus = rvStatus;
	}

	public int getRvNo() {
		return rvNo;
	}

	public void setRvNo(int rvNo) {
		this.rvNo = rvNo;
	}

	public String getRvContents() {
		return rvContents;
	}

	public void setRvContents(String rvContents) {
		this.rvContents = rvContents;
	}

	public int getRvRating() {
		return rvRating;
	}

	public void setRvRating(int rvRating) {
		this.rvRating = rvRating;
	}

	public int getRvStatus() {
		return rvStatus;
	}

	public void setRvStatus(int rvStatus) {
		this.rvStatus = rvStatus;
	}

	@Override
	public String toString() {
		return "Review [rvNo=" + rvNo + ", rvContents=" + rvContents + ", rvRating=" + rvRating + ", rvStatus="
				+ rvStatus + "]";
	}
	
	
}
