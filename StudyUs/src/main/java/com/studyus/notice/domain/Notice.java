package com.studyus.notice.domain;

import java.sql.Date;

public class Notice {
	private int noticeNo;
	private int studyNo;
	private int memberNo;
	private int nMotherNo;
	private Date nInsertDate;
	private String noticeTitle;
	private String noticeContents;
	private String noticeFileName;
	private int noticeStatus;
	private int noticeCount;
	
	public Notice() {}
	
	public int getNoticeNo() {
		return noticeNo;
	}
	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}
	public int getStudyNo() {
		return studyNo;
	}
	public void setStudyNo(int studyNo) {
		this.studyNo = studyNo;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public int getnMotherNo() {
		return nMotherNo;
	}
	public void setnMotherNo(int nMotherNo) {
		this.nMotherNo = nMotherNo;
	}
	public Date getnInsertDate() {
		return nInsertDate;
	}
	public void setnInsertDate(Date nInsertDate) {
		this.nInsertDate = nInsertDate;
	}
	public String getNoticeTitle() {
		return noticeTitle;
	}
	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}
	public String getNoticeContents() {
		return noticeContents;
	}
	public void setNoticeContents(String noticeContents) {
		this.noticeContents = noticeContents;
	}
	public String getNoticeFileName() {
		return noticeFileName;
	}
	public void setNoticeFileName(String noticeFileName) {
		this.noticeFileName = noticeFileName;
	}
	public int getNoticeStatus() {
		return noticeStatus;
	}
	public void setNoticeStatus(int noticeStatus) {
		this.noticeStatus = noticeStatus;
	}
	public int getNoticeCount() {
		return noticeCount;
	}
	public void setNoticeCount(int noticeCount) {
		this.noticeCount = noticeCount;
	}
	@Override
	public String toString() {
		return "Notice [noticeNo=" + noticeNo + ", studyNo=" + studyNo + ", memberNo=" + memberNo + ", nMotherNo="
				+ nMotherNo + ", nInsertDate=" + nInsertDate + ", noticeTitle=" + noticeTitle + ", noticeContents="
				+ noticeContents + ", noticeFileName=" + noticeFileName + ", noticeStatus=" + noticeStatus
				+ ", noticeCount=" + noticeCount + "]";
	}
	
	
}
