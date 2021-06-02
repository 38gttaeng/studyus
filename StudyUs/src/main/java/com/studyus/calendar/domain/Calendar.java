package com.studyus.calendar.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Calendar {
	private String category;
	private String title;
	private String start;
	private String end;
	private String urlStr;
	private String className;
	
	public Calendar() {}

	public Calendar(String category, String title, String start, String end, String urlStr, String className) {
		this.category = category;
		this.title = title;
		this.start = start;
		this.end = end;
		this.urlStr = urlStr;
		this.className = className;
	}
}
