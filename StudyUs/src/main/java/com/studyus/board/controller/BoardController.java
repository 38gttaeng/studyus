package com.studyus.board.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.google.gson.JsonIOException;
import com.studyus.board.domain.Board;
import com.studyus.board.domain.Search;
import com.studyus.board.service.BoardService;
import com.studyus.common.PageInfo;
import com.studyus.common.Pagination5;
import com.studyus.common.RedirectWithMsg;
import com.studyus.member.domain.Member;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boService;
	
	/******************* 게시물 보기 *******************/
	
	// 리스트
	@RequestMapping(value="/study/board", method=RequestMethod.GET)
	public String boardListView(HttpSession session, @RequestParam(value="boCategory", required=false) Integer boCategory){
		
		// 메뉴바에서 해당 카테고리를 선택한 경우
		if(boCategory != null) {
			// 세션에 선택한 카테고리 등록 (이전에 등록된 정보는 삭제)
			if(session.getAttribute("category") != null) {
				session.removeAttribute("category");
			}
			session.setAttribute("category", boCategory);
		}
		
		return "study/boardList";
	}
	
	// 무한 스크롤
	@RequestMapping(value="/study/boardScroll", method=RequestMethod.GET)
	public void boardListScroll(HttpSession session, HttpServletResponse response, @RequestParam("page") int page) throws Exception {
		
		//////////////////////////////////세션에서 스터디번호 가져와서 넣어주기
		Board board = new Board();
		board.setStNo(1);///////////////////////
		board.setBoCategory((Integer) session.getAttribute("category"));
		
		int listCount = boService.getListCount(board);
		PageInfo pi = Pagination5.getPageInfo(page, listCount);
		
		ArrayList<Board> bList = boService.printAll(pi, board);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("boardList", bList);
		map.put("maxPage", pi.getMaxPage());
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		gson.toJson(map, response.getWriter());
	}
	
	// 검색
	public ModelAndView boardSearch(HttpSession session, ModelAndView mv, @ModelAttribute Search search, @RequestParam("boCategory") int boCategory) {
		return null;
	}
	
	// 디테일
	@RequestMapping(value="/study/board/detail", method=RequestMethod.GET)
	public ModelAndView boardDetail(HttpSession session, ModelAndView mv, @RequestParam("boNo") int boNo) {
		
		Board board = boService.printOne(boNo);
		if(board != null) {
			mv.addObject("board", board).setViewName("study/boardDetail");
		} else {
			System.out.println("게시글 디테일 조회 실패");
		}
		
		return mv;
	}
	
	// 댓글 리스트
	@RequestMapping(value="/study/board/replyList", method=RequestMethod.GET)
	public void getReplyList(HttpServletResponse response, @RequestParam("boMotherNo") int boMotherNo, @RequestParam(value="page", required=false) Integer page) throws IOException {
		
		Board board = new Board();
		board.setBoMotherNo(boMotherNo);
		int listCount = boService.getListCount(board);
		
		int currentPage = (page != null) ? page : 1;
		PageInfo pi = Pagination5.getPageInfo(currentPage, listCount);
		ArrayList<Board> rList = boService.printAllReply(pi, boMotherNo);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("page", pi);
		map.put("rList", rList);
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		gson.toJson(map, response.getWriter());
	}
	
	// 댓글 하나
	@RequestMapping(value="/study/board/replyOne", method=RequestMethod.GET)
	public void getReplyOne(HttpServletResponse response, @RequestParam("boMotherNo") int boMotherNo) throws IOException {
		// 댓글 하나
		Board bOne = boService.printOneReply(boMotherNo);
		// 댓글 총 개수
		Board board = new Board();
		board.setBoMotherNo(boMotherNo);
		int count = boService.getListCount(board);
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("bOne", bOne);
		map.put("count", count);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		gson.toJson(map, response.getWriter());
	}
	
	/******************* 게시물 등록, 수정, 삭제 *******************/
	
	// 등록
	@RequestMapping(value="/study/board/registerView", method=RequestMethod.GET)
	public String boardWriteView() {
		return "/study/boardRegister";
	}
	
	@RequestMapping(value="/study/board/register", method=RequestMethod.POST)
	public String boardRegister(HttpServletRequest request, Model model, @ModelAttribute Board board, @RequestParam(value="uploadFile", required=false) MultipartFile uploadFile) {
		//////////////////////////////////////////////
//		HttpSession session = request.getSession();
//		세션에서 스터디 번호 가져오기
		
		// 서버에 파일을 저장하는 작업
		if(!uploadFile.getOriginalFilename().equals("")) {
			String renameFilename = saveFile(uploadFile, request);
			if(renameFilename != null) {
				// 파일 테이블에 파일정보 저장 //////////////////////////////////////////////
				
				// board에 파일이름 저장
				board.setBoFileName(renameFilename);
			}
		}
		
		// DB에 데이터를 저장하는 작업
		int result = 0;
		
		/////////////////////////////////////// 세션에서 가져온 값을 추가로 저장해주기
		board.setStNo(1);
		
		result = boService.registerBoard(board);
		if(result > 0) {
			return new RedirectWithMsg().redirect(request, "게시글이 등록되었습니다!", "/study/board?boCategory=0");
		} else {
			return new RedirectWithMsg().redirect(request, "게시글 등록 실패!!!!", "/study/board");
		}
		
	}

	public String saveFile(MultipartFile file, HttpServletRequest request) {
		// 파일 저장경로 설정
		String savePath = request.getSession().getServletContext().getRealPath("resources") + "\\buploadFiles";
		
		// 저장폴더 선택
		File folder = new File(savePath);
		
		// 폴더가 없을 경우 자동 생성 (한번만 만들면 됨!)
		if(!folder.exists()) {
			folder.mkdir();
		}
		
		// 파일명 변경하기
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmSS");
		String originalFilename = file.getOriginalFilename();
		String renameFilename = sdf.format(new Date(System.currentTimeMillis())) + "." + originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
		
		String filePath = folder + "\\" + renameFilename;
		
		// 파일 저장
		try {
			file.transferTo(new File(filePath));
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// 파일이름 리턴
		return renameFilename;
	}
	
	// 수정
	public String boardModifyView(@RequestParam("boNo") int boNo, Model model) {
		return null;
	}
	
	public ModelAndView boardUpdate(HttpServletRequest request, ModelAndView mv, @ModelAttribute Board board, @RequestParam("reloadFile") MultipartFile reloadFile) {
		return null;
	}
	
	// 삭제
	public ModelAndView boardDelete(HttpServletRequest request, ModelAndView mv, @RequestParam("boNo") int noticeNo) {
		return null;
	}
	
	public void deleteFile(String fileName, HttpServletRequest request) {

	}
	
	/******************* 댓글 등록, 수정, 삭제 *******************/
	
	// 등록
	@ResponseBody
	@RequestMapping(value="/study/board/addReply", method=RequestMethod.POST)
	public String replyRegister(HttpSession session, @ModelAttribute Board board) {
		/////////////////////////////
		// 세션에서 스터디정보 가져와서 넣어주기
		board.setStNo(1);
		
		int result = boService.registerBoard(board);
		if(result > 0) {
			return "success";
		} else {
			return "fail";
		}
	}
	
	// 수정
	@ResponseBody
	@RequestMapping(value="/study/board/modifyReply", method=RequestMethod.POST)
	public String replyUpdate(@ModelAttribute Board board) {
		int result = boService.modifyBoard(board);
		if(result > 0) {
			return "success";
		} else {
			return "fail";
		}
	}
	
	// 삭제
	@ResponseBody
	@RequestMapping(value="/study/board/deleteReply", method=RequestMethod.GET)
	public String replyDelete(@RequestParam("boNo") int boNo) {
		int result = boService.removeBoard(boNo);
		if(result > 0) {
			return "success";
		} else {
			return "fail";
		}
	}
	
}

