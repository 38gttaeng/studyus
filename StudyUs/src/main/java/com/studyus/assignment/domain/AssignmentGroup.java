package com.studyus.assignment.domain;

public class AssignmentGroup {
	private int grNo;
	private int stNo;
	private String grName;
	private String grInfo;
	private int grColor;
	private int grStatus;
	
	public AssignmentGroup() {}

	public int getGrNo() {
		return grNo;
	}

	public void setGrNo(int grNo) {
		this.grNo = grNo;
	}
	
	public int getStNo() {
		return stNo;
	}

	public void setStNo(int stNo) {
		this.stNo = stNo;
	}

	public String getGrName() {
		return grName;
	}

	public void setGrName(String grName) {
		this.grName = grName;
	}
	
	public String getGrInfo() {
		return grInfo;
	}

	public void setGrInfo(String grInfo) {
		this.grInfo = grInfo;
	}

	public int getGrColor() {
		return grColor;
	}

	public void setGrColor(int grColor) {
		this.grColor = grColor;
	}
	
	public int getGrStatus() {
		return grStatus;
	}

	public void setGrStatus(int grStatus) {
		this.grStatus = grStatus;
	}

	@Override
	public String toString() {
		return "AssignmentGroup [grNo=" + grNo + ", stNo=" + stNo + ", grName=" + grName + ", grInfo=" + grInfo
				+ ", grColor=" + grColor + ", grStatus=" + grStatus + "]";
	}

}
