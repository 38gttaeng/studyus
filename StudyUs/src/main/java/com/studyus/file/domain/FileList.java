package com.studyus.file.domain;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FileList {
	
	private int fiNo;
	private int mbNo;
	private Date fiInsertDate;
	private String fiRealName;
	private String fiStoredName;
	private String fiDirectory;
	private int fiStatus;
	private int fiBoardType;
	private int fiMotherNo;
	private String grName;
	private int grColor;
	
	public FileList() {}
}
