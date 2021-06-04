package com.studyus.purchase.domain;

import java.sql.Date;

public class Purchase {
	private int puNo;
	private int mbNo;
	private int stNo;
	private String stName;
	private Date puInsertDate;
	private int puStatus;
	
	public Purchase() {}
	
	public Purchase(int puNo, int mbNo, int stNo, String stName, Date puInsertDate, int puStatus) {
		super();
		this.puNo = puNo;
		this.mbNo = mbNo;
		this.stNo = stNo;
		this.stName = stName;
		this.puInsertDate = puInsertDate;
		this.puStatus = puStatus;
	}

	public int getPuNo() {
		return puNo;
	}

	public void setPuNo(int puNo) {
		this.puNo = puNo;
	}

	public int getMbNo() {
		return mbNo;
	}

	public void setMbNo(int mbNo) {
		this.mbNo = mbNo;
	}

	public int getStNo() {
		return stNo;
	}

	public void setStNo(int stNo) {
		this.stNo = stNo;
	}

	public String getStName() {
		return stName;
	}

	public void setStName(String stName) {
		this.stName = stName;
	}

	public Date getPuInsertDate() {
		return puInsertDate;
	}

	public void setPuInsertDate(Date puInsertDate) {
		this.puInsertDate = puInsertDate;
	}

	public int getPuStatus() {
		return puStatus;
	}

	public void setPuStatus(int puStatus) {
		this.puStatus = puStatus;
	}

	@Override
	public String toString() {
		return "Purchase [puNo=" + puNo + ", mbNo=" + mbNo + ", stNo=" + stNo + ", stName=" + stName + ", puInsertDate="
				+ puInsertDate + ", puStatus=" + puStatus + "]";
	}
	
}
