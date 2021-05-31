package com.studyus.notice.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson; 
import com.google.gson.GsonBuilder;
import com.studyus.common.PageInfo;
import com.studyus.common.Pagination10;
import com.studyus.notice.domain.Notice;
import com.studyus.notice.domain.Search;
import com.studyus.notice.service.NoticeService;

@Controller
public class NoticeController {
 
	@Autowired
	private NoticeService nService;
	
	// 전체 목록 조회 
	@RequestMapping(value="/notice/noticeList", method=RequestMethod.GET)
	public ModelAndView noticeList(ModelAndView mv,			
														@RequestParam(value="page", required=false) Integer page) {
		Notice notice = new Notice();
		int listCount = nService.getListCount(notice);
		int currentPage = (page != null) ? page : 1;
		PageInfo pi = Pagination10.getPageInfo(currentPage, listCount);
		ArrayList<Notice> nList = nService.printAll(pi, notice);
		
		for(Notice notice1 : nList) {
			System.out.println(notice1.toString());
		}
		System.out.println(pi.toString());
		// 메인 공지글 출력 
		ArrayList<Notice> mainNotice = nService.printMainNotice(notice);
		System.out.println(mainNotice.toString());
		//@@@@@@@@게시글 뒤에 N 표시하기 @@@@@@
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, -1); // 2일간 보이게
		String nowDay = format.format(cal.getTime());
		
		if(!nList.isEmpty()) {
			mv.addObject("nList", nList);
			mv.addObject("pi", pi);
			mv.addObject("nowDay", nowDay);
			mv.addObject("mainNotice", mainNotice);
			mv.setViewName("notice/noticeListView");
		}else {
			// mv.addObject("msg", "조회 실패");
			mv.setViewName("notice/noData");
		}
		return mv;
	}
	
	// 상세 조회 
	@RequestMapping(value="/notice/noticeDetail", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView noticeDetail(ModelAndView mv, @RequestParam("noNo") int noNo) {
		// 조회수 증가 
		nService.addReadCount(noNo);
		// 공지사항 상세 조회 
		Notice notice = nService.printOne(noNo);
		if(notice != null) {
			// 메소드 체이닝 방식 
			mv.addObject("notice", notice).setViewName("notice/noticeDetail");
		}else {
			mv.addObject("msg", "공지사항 상세 조회 실패");
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
	
	// 검색 
	@RequestMapping(value="/notice/noticeSearch", method=RequestMethod.GET)
	public String noticeSearch(@ModelAttribute Search search, Model model,
											@RequestParam(value="page", required=false) Integer page) {
		// 2개의 값을 하나에 담아서 보내는 방법
		// 1. Domain(VO) 클래스 이용
		// 2. HashMap 사용하기
		int listCount = nService.getPageCount(search);
		int currentPage = (page != null) ? page : 1;
		PageInfo pi = Pagination10.getPageInfo(currentPage, listCount);
		ArrayList<Notice> searchList = nService.printSearchAll(pi, search);
		System.out.println(search.getSearchValue());
		System.out.println(search.getSearchCondition());
		if(!searchList.isEmpty()) {
			model.addAttribute("nList", searchList);
			model.addAttribute("pi", pi);
			model.addAttribute("search", search);
			return "notice/noticeListView";
		}else {
			// model.addAttribute("msg", "공지사항 검색 실패");
			return "notice/noData";
		}
	}
	
	// 메인 공지사항 설정 뷰  
	@RequestMapping(value="/notice/mainSelectView")
	public ModelAndView mainSelectView(ModelAndView mv, @RequestParam(value="page", required=false) Integer page) {
		Notice notice = new Notice();
		int listCount = nService.getListCount(notice);
		int currentPage = (page != null) ? page : 1;
		PageInfo pi = Pagination10.getPageInfo(currentPage, listCount);
		ArrayList<Notice> nList = nService.printAll(pi, notice);
		
		ArrayList<Notice> mainNotice = nService.printMainNotice(notice);
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, -2); // 2일간 보이게
		String nowDay = format.format(cal.getTime());
		
		if(!nList.isEmpty()) {
			mv.addObject("nList", nList);
			mv.addObject("pi", pi);
			mv.addObject("nowDay", nowDay);
			mv.addObject("mainNotice", mainNotice);
			mv.setViewName("notice/mainSelectView");
		}else {
			// mv.addObject("msg", "조회 실패");
			mv.setViewName("notice/noData");
		}
		return mv;
	}
	
	// 메인 공지사항 설정
	@RequestMapping(value="/notice/mainNoticeUpdate", method=RequestMethod.POST)
	public ModelAndView mainUpdate(ModelAndView mv, @ModelAttribute Notice notice, @RequestParam("noNo") int noNo) {
		// 전체 mainNotice = 0으로 만들어주기 
		nService.resetMainNotice(notice);
		// mainNotice = 1로 업데이트 
		int result = nService.updateMainNotice(noNo);
		
		if(result > 0) {
			mv.setViewName("redirect:/notice/noticeList");
		}else {
			mv.addObject("msg", "수정 실패").setViewName("common/errorPage");
		}
		return mv;
	}

	// 작성 뷰 
		@RequestMapping(value="/notice/noticeWriteView", method=RequestMethod.GET)
		public String noticeWriteView() {
			return "notice/noticeWriteForm";
		}
		
		// 등록 
		@RequestMapping(value="/notice/noticeWrite", method=RequestMethod.POST)
		public String registerNotice(@ModelAttribute Notice notice,
													@RequestParam(value="uploadFile", required=false) MultipartFile uploadFile,
													HttpServletRequest request, Model model) {
			if(!uploadFile.getOriginalFilename().equals("")) {
				String filePath = saveFile(uploadFile, request);
				if(filePath != null) {
					notice.setNoFileName(uploadFile.getOriginalFilename());
					notice.setNoReFileName(filePath);
				}
			}
			int result = 0;
			result = nService.registerNotice(notice);
			System.out.println(notice.toString());
			if(result > 0) {
				return "redirect:noticeList";
			}else {
				model.addAttribute("msg", "등록 실패");
				return "common/errorPage";
			}
		}
		
		// 파일 저장 
		public String saveFile(MultipartFile file, HttpServletRequest request) {
			// 파일 저장 경로 설정 
			String root = request.getSession().getServletContext().getRealPath("resources");
			String savePath = root + "/nuploadFiles";
			// 저장 폴더 선택 
			File folder = new File(savePath);
			// 폴더가 없으면 자동으로 생성 
			if(!folder.exists()) {
				folder.mkdir();
			}
			// 파일명 변경하기 
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			String originalFileName = file.getOriginalFilename();
			String renameFileName = sdf.format(new Date(System.currentTimeMillis())) + "." + originalFileName.substring(originalFileName.lastIndexOf(".")+1);
			String filePath = folder + "/" + renameFileName;
			
			// 파일 저장 
			try {
				file.transferTo(new File(filePath));
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return filePath;
		}
	
	// 수정 뷰 
	@RequestMapping(value="/notice/noticeModifyView", method=RequestMethod.GET)
	public ModelAndView noticeModifyView(ModelAndView mv, @RequestParam("noNo") int noNo) {
		Notice notice = nService.printOne(noNo);
		if(notice != null) {
			mv.addObject("notice", notice).setViewName("notice/noticeUpdateView");
		}else {
			mv.addObject("msg", "조회 실패").setViewName("common/errorPage");
		}
		return mv;
	}
	
	// 수정 
	@RequestMapping(value="/notice/noticeUpdate", method=RequestMethod.POST)
	public ModelAndView noticeUpdate(ModelAndView mv, @ModelAttribute Notice notice, HttpServletRequest request, @RequestParam(value="reloadFile", required=false) MultipartFile reloadFile) {
		// 파일 삭제 후 업로드 (수정)
		if(reloadFile != null && !reloadFile.isEmpty()) {
			// 기존 파일 삭제 
			if(notice.getNoFileName() != "") {
				deleteFile(notice.getNoReFileName(), request);
			}
			// 새 파일 업로드 
			String renameFileName = saveFile(reloadFile, request);
			if(renameFileName != null) {
				notice.setNoFileName(reloadFile.getOriginalFilename());
				notice.setNoReFileName(renameFileName);
			}
		}
		// DB 수정 
		int result = nService.modifyNotice(notice);
		System.out.println(notice.toString());
		if(result > 0) {
			mv.setViewName("redirect:/notice/noticeList");
		}else {
			mv.addObject("msg", "수정 실패").setViewName("common/errorPage");
		}
		return mv;
	}
	
	// 삭제
	@RequestMapping(value="/notice/noticeDelete", method=RequestMethod.GET)
	public String noticeDelete(@RequestParam("noNo") int noNo, 
											@RequestParam(value="noReFileName", required=false) String noReFileName, 
											Model model, HttpServletRequest request) {
		// 업로드된 파일 삭제 
		if(noReFileName != null) {
			deleteFile(noReFileName, request);
		}
		
		//디비에 데이터 업데이트 
		int result = nService.removeNotice(noNo);
		if(result > 0) {
			return "redirect:noticeList";
		}else {
			model.addAttribute("msg", "삭제 실패");
			return "common/errorPage";
		}
	}
	
	// 저장되어있는 파일 삭제 
	public void deleteFile(String fileName, HttpServletRequest request) {
		String root = request.getSession().getServletContext().getRealPath("resources");
		String savePath = root + "/nuploadFiles";
		File file = new File(savePath + "/" + fileName);
		if(file.exists()) {
			file.delete();
		}
	}
	
	// 댓글 목록 
	@RequestMapping(value="/notice/ReplyList")
	public void getReplyList(HttpServletResponse response, @RequestParam("noMotherNo") int noMotherNo, @RequestParam(value="page", required=false) Integer page) throws Exception {
		Notice notice = new Notice();
		notice.setNoMotherNo(noMotherNo);
		int listCount = nService.getListCount(notice);
		
		int currentPage = (page != null) ? page : 1;
		PageInfo pi = Pagination10.getPageInfo(currentPage, listCount);
		ArrayList<Notice> rList = nService.printAllReply(pi, noMotherNo);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("page", pi);
		map.put("rList", rList);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		gson.toJson(map, response.getWriter());
	}

	// 댓글 하나 
	@RequestMapping(value="/notice/ReplyOne")
	public void getReplyOne(HttpServletResponse response, @RequestParam("noMotherNo") int noMotherNo) throws Exception {
		Notice nOne = nService.printOneReply(noMotherNo);
		// 댓글 총 갯수
		Notice notice = new Notice();
		notice.setNoMotherNo(noMotherNo);
		int count = nService.getListCount(notice);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("nOne", nOne);
		map.put("count", count);
		// map.put("update", update);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		gson.toJson(map, response.getWriter());
	}
	// 댓글 작성 
	@ResponseBody
	@RequestMapping(value="/notice/addReply", method=RequestMethod.POST)
	public String registerReply(@ModelAttribute Notice notice, @RequestParam("noMotherNo") int noMotherNo, HttpSession session) {
		// 세션 추가하기 
		//Member loginUser = (Member)session.getAttribute("loginUser");
		//notice.setReplyWriter(loginUser.getUserId());
		notice.setStNo(1);
		
		int result = nService.registerReply(notice);
		int update = nService.updateReplyCount(noMotherNo); // 댓글 수 업데이트
		if(result > 0 && update > 0) {
			return "success";
		}else {
			return "fail"; 
		}
	}
	@RequestMapping(value="/notice/modifyReply", method=RequestMethod.POST)
	public String updateReply(@ModelAttribute Notice notice) {
		int result = nService.modifyReply(notice);
		if(result > 0) {
			return "success";
		}else {
			return "fail";
		}
	}
	
	// 댓글 삭제 
	@ResponseBody
	@RequestMapping(value="/notice/deleteReply", method=RequestMethod.GET)
	public String removeReply(@ModelAttribute Notice notice) {
		int result = nService.removeReply(notice);
		if(result > 0) {
			return "success";
		}else {
			return "fail";
		}
	}
	
	
}
