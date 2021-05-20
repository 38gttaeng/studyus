package com.studyus.calendar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.studyus.assignment.service.AssignmentService;

@Controller
public class CalendarController {
	
	@Autowired
	private AssignmentService aService;
	
	@RequestMapping(value="/study/calendar", method=RequestMethod.GET)
	public String calendarView() {
		return "/study/calendar";
	}
}
