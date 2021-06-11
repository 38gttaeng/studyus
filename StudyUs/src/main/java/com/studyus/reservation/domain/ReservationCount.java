package com.studyus.reservation.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReservationCount {
	private String dayName;
	private int count;
	
	public ReservationCount() {}
}
