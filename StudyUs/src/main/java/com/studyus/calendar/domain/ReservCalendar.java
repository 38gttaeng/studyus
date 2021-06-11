package com.studyus.calendar.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReservCalendar {
	private int rsNo;
	private String title;
	private String mbName;
	private String start;
	private String end;
	private int price;
	private String className;
	
	public ReservCalendar() {}

	public ReservCalendar(int rsNo, String title, String mbName, String start, String end, int price,
			String className) {
		super();
		this.rsNo = rsNo;
		this.title = title;
		this.mbName = mbName;
		this.start = start;
		this.end = end;
		this.price = price;
		this.className = className;
	}
}
