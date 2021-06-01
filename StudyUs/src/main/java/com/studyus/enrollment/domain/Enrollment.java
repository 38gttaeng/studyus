package com.studyus.enrollment.domain;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Enrollment {
	private int enrollmentNo;
	private int memberNo;
	private int studyNo;
	private Date insertDate;
	private String message;
	private int status;	
}
