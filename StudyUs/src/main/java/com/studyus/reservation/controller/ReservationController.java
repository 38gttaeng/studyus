package com.studyus.reservation.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.studyus.cafe.domain.Cafe;
import com.studyus.cafe.service.CafeService;
import com.studyus.caferoom.domain.Caferoom;
import com.studyus.caferoom.service.CaferoomService;
import com.studyus.common.RedirectWithMsg;
import com.studyus.member.domain.Member;
import com.studyus.reservation.domain.Reservation;
import com.studyus.reservation.domain.ReservationMember;
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
	@RequestMapping(value="/study/reservation/detail", method=RequestMethod.GET)
	public ModelAndView reservationDetail(ModelAndView mv, @RequestParam("rsNo") int rsNo) {
		
		Reservation reservation = rsService.printOne(rsNo); // 예약하나 정보
		Caferoom caferoom = crService.printOne(reservation.getCrNo()); // 예약 하나에 해당하는 카페룸 정보
		Cafe cafe = cfService.printOne(caferoom.getCaNo()); // 카페룸 하나에 해당하는 카페 정보
		
		if(reservation != null && caferoom != null && cafe != null) {
			mv.addObject("reservation", reservation);
			mv.addObject("caferoom", caferoom);
			mv.addObject("cafe", cafe);
			mv.setViewName("study/reservationDetail");
		} else {
			System.out.println("스터디 카페 예약확인 실패");
			System.out.println("reservation : " + reservation);
			System.out.println("caferoom : " + caferoom);
			System.out.println("cafe : " + cafe);
		}
		return mv;
	}
	
	// 예약 멤버 리스트
	@RequestMapping(value="/study/reservation/member", method=RequestMethod.GET)
	public void reservationMbList(HttpServletResponse response, @RequestParam("rsNo") int rsNo) throws Exception {
		
		// 예약에 할당된 멤버 리스트(값이 없을 수 있음)
		ArrayList<ReservationMember> mbList = rsService.printAllMember(rsNo);
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		gson.toJson(mbList, response.getWriter());
	}
	
	// 예약 멤버 등록
	@ResponseBody
	@RequestMapping(value="/study/reservation/member-register", method=RequestMethod.GET)
	public String memberRegister(HttpSession session, @RequestParam("rsNo") int rsNo) {
		int mbNo = ((Member)session.getAttribute("loginUser")).getMbNo();
		ReservationMember reservMember = new ReservationMember(rsNo, mbNo);
		
		int result = rsService.registerMember(reservMember);
		if(result > 0) {
			return "success";
		} else {
			return "fail";
		}
	}
	
	// 예약 멤버 삭제
	@ResponseBody
	@RequestMapping(value="/study/reservation/member-remove", method=RequestMethod.GET)
	public String memberRemove(HttpSession session, @RequestParam("rsNo") int rsNo) {
		int mbNo = ((Member)session.getAttribute("loginUser")).getMbNo();
		ReservationMember reservMember = new ReservationMember(rsNo, mbNo);
		
		int result = rsService.removeMember(reservMember);
		if(result > 0) {
			return "success";
		} else {
			return "fail";
		}
	}
	
	/*********************************** 등록, 삭제 ***********************************/
	
	// 예약하기
	@RequestMapping(value="/cafe/reservation-register", method=RequestMethod.POST)
	public String reservationRegister(HttpServletRequest request, @ModelAttribute Reservation reservation
			, @RequestParam("startStr") String start, @RequestParam("endStr") String end) {
		
		// 숫자가 안보내지는 거 같아서 이렇게 보낸다...
		int rsStart = Integer.parseInt(start);
		int rsEnd = Integer.parseInt(end);
		reservation.setRsStart(rsStart);
		reservation.setRsEnd(rsEnd);
		int rsNo = rsService.registerReservation(reservation);
		
		if(rsNo == 0) { 
			System.out.println("등록 실패애ㅑ애애애애애ㅐ : " + rsNo);
		}
		return new RedirectWithMsg().redirect(request, "예약이 완료되었습니다.", "/study/reservation/detail?rsNo=" + rsNo);
	}
	
	// 예약취소
	@RequestMapping(value="/study/reservation/delete", method=RequestMethod.GET)
	public String reservationDelete(HttpServletRequest request, @ModelAttribute Reservation reservation) {
		int result = rsService.removeReservation(reservation);
		
		if(result > 0) {
			return new RedirectWithMsg().redirect(request, "예약이 취소되었습니다.", "/study/calendar");
		} else {
			return new RedirectWithMsg().redirect(request, "예약 삭제 실패!", "/study/calendar");
		}
	}
}
