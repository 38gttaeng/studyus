package com.studyus.study.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.studyus.common.RedirectWithMsg;
import com.studyus.enrollment.service.EnrollmentService;
import com.studyus.member.domain.Member;
import com.studyus.study.domain.Study;
import com.studyus.study.domain.StudySearchCriteria;
import com.studyus.study.domain.StudySearchResult;
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
	@RequestMapping(value="/study/registerPost", method=RequestMethod.POST)
	public String registerStudy(HttpServletRequest request, 
								@ModelAttribute Study study, 
								@RequestParam(value="hashtag", required=false) String[] hashtagList,
								@RequestParam(value="start-h") String startHour,
								@RequestParam(value="start-m") String startMinute,
								@RequestParam(value="end-h") String endHour,
								@RequestParam(value="end-m") String endMinute) throws Exception {
		
		Member loginUser = (Member)request.getSession().getAttribute("loginUser");
		
		//TODO 파일 저장기능 구현 후 setFilename()
		study.setLeaderNo(loginUser.getMbNo());
		study.setStart(startHour + ":" + startMinute);
		study.setEnd(endHour + ":" + endMinute);
		
		int result = sService.registerStudy(study, hashtagList);
		
		if (0 < result) {
			// TODO 스터디 메인으로 url 변경
			return new RedirectWithMsg().redirect(request, "스터디가 생성되었습니다.", "/");
		} else {
			String referer = request.getHeader("Referer");
			return new RedirectWithMsg().redirect(request, "스터디 생성 오류", "redirect:" + referer);
		}
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
		
		return "study/search";
	}
	
	// 스터디 검색 결과페이지 get
	@RequestMapping(value="/study/search/result", method=RequestMethod.GET)
	public ModelAndView searchStudy(ModelAndView mv,
									@RequestParam(value="keyword") String keyword,
									@RequestParam(value="hashtag", required=false) String[] hashtags,
									@RequestParam(value="page", required=false) int page) throws Exception {
		
		StudySearchCriteria sc = StudySearchCriteria.searchReady(page, hashtags, keyword);
		ArrayList<StudySearchResult> searchResult = sService.printSearchResult(sc);
		
		mv.addObject("searchResult", searchResult);
		mv.addObject("page", page);
		mv.setViewName("/study/searchResult");
		
		return mv;
	}
	
	// 스터디 검색 비동기 추가로딩
	@ResponseBody
	@RequestMapping(value="/study/search/additional", method=RequestMethod.GET)
	public String searchAdditionally(@RequestParam(required=false) String keyword,
							@RequestParam(required=false) String[] hashtags,
							@RequestParam(required=false) int page) throws Exception {
		
		StudySearchCriteria sc = StudySearchCriteria.searchReady(page + 1, hashtags, keyword);
		ArrayList<StudySearchResult> searchResult = sService.printSearchResult(sc);
		
//		HashMap<String, Object> hm = new HashMap<String, Object>();
//		hm.put("searchResult", searchResult);
		
		return new Gson().toJson(searchResult);
	}
	
	// 스터디 상세 페이지 get
	@RequestMapping(value="/study", method=RequestMethod.GET)
	public String mainView() {
		return "study/study";
	}
	
	// TODO 스터디 상세 페이지 get by Url
	@RequestMapping(value="/study/{url}", method=RequestMethod.GET)
	public String mainViewUrl(HttpServletRequest request, @PathVariable("url") String url) {
		Study study = sService.printOneByUrl(url);
		
		request.getSession().setAttribute("study", study);
		
		return "study/study";
	}
	
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
