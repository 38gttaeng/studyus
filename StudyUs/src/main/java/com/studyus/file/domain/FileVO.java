package com.studyus.file.domain;

import java.sql.Date;

public class FileVO {

	private int fiNo;
	private int mbNo;
	private Date fiInsertDate;
	private String fiRealName;
	private String fiStoredName;
	private String fiDirectory;
	private int fiStatus;
	
	public FileVO() {}
	
	// 등록용
	public FileVO(int mbNo, String fiRealName, String fiStoredName, String fiDirectory) {
		this.mbNo = mbNo;
		this.fiRealName = fiRealName;
		this.fiStoredName = fiStoredName;
		this.fiDirectory = fiDirectory;
	}
	
	public int getFiNo() {
		return fiNo;
	}


	public void setFiNo(int fiNo) {
		this.fiNo = fiNo;
	}

	public int getMbNo() {
		return mbNo;
	}

	public void setMbNo(int mbNo) {
		this.mbNo = mbNo;
	}

	public Date getFiInsertDate() {
		return fiInsertDate;
	}

	public void setFiInsertDate(Date fiInsertDate) {
		this.fiInsertDate = fiInsertDate;
	}

	public String getFiRealName() {
		return fiRealName;
	}

	public void setFiRealName(String fiRealName) {
		this.fiRealName = fiRealName;
	}

	public String getFiStoredName() {
		return fiStoredName;
	}

	public void setFiStoredName(String fiStoredName) {
		this.fiStoredName = fiStoredName;
	}

	public String getFiDirectory() {
		return fiDirectory;
	}

	public void setFiDirectory(String fiDirectory) {
		this.fiDirectory = fiDirectory;
	}

	public int getFiStatus() {
		return fiStatus;
	}

	public void setFiStatus(int fiStatus) {
		this.fiStatus = fiStatus;
	}

	@Override
	public String toString() {
		return "File [fiNo=" + fiNo + ", mbNo=" + mbNo + ", fiInsertDate=" + fiInsertDate + ", fiRealName=" + fiRealName
				+ ", fiStoredName=" + fiStoredName + ", fiDirectory=" + fiDirectory + ", fiStatus=" + fiStatus + "]";
	}
	
}
