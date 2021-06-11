package com.studyus.attendance.store;

import java.util.ArrayList;
import java.util.HashMap;

import com.studyus.attendance.domain.Attendance;
import com.studyus.member.domain.Member; 

public interface AttendanceStore {
	public ArrayList<Attendance> printAll(int studyNo);
	public int insertAttendance(Attendance attendance);
	public int addPoint(Attendance attendance);
	public int attRate(Attendance attendance);
	/**
	 * 오늘 이미 출석하였는지 확인합니다.<br>
	 * @author 김동현
	 * @param meetingNo, memberNo
	 * @return 이미 출석하였으면 true, 출석하지 않았으면 false
	 */
	public boolean checkTodayAttendedAlready(int meetingNo, int memberNo);
	/**
	 * 사용자의 최근 출석률을 출력합니다.<br>
	 * recentDays: 최근 몇 일까지의 출석률을 확인할지 설정합니다.<br>
	 * studyNo: 어떤 스터디에서의 출석률을 확인할지 설정합니다.<br>
	 * memberNo: 어떤 멤버의 출석률을 확인할지 설정합니다.<br>
	 * @author 김동현
	 * @param memberNo
	 * @param studyNo
	 * @param recentDays
	 * @return
	 * 최근 redentDays일 studyNo에서의 memberNo회원 출석률 
	 */
	public float selectPersonalAttendanceRate(int memberNo, int studyNo, int recentDays);
	
	/**
	 * 스터디의 특정 기간동안 종합 출석률을 출력합니다.<br>
	 * @author 김동현<br>
	 * @param studyNo 출석률을 확인할 스터디<br>
	 * @param recentDays 최근 몇 일 까지<br>
	 * @return
	 * float 스터디 출석률
	 */
	public float selectStudyAttendanceRate(int studyNo, int recentDays);
	
	/**
	 * 스터디 출석 횟수 상위 N명의 번호를 출력합니다.<br>
	 * @author 김동현
	 * @param studyNo 출석을 확인할 스터디<br>
	 * @param memberAmount 출력할 상위멤버 수<br>
	 * @return
	 * ArrayList
	 * 멤버 번호 리스트
	 */
	public ArrayList<Member> selectStudyTopAttendanceMember(int studyNo, int memberAmount);

	/**
	 * studyNo에 대해 memberNo가 오늘 출석체크한 기록을 확인합니다.<br>
	 * 시간은 확인하지 않습니다.<br>
	 * @author 김동현
	 * @param studyNo
	 * @param memberNo
	 * @return
	 * 출석체크 하였으면 true, 아니면 false.
	 */
	boolean checkAttendanceToday(int studyNo, int memberNo);
	
	/**
	 * 입력한 스터디가 현재 출석체크 시간인지 확인합니다.<br>
	 * 요일은 확인하지 않습니다.<br>
	 * @author 김동현
	 * @param studyNo
	 * @return
	 * 출석체크 시간이 맞으면 true, 아니면 false.
	 */
	boolean checkAttendanceTime(int studyNo);
	
	
}
