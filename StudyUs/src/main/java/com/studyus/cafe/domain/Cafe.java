package com.studyus.cafe.domain;

public class Cafe {

	private int caNo;
	private String caName;
	private String caInfo;
	private String caAddr;
	private String caRealName;
	private String caStatus;
	
	public Cafe() {}

	public Cafe(int caNo, String caName, String caInfo, String caAddr, String caRealName, String caStatus) {
		super();
		this.caNo = caNo;
		this.caName = caName;
		this.caInfo = caInfo;
		this.caAddr = caAddr;
		this.caRealName = caRealName;
		this.caStatus = caStatus;
	}

	public int getCaNo() {
		return caNo;
	}

	public void setCaNo(int caNo) {
		this.caNo = caNo;
	}

	public String getCaName() {
		return caName;
	}

	public void setCaName(String caName) {
		this.caName = caName;
	}

	public String getCaInfo() {
		return caInfo;
	}

	public void setCaInfo(String caInfo) {
		this.caInfo = caInfo;
	}

	public String getCaAddr() {
		return caAddr;
	}

	public void setCaAddr(String caAddr) {
		this.caAddr = caAddr;
	}

	public String getCaRealName() {
		return caRealName;
	}

	public void setCaRealName(String caRealName) {
		this.caRealName = caRealName;
	}

	public String getCaStatus() {
		return caStatus;
	}

	public void setCaStatus(String caStatus) {
		this.caStatus = caStatus;
	}

	@Override
	public String toString() {
		return "Cafe [caNo=" + caNo + ", caName=" + caName + ", caInfo=" + caInfo + ", caAddr=" + caAddr
				+ ", caRealName=" + caRealName + ", caStatus=" + caStatus + "]";
	}
	
	
}
