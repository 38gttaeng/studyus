package com.studyus.submittedAssignment.domain;

import java.util.ArrayList;

import com.studyus.file.domain.FileVO;
import com.studyus.member.domain.Member;

public class SubmittedAssignment {
	
	private int suNo;
	private int mbNo;
	private int asNo;
	private int suMotherNo;
	private String suInsertDate;
	private String suContents;
	private int suStatus;
	private Member member;
	private ArrayList<FileVO> suFiles;
	private SubmittedAssignment reply;
	
	public SubmittedAssignment() {}

	public int getSuNo() {
		return suNo;
	}

	public void setSuNo(int suNo) {
		this.suNo = suNo;
	}

	public int getMbNo() {
		return mbNo;
	}

	public void setMbNo(int mbNo) {
		this.mbNo = mbNo;
	}

	public int getAsNo() {
		return asNo;
	}

	public void setAsNo(int asNo) {
		this.asNo = asNo;
	}

	public int getSuMotherNo() {
		return suMotherNo;
	}

	public void setSuMotherNo(int suMotherNo) {
		this.suMotherNo = suMotherNo;
	}

	public String getSuInsertDate() {
		return suInsertDate;
	}

	public void setSuInsertDate(String suInsertDate) {
		this.suInsertDate = suInsertDate;
	}

	public String getSuContents() {
		return suContents;
	}

	public void setSuContents(String suContents) {
		this.suContents = suContents;
	}

	public int getSuStatus() {
		return suStatus;
	}

	public void setSuStatus(int suStatus) {
		this.suStatus = suStatus;
	}
	
	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public ArrayList<FileVO> getSuFiles() {
		return suFiles;
	}

	public void setSuFiles(ArrayList<FileVO> suFiles) {
		this.suFiles = suFiles;
	}

	public SubmittedAssignment getReply() {
		return reply;
	}

	public void setReply(SubmittedAssignment reply) {
		this.reply = reply;
	}

	@Override
	public String toString() {
		return "SubmittedAssignment [suNo=" + suNo + ", mbNo=" + mbNo + ", asNo=" + asNo + ", suMotherNo=" + suMotherNo
				+ ", suInsertDate=" + suInsertDate + ", suContents=" + suContents + ", suStatus=" + suStatus
				+ ", member=" + member + ", suFiles=" + suFiles + ", reply=" + reply + "]";
	}

}
