package com.studyus.meeting.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Meeting {
	private int meetingNo;
	private int studyNo;
	private String start;
	private String end;
	private int requiredAttendance;
}
