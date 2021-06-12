package com.studyus.enrollment.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.studyus.assignment.domain.Assignment;
import com.studyus.assignment.service.AssignmentService;
import com.studyus.attendance.service.AttendanceService;
import com.studyus.enrollment.domain.Enrollment;
import com.studyus.enrollment.service.EnrollmentService;
import com.studyus.member.domain.Member;
import com.studyus.member.service.MemberService;
import com.studyus.study.domain.Study;
import com.studyus.study.service.StudyService;

@Controller
public class EnrollmentController {
	@Autowired
	private MemberService mService;
	
	@Autowired
	private EnrollmentService eService;
	
	@Autowired
	private StudyService sService;
	
	@Autowired
	private AssignmentService aService;
	
	@Autowired
	private AttendanceService attService;
	
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
		
		int result = eService.applyEnrollment(loginMember.getMbNo(), message, url);
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
	
	// 스터디 가입한 회원 목록
	@RequestMapping(value="/study/member", method=RequestMethod.GET)
	public ModelAndView printStudyMember(ModelAndView mv, HttpSession session) {
		Study study = (Study)session.getAttribute("study");
		ArrayList<Member> mList = mService.printAllByStudyNo(study.getStudyNo());
		float personalAttendanceRate = 0;
		for(Member m : mList) {
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			map.put("stNo", study.getStudyNo());
			map.put("mbNo", m.getMbNo());
			int rate = aService.printAssignmentRate(map);
			m.setMbReputation(rate);
			personalAttendanceRate = attService.printPersonalAttendanceRate(m.getMbNo(), study.getStudyNo(), 30);
			m.setAttPer((int)personalAttendanceRate * 100);
		}
		mv.addObject("mList", mList);
		mv.setViewName("study/studyMember");
		return mv;
	}
	
	// 추방 
	@RequestMapping(value="/study/banish", method=RequestMethod.GET)
	public ModelAndView banishMember(ModelAndView mv, HttpSession session, @RequestParam("memberNo") int memberNo) {
		Study study = (Study)session.getAttribute("study");
		Enrollment enrollment = new Enrollment();
		enrollment.setStudyNo(study.getStudyNo());
		enrollment.setMemberNo(memberNo);
		int result = eService.banishMember(enrollment);
		if(result > 0) {
			mv.setViewName("redirect:/study/member");
		}else {
			mv.addObject("msg", "추방 실패").setViewName("common/errorPage");
		}
		return mv;
	}

	
}
