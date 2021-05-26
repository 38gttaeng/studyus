package com.studyus.member.controller;

import java.util.ArrayList;

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

import com.studyus.enrollment.domain.Enrollment;
import com.studyus.member.domain.Member;
import com.studyus.member.service.MemberService;
import com.studyus.study.domain.Study;
import com.studyus.study.service.StudyService;

@Controller
public class MemberController {

	@Autowired
	private MemberService service;
	
	@Autowired
	private StudyService sService;
	
	// 로그인 뷰
	@RequestMapping(value = "/member/loginView", method = {RequestMethod.GET, RequestMethod.POST})
	public String loginView() {
		return "member/login";
	}
	// 로그인
	@RequestMapping(value = "/member/login", method = RequestMethod.POST)
	public String memberLogin(HttpServletRequest request, 
			@ModelAttribute Member member, Model model) {
		Member mOne = new Member(member.getMbId(), member.getMbPassword());
		Member loginUser = service.loginMember(mOne);
		
		if(loginUser != null) {
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", loginUser);
			
			// 가입한 스터디 리스트를 세션에 저장
			ArrayList<Study> enrolledStudyList = sService.printAllEnrolledByMemberNo(loginUser.getMbNo());
			System.out.println(enrolledStudyList);
			session.setAttribute("enrolledStudyList", enrolledStudyList);
			
			return "redirect:/";
		}else {
			model.addAttribute("msg", "로그인 실패!");
			return "common/errorPage";
		}
	}
	
	// 로그아웃
	@RequestMapping(value = "/member/logout", method = RequestMethod.GET)
	public String memberLogout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:/";
	}
	
	// 회원가입 뷰
	@RequestMapping(value="/member/enrollView", method = {RequestMethod.GET, RequestMethod.POST})
	public String enrollView() {
		return "member/memberJoin";
	}
	
	// 회원가입
	@RequestMapping(value = "/member/register", method = RequestMethod.POST)
	public String memberRegister(@ModelAttribute Member member, 
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
	@RequestMapping(value = "/member/dupId", method = RequestMethod.GET)
	public String idDuplicateCheck(@RequestParam("mbId") String mbId) {
		return String.valueOf(service.checkIdDup(mbId));
	}
	
	// 닉네임 중복검사
	@ResponseBody
	@RequestMapping(value = "/member/dupNick", method = RequestMethod.GET)
	public String nickDuplicateCheck(@RequestParam("mbNickname") String mbNickname) {
		return String.valueOf(service.checkNickDup(mbNickname));
	}
	
	// 아이디/비번찾기 뷰
	@RequestMapping(value = "/member/findView", method = RequestMethod.GET)
	public String findView() {
		return "member/findIdPwd";
	} 
	
	// 아이디찾기
	@RequestMapping(value = "/member/findId", method = RequestMethod.POST)
	public String findId(HttpServletRequest request,
			@ModelAttribute Member member, Model model) {
		Member mOne = new Member(member.getMbId(), member.getMbName(), member.getMbEmail());
		Member findId = service.findMemId(mOne);
		if(findId != null) {
			HttpSession session = request.getSession();
			session.setAttribute("findId", findId);
			return "redirect:/member/findIdResultView";
		}else {
			model.addAttribute("msg", "아이디 찾기에 실패했습니다.");
			return "common/errorPage";
		}
	}
	
	// 아이디찾기 결과 뷰
	@RequestMapping(value = "/member/findIdResultView", method = RequestMethod.GET)
	public String findIdResultView() {
		return "member/findIdResult";
	}
	
	// 비밀번호찾기
	@RequestMapping(value = "/member/findPwd", method = RequestMethod.POST)
	public String findPwd(HttpServletRequest request,
			@ModelAttribute Member member, Model model) {
		Member mOne = new Member(member.getMbId(), member.getMbName(), member.getMbEmail());
		Member findPwd = service.findMemPw(mOne);
		if(findPwd != null) {
			HttpSession session = request.getSession();
			session.setAttribute("findPwd", findPwd);
			return "redirect:/member/findPwdResultView";
		}else {
			model.addAttribute("msg", "비밀번호 찾기에 실패했습니다.");
			return "common/errorPage";
		}
	}
	
	// 비밀번호찾기 결과 뷰
	@RequestMapping(value = "/member/findPwdResultView", method = RequestMethod.GET)
	public String findPwdResultView() {
		return "member/findPwdResult";
	}
	
	// 마이페이지 뷰
	@RequestMapping(value = "/member/myPage", method = RequestMethod.GET)
	public String myPageView(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Member member = (Member)session.getAttribute("loginUser");
//		ArrayList<Enrollment> myStudy = service.myStudyList(member.getMbNo());
		
		
		return "member/myPage";
	}
	
	// 회원정보 뷰
	@RequestMapping(value = "/member/myInfo", method = RequestMethod.GET)
	public String myInfoView() {
		return "member/myInfo";
	}
	
	// 정보수정
	@RequestMapping(value="/member/modify", method=RequestMethod.POST)
	public String modifyMember(@ModelAttribute Member member,
								Model model,
								HttpServletRequest request) {
		
		return null;
	}
	
	// 회원탈퇴 뷰
	@RequestMapping(value = "/member/deleteView", method = RequestMethod.GET)
	public String deleteMemView() {
		
		return null;
	}
	
	// 회원탈퇴
	@RequestMapping(value = "/member/delete", method = RequestMethod.GET)
	public String memberDelete(@RequestParam("mbId") String mbId,
								Model model) {
		
		return null;
	}
	
	// 후기모음
	@RequestMapping(value = "/member/myReview", method = RequestMethod.GET)
	public String myReviewList() {
		
		return null;
	}
}
