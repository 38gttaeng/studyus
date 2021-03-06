package com.studyus.reservation.domain;

public class Reservation {
	private int rsNo;
	private int mbNo;
	private int crNo;
	private String rsDate;
	private int rsOccupied;
	
	public Reservation() {}

	public int getRsNo() {
		return rsNo;
	}

	public void setRsNo(int rsNo) {
		this.rsNo = rsNo;
	}

	public int getMbNo() {
		return mbNo;
	}

	public void setMbNo(int mbNo) {
		this.mbNo = mbNo;
	}

	public int getCrNo() {
		return crNo;
	}

	public void setCrNo(int crNo) {
		this.crNo = crNo;
	}

	public String getRsDate() {
		return rsDate;
	}

	public void setRsDate(String rsDate) {
		this.rsDate = rsDate;
	}

	public int getRsOccupied() {
		return rsOccupied;
	}

	public void setRsOccupied(int rsOccupied) {
		this.rsOccupied = rsOccupied;
	}

	@Override
	public String toString() {
		return "Reservation [rsNo=" + rsNo + ", mbNo=" + mbNo + ", crNo=" + crNo + ", rsDate=" + rsDate
				+ ", rsOccupied=" + rsOccupied + "]";
	}
}
