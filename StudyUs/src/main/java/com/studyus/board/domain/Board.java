package com.studyus.board.domain;

import java.sql.Date;

public class Board {
	private int boNo;
	private int stNo;
	private int mbNo;
	private int boMotherNo;
	private Date boInsertDate;
	private String boTitle;
	private String boContents;
	private String boFileName;
	private int boCategory;
	private int boStatus;
	
	public Board() {}

	public int getBoNo() {
		return boNo;
	}

	public void setBoNo(int boNo) {
		this.boNo = boNo;
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

	public int getBoMotherNo() {
		return boMotherNo;
	}

	public void setBoMotherNo(int boMotherNo) {
		this.boMotherNo = boMotherNo;
	}

	public Date getBoInsertDate() {
		return boInsertDate;
	}

	public void setBoInsertDate(Date boInsertDate) {
		this.boInsertDate = boInsertDate;
	}

	public String getBoTitle() {
		return boTitle;
	}

	public void setBoTitle(String boTitle) {
		this.boTitle = boTitle;
	}

	public String getBoContents() {
		return boContents;
	}

	public void setBoContents(String boContents) {
		this.boContents = boContents;
	}

	public String getBoFileName() {
		return boFileName;
	}

	public void setBoFileName(String boFileName) {
		this.boFileName = boFileName;
	}

	public int getBoCategory() {
		return boCategory;
	}

	public void setBoCategory(int boCategory) {
		this.boCategory = boCategory;
	}

	public int getBoStatus() {
		return boStatus;
	}

	public void setBoStatus(int boStatus) {
		this.boStatus = boStatus;
	}

	public Board(int boNo, int stNo, int mbNo, int boMotherNo, Date boInsertDate, String boTitle, String boContents,
			String boFileName, int boCategory, int boStatus) {
		super();
		this.boNo = boNo;
		this.stNo = stNo;
		this.mbNo = mbNo;
		this.boMotherNo = boMotherNo;
		this.boInsertDate = boInsertDate;
		this.boTitle = boTitle;
		this.boContents = boContents;
		this.boFileName = boFileName;
		this.boCategory = boCategory;
		this.boStatus = boStatus;
	}
}
