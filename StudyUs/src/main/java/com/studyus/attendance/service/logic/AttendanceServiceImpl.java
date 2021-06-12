package com.studyus.attendance.service.logic;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; 

import com.studyus.attendance.domain.Attendance;
import com.studyus.attendance.domain.AttendanceAmountWithMemberVO;
import com.studyus.attendance.service.AttendanceService;
import com.studyus.attendance.store.AttendanceStore;
import com.studyus.meeting.domain.Meeting;
import com.studyus.meeting.service.MeetingService;
import com.studyus.meeting.utils.MeetingUtils;
import com.studyus.member.domain.Member;
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
	public ArrayList<Attendance> printAll(int studyNo) { 
		return attStore.printAll(studyNo); 
	}

	@Override
	@Transactional
	public int tryInsertAttendance(int studyNo, int memberNo) throws Exception {
		// 오늘에 대한 MEETING이 있는지 확인
		Meeting currentMeeting = meetingService.printCurrentOneByStudyNo(studyNo);
		
		Study study = studyService.printOneByNo(studyNo);
		
		// 없으면 새로 insert할 오늘의 MEETING 생성
		if (currentMeeting == null) {
			currentMeeting = new Meeting();
			currentMeeting.setStudyNo(studyNo);
			
			// 모임 시작,끝 시간이 null일경우 00:00 ~ 23:59로 설정
			String start = (study.getStart() == null) ? "00:00" : study.getStart();
			String end = (study.getEnd() == null) ? "23:59" : study.getEnd(); 
			currentMeeting.setStart(start);
			currentMeeting.setEnd(end);
			currentMeeting.setRequiredAttendance(memberService.printAllByStudyNo(studyNo).size());
			currentMeeting.setMeetingNo(meetingService.insertOne(currentMeeting));
			
			/*
			 * MEETING이 있을 경우, 해당 MEETING에 대해 이미 출석했는지 확인
			 * 이미 출석체크 했을 경우 2 return
			 */
		} else {
			boolean attendedAlready = attStore.checkTodayAttendedAlready(currentMeeting.getMeetingNo(), memberNo);
			if (attendedAlready) {
				return 2;
			}
		}
		
		// ATTENDANCE 추가
		Attendance attendance = new Attendance();
		attendance.setMeetingNo(currentMeeting.getMeetingNo());
		attendance.setMemberNo(memberNo);
		attendance.setStudyNo(studyNo);
		return attStore.insertAttendance(attendance);
	}
	
//	@Override
//	public boolean checkAttendedAlready(Attendance attendance) {
//		return attStore.checkTodayAttendedAlready(attendance.getStudyNo(), attendance.getMemberNo());
//	}
	
	@Override
	public float printPersonalAttendanceRate(int memberNo, int studyNo, int recentDays) {
		return attStore.selectPersonalAttendanceRate(memberNo, studyNo, recentDays);
	}
	
	@Override
	public float printStudyAttendanceRate(int studyNo, int recentDays) {
		return attStore.selectStudyAttendanceRate(studyNo, recentDays);
	}
	
	public int getAttendanceStatus (Study study, int memberNo) {
		// 오늘이 출석 요일인지 확인 후 맞다면 진행, 아니면 0 리턴
		if (MeetingUtils.isMeetingTime(study) == false) {
			return 0;
		}
		
		// 지금이 출석 시간인지 확인 후 맞다면 진행, 아니면 0 리턴
		if (attStore.checkAttendanceTime(study.getStudyNo()) == false) {
			return 0;
		}
		
		// 출석체크 하였는지 확인 후 했다면 2 리턴, 아니면 1 리턴
		if (attStore.checkAttendanceToday(study.getStudyNo(), memberNo)) {
			return 2;
		} else return 1;
	}
	
	@Override
	public ArrayList<AttendanceAmountWithMemberVO> printStudyTopAttendanceMember(int studyNo, int memberAmount, int recentDays) {
		return attStore.selectStudyTopAttendanceMember(studyNo, memberAmount, recentDays);
	}
	
	@Override
	public float printAllPersonalAttendanceRate(ArrayList<Integer> memberNoList, int studyNo, int recentDays) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int checkAttendance(Attendance attendance) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean checkAttendedAlready(Attendance attendance) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int countAttMember(int studyNo) {
		return attStore.countAttMember(studyNo); 
	}
}
