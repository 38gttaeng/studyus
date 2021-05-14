package com.studyus.member.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.studyus.member.domain.Member;
import com.studyus.member.service.MemberService;

@Controller
public class MemberController {

	@Autowired
	private MemberService service;
	
	// 로그인 뷰
	@RequestMapping(value = "loginView.su", method = RequestMethod.GET)
	public String loginView() {
		
		return null;
	}
	// 로그인
	@RequestMapping(value = "login.su", method = RequestMethod.POST)
	public String memberLogin(HttpServletRequest request, 
			@ModelAttribute Member member, Model model) {
		
		return null;
	}
	// 로그아웃
	@RequestMapping(value = "logout.su", method = RequestMethod.GET)
	public String memberLogout(HttpServletRequest request) {
		
		return null;
	}
	// 회원가입 뷰
	@RequestMapping(value="enrollView.su", method = RequestMethod.GET)
	public String enrollView() {
		
		return null;
	}
	// 회원등록
	@RequestMapping(value = "memberRegister.su", method = RequestMethod.POST)
	public String memberRegister(@ModelAttribute Member member, 
								@RequestParam("post") String post,
								Model model) {
		
		return null;
	}
	
	// 아이디 중복검사
	@ResponseBody
	@RequestMapping(value = "dupId.su", method = RequestMethod.GET)
	public String idDuplicateCheck(@RequestParam("mbId") String mbId) {
		
		return null;
	}
	// 아이디/비번찾기 뷰
	@RequestMapping(value = "findView.su", method = RequestMethod.GET)
	public String findView() {
		
		return null;
	}
	// 아이디찾기
	@RequestMapping(value = "findId.su", method = RequestMethod.POST)
	public String findId(@RequestParam("mbEmail") String mbEmail,
						@RequestParam("mbName") String maName) {
		
		return null;
	}
	// 비밀번호찾기
	@RequestMapping(value = "findPwd.su", method = RequestMethod.POST)
	public String findPwd(@RequestParam("mbEmail") String mbEmail,
						@RequestParam("mbId") String mbId,
						@RequestParam("mbName") String maName) {
		
		return null;
	}
	// 마이페이지 뷰
	@RequestMapping(value = "myPage.su", method = RequestMethod.GET)
	public String myPageView() {
		
		return null;
	}
	// 회원정보 뷰
	@RequestMapping(value = "myInfo.su", method = RequestMethod.GET)
	public String myInfoView() {
		
		return null;
	}
	// 정보수정
	@RequestMapping(value="memberModify.su", method=RequestMethod.POST)
	public String modifyMember(@ModelAttribute Member member,
								@RequestParam("post") String post,
								Model model,
								HttpServletRequest request) {
		
		return null;
	}
	// 회원탈퇴 뷰
	@RequestMapping(value = "deleteMemView.su", method = RequestMethod.GET)
	public String deleteMemView() {
		
		return null;
	}
	// 회원탈퇴
	@RequestMapping(value = "memberDelete.su", method = RequestMethod.GET)
	public String memberDelete(@RequestParam("mbId") String mbId,
								Model model) {
		
		return null;
	}
	// 후기모음
	@RequestMapping(value = "myReview.su", method = RequestMethod.GET)
	public String myReview() {
		
		return null;
	}
}
