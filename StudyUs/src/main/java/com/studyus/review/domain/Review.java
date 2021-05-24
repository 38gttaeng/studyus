package com.studyus.review.domain;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
 
public class Review {
	
	private int rvNo;
	private int caNo;
	private String mbId;
	private String rvContents;
	private int rvRating;
	private Date rvDate;
	private int rvStatus;
	
	public Review() {}

}
