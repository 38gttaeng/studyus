package com.studyus.attendance.service;

import java.util.ArrayList;

import com.studyus.attendance.domain.Attendance;

public interface AttendanceService { 
	public ArrayList<Attendance> printAll() throws Exception;
	public int checkAttendance(Attendance attendance) throws Exception;
	public boolean checkAttendedAlready(Attendance attendance);
	public int addPoint(Attendance attendance) throws Exception;
	public int attRate(Attendance attendance) throws Exception;
}
