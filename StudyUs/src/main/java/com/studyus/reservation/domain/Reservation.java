package com.studyus.reservation.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Reservation {
	private int rsNo;
	private int crNo;
	private int mbNo;
	private int stNo;
	private String rsDate;
	private int rsStart;
	private int rsEnd;
	private int rsStatus;
	
	public Reservation() {}
}
