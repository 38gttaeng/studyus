package com.studyus.review.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

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
import com.studyus.cafe.domain.Cafe;
import com.studyus.common.PageInfo;
import com.studyus.common.Pagination5;
import com.studyus.review.domain.Review;
import com.studyus.review.service.ReviewService;

@Controller
public class ReviewController {

	@Autowired
	private ReviewService rService;

	// 리뷰리스트
	@RequestMapping(value = "/cafe/review/list", method = RequestMethod.GET)
	public void getReviewList(HttpServletResponse response, @RequestParam("caNo") int caNo, @RequestParam(value="page", required=false) Integer page) throws IOException {
		////////////////////
		Cafe cafe = new Cafe();
		cafe.setCaNo(caNo);
		int listCount = rService.getListCount(caNo);
		
		int currentPage = (page != null) ? page : 1;
		PageInfo pi = Pagination5.getPageInfo(currentPage, listCount);
		//////////////
		ArrayList<Review> rList = rService.printAllReview(pi, caNo);
		/////////////
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("page", pi);
		map.put("rList", rList);
		
//		if (!rList.isEmpty()) {
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			gson.toJson(rList, response.getWriter());
//		} else {
//			System.out.println("데이터가 없습니다."); 
//		}
	}

	// 리뷰 등록
	@ResponseBody
	@RequestMapping(value = "/cafe/review/register", method = RequestMethod.POST)
	public String registerReview(@ModelAttribute Review review, HttpSession session) {
		
//		Member loginMember = (Member) session.getAttribute("loginUser");
//		review.setMbNo(loginMember.getMbNo());
		review.setMbNo(1);
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
