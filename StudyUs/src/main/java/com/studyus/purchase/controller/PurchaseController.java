package com.studyus.purchase.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.studyus.purchase.service.PurchaseService;

@Controller
public class PurchaseController {
	
	@Autowired
	public PurchaseService purService;
	// 프리미엄샵 뷰
	@RequestMapping(value="premiumShopView")
	public String premiumShopView(Model model) {
		return "";
	}
	// 프리미엄샵 
	@RequestMapping(value="premiumShop")
	public String premiumShop(Model model, @RequestParam("studyNo") int studyNo) {
		return "";
	}
	// 결제 
	
	// 결제 완료 
	
	// 이용중인 프리미엄 
	@RequestMapping(value="checkPremium")
	public String checkPremium(Model model, @RequestParam("studyNo") int studyNo, HttpServletRequest request) {
		return "";
	}
	
	// 이용 내역 
	@RequestMapping(value="printPremium")
	public String printPremium(Model model, @RequestParam("studyNo") int studyNo, HttpServletRequest request) {
		return "";
	}
}
