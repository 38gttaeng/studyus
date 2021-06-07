package com.studyus.notice.domain;

import java.sql.Date;
import java.util.ArrayList;

import com.studyus.file.domain.FileVO;
import com.studyus.member.domain.Member;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
 
@Getter
@Setter
@ToString
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
	private Member member;
}
