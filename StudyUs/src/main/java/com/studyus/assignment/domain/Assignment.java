package com.studyus.assignment.domain;

public class Assignment {
	private int asNo;
	private int stNo;
	private int asName;
	private String asContents;
	private String asInsertDate;
	private String asDeadLine;
	private String asFileName;
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

	public int getAsName() {
		return asName;
	}

	public void setAsName(int asName) {
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

	public String getAsFileName() {
		return asFileName;
	}

	public void setAsFileName(String asFileName) {
		this.asFileName = asFileName;
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
				+ ", asInsertDate=" + asInsertDate + ", asDeadLine=" + asDeadLine + ", asFileName=" + asFileName
				+ ", asMotherNo=" + asMotherNo + ", asStatus=" + asStatus + "]";
	}
}
