package com.studyus.attendance.store;

import java.util.ArrayList;
import java.util.HashMap;

import com.studyus.attendance.domain.Attendance; 

public interface AttendanceStore {
	public ArrayList<HashMap<String, Object>> printAll(int studyNo); 
	public int insertAttendance(Attendance attendance);
	public int addPoint(Attendance attendance);
	public int attRate(Attendance attendance);
	/**
	 * 오늘 이미 출석하였는지 확인
	 * @param attendance
	 * @return 이미 출석하였으면 true, 출석하지 않았으면 false
	 */
	public boolean checkTodayAttendedAlready(Attendance attendance);
}
