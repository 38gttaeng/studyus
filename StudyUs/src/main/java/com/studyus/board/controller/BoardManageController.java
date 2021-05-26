package com.studyus.board.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.studyus.board.service.BoardService;

@Controller
public class BoardManageController {
	
	@Autowired
	private BoardService bService;
	
	/////////////////// 게시물 관리 페이지 ///////////////////
		// 팀장이면 jsp에서 검색 option에 작성자 추가
		// 세션에서 정보를 받아서 팀장이냐 팀원이냐에 따라 service의 다른 메소드 호출하도록
	
	// 페이지 들어가기
	public String leaderBoardView() {
		return null;
	}
	
	// 게시물 리스트
	public void myBoardListView(HttpSession session, HttpServletResponse response, @RequestParam(value="page", required=false) Integer page) {
	
	}
	
	// 게시물 검색
	public void myBoardSearch(HttpSession session, HttpServletResponse response, HashMap<String, Object> map) {
	
	}
	
	// 게시물 일괄 삭제
	public String boardListDelete(HttpSession session, HttpServletResponse response, @RequestParam("boardList") int [] boardList) {
		return null;
	}
	
	// 댓글 리스트
	public void myReplyListView(HttpSession session, HttpServletResponse response, @RequestParam(value="page", required=false) Integer page) {
	
	}
	
	// 댓글 검색
	public void myReplySearch(HttpSession session, HttpServletResponse response, HashMap<String, Object> map) {
	
	}
	
	// 댓글 일괄 삭제
	public String replyListDelete(HttpSession session, HttpServletResponse response, @RequestParam("replyList") int [] boardList) {
		return null;
	}

}
