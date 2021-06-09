package com.studyus.chat.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class ChatMessage {
	
	private String studyUrl;
	private String nickname;
	private String message;
	private Date insertDate;
	private MessageType type;
	
	public ChatMessage () {
		insertDate = new java.util.Date();
	}
	
	public String getStudyUrl() {
		return studyUrl;
	}
	public void setStudyUrl(String studyUrl) {
		this.studyUrl = studyUrl;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getInsertDate() {
		return insertDate;
	}
	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}
	public MessageType getType() {
		return type;
	}
	public void setType(MessageType type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "ChatMessage [studyUrl=" + studyUrl + ", nickname=" + nickname + ", message=" + message + ", insertDate="
				+ insertDate + ", type=" + type + "]";
	}
}
