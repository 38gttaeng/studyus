package com.studyus.purchase.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.studyus.member.domain.Member;
import com.studyus.purchase.domain.Purchase;
import com.studyus.purchase.service.PurchaseService;
import com.studyus.study.domain.Study;
import com.studyus.study.service.StudyService;

@Controller
public class PurchaseController {
	
	@Autowired
	private PurchaseService pService;
	
	@Autowired
	private StudyService sService;
	
	// 프리미엄샵 뷰
	@RequestMapping(value="/shop/premiumShopView")
	public ModelAndView premiumShopView(ModelAndView mv, HttpSession session) {
		Member member = ((Member)session.getAttribute("loginUser"));
		ArrayList<Study> sList = null;
		if (member != null) {
			Study study = new Study();
			study.setLeaderNo(member.getMbNo());
			sList = pService.printStudyList(study);
		}
		
		mv.addObject("sList",sList);
		mv.setViewName("shop/premiumShopView");
		
		return mv;
	}
	// 결제
	@ResponseBody
	@RequestMapping(value="/shop/premiumShop", method=RequestMethod.GET)
	public String premiumShop(HttpSession session) {
		Member member = ((Member)session.getAttribute("loginUser"));
		Study study = (Study)session.getAttribute("study");
		Purchase purchase = new Purchase();
		purchase.setMbNo(member.getMbNo());
		purchase.setStNo(study.getStudyNo());
		int result = pService.insertPremium(purchase);
		int updateStudy = sService.updateStudyMP(study);
		if(result > 0 & updateStudy > 0) {
			return "success";
		}else {
			return "fail";
		}
	}
	
	// 결제 완료 페이지
	@RequestMapping(value="/shop/paymentCompleted")
	public String paymentCompleted(@ModelAttribute Purchase purchase) {
		return "shop/paymentCompleted";
	}
	
	// 이용중인 프리미엄 
	@RequestMapping(value="checkPremium")
	public String checkPremium(Model model, @RequestParam("stNo") int stNo, HttpServletRequest request) {
		return "";
	}
	
	// 이용 내역 
	@RequestMapping(value="printPremium")
	public String printPremium(Model model, @RequestParam("stNo") int stNo, HttpServletRequest request) {
		return "";
	}
}