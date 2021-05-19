package com.studyus.attendance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.studyus.attendance.domain.Attendance;
import com.studyus.attendance.service.AttendanceService;

@Controller
public class AttendanceController {
	
	@Autowired
	private AttendanceService aService;
	
	// 출석 리스트 보여주기 
//	@RequestMapping(value="", method=RequestMethod.GET)
	public String attList(Model model) {
		return "";
	}
	
	// 출석하기 
//	@RequestMapping(value="", method=RequestMethod.POST)
	public String attCheck(@ModelAttribute Attendance attendance, Model model) {
		return "";
	}
	
	// 평점 추가 
//	@RequestMapping(value="", method=RequestMethod.POST)
	public String addPoint() {
		return "";
	}
	
	// 평균 평점
	public String attRate() {
		return null;
	}
}
