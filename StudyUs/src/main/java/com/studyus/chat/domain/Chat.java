package com.studyus.chat.domain;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Chat {
	
	private int chatNo;
	private int studyNo;
	private int writerNo;
	private Date insertDate;
	private String contents;
	
}
