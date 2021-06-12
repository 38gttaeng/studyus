package com.studyus.meeting.store;

import java.util.ArrayList;

import com.studyus.meeting.domain.Meeting;

public interface MeetingStore {
	/**
	 * 데이터베이스에 Meeting을 insert한 후 meetingNo를 반환합니다.
	 * @author 김동현 
	 * @param meeting
	 * @return meetingNo
	 * @throws Exception
	 */
	public int insertOne(Meeting meeting) throws Exception;
	public Meeting selectCurrentOneByStudyNo(int studyNo) throws Exception;
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
	public int selectMeetingAmountByStudyNo(int studyNo, int recentDays); 
}
