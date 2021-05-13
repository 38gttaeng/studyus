package com.studyus.caferoom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.studyus.caferoom.service.CaferoomService;

@Controller
public class CaferoomController {
	
	@Autowired
	private CaferoomService crService;
	
	// 스터디카페룸 리스트
	@RequestMapping(value="caferoomList.kh", method= RequestMethod.GET)
	public String caferoomList(Model model) {
		return null;
	
	}

	
	
}
