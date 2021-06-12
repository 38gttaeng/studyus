package com.studyus.meeting.service;

import java.util.ArrayList;

import com.studyus.meeting.domain.Meeting;

public interface MeetingService {
	
	public int insertOne(Meeting meeting) throws Exception;
	
	/**
	 * 스터디 번호를 입력받아 해당 스터디의 오늘 모임을 반환합니다.<br>
	 * @author 김동현 
	 * @param studyNo
	 * @return Meeting
	 * @throws Exception
	 */
	public Meeting printCurrentOneByStudyNo(int studyNo) throws Exception;

	public ArrayList<Meeting> printAttMember(Meeting meeting);

	/**
	 * 특정 스터디가 최근 얼마동안 몇 번의 모임을 가졌는지를 반환합니다.<br>
	 * @author 김동현
	 * @param studyNo <br>
	 * 모임 횟수를 확인할 스터디 번호
	 * @param recentDays <br>
	 * 최근 몇 일까지 확인할 것인지 설정
	 * @return
	 * 최근 recentDays일 내 studyNo의 모임 횟수
	 */
	public int printMeetingAmountByStudyNo(int studyNo, int recentDays); 
}
