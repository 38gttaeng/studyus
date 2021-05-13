package com.studyus.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	StudyService service;
	
	// 스터디 생성 폼 페이지 get
	@RequestMapping(value="/study/register", method=RequestMethod.GET)
	public String getStudyCreate() {
		return "";
	}
	
	// 스터디 생성 post
	@RequestMapping(value="/study/register", method=RequestMethod.POST)
	public String postStudyCreate(@ModelAttribute Study study) {
		return "";
	}
	
	// 스터디 검색 페이지 get
	@RequestMapping(value="/study/search", method=RequestMethod.GET)
	public String getStudySearch() {
		return "";
	}
	
	// 스터디 검색 결과페이지 get
	@RequestMapping(value="/study/search", method=RequestMethod.POST)
	public String postStudySearch() {
		return "";
	}
	
	// 스터디 상세 페이지 get
	@RequestMapping(value="/study", method=RequestMethod.GET)
	public String getStudyMain() {
		return "";
	}
	
	// TODO 스터디 상세 페이지 get by Url
	
	// 스터디 수정 페이지 get
	@RequestMapping(value="/study/modify", method=RequestMethod.GET)
	public String getStudyModify() {
		return "";
	}
	
	// 스터디 수정 post
	@RequestMapping(value="/study/modify", method=RequestMethod.POST)
	public String postStudyModify() {
		return "";
	}
	
	// 스터디 삭제 post
	@RequestMapping(value="/study/delete", method=RequestMethod.POST)
	public String postStudyDelete() {
		return "";
	}
	
}
