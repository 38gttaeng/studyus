package com.studyus.board.domain;

import java.sql.Date;
import java.util.ArrayList;

import com.studyus.file.domain.FileVO;
import com.studyus.member.domain.Member;

public class Board {
	private int boNo;
	private int stNo;
	private int mbNo;
	private int boMotherNo;
	private Date boInsertDate;
	private String boTitle;
	private String boContents;
	private int boCategory;
	private int boStatus; 
	private Member member;
	private ArrayList<FileVO> boFiles;
	
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
	
	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}
	
	public ArrayList<FileVO> getBoFiles() {
		return boFiles;
	}

	public void setBoFiles(ArrayList<FileVO> boFiles) {
		this.boFiles = boFiles;
	}

	@Override
	public String toString() {
		return "Board [boNo=" + boNo + ", stNo=" + stNo + ", mbNo=" + mbNo + ", boMotherNo=" + boMotherNo
				+ ", boInsertDate=" + boInsertDate + ", boTitle=" + boTitle + ", boContents=" + boContents
				+ ", boCategory=" + boCategory + ", boStatus=" + boStatus + ", member=" + member + ", boFiles="
				+ boFiles + "]";
	}

}
