package com.studyus.chat.domain;

import java.sql.Date;

public class Chat {
	private int chatNo;
	private int studyNo;
	private int writerNo;
	private Date insertDate;
	private String contents;
	
	public Chat () {}

	public int getChatNo() {
		return chatNo;
	}
	public void setChatNo(int chatNo) {
		this.chatNo = chatNo;
	}
	public int getStudyNo() {
		return studyNo;
	}
	public void setStudyNo(int studyNo) {
		this.studyNo = studyNo;
	}
	public int getWriterNo() {
		return writerNo;
	}
	public void setWriterNo(int writerNo) {
		this.writerNo = writerNo;
	}
	public Date getInsertDate() {
		return insertDate;
	}
	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}

	@Override
	public String toString() {
		return "Chat [chatNo=" + chatNo + ", studyNo=" + studyNo + ", writerNo=" + writerNo + ", insertDate="
				+ insertDate + ", contents=" + contents + "]";
	}
}
