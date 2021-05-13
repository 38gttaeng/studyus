package com.studyus.attendance.store;

import java.util.ArrayList;

import com.studyus.attendance.domain.Attendance;

public interface AttendanceStore {
	public ArrayList<Attendance> printAll();
	public int attCheck(Attendance attendance);
	public int addPoint(Attendance attendance);
	public int attRate(Attendance attendance);
}
