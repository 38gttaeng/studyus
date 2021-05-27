package com.studyus.assignment.domain;

public class AssignmentGroup {
	private int grNo;
	private String grName;
	private int grColor;
	
	public AssignmentGroup() {}

	public int getGrNo() {
		return grNo;
	}

	public void setGrNo(int grNo) {
		this.grNo = grNo;
	}

	public String getGrName() {
		return grName;
	}

	public void setGrName(String grName) {
		this.grName = grName;
	}

	public int getGrColor() {
		return grColor;
	}

	public void setGrColor(int grColor) {
		this.grColor = grColor;
	}

	@Override
	public String toString() {
		return "AssignmentGroup [grNo=" + grNo + ", grName=" + grName + ", grColor=" + grColor + "]";
	}
}
