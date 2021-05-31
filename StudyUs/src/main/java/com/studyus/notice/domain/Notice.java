package com.studyus.notice.domain;

import java.sql.Date;
import java.util.ArrayList;

import com.studyus.file.domain.FileVO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
 
public class Notice {
	private int noNo;
	private int rowNum;
	private int stNo;
	private int mbNo;
	private int noMotherNo;
	private String noWriter;
	private String replyWriter;
	private Date noInsertDate;
	private String noTitle;
	private String noContents;
	private String noFileName;
	private String noReFileName;
	private int noStatus;
	private int noCount;
	private int replyCnt; // 게시글 댓글 수 
	private int mainNotice;
	private String mainSetDate;
	private ArrayList<FileVO> noFiles;
	
	public Notice() {}

	public int getNoNo() {
		return noNo;
	}

	public void setNoNo(int noNo) {
		this.noNo = noNo;
	}

	public int getRowNum() {
		return rowNum;
	}

	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
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

	public int getNoMotherNo() {
		return noMotherNo;
	}

	public void setNoMotherNo(int noMotherNo) {
		this.noMotherNo = noMotherNo;
	}

	public String getNoWriter() {
		return noWriter;
	}

	public void setNoWriter(String noWriter) {
		this.noWriter = noWriter;
	}

	public String getReplyWriter() {
		return replyWriter;
	}

	public void setReplyWriter(String replyWriter) {
		this.replyWriter = replyWriter;
	}

	public Date getNoInsertDate() {
		return noInsertDate;
	}

	public void setNoInsertDate(Date noInsertDate) {
		this.noInsertDate = noInsertDate;
	}

	public String getNoTitle() {
		return noTitle;
	}

	public void setNoTitle(String noTitle) {
		this.noTitle = noTitle;
	}

	public String getNoContents() {
		return noContents;
	}

	public void setNoContents(String noContents) {
		this.noContents = noContents;
	}

	public String getNoFileName() {
		return noFileName;
	}

	public void setNoFileName(String noFileName) {
		this.noFileName = noFileName;
	}

	public String getNoReFileName() {
		return noReFileName;
	}

	public void setNoReFileName(String noReFileName) {
		this.noReFileName = noReFileName;
	}

	public int getNoStatus() {
		return noStatus;
	}

	public void setNoStatus(int noStatus) {
		this.noStatus = noStatus;
	}

	public int getNoCount() {
		return noCount;
	}

	public void setNoCount(int noCount) {
		this.noCount = noCount;
	}

	public int getReplyCnt() {
		return replyCnt;
	}

	public void setReplyCnt(int replyCnt) {
		this.replyCnt = replyCnt;
	}

	public int getMainNotice() {
		return mainNotice;
	}

	public void setMainNotice(int mainNotice) {
		this.mainNotice = mainNotice;
	}

	public String getMainSetDate() {
		return mainSetDate;
	}

	public void setMainSetDate(String mainSetDate) {
		this.mainSetDate = mainSetDate;
	}

	public ArrayList<FileVO> getNoFiles() {
		return noFiles;
	}

	public void setNoFiles(ArrayList<FileVO> noFiles) {
		this.noFiles = noFiles;
	}

	@Override
	public String toString() {
		return "Notice [noNo=" + noNo + ", rowNum=" + rowNum + ", stNo=" + stNo + ", mbNo=" + mbNo + ", noMotherNo="
				+ noMotherNo + ", noWriter=" + noWriter + ", replyWriter=" + replyWriter + ", noInsertDate="
				+ noInsertDate + ", noTitle=" + noTitle + ", noContents=" + noContents + ", noFileName=" + noFileName
				+ ", noReFileName=" + noReFileName + ", noStatus=" + noStatus + ", noCount=" + noCount + ", replyCnt="
				+ replyCnt + ", mainNotice=" + mainNotice + ", mainSetDate=" + mainSetDate + ", noFiles=" + noFiles
				+ "]";
	}
	
}
