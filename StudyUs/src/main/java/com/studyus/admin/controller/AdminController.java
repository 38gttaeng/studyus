package com.studyus.admin.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.studyus.admin.service.AdminService;
import com.studyus.common.PageInfo;
import com.studyus.member.domain.Member;
import com.studyus.member.service.MemberService;
import com.studyus.study.service.StudyService;

@Controller
@RequestMapping(value="/admin/*")
public class AdminController {
	
	// 멤버 리스트가 필요하면 - memberService
	@Autowired
	MemberService mService;
	
	@Autowired
	StudyService sService;
	
	// 스터디 리스트 필요하면 - studyService

//	@Autowired
//	private AdminService aService;
	
	
//	@RequestMapping(value="/member/list", method=RequestMethod.GET)
//	public ModelAndView adminMemberView(ModelAndView mv, @RequestParam(value = "page", required = false) Integer page, PageInfo pi) {
//		int currentPage = (page != null) ? page : 1;
//		ArrayList<Member> mList = mService.printAllMb(pi);
//		if (!mList.isEmpty()) {
//			mv.addObject("mList", mList);
//			mv.setViewName("admin/adminMemberView");
//		} else {
//			mv.addObject("msg", "게시글 전체조회 실패");
//			mv.setViewName("common/errorPage");
//		}
//		return mv;
//
//	}
	
//	@RequestMapping(value="/study/list"
	
}
