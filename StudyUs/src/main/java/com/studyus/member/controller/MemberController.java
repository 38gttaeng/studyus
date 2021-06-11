package com.studyus.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import com.studyus.enrollment.service.EnrollmentService;
import com.studyus.member.domain.Member;
import com.studyus.member.domain.MyStudyInfo;
import com.studyus.member.service.MemberService;
import com.studyus.purchase.domain.Purchase;
import com.studyus.purchase.service.PurchaseService;
import com.studyus.study.domain.Study;
import com.studyus.study.service.StudyService;

@Controller
public class MemberController {

	@Autowired
	private MemberService service;
	
	@Autowired
	private StudyService sService;
	
	@Autowired
	private EnrollmentService eService;
	
	@Autowired
	private PurchaseService pService;
	
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
	public String memberLogin(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute Member member, Model model) throws IOException {
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
			PrintWriter out = response.getWriter();
			response.setContentType("text/html; charset=UTF-8");
			out.println("<script>alert('아이디 또는 비밀번호가 일치하지 않습니다.'); location.href='/member/loginView'</script>");
			out.flush();
			out.close();
			return null;
		}
	}
	
	//네이버 로그인 성공시 callback호출 메소드
	@RequestMapping(value = "/member/callback", method = { RequestMethod.GET, RequestMethod.POST })
	public String callback(Model model, @RequestParam String code, @RequestParam String state,
						HttpSession session, HttpServletResponse response) throws IOException, ParseException {
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
				PrintWriter out = response.getWriter();
				response.setContentType("text/html; charset=UTF-8");
				out.println("<script>alert('네이버 회원 등록에 실패하였습니다.'); location.href='/member/loginView'</script>");
				out.flush();
				out.close();
				return null;
			}
		}else {
			// 4-2. 파싱 정보 세션으로 저장
			Member loginUser = service.selectOneById(id);
			System.out.println(loginUser);
			session.setAttribute("loginUser", loginUser); // 세션 생성
			
			// 가입한 스터디 리스트를 세션에 저장
			ArrayList<Study> enrolledStudyList = sService.printAllEnrolledByMemberNo(loginUser.getMbNo());
			session.setAttribute("enrolledStudyList", enrolledStudyList);
			
			model.addAttribute("result", apiResult);
			return "member/loginSuccess";
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
	public String memberRegister(@ModelAttribute Member member, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		int result = service.registerMember(member);
		
		if(result > 0) {
			response.setContentType("text/html; charset=UTF-8");
			out.println("<script>alert('회원가입 인증 이메일이 발송되었습니다.'); location.href='/'</script>");
			out.flush();
			out.close();
			return null;
		}else {
			response.setContentType("text/html; charset=UTF-8");
			out.println("<script>alert('회원가입에 실패하였습니다.'); location.href='/member/enrollView'</script>");
			out.flush();
			out.close();
			return null;
		}
	}
	
	// email 인증
    @RequestMapping(value="/member/joinConfirm", method=RequestMethod.GET)
    public String emailConfirm(@ModelAttribute Member member, HttpServletResponse response) throws Exception {
    	PrintWriter out = response.getWriter();
    	Member checkKey = service.checkAuthKey(member.getMbId());
    	
    	System.out.println(member);
    	System.out.println(checkKey.getAuthKey());
    	System.out.println(member.getAuthKey());
    	
    	if(member.getAuthKey().equals(checkKey.getAuthKey())) {
    		member.setMbStatus(1);
            int result = service.updateMbStatus(member);
            if(result > 0) {
            	response.setContentType("text/html; charset=UTF-8");
        		out.println("<script>alert('이메일 인증이 완료되었습니다.'); location.href='/'</script>");
        		member.setAuthKey("authKeyisnull");
        		service.updateAuthKey(member);
        		out.flush();
        		out.close();
        		return null;
            }else {
            	response.setContentType("text/html; charset=UTF-8");
        		out.println("<script>alert('이메일 인증에 실패했습니다.\n다시 시도해주세요.'); location.href='/'</script>");
        		out.flush();
        		out.close();
            	return null;
            }
    	}else {
    		response.setContentType("text/html; charset=UTF-8");
    		out.println("<script>alert('유효하지 않은 인증 링크입니다.'); location.href='/'</script>");
    		out.flush();
    		out.close();
    		return null;
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
	
	// 이메일 중복검사
	@ResponseBody
	@RequestMapping(value = "/member/dupEmail", method = RequestMethod.GET)
	public String emailDuplicateCheck(@RequestParam("mbEmail") String mbEmail) {
		return String.valueOf(service.checkEmailDup(mbEmail));
	}
	
	// 아이디/비번찾기 뷰
	@RequestMapping(value = "/member/findView", method = RequestMethod.GET)
	public String findView() {
		return "member/findIdPwd";
	} 
	
	// 아이디찾기
	@RequestMapping(value = "/member/findId", method = RequestMethod.POST)
	public void findId(HttpServletRequest request, HttpServletResponse response,
						@ModelAttribute Member member) throws IOException {
		Member mOne = new Member(member.getMbId(), member.getMbName(), member.getMbEmail());
		Member findId = service.findMemId(mOne);
		System.out.println(findId);
		if(findId != null) {
			HttpSession session = request.getSession();
			session.setAttribute("findId", findId);
		}else {
			PrintWriter out = response.getWriter();
			response.setContentType("text/html; charset=UTF-8");
			out.println("<script>alert('아이디 찾기에 실패했습니다.'); location.href='/member/findView'</script>");
			out.flush();
			out.close();
		}
	}
	
	// 비밀번호찾기
	@RequestMapping(value = "/member/findPwd", method = RequestMethod.POST)
	public void findPwd(HttpServletRequest request, HttpServletResponse response,
						@ModelAttribute Member member) throws IOException {
		Member mOne = new Member(member.getMbId(), member.getMbName(), member.getMbEmail());
		Member findPwd = service.findMemPw(mOne);
		if(findPwd != null) {
			HttpSession session = request.getSession();
			session.setAttribute("findPwd", findPwd);
		}else {
			PrintWriter out = response.getWriter();
			response.setContentType("text/html; charset=UTF-8");
			out.println("<script>alert('비밀번호 찾기에 실패했습니다.'); location.href='/member/findView'</script>");
			out.flush();
			out.close();
		}
	}
	
	// 아이디 찾기 인증번호 입력
	@RequestMapping(value="/member/findIdConfirm", method={ RequestMethod.GET, RequestMethod.POST })
	public String findIdConfirm(@RequestParam("authKey") String authKey,
								HttpServletRequest request,
								HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Member findId = (Member)session.getAttribute("findId");
		Member checkKey = service.checkKeyByEmail(findId.getMbEmail());
		
		System.out.println(authKey);
		System.out.println(checkKey.getAuthKey());
	    
	    if(authKey.equals(checkKey.getAuthKey())) {
	    	findId.setAuthKey("authKeyisnull");
        	service.updateAuthKey(findId);
        	return "redirect:/member/findIdResultView";
    	}else {
    		PrintWriter out = response.getWriter();
    		response.setContentType("text/html; charset=UTF-8");
    		out.println("<script>alert('인증번호가 일치하지 않습니다.'); location.href='/member/findView'</script>");
    		out.flush();
    		out.close();
    		return null;
    	}
	 }
	
	// 비밀번호 찾기 인증번호 입력
	@RequestMapping(value="/member/findPwdConfirm", method=RequestMethod.POST)
	public String findPwdConfirm(@RequestParam("authKey") String authKey,
								HttpServletRequest request,
								HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Member findPwd = (Member)session.getAttribute("findPwd");
		System.out.println("findPwd : " + findPwd);
		Member checkKey = service.checkAuthKey(findPwd.getMbId());
	   
		System.out.println(checkKey.getAuthKey());
	    System.out.println(findPwd.getAuthKey());
	    
	    if(findPwd.getAuthKey().equals(checkKey.getAuthKey())) {
	    	findPwd.setAuthKey("authKeyisnull");
        	service.updateAuthKey(findPwd);
        	session.setAttribute("findPwd", findPwd);
        	return "redirect:/member/findPwdResultView";
    	}else {
    		PrintWriter out = response.getWriter();
    		response.setContentType("text/html; charset=UTF-8");
    		out.println("<script>alert('인증번호가 일치하지 않습니다.'); location.href='/member/findView'</script>");
    		out.flush();
    		out.close();
    		return null;
    	}
	 }
	
	// 아이디찾기 결과 뷰
	@RequestMapping(value = "/member/findIdResultView", method = RequestMethod.GET)
	public String findIdResultView() {
		return "member/findIdResult";
	}
		 
	// 비밀번호찾기 결과 뷰
	@RequestMapping(value = "/member/findPwdResultView", method = RequestMethod.GET)
	public String findPwdResultView() {
		return "member/findPwdResult";
	}
	
	// 비밀번호 변경
	@RequestMapping(value = "/member/changePwd", method = RequestMethod.POST)
	public String changePassword(@RequestParam("mbPassword") String mbPassword,
								HttpServletRequest request,
								HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		Member member = (Member) session.getAttribute("findPwd");
		member.setMbPassword(mbPassword);
		System.out.println(member);
		int result = service.modifyMember(member);
		if(result > 0) {
			response.setContentType("text/html; charset=UTF-8");
    		out.println("<script>alert('비밀번호가 변경되었습니다.'); location.href='/member/loginView'</script>");
    		out.flush();
    		out.close();
    		return null;
		}else {
			response.setContentType("text/html; charset=UTF-8");
    		out.println("<script>alert('비밀번호 변경이 실패했습니다.'); location.href='/member/findPwdResultView'</script>");
    		out.flush();
    		out.close();
			return null;
		}
	}
	
	// 마이페이지 뷰
	@RequestMapping(value = "/member/myPage", method = RequestMethod.GET)
	public String myPageView(HttpServletRequest request) {
		HttpSession session = request.getSession();
		ArrayList<Study> enrolledStudyList = (ArrayList<Study>)session.getAttribute("enrolledStudyList");
		Member loginUser = (Member)session.getAttribute("loginUser");
		int mbNo = loginUser.getMbNo();
		if(loginUser == null) {
    		return "redirect:/doLogin";
		} else {
			ArrayList<MyStudyInfo> myStudyList = service.printMyStudy(mbNo, enrolledStudyList);
			session.setAttribute("myStudyList", myStudyList);
			return "member/myPage";
		}
	}
	
	// 회원정보 뷰
	@RequestMapping(value = "/member/myInfo", method = RequestMethod.GET)
	public String myInfoView(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Member loginUser = (Member)session.getAttribute("loginUser");
		if(loginUser == null) {
    		return "redirect:/doLogin";
		} else {
			return "member/myInfo";
		}
	}
	
	// 정보수정
	@RequestMapping(value="/member/modify", method=RequestMethod.POST)
	public String modifyMember(@ModelAttribute Member member,
								Model model,
								HttpServletRequest request,
								HttpServletResponse response) throws IOException {
		int result = service.modifyMember(member);
		if(result > 0) {
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", member);
			return "redirect:/member/myInfo";
		}else {
			PrintWriter out = response.getWriter();
			response.setContentType("text/html; charset=UTF-8");
    		out.println("<script>alert('회원 정보 수정에 실패했습니다.'); location.href='/member/myInfo'</script>");
    		out.flush();
    		out.close();
        	return null;
		}
	}
	
	// 회원탈퇴 뷰
	@RequestMapping(value = "/member/deleteView", method = RequestMethod.GET)
	public String deleteMemView() {
		return "member/memberDelete";
	}
	
	// 회원탈퇴 
	@RequestMapping(value = "/member/delete", method = {RequestMethod.GET, RequestMethod.POST})
	public String memberDelete(@RequestParam("mbId") String mbId, Model model,
								HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		int mbStatus = 0;
		Member mOne = new Member(mbId, mbStatus);
		int result = service.removeMember(mOne);
		if(result > 0) {
			HttpSession session = request.getSession();
			session.invalidate();
			response.setContentType("text/html; charset=UTF-8");
			out.println("<script>alert('회원탈퇴가 완료되었습니다.'); location.href='/'</script>");
			out.flush();
			out.close();
			return null;
		} else {
			response.setContentType("text/html; charset=UTF-8");
			out.println("<script>alert('회원탈퇴에 실패하셨습니다.\n문의는 FAQ를 이용해주세요.'); location.href='/'</script>");
			out.flush();
			out.close();
			return null;
		}
	}
	
	// 결제관리 뷰
	@RequestMapping(value = "/member/purchaseView", method = RequestMethod.GET)
	public String memPurchaseView(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Member loginUser = (Member)session.getAttribute("loginUser");
		if(loginUser == null) {
    		return "redirect:/doLogin";
		} else {
			ArrayList<Purchase> pList = pService.printOnePuByMbNo(loginUser.getMbNo());
			session.setAttribute("pList", pList);
			return "member/memberPurchase";
		}
	}
	
	
//	@RequestMapping(value="/study/38gttaeng/member")
//	public String memberView() {
//		return "study/studyMember";
//	}
	
}
