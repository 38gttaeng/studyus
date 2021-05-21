package com.studyus.board.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
import com.studyus.common.RedirectWithMsg;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boService;
	
	/////////////////// 게시물 보기 ///////////////////
	
	// 리스트
	@RequestMapping(value="/study/board", method=RequestMethod.GET)
	public String boardListView() {
		// ModelAndView로 변경
		// 파라미터 : HttpSession session, ModelAndView mv, @RequestParam("boCategory") int boCategory, @RequestParam(value="page", required=false) Integer page
		
		// 카테고리에 해당하는거 전부 가져와서
		// 원글번호가 null인 것 게시물로 담고
		// 그 게시물에 해당하는 댓글 하나만 담아서 -> HashMap 형태로 전송
		return "study/boardList";
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
	public void getReplyList(HttpServletResponse response, @RequestParam("boMotherNo") int boMotherNo) throws IOException {
		ArrayList<Board> rList = boService.printAllReply(boMotherNo);
		
		if(!rList.isEmpty()) {
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			gson.toJson(rList, response.getWriter());
		}
	}
	
	/////////////////// 게시물 등록, 수정, 삭제 ///////////////////
	
	// 등록
	@RequestMapping(value="/study/board/registerView", method=RequestMethod.GET)
	public String boardWriteView() {
		return "/study/boardRegister";
	}
	
	@RequestMapping(value="/study/board/register", method=RequestMethod.POST)
	public String boardRegister(HttpServletRequest request, Model model, @ModelAttribute Board board, @RequestParam(value="uploadFile", required=false) MultipartFile uploadFile) {
		//////////////////////////////////////////////
//		HttpSession session = request.getSession();
//		(Member)session.getAttribute("member").get어쩌구..해서 세션에서 스터디 번호랑 아이디 글쓴 사람 번호 가져오기
		
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
			return new RedirectWithMsg().redirect(request, "게시글이 등록되었습니다!", "/study/board");
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
	
	/////////////////// 댓글 등록, 수정, 삭제 ///////////////////
	
	// 등록
	@ResponseBody
	@RequestMapping(value="/study/board/addReply", method=RequestMethod.POST)
	public String replyRegister(HttpSession session, @ModelAttribute Board board) {
		/////////////////////////////
		// 세션에서 멤버정보 + 스터디정보 가져와서 넣어주기
		board.setMbNo(1);
		board.setStNo(1);
		
		int result = boService.registerBoard(board);
		if(result > 0) {
			return "success";
		} else {
			return "fail";
		}
	}
	
	// 수정
	public void replyModifyView(@RequestParam("boNo") int boNo) {

	}
	
	public String replyUpdate(@ModelAttribute Board board) {
		return null;
	}
	
	// 삭제
	public String replyDelete(@RequestParam("boNo") int boNo) {
		return null;
	}
	
}

