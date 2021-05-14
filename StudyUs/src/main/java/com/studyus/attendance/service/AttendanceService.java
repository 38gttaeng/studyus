package com.studyus.attendance.service;

import java.util.ArrayList;

import com.studyus.attendance.domain.Attendance;

public interface AttendanceService {
	public ArrayList<Attendance> printAll();
	public int attCheck(Attendance attendance);
	public int addPoint(Attendance attendance);
	public int attRate(Attendance attendance);
}
