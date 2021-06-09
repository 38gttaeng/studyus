package com.studyus.admin.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.studyus.cafe.domain.Cafe;
import com.studyus.cafe.service.CafeService;
import com.studyus.common.PageInfo;
import com.studyus.common.Pagination10;
import com.studyus.member.domain.Member;
import com.studyus.member.service.MemberService;
import com.studyus.purchase.domain.Purchase;
import com.studyus.purchase.service.PurchaseService;
import com.studyus.study.domain.Study;
import com.studyus.study.domain.StudySearchCriteria;
import com.studyus.study.service.StudyService;

@Controller
//@RequestMapping(value="/admin/*")
public class AdminController {
	
	// 멤버 리스트가 필요하면 - memberService
	@Autowired
	private MemberService service;
	
	// 스터디 리스트 필요하면 - studyService
	@Autowired
	private StudyService sService;
	
	// 스터디카페 리스트가 필요하면 - cafeService
	@Autowired
	private CafeService cService;
	
	// 결제 리스트 
	@Autowired
	private PurchaseService pService;
	
	/*********** 회원 관리 ************/
	
		// 회원 목록 화면
		@RequestMapping(value="/admin/member", method=RequestMethod.GET)
		public String memberListView() {
			return "admin/memberAdmin";
		}
		
		// 회원 목록
		@RequestMapping(value="/admin/member/list", method=RequestMethod.GET)
		public void memberList(HttpSession session, HttpServletResponse response) throws JsonIOException, IOException {
			ArrayList<Member> data = service.printAll();
			
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			gson.toJson(data, response.getWriter());
		}
		
		// 회원 선택 삭제
//		@ResponseBody
//		@RequestMapping(value="/admin/member/delete", method=RequestMethod.GET)
//		public String memberListDelete(@RequestParam("deList") List<String> deList) {
//			
//			int result = 0;
//			for(String delNo : deList) {
//				result += service.removeMember(delNo);
//			}
//			
//			if(result == deList.size()) {
//				return "success";
//			} else {
//				return "error";
//			}
//		}
	
		
	/*********** 스터디 관리 ************/
		
		// 스터디 목록 화면
		@RequestMapping(value="/admin/study", method=RequestMethod.GET)
		public String studyListView() {
			return "admin/studyAdmin";
		}
		
		// 스터디 목록
		@RequestMapping(value="/admin/study/list", method=RequestMethod.GET)
		public void studyList(HttpSession session, HttpServletResponse response, @RequestParam("sc") StudySearchCriteria sc) throws JsonIOException, IOException {
			ArrayList<Study> data = sService.printAll(sc);
			
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			gson.toJson(data, response.getWriter());
		}
		
	/*********** 스터디카페 관리 ************/
		
		// 스터디카페 목록 페이지
		@RequestMapping(value="/admin/cafe", method=RequestMethod.GET)
		public String cafeListView() {
			return "admin/cafeAdmin";
		}
		
		// 스터디카페 리스트
		@RequestMapping(value="/admin/cafe/list", method=RequestMethod.GET)
		public void cafeList(HttpSession session, HttpServletResponse response) throws JsonIOException, IOException {
			ArrayList<Cafe> data = cService.printAll();
			
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			gson.toJson(data, response.getWriter());
		}
		
		// 스터디카페 선택 삭제
		@ResponseBody
		@RequestMapping(value="/admin/cafe/delete", method=RequestMethod.GET)
		public String cafeListDelete(@RequestParam("deList") List<Integer> deList) {
			System.out.println("deList란" + deList);
			int result = 0;
			for(int delNo : deList) {
				result += cService.removeCafe(delNo);
				System.out.println("delNo란"+delNo);
			}
			
			if(result == deList.size()) {
				return "success";
			} else {
				return "error";
			}
		}
		
	/*********** 결제 관리 ************/
		
	// 결제 정보 조회
	@RequestMapping(value="/admin/purchase", method=RequestMethod.GET)
	public String purchaseListView() {
		return "admin/purchaseAdmin";
	}
				
	// 결제 정보 리스트 
	@RequestMapping(value="/admin/purchase/list", method=RequestMethod.GET)
	public void purchaseList(HttpSession session, HttpServletResponse response) throws JsonIOException, IOException {
		ArrayList<Purchase> data = pService.printAll();
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		gson.toJson(data, response.getWriter());
	}
	
	/*********** 예약 관리 ************/
	
	// 예약 목록 화면
	@RequestMapping(value="/admin/reservation", method=RequestMethod.GET)
	public String reservationListView() {
		return "admin/reservationAdmin";
	}
}
