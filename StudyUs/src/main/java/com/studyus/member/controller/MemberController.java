package com.studyus.member.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.scribejava.core.model.OAuth2AccessToken;
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
	
	// NaverLoginBO
	private NaverLoginBO naverLoginBO;
	private String apiResult = null;
	
	@Autowired
	private void setNaverLoginBO(NaverLoginBO naverLoginBO) {
		this.naverLoginBO = naverLoginBO;
	}
	
	// 로그인 뷰
	@RequestMapping(value = "/member/loginView", method = {RequestMethod.GET, RequestMethod.POST})
	public String loginView(HttpSession session, Model model) {
		// 네이버 로그인 url 생성
		String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);
		System.out.println("네이버: " + naverAuthUrl);
		
		

		// 네이버
		model.addAttribute("naver_url", naverAuthUrl);
		
		
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
			session.setAttribute("enrolledStudyList", enrolledStudyList);
			
			return "redirect:/";
		}else {
			model.addAttribute("msg", "로그인 실패!");
			return "common/errorPage";
		}
	}
	
	//네이버 로그인 성공시 callback호출 메소드
	@RequestMapping(value = "/member/callback", method = { RequestMethod.GET, RequestMethod.POST })
	public String callback(Model model, @RequestParam String code, @RequestParam String state, HttpSession session)
			throws IOException, ParseException {
		System.out.println("여기는 callback");
		OAuth2AccessToken oauthToken;
		oauthToken = naverLoginBO.getAccessToken(session, code, state);
		// 1. 로그인 사용자 정보를 읽어온다.
		apiResult = naverLoginBO.getUserProfile(oauthToken); // String형식의 json데이터
		/**
		 * apiResult json 구조 {"resultcode":"00", "message":"success",
		 * "response":{"id":"33666449","nickname":"shinn****","age":"20-29","gender":"M","email":"sh@naver.com","name":"\uc2e0\ubc94\ud638"}}
		 **/
		// 2. String형식인 apiResult를 json형태로 바꿈
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(apiResult);
		JSONObject jsonObj = (JSONObject) obj;
		
		// 3. 데이터 파싱
		// Top레벨 단계 _response 파싱
		JSONObject response_obj = (JSONObject) jsonObj.get("response");
		// response의 정보 파싱
		String id = (String) response_obj.get("id");
		String name = (String) response_obj.get("name");
		String email = (String) response_obj.get("email");
		String nickname = (String) response_obj.get("nickname");
		
		Member naverUser = new Member(id, name, email, nickname);
		System.out.println(naverUser);
		
		// 4-1. 회원등록
		int result = service.checkIdDup(id);
		if(result == 0) {
			int insertResult = service.registerNaverMem(naverUser);
			if(insertResult > 0) {
				Member loginUser = service.selectOneById(id);
				session.setAttribute("loginUser", loginUser); // 세션 생성
				model.addAttribute("result", apiResult);
				return "member/loginSuccess";
			}else {
				model.addAttribute("msg", "회원 가입 실패!!");
				return "common/errorPage";
			}
		}else {
			// 4-2. 파싱 정보 세션으로 저장
			Member loginUser = service.selectOneById(id);
			System.out.println(loginUser);
			session.setAttribute("loginUser", loginUser); // 세션 생성
			model.addAttribute("result", apiResult);
			return "member/loginSuccess";
		}
	}
	
	// 구글 Callback호출 메소드
	@RequestMapping(value = "oauth2callback.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String googleCallback(Model model, @RequestParam String code) throws IOException {
		System.out.println("Google login success");
		
		
		
		return "member/loginSuccess";
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
		ArrayList<Enrollment> myStudy = service.myStudyList(member.getMbNo());
		session.setAttribute("myStudy", myStudy);
		
		return "member/myPage";
	}
	
	// 회원정보 뷰
	@RequestMapping(value = "/member/myInfo", method = RequestMethod.GET)
	public String myInfoView(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Member member = (Member)session.getAttribute("loginUser");
		System.out.println(member);
		return "member/myInfo";
	}
	
	// 정보수정
	@RequestMapping(value="/member/modify", method=RequestMethod.POST)
	public String modifyMember(@ModelAttribute Member member,
								Model model,
								HttpServletRequest request) {
		int result = service.modifyMember(member);
		if(result > 0) {
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", member);
			return "redirect:/member/myInfo";
		}else {
			model.addAttribute("msg", "정보 수정에 실패했습니다.");
			return "common/errorPage";
		}
	}
	
	// 회원탈퇴 뷰
	@RequestMapping(value = "/member/deleteView", method = RequestMethod.GET)
	public String deleteMemView() {
		return "member/memberDelete";
	}
	
	// 회원탈퇴
	@RequestMapping(value = "/member/delete", method = RequestMethod.GET)
	public String memberDelete(@RequestParam("mbId") String mbId,
								Model model) {
		
		return null;
	}
	
	// 결제관리 뷰
	@RequestMapping(value = "/member/purchaseView", method = RequestMethod.GET)
	public String memPurchaseView() {
		return "member/memberPurchase";
	}
	
	
	// 후기모음
	@RequestMapping(value = "/member/myReview", method = RequestMethod.GET)
	public String myReviewList() {
		
		return null;
	}
}
