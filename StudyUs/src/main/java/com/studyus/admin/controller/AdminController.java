package com.studyus.admin.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
import com.studyus.calendar.controller.CalendarController;
import com.studyus.calendar.domain.ReservCalendar;
import com.studyus.member.domain.Member;
import com.studyus.member.service.MemberService;
import com.studyus.purchase.domain.Purchase;
import com.studyus.purchase.service.PurchaseService;
import com.studyus.reservation.domain.Reservation;
import com.studyus.reservation.service.ReservationService;
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
	
	// 예약 리스트
	@Autowired
	private ReservationService rService;
	
	// 캘린더 변환
	@Autowired
	private CalendarController calController;
	
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
			ArrayList<Study> data = sService.printAll();
			
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
	public ModelAndView reservationListView(ModelAndView mv) {
		ArrayList<Cafe> caList = cService.printAll();
		if(!caList.isEmpty()) {
			mv.addObject("caList", caList);
			mv.setViewName("admin/reservationAdmin");
		} else {
			mv.addObject("msg", "카페 리스트 조회 실패");
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
	
	// 카페에 해당하는 예약 정보 전부 가져오기 (관리자 확인용)
	@RequestMapping(value="/admin/reservation-list", method=RequestMethod.GET)
	public void getAllReservationByCaNo(HttpServletResponse response) throws Exception {
		
		// 카페전체 예약 리스트를 담는 메소드
			// 카페별로 나누어 담기 위해서 
		ArrayList<ArrayList<ReservCalendar>> cafeList = new ArrayList<ArrayList<ReservCalendar>>();
		
		// 카페 리스트 출력
		ArrayList<Cafe> caList = cService.printAll();
		
		// 카페전체 리스트에 카페에 해당하는 예약리스트 넣기
		for(Cafe cafe : caList) {
			ArrayList<Reservation> rList = rService.printAll(cafe.getCaNo());
			ArrayList<ReservCalendar> cList = new ArrayList<ReservCalendar>();
			for(Reservation rOne : rList) {
				ReservCalendar cal = new ReservCalendar();
				cal = calController.reservationManage(rOne);
				cList.add(cal);
			}
			cafeList.add(cList);
		}
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		gson.toJson(cafeList, response.getWriter());
	}
	
	// 카페별 한달 예약수 가져오기
	@RequestMapping(value="/admin/reservation/month-chart", method=RequestMethod.GET)
	public void getReservationChartByMonth(HttpServletResponse response) throws Exception {
		
		// 각각에 해당하는 어레이 만들어서 넣어주기
		ArrayList<String> labelList = new ArrayList<String>();
		ArrayList<Integer> countList = new ArrayList<Integer>();
		
		// 카페 리스트로 for문 돌려서 넣어주기
		ArrayList<Cafe> caList = cService.printAll();
		for(Cafe cafe : caList) {
			labelList.add(cafe.getCaName());
			int count = rService.printChartByMonth(cafe.getCaNo());
			countList.add(count);
		}
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("labelList", labelList);
		map.put("countList", countList);
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		gson.toJson(map, response.getWriter());
	}
	
	// 카페별 일주일 예약수 가져오기
	@RequestMapping(value="/admin/reservation/week-chart", method=RequestMethod.GET)
	public void getReservationChartByWeek(HttpServletResponse response) throws Exception {
		
		// 각각에 해당하는 어레이 만들어서 넣어주기
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		
		// 카페 리스트로 for문 돌려서 넣어주기
		ArrayList<Cafe> caList = cService.printAll();
		for(Cafe cafe : caList) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("label", cafe.getCaName());
			map.put("data", rService.printChartByWeek(cafe.getCaNo()));
			list.add(map);
		}
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		gson.toJson(list, response.getWriter());
	}
}
