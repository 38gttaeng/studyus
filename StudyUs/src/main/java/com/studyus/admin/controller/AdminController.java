package com.studyus.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.studyus.cafe.service.CafeService;
import com.studyus.member.service.MemberService;
import com.studyus.study.service.StudyService;

@Controller
@RequestMapping(value="/admin/*")
public class AdminController {
	
	// 멤버 리스트가 필요하면 - memberService
	@Autowired
	MemberService mService;
	
	// 스터디 리스트 필요하면 - studyService
	@Autowired
	StudyService sService;
	
	// 스터디카페 리스트가 필요하면 - cafeService
	@Autowired
	CafeService cService;
	
}
