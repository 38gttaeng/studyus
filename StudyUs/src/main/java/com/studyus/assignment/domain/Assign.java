package com.studyus.assignment.domain;

public class Assign {
	
	private int anNo;
	private int grNo;
	private int mbNo;
	private int anCheck;
	
	public Assign() {}

	public int getAnNo() {
		return anNo;
	}

	public void setAnNo(int anNo) {
		this.anNo = anNo;
	}

	public int getGrNo() {
		return grNo;
	}

	public void setGrNo(int grNo) {
		this.grNo = grNo;
	}

	public int getMbNo() {
		return mbNo;
	}

	public void setMbNo(int mbNo) {
		this.mbNo = mbNo;
	}

	public int getAnCheck() {
		return anCheck;
	}

	public void setAnCheck(int anCheck) {
		this.anCheck = anCheck;
	}

	@Override
	public String toString() {
		return "Assign [anNo=" + anNo + ", grNo=" + grNo + ", mbNo=" + mbNo + ", anCheck=" + anCheck + "]";
	}

}
