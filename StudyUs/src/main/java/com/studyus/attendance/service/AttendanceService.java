package com.studyus.attendance.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.studyus.attendance.domain.Attendance;
import com.studyus.member.domain.Member;
import com.studyus.study.domain.Study;

public interface AttendanceService { 
	public ArrayList<HashMap<String, Object>> printAll(int studyNo); 
	
	/**
	 * 출석체크를 합니다.
	 * @author 김동현
	 * @param attendance
	 * @return
	 * @throws Exception
	 */
	public int tryInsertAttendance(int studyNo, int memberNo) throws Exception;
	
	/**
	 * memberNo가 studyNo에 오늘 출석한 상태를 확인하여 반환합니다.<br>
	 * memberNo가 이미 studyNo에 가입되어 있어야 합니다.<br>
	 * @author 김동현
	 * @param studyNo
	 * 출석 대상 스터디 번호<br>
	 * @param memberNo
	 * 출석할 사용자 번호<br>
	 * @return
	 * 0: 현재 studyNo의 출석시간이 아님<br>
	 * 1: 현재 studyNo의 출석시간이지만 memberNo가 아직 출석체크하지 않음<br>
	 * 2: studyNo에 대한 memberNo의 오늘 출석이 이미 완료됨<br>
	 */
	public int getAttendanceStatus(Study study, int mbNo);
	
	/**
	 * 특정 사용자의 특정 스터디에서의 최근 출석률을 출력합니다.<br>
	 * @author 김동현
	 * @param memberNo 어떤 멤버의 출석률을 확인할지 설정합니다.<br>
	 * @param studyNo 어떤 스터디에서의 출석률을 확인할지 설정합니다.<br>
	 * @param recentDays 최근 몇 일까지의 출석률을 확인할지 설정합니다.<br>
	 * @return
	 * 0 ~ 1<br>
	 * 최근 redentDays일 studyNo에서의 memberNo회원 출석률 
	 */
	public float printPersonalAttendanceRate(int memberNo, int studyNo, int recentDays);
	
	/**
	 * 스터디의 특정 기간동안 종합 출석률을 출력합니다.<br>
	 * @author 김동현<br>
	 * @param studyNo 출석률을 확인할 스터디<br>
	 * @param recentDays 최근 몇 일 까지<br>
	 * @return
	 * 0 ~ 1<br>
	 * float 스터디 출석률
	 */
	public float printStudyAttendanceRate(int studyNo, int recentDays);
	
	/**
	 * 스터디 출석 횟수 상위 N명의 번호를 출력합니다.<br>
	 * @author 김동현
	 * @param studyNo 출석을 확인할 스터디<br>
	 * @param memberAmount 출력할 상위멤버 수<br>
	 * @return
	 * ArrayList
	 * 멤버 번호 리스트
	 */
	ArrayList<Member> printStudyTopAttendanceMember(int studyNo, int memberAmount);
	
	/**
	 * TODO
	 * 한 스터디에 가입한 여러 사용자의 최근 출석률을 출력합니다.<br>
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
	public float printAllPersonalAttendanceRate(ArrayList<Integer> memberNoList, int studyNo, int recentDays);

}
