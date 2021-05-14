package com.studyus.reservation.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.studyus.reservation.domain.Reservation;
import com.studyus.reservation.service.ReservationService;

@Controller
public class ReservationController {
	
	@Autowired
	private ReservationService rsService;
	
	//////////////////// 예약 정보 ////////////////////
		// 예약 정보는 모두 ajax로 처리
	
	// 예약하기 페이지
		// 예약하려는 사람이 해당 날짜에 예약정보 있는 지 확인 우선
	public String reservationListByDate(@RequestParam("crNo") int caNo, @RequestParam("rsDate") String rsDate) {
		return null;
	}
	
	// 일정 페이지
		// 팀장 아이디에 해당하는 예약 모두 불러오기 (json)
	public void reservationListAll(HttpSession session) {

	}
	
	// 예약 확인 페이지
	public String reservationDetail(@RequestParam("rsNo") int rsNo) {
		return null;
	}
	
	//////////////////// 등록, 삭제 ////////////////////
	
	// 예약하기
	public ModelAndView reservationRegister(HttpSession session, ModelAndView mv, @ModelAttribute Reservation reservation) {
		return null;
	}
	
	// 예약취소
	public ModelAndView reservationDelete(ModelAndView mv, @RequestParam("rsNo") int rsNo) {
		return null;
	}
}
