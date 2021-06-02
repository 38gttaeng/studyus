package com.studyus.board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
import com.studyus.assignment.domain.Assignment;
import com.studyus.board.domain.Board;
import com.studyus.board.service.BoardService;
import com.studyus.common.PageInfo;
import com.studyus.common.Pagination5;
import com.studyus.common.RedirectWithMsg;
import com.studyus.file.controller.FileController;
import com.studyus.file.domain.FileVO;
import com.studyus.file.service.FileService;
import com.studyus.member.domain.Member;
import com.studyus.study.domain.Study;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boService;
	
	@Autowired
	private FileService fiService;
	
	@Autowired
	private FileController fiController;
	
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
	
	// 리스트 무한 스크롤
	@RequestMapping(value="/study/boardScroll", method=RequestMethod.GET)
	public void boardListScroll(HttpSession session, HttpServletResponse response, @RequestParam("page") int page) throws Exception {
		
		int stNo = ((Study)session.getAttribute("study")).getStudyNo();
		
		Board board = new Board();
		board.setStNo(stNo);
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
	@RequestMapping(value="/study/board/search", method=RequestMethod.GET)
	public void boardSearchView(HttpSession session, HttpServletResponse response, @RequestParam("page") int page,
			@RequestParam("searchValue") String searchValue, @RequestParam("searchCondition") String searchCondition) throws Exception {
		
		int stNo = ((Study)session.getAttribute("study")).getStudyNo();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("stNo", stNo);
		map.put("boCategory", (Integer) session.getAttribute("category"));
		map.put("searchValue", searchValue);
		map.put("searchCondition", searchCondition);
		
		int listCount = boService.getSearchCount(map);
		PageInfo pi = Pagination5.getPageInfo(page, listCount);
		ArrayList<Board> searchList = boService.printSearchAll(pi, map);
		
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("searchList", searchList);
		data.put("maxPage", pi.getMaxPage());
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		gson.toJson(data, response.getWriter());
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
	public String boardRegister(HttpServletRequest request, @ModelAttribute Board board, 
			@RequestParam(value="fList", required=false) List<MultipartFile> fList,
			@RequestParam(value="picList", required=false) List<String> picList) {
		
		// 텍스트 에디터 사진 처리
		fiController.addImages("\\buploadImages", board.getBoContents(), picList, request);
		
		// 실제 파일 저장
		ArrayList<FileVO> boFiles = null;
		if(fList != null && !fList.isEmpty()) {
        	boFiles = fiController.saveFile(fList, 5, request);
        }
		
		HttpSession session = request.getSession();
		int mbNo = ((Member)session.getAttribute("loginUser")).getMbNo();
		int stNo = ((Study)session.getAttribute("study")).getStudyNo();
		board.setStNo(stNo);
		
		// Board DB 저장
		int boNo = boService.registerBoard(board);
		if(boNo > 0) {
			// File DB 저장
			int fiResult = 0;
			if(boFiles != null) {
				for(FileVO file : boFiles) {
					file.setMbNo(mbNo);
					file.setFiMotherNo(boNo);
					
					fiResult = fiService.uploadFile(file);
				}
			} else {
				fiResult = 1;
			}
			
			if(fiResult > 0) {
				return new RedirectWithMsg().redirect(request, "게시글이 등록되었습니다!", "/study/board/detail?boNo=" + boNo);
			} else {
				return new RedirectWithMsg().redirect(request, "파일 등록 실패!!!!", "/study/board?boCategory=" + (Integer)session.getAttribute("category"));
			}
		} else {
			return new RedirectWithMsg().redirect(request, "게시글 등록 실패!!!!", "/study/board?boCategory=" + (Integer)session.getAttribute("category"));
		}
		
	}

	// 수정
	@RequestMapping(value="/study/board/modifyView")
	public ModelAndView boardModifyView(ModelAndView mv, @RequestParam("boNo") int boNo) {
		Board board = boService.printOne(boNo);
		if(board != null) {
			mv.addObject("board", board).setViewName("study/boardModify");
		} else {
			System.out.println("게시글 수정 페이지로 이동 실패(게시물 디테일 조회 실패)");
		}
		
		return mv;
	}
	
	@RequestMapping(value="/study/board/modify", method=RequestMethod.POST)
	public ModelAndView boardUpdate(HttpServletRequest request, ModelAndView mv, @ModelAttribute Board board, 
			@RequestParam(value="fList", required=false) List<MultipartFile> fList,
			@RequestParam(value="delFiles", required=false) List<String> delFiles,
			@RequestParam(value="picList", required=false) List<String> picList) {
		
		// 텍스트 에디터 사진 처리
		fiController.addImages("\\buploadImages", board.getBoContents(), picList, request);
		Board oldBoard = boService.printOne(board.getBoNo());
		fiController.editImages("\\buploadImages", board.getBoContents(), oldBoard.getBoContents(), request);

		// 기존 파일 삭제
		if(delFiles != null && !delFiles.isEmpty()) {
			for(int i=0; i<delFiles.size(); i++) {
				FileVO file = fiService.selectOne(Integer.parseInt(delFiles.get(i)));
				fiController.deleteFile("\\buploadFiles", file.getFiStoredName(), request);
				fiService.removeFileByFiNo(file.getFiNo());
			}
		}
		
		// 새파일 업로드
		ArrayList<FileVO> boFiles = null;
		if(fList != null && !fList.isEmpty()) {
			boFiles = fiController.saveFile(fList, 5, request);
		}
		
		// Board DB 수정
		int boResult = boService.modifyBoard(board);
		if(boResult > 0) {
			// 새파일 File DB 저장
			int fiResult = 0;
			if(boFiles != null) {
				for(FileVO file : boFiles) {
					file.setMbNo(board.getMbNo());
					file.setFiMotherNo(board.getBoNo());
					
					fiResult = fiService.uploadFile(file);
				}
			} else {
				fiResult = 1;
			}
			
			if(fiResult > 0) {
				mv.addObject("board", board).setViewName("redirect:/study/board/detail?boNo=" + board.getBoNo());
			} else {
				mv.addObject("msg", "파일 수정 오류!").setViewName("common/errorPage");
			}
		} else {
			mv.addObject("msg", "게시물 수정 오류!").setViewName("common/errorPage");
		}
			
		return mv;
	}
	
	// 삭제
	@RequestMapping(value="/study/board/delete", method=RequestMethod.GET)
	public String boardDelete(HttpServletRequest request, @RequestParam("boNo") int boNo) {
		
		HttpSession session = request.getSession();
		
		// 텍스트 에디터 이미지 삭제
		fiController.deleteImages("\\buploadImages", boService.printOne(boNo).getBoContents(), request);
		
		// 파일 삭제
		int fiResult = 0;
		FileVO fileVO = new FileVO(5, boNo);
		ArrayList<FileVO> boFiles = fiService.selectList(fileVO); 
		if(!boFiles.isEmpty()) {
			String folder = "\\buploadFiles";
			for(FileVO file : boFiles) {
				fiController.deleteFile(folder, file.getFiStoredName(), request);
			}
			
			fiResult = fiService.removeFile(fileVO);
		} else {
			fiResult = 1;
		}
		
		int boResult = 0;
		if(fiResult > 0) {
			// 댓글과 게시물 삭제
			boResult = boService.removeBoard(boNo);
			if(boResult > 0) {
				// 댓글은 있을수도 없을수도 있기 때문에 0 이상
				return new RedirectWithMsg().redirect(request, "게시글이 삭제되었습니다!", "/study/board?boCategory=" + (Integer)session.getAttribute("category"));
			} else {
				return new RedirectWithMsg().redirect(request, "게시글 삭제 실패!", "/study/board?boCategory=" + (Integer)session.getAttribute("category"));
			}
		} else {
			return new RedirectWithMsg().redirect(request, "파일 삭제 실패!", "/study/board?boCategory=" + (Integer)session.getAttribute("category"));
		}
	}
	
	/******************* 댓글 등록, 수정, 삭제 *******************/
	
	// 등록
	@ResponseBody
	@RequestMapping(value="/study/board/addReply", method=RequestMethod.POST)
	public String replyRegister(HttpSession session, @ModelAttribute Board board,
			@RequestParam(value="picList", required=false) List<String> picList) {
		
		int stNo = ((Study)session.getAttribute("study")).getStudyNo();
		board.setStNo(stNo);
		
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
	
	/******************* 게시물 관리 *******************/

	// 게시물 목록 페이지
	@RequestMapping(value="/study/contentsList", method=RequestMethod.GET)
	public String contentsListView() {
		return "study/contentsList";
	}
	
	// 게시물 리스트
	@RequestMapping(value="/study/contentsList/board", method=RequestMethod.GET)
	public void contentsView(HttpSession session, HttpServletResponse response, @RequestParam("user") String user) throws Exception {
		
		int stNo = ((Study)session.getAttribute("study")).getStudyNo();
		
		ArrayList<Board> data = null;
		if(user.equals("l")) {
			data = boService.printAllByStNo(stNo);
		} else if(user.equals("m")) {
			Board board = new Board();
			int mbNo = ((Member)session.getAttribute("loginUser")).getMbNo();
			board.setStNo(stNo);
			board.setMbNo(mbNo);
			data = boService.printAllByMemberNo(board);
		}
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		gson.toJson(data, response.getWriter());
	}
	
	// 댓글 목록 페이지
	@RequestMapping(value="/study/commentsList", method=RequestMethod.GET)
	public String commentsListView() {
		return "study/commentsList";
	}
	
	// 댓글 리스트
	@RequestMapping(value="/study/commentsList/board", method=RequestMethod.GET)
	public void myReplyListView(HttpSession session, HttpServletResponse response, @RequestParam("user") String user) throws Exception {
		
		int stNo = ((Study)session.getAttribute("study")).getStudyNo();
		
		ArrayList<Board> data = null;
		if(user.equals("l")) {
			data = boService.printAllReplyByStNo(stNo);
		} else if(user.equals("m")) {
			Board board = new Board();
			int mbNo = ((Member)session.getAttribute("loginUser")).getMbNo();
			board.setStNo(stNo);
			board.setMbNo(mbNo);
			data = boService.printAllReplyByMemberNo(board);
		}
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		gson.toJson(data, response.getWriter());
	}
	
	// 게시물 댓글 일괄 삭제
	@ResponseBody
	@RequestMapping(value="/study/contentsList/delete-board", method=RequestMethod.GET)
	public String boardListDelete(@RequestParam("deList") List<Integer> deList) {
		
		int result = 0;
		for(int delNo : deList) {
			result += boService.removeBoard(delNo);
		}
		
		if(result == deList.size()) {
			return "success";
		} else {
			return "error";
		}
	}
	
}

