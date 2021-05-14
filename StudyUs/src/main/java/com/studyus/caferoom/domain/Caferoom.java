package com.studyus.caferoom.domain;

public class Caferoom {

	private int crNo;
	private int caNo;
	private int crMax;
	private int crCol;
	private int crRow;

	public Caferoom() {}

	public int getCrNo() {
		return crNo;
	}

	public void setCrNo(int crNo) {
		this.crNo = crNo;
	}

	public int getCaNo() {
		return caNo;
	}

	public void setCaNo(int caNo) {
		this.caNo = caNo;
	}

	public int getCrMax() {
		return crMax;
	}

	public void setCrMax(int crMax) {
		this.crMax = crMax;
	}

	public int getCrCol() {
		return crCol;
	}

	public void setCrCol(int crCol) {
		this.crCol = crCol;
	}

	public int getCrRow() {
		return crRow;
	}

	public void setCrRow(int crRow) {
		this.crRow = crRow;
	}

	@Override
	public String toString() {
		return "Caferoom [crNo=" + crNo + ", caNo=" + caNo + ", crMax=" + crMax + ", crCol=" + crCol + ", crRow="
				+ crRow + "]";
	}
}
