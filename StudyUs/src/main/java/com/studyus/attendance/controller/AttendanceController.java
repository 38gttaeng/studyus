package com.studyus.attendance.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.studyus.attendance.domain.Attendance;
import com.studyus.attendance.domain.AttendanceAmountWithMemberVO;
import com.studyus.attendance.service.AttendanceService;
import com.studyus.meeting.service.MeetingService;
import com.studyus.member.domain.Member;
import com.studyus.study.domain.Study;

@Controller
public class AttendanceController {
	
	@Autowired
	private AttendanceService aService;
	
	@Autowired
	private MeetingService mService;

	// 출석 리스트
	@RequestMapping(value="/attendance/list")
	public String printAtt() {
		return "study/attendanceList";
	}
	
	// 출석 리스트 보여주기 
	@RequestMapping(value="/attendance/attList", method=RequestMethod.GET)
	public void attList(HttpSession session, HttpServletResponse response) throws Exception {
		Study study = (Study)session.getAttribute("study");
		ArrayList<Attendance> data = aService.printAll(study.getStudyNo());
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		gson.toJson(data, response.getWriter());
	} 
	
	/*
	 * 출석체크를 시도합니다.
	 * @return
	 * 0: 출석체크 오류
	 * 1: 출석체크 성공
	 * 2: 이미 오늘의 출석체크 완료
	 */
	@ResponseBody
	@RequestMapping(value="/attendance/check", method=RequestMethod.GET, produces="application/text; charset=UTF-8")
	public String attCheck(@ModelAttribute Attendance attendance,
							Model model) throws Exception {
		return String.valueOf(aService.tryInsertAttendance(attendance.getStudyNo(), attendance.getMemberNo()));
	}
	
	@ResponseBody
	@RequestMapping(value="/attendance/rate/study/{studyNo}", method=RequestMethod.GET, produces="application/text; charset=UTF-8")
	public float studyAttendanceRate(@PathVariable int studyNo) throws Exception {
		// 최근 30일간의 출석률
		float attendanceRate = aService.printStudyAttendanceRate(studyNo, 30);
		return attendanceRate;
	}
	
	@ResponseBody
	@RequestMapping(value="/attendance/study/top", method=RequestMethod.GET, produces="application/text; charset=UTF-8")
	public String studyTopAttendance(int studyNo) throws Exception {
		
		// 최근 몇 일의 출석률을 확인할 것인지 설정
		int period = 30;
		
		ArrayList<AttendanceAmountWithMemberVO> memberList = aService.printStudyTopAttendanceMember(studyNo, 5, period);
		int requiredAttendance = mService.printMeetingAmountByStudyNo(studyNo, period);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("memberList", memberList);
		map.put("requiredAttendance", requiredAttendance);
		
		return new Gson().toJson(map);
	}
	
	// 평점 추가 
	@RequestMapping(value="addPoint", method=RequestMethod.POST)
	public String addPoint() {
		return "";
	}
	
	// 평균 평점
	public String attRate() {
		return null;
	}
}
