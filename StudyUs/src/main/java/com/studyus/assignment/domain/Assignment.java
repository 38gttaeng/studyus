package com.studyus.assignment.domain;

import java.util.ArrayList;

import com.studyus.file.domain.FileVO;

public class Assignment {
	private int asNo;
	private int grNo;
	private String asName;
	private String asContents;
	private String asInsertDate;
	private String asDeadLine;
	private int asStatus;
	private ArrayList<FileVO> asFiles;
	
	public Assignment() {}
	
	public int getAsNo() {
		return asNo;
	}

	public void setAsNo(int asNo) {
		this.asNo = asNo;
	}

	public int getGrNo() {
		return grNo;
	}

	public void setGrNo(int grNo) {
		this.grNo = grNo;
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

	public int getAsStatus() {
		return asStatus;
	}

	public void setAsStatus(int asStatus) {
		this.asStatus = asStatus;
	}
	
	public ArrayList<FileVO> getAsFiles() {
		return asFiles;
	}

	public void setAsFiles(ArrayList<FileVO> asFiles) {
		this.asFiles = asFiles;
	}

	@Override
	public String toString() {
		return "Assignment [asNo=" + asNo + ", grNo=" + grNo + ", asName=" + asName + ", asContents=" + asContents
				+ ", asInsertDate=" + asInsertDate + ", asDeadLine=" + asDeadLine + ", asStatus=" + asStatus
				+ ", asFiles=" + asFiles + "]";
	}

}
