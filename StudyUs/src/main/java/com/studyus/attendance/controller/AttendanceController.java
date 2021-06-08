package com.studyus.attendance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.studyus.attendance.domain.Attendance;
import com.studyus.attendance.service.AttendanceService;

@Controller
public class AttendanceController {
	
	@Autowired
	private AttendanceService aService;
	
	// 출석 리스트 보여주기 
	@RequestMapping(value="attList", method=RequestMethod.GET)
	public String attList(Model model) {
		return "";
	}
	
	// 출석하기 
	@ResponseBody 
	@RequestMapping(value="/attendance/check", method=RequestMethod.GET, produces="application/text; charset=UTF-8")
	public String attCheck(@ModelAttribute Attendance attendance,
							Model model) throws Exception {
		return String.valueOf(aService.checkAttendance(attendance));
	}
	
	// 출석 리스트
	@RequestMapping(value="/attendance/list")
	public String printAtt() {
		return "study/attendanceList";
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
