package com.studyus.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
	@RequestMapping(value = "loginView", method = RequestMethod.GET)
	public String loginView() {
		return "common/login";
	}
	// 로그인
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String memberLogin(HttpServletRequest request, 
			@ModelAttribute Member member, Model model) {
		Member mOne = new Member(member.getMbId(), member.getMbPassword());
		Member loginUser = service.loginMember(mOne);
		if(loginUser != null) {
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", loginUser);
			return "cohost";
		}else {
			model.addAttribute("msg", "로그인 실패!");
			return "common/errorPage";
		}
	}
	
	// 로그아웃
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String memberLogout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:/";
	}
	
	// 회원가입 뷰
	@RequestMapping(value="enrollView", method = RequestMethod.GET)
	public String enrollView() {
		return "member/memberJoin";
	}
	
	// 회원가입
	@RequestMapping(value = "memberRegister", method = RequestMethod.POST)
	public String memberRegister(@ModelAttribute Member member, 
								@RequestParam("post") String post,
								Model model) {
		int result = service.registerMember(member);
		if(result > 0) {
			return "redirect:/";
		}else {
			model.addAttribute("msg", "회원 가입 실패!!");
			return "common/errorPage";
		}
	}
	
	// 아이디 중복검사
	@ResponseBody
	@RequestMapping(value = "dupId", method = RequestMethod.GET)
	public String idDuplicateCheck(@RequestParam("mbId") String mbId) {
		return String.valueOf(service.checkIdDup(mbId));
	}
	
	// 닉네임 중복검사
	@ResponseBody
	@RequestMapping(value = "dupNick", method = RequestMethod.GET)
	public String nickDuplicateCheck(@RequestParam("mbNickname") String mbNickname) {
		return String.valueOf(service.checkNickDup(mbNickname));
	}
	
	// 아이디/비번찾기 뷰
	@RequestMapping(value = "findView", method = RequestMethod.GET)
	public String findView() {
		
		return "member/findView";
	}
	
	// 아이디찾기
	@RequestMapping(value = "findId", method = RequestMethod.POST)
	public String findId(@RequestParam("mbEmail") String mbEmail,
						@RequestParam("mbName") String maName) {
		
		return null;
	}
	
	// 비밀번호찾기
	@RequestMapping(value = "findPwd", method = RequestMethod.POST)
	public String findPwd(@RequestParam("mbEmail") String mbEmail,
						@RequestParam("mbId") String mbId,
						@RequestParam("mbName") String maName) {
		
		return null;
	}
	
	// 마이페이지 뷰
	@RequestMapping(value = "myPage", method = RequestMethod.GET)
	public String myPageView() {
		
		return null;
	}
	
	// 회원정보 뷰
	@RequestMapping(value = "myInfo", method = RequestMethod.GET)
	public String myInfoView() {
		
		return null;
	}
	
	// 정보수정
	@RequestMapping(value="memberModify", method=RequestMethod.POST)
	public String modifyMember(@ModelAttribute Member member,
								@RequestParam("post") String post,
								Model model,
								HttpServletRequest request) {
		
		return null;
	}
	
	// 회원탈퇴 뷰
	@RequestMapping(value = "deleteMemView", method = RequestMethod.GET)
	public String deleteMemView() {
		
		return null;
	}
	
	// 회원탈퇴
	@RequestMapping(value = "memberDelete", method = RequestMethod.GET)
	public String memberDelete(@RequestParam("mbId") String mbId,
								Model model) {
		
		return null;
	}
	
	// 후기모음
	@RequestMapping(value = "myReview", method = RequestMethod.GET)
	public String myReviewList() {
		
		return null;
	}
}
