package com.studyus.purchase.domain;

import java.sql.Date;

public class Purchase {
	private int puNo;
	private int stNo;
	private int mbNo;
	private Date puInsertDate;
	private int puStatus;
	
	public Purchase() {}

	public int getPuNo() {
		return puNo;
	}

	public void setPuNo(int puNo) {
		this.puNo = puNo;
	}

	public int getStNo() {
		return stNo;
	}

	public void setStNo(int stNo) {
		this.stNo = stNo;
	}

	public int getMbNo() {
		return mbNo;
	}

	public void setMbNo(int mbNo) {
		this.mbNo = mbNo;
	}

	public Date getpuInsertDate() {
		return puInsertDate;
	}

	public void setpuInsertDate(Date puInsertDate) {
		this.puInsertDate = puInsertDate;
	}

	public int getpuStatus() {
		return puStatus;
	}

	public void setpuStatus(int puStatus) {
		this.puStatus = puStatus;
	}

	@Override
	public String toString() {
		return "Purchase [puNo=" + puNo + ", stNo=" + stNo + ", mbNo=" + mbNo + ", puInsertDate=" + puInsertDate
				+ ", puStatus=" + puStatus + "]";
	}
	
}
