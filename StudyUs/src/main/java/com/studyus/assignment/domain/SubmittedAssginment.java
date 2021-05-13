package com.studyus.assignment.domain;

public class SubmittedAssginment {
	private int suNo;
	private int mbNo;
	private int asNo;
	private int suMotherNo;
	private String suInsertDate;
	private String suContents;
	private String suFileName;
	private int suStatus;
	
	public SubmittedAssginment() {}

	public int getSuNo() {
		return suNo;
	}

	public void setSuNo(int suNo) {
		this.suNo = suNo;
	}

	public int getMbNo() {
		return mbNo;
	}

	public void setMbNo(int mbNo) {
		this.mbNo = mbNo;
	}

	public int getAsNo() {
		return asNo;
	}

	public void setAsNo(int asNo) {
		this.asNo = asNo;
	}

	public int getSuMotherNo() {
		return suMotherNo;
	}

	public void setSuMotherNo(int suMotherNo) {
		this.suMotherNo = suMotherNo;
	}

	public String getSuInsertDate() {
		return suInsertDate;
	}

	public void setSuInsertDate(String suInsertDate) {
		this.suInsertDate = suInsertDate;
	}

	public String getSuContents() {
		return suContents;
	}

	public void setSuContents(String suContents) {
		this.suContents = suContents;
	}

	public String getSuFileName() {
		return suFileName;
	}

	public void setSuFileName(String suFileName) {
		this.suFileName = suFileName;
	}

	public int getSuStatus() {
		return suStatus;
	}

	public void setSuStatus(int suStatus) {
		this.suStatus = suStatus;
	}

	@Override
	public String toString() {
		return "SubmittedAssginment [suNo=" + suNo + ", mbNo=" + mbNo + ", asNo=" + asNo + ", suMotherNo=" + suMotherNo
				+ ", suInsertDate=" + suInsertDate + ", suContents=" + suContents + ", suFileName=" + suFileName
				+ ", suStatus=" + suStatus + "]";
	}
}
