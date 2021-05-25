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
import com.studyus.board.domain.Board;
import com.studyus.board.domain.Search;
import com.studyus.board.service.BoardService;
import com.studyus.common.PageInfo;
import com.studyus.common.Pagination5;
import com.studyus.common.RedirectWithMsg;
import com.studyus.file.domain.FileVO;
import com.studyus.file.service.FileService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boService;
	
	@Autowired
	private FileService fiService;
	
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
		HttpSession session = request.getSession();
//		세션에서 스터디 번호 가져오기
		board.setStNo(1);
		
		// 서버에 파일을 저장하는 작업
		int fiResult = 1;
		if(!uploadFile.getOriginalFilename().equals("")) {
			FileVO fileVO = saveFile(uploadFile, request);
			if(fileVO.getFiStoredName() != null) {
				// 파일 테이블에 파일정보 저장
				FileVO file = new FileVO(board.getMbNo(), uploadFile.getOriginalFilename(), fileVO.getFiStoredName(), fileVO.getFiDirectory());
				fiResult = fiService.uploadFile(file);
				// board에 파일이름 저장
				board.setBoFileName(fileVO.getFiStoredName());
			}
		}
		
		int boResult = 0;
		if(fiResult > 0) {
			boResult = boService.registerBoard(board);
			
			if(boResult > 0) {
				return new RedirectWithMsg().redirect(request, "게시글이 등록되었습니다!", "/study/board?boCategory=" + (Integer)session.getAttribute("category"));
			} else {
				return new RedirectWithMsg().redirect(request, "게시글 등록 실패!!!!", "/study/board?boCategory=" + (Integer)session.getAttribute("category"));
			}
		} else {
			return new RedirectWithMsg().redirect(request, "파일 등록 실패!!!!", "/study/board?boCategory=" + (Integer)session.getAttribute("category"));
		}
	}

	public FileVO saveFile(MultipartFile file, HttpServletRequest request) {
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
		
		// 새로운 파일이름과 경로 리턴
		FileVO fileVO = new FileVO();
		fileVO.setFiStoredName(renameFilename);
		fileVO.setFiDirectory(filePath);
		return fileVO;
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
	public ModelAndView boardUpdate(HttpServletRequest request, ModelAndView mv, @ModelAttribute Board board, @RequestParam("reloadFile") MultipartFile reloadFile) {
		int fiResult = 0;
		
		if(reloadFile != null && !reloadFile.isEmpty()) {
			// 새 파일 업로드
			FileVO fileVO = saveFile(reloadFile, request);
			
			if(!board.getBoFileName().equals("")) {
				// 1. 파일 수정 
				deleteFile(board.getBoFileName(), request); // 기존 파일 삭제
				int fiNo = fiService.selectOne(board.getBoFileName()); // 기존 파일 저장 번호 알아오기
				FileVO file = new FileVO(fiNo, reloadFile.getOriginalFilename(), fileVO.getFiStoredName(), fileVO.getFiDirectory());
				fiResult = fiService.modifyFile(file);
			} else {
				// 2. 파일 추가
				FileVO file = new FileVO(board.getMbNo(), reloadFile.getOriginalFilename(), fileVO.getFiStoredName(), fileVO.getFiDirectory());
				fiResult = fiService.uploadFile(file);				
			}
			
			board.setBoFileName(fileVO.getFiStoredName());
		} else {
			// 3. 파일 삭제
			if(!board.getBoFileName().equals("")) {
				deleteFile(board.getBoFileName(), request); // 기존 파일 삭제
				fiResult = fiService.removeFile(board.getBoFileName()); // File DB에서 삭제
				
				board.setBoFileName("");
			} else {
				fiResult = 1;
			}
		}
		
		// 파일이 잘 수정되었다면 DB 수정
		int result = 0;
		if(fiResult > 0) {
			result = boService.modifyBoard(board);
			if(result > 0) {
				mv.addObject("board", board).setViewName("redirect:/study/board/detail?boNo=" + board.getBoNo());
			} else {
				mv.addObject("msg", "게시물 수정 오류!").setViewName("common/errorPage");
			}
		} else {
			mv.addObject("msg", "파일 수정 오류!").setViewName("common/errorPage");
		}
		
		return mv;
	}
	
	// 삭제
	@RequestMapping(value="/study/board/delete", method=RequestMethod.GET)
	public String boardDelete(HttpServletRequest request, @ModelAttribute Board board) {
		
		HttpSession session = request.getSession();
		
		// 파일 삭제
		int fiResult = 0;
		if(!board.getBoFileName().equals("")) {
			deleteFile(board.getBoFileName(), request);
			fiResult = fiService.removeFile(board.getBoFileName());
		} else {
			fiResult = 1;
		}
		
		int boResult = 0;
		if(fiResult > 0) {
			// 댓글과 게시물 삭제
			boResult = boService.removeBoard(board.getBoNo());
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
	
	public void deleteFile(String fileName, HttpServletRequest request) {
		// 실제 파일 경로를 만들어서 실제 파일 삭제
		String root = request.getSession().getServletContext().getRealPath("resources");
		String savePath = root + "\\buploadFiles";
		File file = new File(savePath + "\\" + fileName);
		if(file.exists()) {
			file.delete();
		}
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

