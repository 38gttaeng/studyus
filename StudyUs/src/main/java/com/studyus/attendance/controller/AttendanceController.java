package com.studyus.attendance.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.studyus.attendance.domain.Attendance;
import com.studyus.attendance.service.AttendanceService;
import com.studyus.study.domain.Study;

@Controller
public class AttendanceController {
	
	@Autowired
	private AttendanceService aService;
	
//	// 출석 리스트 보여주기 
//	@RequestMapping(value="/attendance/attList", method=RequestMethod.GET)
//	public void attList(HttpSession session, HttpServletResponse response) throws Exception {
//		Study study = (Study)session.getAttribute("study");
//		ArrayList<HashMap<String, Object>> data = null;
//		
//		data = aService.printAll(study.getStudyNo());
//		
//		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
//		gson.toJson(data, response.getWriter());
//	}
	
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
