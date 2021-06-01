package com.studyus.enrollment.domain;

import java.sql.Date;

import com.studyus.member.domain.Member;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EnrollmentWithMember {
	
	private int rNum;
	
	// 가입신청 클래스 
	private int enrollmentNo;
	private int memberNo;
	private int studyNo;
	private Date insertDate;
	private String message;
	private int status;	
	
	// 멤버 클래스 
//	private int mbNo;
	private String mbId;
	private String mbPassword;
	private String mbName;
	private String mbEmail;
	private String mbNickname;
	private String mbPhone;
	private int mbReputation;
	private int mbPhoto;
	private int mbStatus;
}
