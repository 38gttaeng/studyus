package com.studyus.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.studyus.board.domain.Board;
import com.studyus.board.domain.Search;
import com.studyus.board.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boService;
	
	/////////////////// 게시물 보기 ///////////////////
	
	// 리스트
	public ModelAndView boardListView(HttpSession session, ModelAndView mv, @RequestParam("boCategory") int boCategory, @RequestParam(value="page", required=false) Integer page) {
		return null;
	}
	
	// 검색
	public ModelAndView boardSearch(HttpSession session, ModelAndView mv, @ModelAttribute Search search, @RequestParam("boCategory") int boCategory) {
		return null;
	}
	
	// 디테일
	public ModelAndView boardDetail(HttpSession session, ModelAndView mv, @RequestParam("boNo") int boNo) {
		return null;
	}
	
	// 댓글 리스트
	public void getReplyList(HttpServletResponse response, @RequestParam("boNo") int boNo) {

	}
	
	/////////////////// 게시물 등록, 수정, 삭제 ///////////////////
	
	// 등록
	public String boardWriteView() {
		return null;
	}
	
	public ModelAndView boardRegister(HttpServletRequest request, ModelAndView mv, @ModelAttribute Board board, @RequestParam(value="uploadFile", required=false) MultipartFile uploadFile) {
		return null;
	}

	public String saveFile(MultipartFile file, HttpServletRequest request) {
		return null;
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
	public String replyRegister(HttpSession session, @ModelAttribute Board board) {
		return null;
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

