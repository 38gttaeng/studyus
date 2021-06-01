package com.studyus.purchase.controller;

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

import com.studyus.member.domain.Member;
import com.studyus.purchase.domain.Purchase;
import com.studyus.purchase.service.PurchaseService;

@Controller
public class PurchaseController {
	
	@Autowired
	private PurchaseService pService;
	
	// 프리미엄샵 뷰
	@RequestMapping(value="/shop/premiumShopView")
	public String premiumShopView() {
		// Purchase purchase = nService.
		return "shop/premiumShopView";
	}
	// 결제
	@ResponseBody
	@RequestMapping(value="/shop/premiumShop", method=RequestMethod.GET)
	public String premiumShop(HttpSession session) {
		int mbNo = ((Member)session.getAttribute("loginUser")).getMbNo();
		Purchase purchase = new Purchase();
		purchase.setMbNo(mbNo);
		int result = pService.insertPremium(purchase);
		if(result > 0) {
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
