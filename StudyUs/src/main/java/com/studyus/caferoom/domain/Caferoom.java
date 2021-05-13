package com.studyus.caferoom.domain;

public class Caferoom {

	private int caferoomNo;
	private int cafeNo;
	private int caferoomMax;
	private int caferoomCol;
	private int caferoomRow;

	public Caferoom() {}

	public Caferoom(int caferoomNo, int cafeNo, int caferoomMax, int caferoomCol, int caferoomRow) {
		super();
		this.caferoomNo = caferoomNo;
		this.cafeNo = cafeNo;
		this.caferoomMax = caferoomMax;
		this.caferoomCol = caferoomCol;
		this.caferoomRow = caferoomRow;
	}

	public int getCaferoomNo() {
		return caferoomNo;
	}

	public void setCaferoomNo(int caferoomNo) {
		this.caferoomNo = caferoomNo;
	}

	public int getCafeNo() {
		return cafeNo;
	}

	public void setCafeNo(int cafeNo) {
		this.cafeNo = cafeNo;
	}

	public int getCaferoomMax() {
		return caferoomMax;
	}

	public void setCaferoomMax(int caferoomMax) {
		this.caferoomMax = caferoomMax;
	}

	public int getCaferoomCol() {
		return caferoomCol;
	}

	public void setCaferoomCol(int caferoomCol) {
		this.caferoomCol = caferoomCol;
	}

	public int getCaferoomRow() {
		return caferoomRow;
	}

	public void setCaferoomRow(int caferoomRow) {
		this.caferoomRow = caferoomRow;
	}

	@Override
	public String toString() {
		return "Caferoom [caferoomNo=" + caferoomNo + ", cafeNo=" + cafeNo + ", caferoomMax=" + caferoomMax
				+ ", caferoomCol=" + caferoomCol + ", caferoomRow=" + caferoomRow + "]";
	}
	
	
}
