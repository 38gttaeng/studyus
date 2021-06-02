package com.studyus.review.domain;

import java.sql.Date;

import com.studyus.member.domain.Member;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
 
public class Review {
	
	private int rvNo;
	private int caNo;
	private int mbNo;
	private String rvContents;
	private String rvRating;
	private Date rvDate;
	private int rvStatus;
	private Member member;
	
	public Review() {}

}
