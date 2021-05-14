package com.studyus.board.controller;

import javax.servlet.http.HttpServletRequest;

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
	private BoardService service;
	
	/////////////////// 게시물 보기 ///////////////////
	
	// 리스트
	public ModelAndView boardListView(HttpServletRequest request, ModelAndView mv, @RequestParam("boCategory") int boCategory, @RequestParam(value="page", required=false) Integer page) {
		return null;
	}
	
	// 검색
	public ModelAndView boardSearch(HttpServletRequest request, ModelAndView mv, @ModelAttribute Search search, @RequestParam("boCategory") int boCategory) {
		return null;
	}
	
	// 디테일
	public ModelAndView boardDetail(HttpServletRequest request, ModelAndView mv, @RequestParam("boNo") int boNo) {
		return null;
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
	public String boardDelete(HttpServletRequest request, ModelAndView mv, @RequestParam("boNo") int noticeNo) {
		return null;
	}
	
	public String deleteFile(String fileName, HttpServletRequest request) {
		return null;
	}
	
	/////////////////// 댓글 등록, 수정, 삭제 ///////////////////
	
	// 등록
	public ModelAndView replyRegister(HttpServletRequest request, ModelAndView mv, @ModelAttribute Board board) {
		return null;
	}
	
	// 수정
	public String replyModifyView(@RequestParam("boNo") int boNo, Model model) {
		return null;
	}
	
	public ModelAndView replyUpdate(HttpServletRequest request, ModelAndView mv, @ModelAttribute Board board) {
		return null;
	}
	
	// 삭제
	public String replyDelete(HttpServletRequest request, ModelAndView mv, @RequestParam("boNo") int noticeNo) {
		return null;
	}
	
	/////////////////// 팀장 보기 ///////////////////
	
	// 페이지 들어가기
	public String leaderBoardView() {
		return null;
	}
	
	// 게시물 리스트
	public ModelAndView leaderBoardListView(HttpServletRequest request, ModelAndView mv, @RequestParam(value="page", required=false) Integer page) {
		return null;
	}
	
	// 게시물 검색
	public ModelAndView leaderBoardSearch(HttpServletRequest request, ModelAndView mv, @ModelAttribute Search search) {
		return null;
	}
	
	// 댓글 리스트
	public ModelAndView leaderReplyListView(HttpServletRequest request, ModelAndView mv, @RequestParam(value="page", required=false) Integer page) {
		return null;
	}
	
	// 댓글 검색
	public ModelAndView leaderReplySearch(HttpServletRequest request, ModelAndView mv, @ModelAttribute Search search) {
		return null;
	}
	
	/////////////////// 팀원 보기 ///////////////////
	
	// 페이지 들어가기
	public String myBoardView() {
		return null;
	}
	
	// 게시물 리스트
	public ModelAndView myBoardListView(HttpServletRequest request, ModelAndView mv, @RequestParam(value="page", required=false) Integer page) {
		return null;
	}
	
	// 게시물 검색
	public ModelAndView myBoardSearch(HttpServletRequest request, ModelAndView mv, @ModelAttribute Search search) {
		return null;
	}
	
	// 댓글 리스트
	public ModelAndView myReplyListView(HttpServletRequest request, ModelAndView mv, @RequestParam(value="page", required=false) Integer page) {
		return null;
	}
	
	// 댓글 검색
	public ModelAndView myReplySearch(HttpServletRequest request, ModelAndView mv, @ModelAttribute Search search) {
		return null;
	}
}
