package com.studyus.admin.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.studyus.admin.service.AdminService;
import com.studyus.board.domain.Board;

@Controller
public class AdminController {

	@Autowired
	private AdminService aService; 
	
	@RequestMapping(value="admin/member", method=RequestMethod.GET)
	public ModelAndView adminMemberView(ModelAndView mv, @RequestParam(value="page", required = false)Integer page) {
//		int currentPage = (page != null) ? page : 1;
//		int listCount = aService.getListCount();
//		ArrayList<Member> mList = mService.printAll(pi);
//		if (!bList.isEmpty()) {
//			mv.addObject("bList", bList);
//			mv.setViewName("admin/adminMemberView");
//		} else {
//			mv.addObject("msg", "게시글 전체조회 실패");
//			mv.setViewName("common/errorPage");
//		}
		return mv;

	}
}
