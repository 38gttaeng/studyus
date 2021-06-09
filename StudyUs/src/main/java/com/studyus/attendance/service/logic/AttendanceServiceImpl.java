package com.studyus.attendance.service.logic;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; 

import com.studyus.attendance.domain.Attendance;
import com.studyus.attendance.service.AttendanceService;
import com.studyus.attendance.store.AttendanceStore;
import com.studyus.meeting.domain.Meeting;
import com.studyus.meeting.service.MeetingService;
import com.studyus.member.service.MemberService;
import com.studyus.study.domain.Study;
import com.studyus.study.service.StudyService;

@Service
public class AttendanceServiceImpl implements AttendanceService {
	
	@Autowired
	public AttendanceStore  attStore;
	
	@Autowired
	public MeetingService meetingService;
	
	@Autowired
	public MemberService memberService;
	
	@Autowired
	public StudyService studyService;

	@Override
	public ArrayList<HashMap<String, Object>> printAll(int studyNo) {
		return attStore.printAll(studyNo); 
	}

	/**
	 * 지금이 출석 날짜 및 출석 시간인지 확인 후, 맞으면 출석체크를 진행합니다.
	 * 
	 * @return
	 * 2: 이미 오늘의 출석체크가 되어있을 경우
	 */
	@Override
	@Transactional
	public int checkAttendance(Attendance attendance) throws Exception {
		// 오늘에 대한 MEETING이 있는지 확인
		Meeting currentMeeting = meetingService.printCurrentOneByStudyNo(attendance.getStudyNo());
		
		Study study = studyService.printOneByNo(attendance.getStudyNo());
		
		// 없으면 새로 insert할 오늘의 MEETING 생성
		if (currentMeeting == null) {
			currentMeeting = new Meeting();
			currentMeeting.setStudyNo(attendance.getStudyNo());
			
			// 모임 시작,끝 시간이 null일경우 00:00 ~ 23:59로 설정
			String start = (study.getStart() == null) ? "00:00" : study.getStart();
			String end = (study.getEnd() == null) ? "23:59" : study.getEnd(); 
			currentMeeting.setStart(start);
			currentMeeting.setEnd(end);
			currentMeeting.setRequiredAttendance(memberService.printAllByStudyNo(attendance.getStudyNo()).size());
			currentMeeting.setMeetingNo(meetingService.insertOne(currentMeeting));
			
			/*
			 * MEETING이 있을 경우, 해당 MEETING에 대해 이미 출석했는지 확인
			 * 이미 출석체크 했을 경우 2 return
			 */
		} else {
			attendance.setMeetingNo(currentMeeting.getMeetingNo());
			boolean attendedAlready = attStore.checkTodayAttendedAlready(attendance);
			if (attendedAlready) {
				return 2;
			}
		}
		
		// ATTENDANCE 추가
		attendance.setMeetingNo(currentMeeting.getMeetingNo());
		return attStore.insertAttendance(attendance);
	}
	
	/**
	 * 오늘 이미 출석체크를 완료하였는지 확인합니다.
	 * @return 이미 출석되었으면 true, 아니면 false
	 */
	@Override
	public boolean checkAttendedAlready(Attendance attendance) {
		return attStore.checkTodayAttendedAlready(attendance);
	}

	@Override
	public int addPoint(Attendance attendance) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int attRate(Attendance attendance) {
		// TODO Auto-generated method stub
		return 0;
	}

}
