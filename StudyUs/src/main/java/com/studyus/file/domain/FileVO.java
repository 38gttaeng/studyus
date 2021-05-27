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
	private int fiBoardType;
	private int fiMotherNo;
	
	public FileVO() {}

	public FileVO(int fiBoardType, int fiMotherNo) {
		super();
		this.fiBoardType = fiBoardType;
		this.fiMotherNo = fiMotherNo;
	}

	public FileVO(String fiRealName, String fiStoredName, String fiDirectory, int fiBoardType) {
		super();
		this.fiRealName = fiRealName;
		this.fiStoredName = fiStoredName;
		this.fiDirectory = fiDirectory;
		this.fiBoardType = fiBoardType;
	}
 
	public FileVO(int mbNo, String fiRealName, String fiStoredName, String fiDirectory, int fiBoardType,
			int fiMotherNo) {
		super();
		this.mbNo = mbNo;
		this.fiRealName = fiRealName;
		this.fiStoredName = fiStoredName;
		this.fiDirectory = fiDirectory;
		this.fiBoardType = fiBoardType;
		this.fiMotherNo = fiMotherNo;
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
	
	public int getFiBoardType() {
		return fiBoardType;
	}

	public void setFiBoardType(int fiBoardType) {
		this.fiBoardType = fiBoardType;
	}

	public int getFiMotherNo() {
		return fiMotherNo;
	}

	public void setFiMotherNo(int fiMotherNo) {
		this.fiMotherNo = fiMotherNo;
	}

	@Override
	public String toString() {
		return "FileVO [fiNo=" + fiNo + ", mbNo=" + mbNo + ", fiInsertDate=" + fiInsertDate + ", fiRealName="
				+ fiRealName + ", fiStoredName=" + fiStoredName + ", fiDirectory=" + fiDirectory + ", fiStatus="
				+ fiStatus + ", fiBoardType=" + fiBoardType + ", fiMotherNo=" + fiMotherNo + "]";
	}
}
