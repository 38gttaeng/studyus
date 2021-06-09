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

	/**
	 * studyNo에 대해 memberNo가 오늘 출석체크한 기록을 확인합니다.
	 * 시간은 확인하지 않습니다.
	 * @param studyNo
	 * @param memberNo
	 * @return
	 * 출석체크 하였으면 true, 아니면 false.
	 */
	boolean checkAttendanceToday(int studyNo, int memberNo);
	
	/**
	 * 입력한 스터디가 현재 출석체크 시간인지 확인합니다.
	 * 요일은 확인하지 않습니다.
	 * @param studyNo
	 * @return
	 * 출석체크 시간이 맞으면 true, 아니면 false.
	 */
	boolean checkAttendanceTime(int studyNo);
	
}
