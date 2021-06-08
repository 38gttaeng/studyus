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
import com.studyus.member.domain.Member;
import com.studyus.reservation.domain.Reservation;
import com.studyus.reservation.service.ReservationService;
import com.studyus.study.domain.Study;

@Controller
public class CalendarController {
	
	@Autowired
	private AssignmentService asService;
	
	@Autowired
	private ReservationService rsService;
	
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
	
	// 마이페이지에서 띄우기
	@RequestMapping(value="/member/calendar/assignment", method=RequestMethod.GET)
	public void memAsCalendar(HttpSession session, HttpServletResponse response) throws Exception {
		
		int mbNo = ((Member)session.getAttribute("loginUser")).getMbNo();
		ArrayList<Assignment> asList = asService.printAllByMbNo(mbNo);
		
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
	
	// 모임
	@RequestMapping(value="/study/calendar/reservation", method=RequestMethod.GET)
	public void reservationCalendar(HttpSession session, HttpServletResponse response) throws Exception {
		
		int stNo = ((Study)session.getAttribute("study")).getStudyNo();
		ArrayList<Reservation> rsList = rsService.printReservationByStNo(stNo);
		
		if(!rsList.isEmpty()) {
			ArrayList<Calendar> caList = new ArrayList<Calendar>();
			for(Reservation rsOne : rsList) {
				//String url = "/study/assignment/detail?asNo=" + asOne.getAsNo();
				String url = "";
				String start = rsOne.getRsDate() + " " + rsOne.getRsStart() + ":00";
				String end = rsOne.getRsDate() + " " + rsOne.getRsEnd() + ":00";
				Calendar calendar = new Calendar("모임", rsOne.getCaName() + " " + rsOne.getCrName(), start, end, url, "backHover" + (rsOne.getRsStatus() + 4));
				caList.add(calendar);
			}
			
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			gson.toJson(caList, response.getWriter());
		} else {
			System.out.println("스터디에 해당하는 과제 전부 가져오기 실패~~~");
		}
	}
}
