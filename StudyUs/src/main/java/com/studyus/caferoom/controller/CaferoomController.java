package com.studyus.caferoom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.studyus.cafe.domain.Cafe;
import com.studyus.caferoom.domain.Caferoom;
import com.studyus.caferoom.service.CaferoomService;

@Controller
public class CaferoomController {
	
	@Autowired
	private CaferoomService crService;
	
		// 모두 ajax로 처리
	
	// 카페룸 리스트 (관리자 수정 페이지)
	public String caferoomListView(@ModelAttribute Cafe cafe) {
		return null;
	}

	// 카페룸 추가
	public String addCaferoom(@ModelAttribute Caferoom caferoom) {
		return null;
	}
	
	// 카페룸 삭제
	public String deleteCaferoom(@RequestParam("crNo") int crNo) {
		return null;
	}
	
}
