package com.studyus.review.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.studyus.member.domain.Member;
import com.studyus.review.domain.Review;
import com.studyus.review.service.ReviewService;

@Controller
public class ReviewController {

	@Autowired
	private ReviewService rService;

	// 리뷰리스트
	@RequestMapping(value = "/cafe/review/list", method = RequestMethod.GET)
	public void getReviewList(HttpServletResponse response, @RequestParam("caNo") int caNo)
			throws JsonIOException, IOException {
		ArrayList<Review> rList = rService.printAllReview(caNo);
		if (!rList.isEmpty()) {
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			gson.toJson(rList, response.getWriter());
		} else {
			System.out.println("데이터가 없습니다."); 
		}
	}

	// 리뷰 등록
	@ResponseBody
	@RequestMapping(value = "/cafe/review/register", method = RequestMethod.POST)
	public String registerReview(@ModelAttribute Review review, HttpSession session) {
		Member loginMember = (Member) session.getAttribute("loginMember");
		review.setMbNo(loginMember.getMbNo());
		int result = rService.registerReview(review);
		if (result > 0) {
			return "success"; 
		} else {
			return "fail";
		}
	}

	// 리뷰 수정
	@ResponseBody
	@RequestMapping(value = "/cafe/review/update", method = RequestMethod.POST)
	public String reviewUpdate(@ModelAttribute Review review) {
		int result = rService.modifyReview(review);
		if (result > 0) {
			return "success";
		} else {
			return "fail";
		} 
	}

	// 리뷰 삭제
	@ResponseBody
	@RequestMapping(value = "/cafe/review/delete", method = RequestMethod.GET)
	public String reviewDelete(@ModelAttribute Review review) {
		int result = rService.removeReview(review);
		if (result > 0) {
			return "success";
		} else {
			return "fail";
		}

	}
}
