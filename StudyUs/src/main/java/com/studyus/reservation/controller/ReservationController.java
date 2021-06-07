package com.studyus.reservation.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.studyus.cafe.domain.Cafe;
import com.studyus.cafe.service.CafeService;
import com.studyus.reservation.domain.Reservation;
import com.studyus.reservation.service.ReservationService;

@Controller
public class ReservationController {
	
	@Autowired
	private ReservationService rsService;
	
	@Autowired
	private CafeService cfService;
	
	/*********************************** 예약 정보 ***********************************/
	// 예약하려는 사람이 해당 날짜에 예약정보 있는 지 확인 우선 -> ajax로!
	
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
	public String registerReservation(@RequestParam("crNo") int crNo) {
		return "cafe/cafeReservation2";
	}
	
	// 예약 확인 페이지
	public String reservationDetail(@RequestParam("rsNo") int rsNo) {
		return null;
	}
	
	/*********************************** 등록, 삭제 ***********************************/
	
	// 예약하기
	public ModelAndView reservationRegister(HttpSession session, ModelAndView mv, @ModelAttribute Reservation reservation) {
		return null;
	}
	
	// 예약취소
	public ModelAndView reservationDelete(ModelAndView mv, @RequestParam("rsNo") int rsNo) {
		return null;
	}
}
