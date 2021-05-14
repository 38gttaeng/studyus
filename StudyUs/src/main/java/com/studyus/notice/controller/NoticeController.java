package com.studyus.notice.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.studyus.notice.domain.Notice;
import com.studyus.notice.domain.Search;
import com.studyus.notice.service.NoticeService;

@Controller
public class NoticeController {

	@Autowired
	private NoticeService nService;
	
	// 전체 목록 조회 
	@RequestMapping(value="", method=RequestMethod.GET)
	public String noticeList(ModelAndView mv, 
										@RequestParam(value="page", required=false) Integer page) {
		return "";
	}
	
	// 상세 조회 
	@RequestMapping(value="", method=RequestMethod.GET)
	public String noticeDetail(@RequestParam("noticeNo") int noticeNo,
											@RequestParam("nMotherNo") int nMotherNo, 
											Model model) {
		return "";
	}
	
	// 검색 
	@RequestMapping(value="", method=RequestMethod.GET)
	public String noticeSearch(@ModelAttribute Search search, Model model) {
		return "";
	}
	
	// 작성 뷰 
	@RequestMapping(value="", method=RequestMethod.GET)
	public String noticeWriteView() {
		return "";
	}
	
	// 작성
	@RequestMapping(value="", method=RequestMethod.POST)
	public String registerNotice(ModelAndView mv,
												@ModelAttribute Notice notice,
												@RequestParam(value="uploadFile", required=false) MultipartFile uploadFile,
												HttpServletRequest request, Model model) {
		return "";
	}
	
	// 파일 저장 
	public String saveFile(MultipartFile file, HttpServletRequest request) {
		return null;
	}
	
	// 수정 뷰 
	@RequestMapping(value="", method=RequestMethod.GET)
	public String noticeModifyView(@RequestParam("noticeNo") int noticeNo, Model model) {
		return "";
	}
	
	// 수정 
	@RequestMapping(value="", method=RequestMethod.POST)
	public String noticeUpdate(@ModelAttribute Notice notice, Model model, HttpServletRequest request, @RequestParam("reloadFile") MultipartFile reloadFile) {
		return "";
	}
	
	// 삭제
	@RequestMapping(value="", method=RequestMethod.GET)
	public String noticeDelete(@RequestParam("noticeNo") int noticeNo, Model model, HttpServletRequest request) {
		return "";
	}
	
	// 저장되어있는 파일 삭제 
	public void deleteFile(String fileName, HttpServletRequest request) {
		
	}
	
	// 댓글 작성 
	@RequestMapping(value="", method=RequestMethod.POST)
	public String registerComment(@ModelAttribute Notice notice, Model model) {
		return "";
	}
}
