package com.studyus.attendance.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString 
public class Attendance {
	private int attendanceNo;
	private int memberNo;
	private int studyNo;
	private int meetingNo;
}
