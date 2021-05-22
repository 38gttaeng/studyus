package com.studyus.purchase.domain;

import java.sql.Date;

public class Purchase {
	private int purchaseNo;
	private int studyNo;
	private Date purchaseDate;
	private int purchasePrice;
	
	public Purchase() {}
	
	public int getPurchaseNo() {
		return purchaseNo;
	}
	public void setPurchaseNo(int purchaseNo) {
		this.purchaseNo = purchaseNo;
	}
	public int getStudyNo() {
		return studyNo;
	}
	public void setStudyNo(int studyNo) {
		this.studyNo = studyNo;
	}
	public Date getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public int getPurchasePrice() {
		return purchasePrice;
	}
	public void setPurchasePrice(int purchasePrice) {
		this.purchasePrice = purchasePrice;
	}
	@Override
	public String toString() {
		return "Purchase [purchaseNo=" + purchaseNo + ", studyNo=" + studyNo + ", purchaseDate=" + purchaseDate
				+ ", purchasePrice=" + purchasePrice + "]";
	}
	
}
