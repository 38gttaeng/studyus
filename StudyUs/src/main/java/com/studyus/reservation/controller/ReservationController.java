package com.studyus.reservation.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.studyus.cafe.domain.Cafe;
import com.studyus.cafe.service.CafeService;
import com.studyus.caferoom.domain.Caferoom;
import com.studyus.caferoom.service.CaferoomService;
import com.studyus.member.domain.Member;
import com.studyus.reservation.domain.Reservation;
import com.studyus.reservation.service.ReservationService;
import com.studyus.study.domain.Study;
import com.studyus.study.service.StudyService;

@Controller
public class ReservationController {
	
	@Autowired
	private ReservationService rsService;
	
	@Autowired
	private CafeService cfService;
	
	@Autowired
	private CaferoomService crService;
	
	@Autowired
	private StudyService stService;
	
	/*********************************** 예약 정보 ***********************************/
	
	// 예약하기 페이지 ( 1 : 룸 선택 )
	@RequestMapping(value="/cafe/reservation", method=RequestMethod.GET)
	public ModelAndView getRoomList(ModelAndView mv, @RequestParam("caNo") int caNo) {
		Cafe cafe = cfService.printOne(caNo);
		
		mv.addObject("cafe", cafe);
		mv.setViewName("cafe/cafeReservation");
		return mv;
	}
	
	// 예약하기 페이지 ( 2 : 날짜와 시간, 상세정보 선택 )
	@RequestMapping(value="/cafe/reservation-room", method=RequestMethod.GET)
	public ModelAndView registerReservation(HttpSession session, ModelAndView mv, @RequestParam("crNo") int crNo) {
		Caferoom caferoom = crService.printOne(crNo);
		Cafe cafe = cfService.printOne(caferoom.getCaNo());
		
		int leaderNo = ((Member)session.getAttribute("loginUser")).getMbNo();
		ArrayList<Study> stList = stService.getStudyListByMbNo(leaderNo);
		
		mv.addObject("caferoom", caferoom);
		mv.addObject("cafe", cafe);
		mv.addObject("stList", stList);
		mv.setViewName("cafe/cafeReservation2");
		return mv;
	}
	
	// 예약 날짜에 해당하는 예약 리스트 전부 불러오기
	@RequestMapping(value="/cafe/reservation-check", method=RequestMethod.GET)
	public void reservationCheck(HttpServletResponse response, @ModelAttribute Reservation reservation) throws Exception {
		// crNo(방번호)와 rsDate(날짜)로 해당 리스트 불러오기
		ArrayList<Reservation> rsList = rsService.getReservationCheck(reservation);
		
		// 숫자별로 쪼개서 숫자만 있는 리스트 새로 만들기
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(Reservation rsOne : rsList) {
			int duration = rsOne.getRsEnd() - rsOne.getRsStart();
			for(int i=rsOne.getRsStart(); i<rsOne.getRsStart()+duration; i++) {
				list.add(i);
			}
		}
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		gson.toJson(list, response.getWriter());
	}
	
	// 예약 확인 페이지
	@RequestMapping(value="/cafe/reservation/ss", method=RequestMethod.GET)
	public String reservationDetail(@RequestParam("rsNo") int rsNo) {
		return null;
	}
	
	/*********************************** 등록, 삭제 ***********************************/
	
	// 예약하기
	@RequestMapping(value="/cafe/reservation-register", method=RequestMethod.POST)
	public ModelAndView reservationRegister(ModelAndView mv, @ModelAttribute Reservation reservation
			, @RequestParam("startStr") String start, @RequestParam("endStr") String end) {
		
		// 숫자가 안보내지는 거 같아서 이렇게 보낸다...
		int rsStart = Integer.parseInt(start);
		int rsEnd = Integer.parseInt(end);
		reservation.setRsStart(rsStart);
		reservation.setRsEnd(rsEnd);
		int rsNo = rsService.registerReservation(reservation);
		
		if(rsNo > 0) {
			mv.addObject("reservation", reservation);
			// 이동하는 곳은 예약정보 확인하는 곳으로~~~~~~변경해라ㅏㅏㅏㅏㅇ나아아아
			mv.setViewName("cafe/reservation-room?crNo=" + reservation.getCrNo());
		} else {
			System.out.println("예약 등록 실패!");
		}
		
		return mv;
	}
	
	// 예약취소
	public ModelAndView reservationDelete(ModelAndView mv, @RequestParam("rsNo") int rsNo) {
		return null; 
	}
}
