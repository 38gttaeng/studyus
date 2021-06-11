package com.studyus.attendance.domain;

import java.sql.Date;

import com.studyus.member.domain.Member;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString 
public class Attendance {
	private int attendanceNo;
	private int rNum;
	private int memberNo;
	private int studyNo;
	private int meetingNo;
	private Date atInsertDate; 
	private Member member;
}
