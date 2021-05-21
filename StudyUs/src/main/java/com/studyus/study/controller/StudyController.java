package com.studyus.study.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.studyus.common.RedirectWithMsg;
import com.studyus.enrollment.service.EnrollmentService;
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
	@RequestMapping(value="/study/register", method=RequestMethod.POST)
	public String registerStudy(HttpServletRequest request, @ModelAttribute Study study, @RequestParam(required=false) ArrayList<String> hashtagList) {
		
		System.out.println(study.toString());
		if (hashtagList != null)
			System.out.println(hashtagList.toString());
		
		return new RedirectWithMsg().redirect(request, "스터디 생성", "/");
	}
	
	@ResponseBody
	@RequestMapping(value="/study/check/url", method=RequestMethod.POST)
	public String checkUrl(@RequestParam String inputUrl) {
		
		int dupCheck = sService.checkUrl(inputUrl);
		
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
		return "";
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
