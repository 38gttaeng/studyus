package com.studyus.enrollment.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.studyus.enrollment.domain.Enrollment;
import com.studyus.enrollment.service.EnrollmentService;
import com.studyus.member.domain.Member;

@Controller
public class EnrollmentController {

	@Autowired
	EnrollmentService eService;
	
	// 가입신청 보내기
	@ResponseBody
	@RequestMapping(value="/enrollment/register", method=RequestMethod.GET, produces="application/text; charset=UTF-8")
	public String apply(HttpServletRequest request, 
						@RequestParam(required=false) String url, 
						@RequestParam(value="greeting", required=false) String message) throws Exception {
		// 로그인 검사
		Member loginMember = (Member) request.getSession().getAttribute("loginUser");
		if (loginMember == null) {
			return String.valueOf(-1);
		}
		
		Enrollment enrollment = new Enrollment();
		enrollment.setMemberNo(loginMember.getMbNo());
		enrollment.setMessage(message);
		int result = eService.apply(enrollment, url);
		
		return String.valueOf(result);
	}

	// 가입신청 수정 (가입 수락, 거절, 탈퇴)
	@ResponseBody
	@RequestMapping(value="/enrollment/modify/status")
	public String modifyEnrollmentStatus(@ModelAttribute Enrollment enrollment) throws Exception {
		
		int result = eService.modifyStatus(enrollment);
		return String.valueOf(result);
	}
	
	// 스터디 번호로 신청서 출력
	@RequestMapping(value="enrollment/list", method=RequestMethod.GET)
	public String printEnrollmentByStudyNo(@RequestParam int studyNo) throws Exception {
		return "";
	}
	
	// 추방 
		@RequestMapping(value="/member/banish")
		public String banishMember(@RequestParam("memberNo") int memberNo, @RequestParam(value="studyNo", required=false) int studyNo) {
			int result = eService.banishMember(memberNo);
			if(result > 0) {
				return "study/studyMember";
			}else {
				return "";
			}
		}
	
}
