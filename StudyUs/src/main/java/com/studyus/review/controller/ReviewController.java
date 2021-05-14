package com.studyus.review.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.studyus.review.domain.Review;
import com.studyus.review.service.ReviewService;

@Controller
public class ReviewController {
	
	@Autowired
	private ReviewService rService;
	
	// 리뷰리스트 
	@RequestMapping(value = "reviewList.kh", method=RequestMethod.GET)
	public String reviewList(Model model) {
		return null;
		
	}
	
	// 리뷰 등록
	@RequestMapping(value="reviewRegister.kh", method=RequestMethod.POST)
	public String registerReview(@ModelAttribute Review review,
								@RequestParam(value = "uploadFile", required=false) MultipartFile uploadFile, HttpServletRequest request, Model model) {
									return null;
		
	}
	
	// 파일 저장
	public String saveFile(MultipartFile file, HttpServletRequest request) {
		return null;
		
	}
	
	// 리뷰 수정
	@RequestMapping(value="reviewModifyView.kh", method=RequestMethod.GET)
	public String reviewModifyView(@RequestParam("rvNo") int rvNo, Model model) {
		return null;
		
	}
	
	@RequestMapping(value="reviewUpdate.kh", method=RequestMethod.POST)
	public String reviewUpdate(@ModelAttribute Review review, Model model, HttpServletRequest request, @RequestParam("reloadFile") MultipartFile reloadFile) {
		return null;
		
	}
	
	// 리뷰 삭제
	@RequestMapping(value = "reviewDelete.kh", method=RequestMethod.GET)
	public String reviewDelete(@RequestParam("reNo") int reNo, Model model, HttpServletRequest request) {
		return null;
		
	}
}
