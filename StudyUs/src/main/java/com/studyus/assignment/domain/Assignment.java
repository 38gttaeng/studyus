package com.studyus.assignment.domain;

public class Assignment {
	private int asNo;
	private int stNo;
	private String asName;
	private String asContents;
	private String asInsertDate;
	private String asDeadLine;
	private int asMotherNo;
	private int asStatus;
	
	public Assignment() {}

	public int getAsNo() {
		return asNo;
	}

	public void setAsNo(int asNo) {
		this.asNo = asNo;
	}

	public int getStNo() {
		return stNo;
	}

	public void setStNo(int stNo) {
		this.stNo = stNo;
	}

	public String getAsName() {
		return asName;
	}

	public void setAsName(String asName) {
		this.asName = asName;
	}

	public String getAsContents() {
		return asContents;
	}

	public void setAsContents(String asContents) {
		this.asContents = asContents;
	}

	public String getAsInsertDate() {
		return asInsertDate;
	}

	public void setAsInsertDate(String asInsertDate) {
		this.asInsertDate = asInsertDate;
	}

	public String getAsDeadLine() {
		return asDeadLine;
	}

	public void setAsDeadLine(String asDeadLine) {
		this.asDeadLine = asDeadLine;
	}

	public int getAsMotherNo() {
		return asMotherNo;
	}

	public void setAsMotherNo(int asMotherNo) {
		this.asMotherNo = asMotherNo;
	}

	public int getAsStatus() {
		return asStatus;
	}

	public void setAsStatus(int asStatus) {
		this.asStatus = asStatus;
	}

	@Override
	public String toString() {
		return "Assignment [asNo=" + asNo + ", stNo=" + stNo + ", asName=" + asName + ", asContents=" + asContents
				+ ", asInsertDate=" + asInsertDate + ", asDeadLine=" + asDeadLine + ", asMotherNo=" + asMotherNo
				+ ", asStatus=" + asStatus + "]";
	}

}
