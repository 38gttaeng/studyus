package com.studyus.calendar.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.studyus.assignment.domain.Assignment;
import com.studyus.assignment.service.AssignmentService;
import com.studyus.calendar.domain.Calendar;
import com.studyus.study.domain.Study;

@Controller
public class CalendarController {
	
	@Autowired
	private AssignmentService asService;
	
	@RequestMapping(value="/study/calendar", method=RequestMethod.GET)
	public String calendarView() {
		return "/study/calendar";
	}
	
	// 일정
	@RequestMapping(value="/study/calendar/assignment", method=RequestMethod.GET)
	public void assignmentCalendar(HttpSession session, HttpServletResponse response) throws Exception {
		
		int stNo = ((Study)session.getAttribute("study")).getStudyNo();
		ArrayList<Assignment> asList = asService.printAllByStudyNo(stNo);
		
		if(!asList.isEmpty()) {
			ArrayList<Calendar> caList = new ArrayList<Calendar>();
			for(Assignment asOne : asList) {
				String url = "/study/assignment/detail?asNo=" + asOne.getAsNo();
				Calendar calendar = new Calendar("과제", asOne.getAsName(), asOne.getAsInsertDate(), asOne.getAsDeadLine(), url, "backHover" + asOne.getAsStatus());
				caList.add(calendar);
			}
			
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			gson.toJson(caList, response.getWriter());
		} else {
			System.out.println("스터디에 해당하는 과제 전부 가져오기 실패~~~");
		}
	}
}
