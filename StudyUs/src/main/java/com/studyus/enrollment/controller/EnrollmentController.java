package com.studyus.enrollment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.studyus.enrollment.domain.Enrollment;
import com.studyus.enrollment.service.EnrollmentService;

@Controller
public class EnrollmentController {

	@Autowired
	EnrollmentService eService;
	
	// 가입신청 보내기
	@RequestMapping(value="enrollment/register", method=RequestMethod.POST)
	public String sendEnrollment(@ModelAttribute Enrollment enrollment) throws Exception {
		return "";
	}
	
	// 가입신청 수정 (가입 수락, 거절, 탈퇴)
	@RequestMapping(value="enrollment/modify", method=RequestMethod.POST)
	public String modifyEnrollment(@ModelAttribute Enrollment enrollment) throws Exception {
		return "";
	}
	
	// 스터디 번호로 신청서 출력
	@RequestMapping(value="enrollment/list", method=RequestMethod.GET)
	public String printEnrollmentByStudyNo(@RequestParam int studyNo) throws Exception {
		return "";
	}
	
}
