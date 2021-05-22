package com.studyus.study.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.studyus.common.RedirectWithMsg;
import com.studyus.enrollment.service.EnrollmentService;
import com.studyus.hashtag.service.HashtagService;
import com.studyus.study.domain.Study;
import com.studyus.study.service.StudyService;

@Controller
public class StudyController {
	
	// 스터디 생성 페이지 get
	// 스터디 생성 post
	// 스터디 검색 페이지 get
	// 스터디 검색 결과페이지 get
	// 스터디 상세 페이지 get
	// 스터디 수정 페이지 get
	// 스터디 수정 post
	// 스터디 삭제 post
	
	///////////////////////////////////////////
	
	@Autowired
	StudyService sService;
	
	@Autowired
	EnrollmentService eService;
	
	// 스터디 생성 폼 페이지 get
	@RequestMapping(value="/study/register", method=RequestMethod.GET)
	public String registerView() {
		
		return "study/register";
	}
	
	// 스터디 생성 post
	@RequestMapping(value="/study/register", method=RequestMethod.POST, produces="application/text;charset=utf-8")
	public String registerStudy(HttpServletRequest request, 
								@ModelAttribute Study study, 
								@RequestParam(value="hashtagList", required=false) ArrayList<String> hashtagList,
								@RequestParam(value="start-h") String startHour,
								@RequestParam(value="start-m") String startMinute,
								@RequestParam(value="end-h") String endHour,
								@RequestParam(value="end-m") String endMinute) throws Exception {
		
		//TODO 파일 저장기능 구현 후 setFilename()
		
		// TODO 로그인 구현시 memberNo
		study.setLeaderNo(1); 
		
		study.setStart(startHour + ":" + startMinute);
		study.setEnd(endHour + ":" + endMinute);
		
		System.out.println(study.toString());
		int result = sService.registerStudy(study, hashtagList);
		
		System.out.println("result: " + result);
		
		if (0 < result) {
			// TODO 스터디 메인으로 url 변경
			return new RedirectWithMsg().redirect(request, "스터디가 생성되었습니다.", "/");
		} else {
			return new RedirectWithMsg().redirect(request, "스터디 생성 오류", "redirect:/");
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/study/check/url", method=RequestMethod.POST)
	public String checkUrl(@RequestParam String inputUrl) {
		
		int dupCheck = sService.checkUrl(inputUrl);
		System.out.println(dupCheck);
		if (0 < dupCheck) {
			return String.valueOf(dupCheck);
		} else {
			return String.valueOf(dupCheck);
		}
	}
	
	// 스터디 검색 페이지 get
	@RequestMapping(value="/study/search", method=RequestMethod.GET)
	public String searchView() {
		return "";
	}
	
	// 스터디 검색 결과페이지 get
	@RequestMapping(value="/study/search", method=RequestMethod.POST)
	public String searchStudy() {
		return "";
	}
	
	// 스터디 상세 페이지 get
	@RequestMapping(value="/study", method=RequestMethod.GET)
	public String mainView() {
		return "study/study";
	}
	
	// TODO 스터디 상세 페이지 get by Url
	
	// 스터디 수정 페이지 get
	@RequestMapping(value="/study/modify", method=RequestMethod.GET)
	public String modifyView() {
		return "";
	}
	
	// 스터디 수정 post
	@RequestMapping(value="/study/modify", method=RequestMethod.POST)
	public String modifyStudy() {
		return "";
	}
	
	// 스터디 삭제 post
	@RequestMapping(value="/study/delete", method=RequestMethod.POST)
	public String deleteStudy() {
		return "";
	}
	
}
