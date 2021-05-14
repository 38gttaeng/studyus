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
	
	///////////////////// 카페룸
	
	// 리스트
		// 관리자 수정 페이지 / 예약 페이지 / 예약 확인 페이지
		// cafe 정보 + cafe에 해당하는 룸정보 보내기
	public String caferoomListView(@ModelAttribute Cafe cafe) {
		return null;
	}
	
	// 디테일 (ajax)
	public String getCaferoomMax(@RequestParam("crNo") int crNo) {
		return null;
	}

	/////////////////// 카페룸 등록, 수정, 삭제 ///////////////////
	
	// 등록
	public String addCaferoom(@ModelAttribute Caferoom caferoom) {
		return null;
	}
	
	// 수정
	public String modifyCaferoom(@ModelAttribute Caferoom caferoom) {
		return null;
	}
	
	// 삭제
	public String deleteCaferoom(@RequestParam("crNo") int crNo) {
		return null;
	}
	
}
