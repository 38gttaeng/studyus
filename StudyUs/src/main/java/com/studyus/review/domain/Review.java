package com.studyus.review.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class Review {
	
	private int rvNo;
	private String rvContents;
	private int rvRating;
	private int rvStatus;
	
	public Review() {}

}
