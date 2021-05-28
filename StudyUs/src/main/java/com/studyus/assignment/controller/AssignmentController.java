package com.studyus.assignment.controller;

import java.io.IOException;
import java.util.ArrayList;

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
import com.studyus.assignment.domain.Assignment;
import com.studyus.assignment.domain.AssignmentGroup;
import com.studyus.assignment.service.AssignmentService;
import com.studyus.common.PageInfo;
import com.studyus.common.Pagination5;
import com.studyus.common.RedirectWithMsg;
import com.studyus.study.domain.Study;
import com.studyus.submittedAssignment.service.SAssignmentService;

@Controller
public class AssignmentController {
	
	@Autowired
	private AssignmentService asService;
	
	@Autowired
	private SAssignmentService suService;
	
	/******************* 그룹에 따른 리스트 보기 *******************/
	
	// 리스트
	@RequestMapping(value="/study/assignment/groupList", method=RequestMethod.GET)
	public void groupListView(HttpSession session, HttpServletResponse response, @RequestParam("grStatus") int grStatus) throws IOException {
		int stNo = ((Study)session.getAttribute("study")).getStudyNo();
		AssignmentGroup asGroup = new AssignmentGroup();
		asGroup.setStNo(stNo);
		asGroup.setGrStatus(grStatus);
		ArrayList<AssignmentGroup> grList = asService.printAllGroup(asGroup);
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		gson.toJson(grList, response.getWriter());
	}
	
	@RequestMapping(value="study/assignment", method=RequestMethod.GET)
	public ModelAndView assignmentListView(HttpSession session, ModelAndView mv, 
			@RequestParam("grNo") int grNo, @RequestParam(value="page", required=false) Integer page) {
		
		// 상단에서 해당 그룹을 선택한 경우
		// 세션에 선택한 그룹 등록 (이전에 등록된 정보는 삭제)
		if(session.getAttribute("grNo") != null) {
			session.removeAttribute("groupNo");
		}
		session.setAttribute("groupNo", grNo);
		
		int currentPage = (page != null) ? page : 1;
		int listCount = asService.getListCount(grNo);
		PageInfo pi = Pagination5.getPageInfo(currentPage, listCount);
		
		ArrayList<Assignment> asList = asService.printAll(pi, grNo);
		
		AssignmentGroup asGroup = asService.printOneGroup(grNo);
		
		mv.addObject("asList", asList);
		mv.addObject("pi", pi);
		mv.addObject("asGroup", asGroup);
		mv.setViewName("study/assignmentList");
		//////////////////////////////////// 과제제출 확인 관련 메소드도 함께 호출
		
		return mv;
	}
	
	// 일정
	public ModelAndView assignmentCalendar(HttpSession session, ModelAndView mv) {
		return null;
	}
	
	/******************* 과제1 + 과제제출 리스트 보기 *******************/
	
	// 디테일
	@RequestMapping(value="study/assignment/detail", method=RequestMethod.GET)
	public ModelAndView assignmentDetail(HttpSession session, ModelAndView mv, @RequestParam("asNo") int asNo) {
		Assignment assignment = asService.printOne(asNo);
		if(assignment != null) {
			mv.addObject("assignment", assignment).setViewName("study/assignmentDetail");
		} else {
			System.out.println("과제 디테일 조회 실패");
		}
		
		// 과제 하나 + 과제제출 리스트
		///////////////////// 과제제출 확인 관련 메소드도 함께 호출
		
		return mv;
	}
	
	// 과제 댓글 리스트
	public void getReplyList(HttpServletResponse response, @RequestParam("asNo") int asNo) {

	}
	
	// 과제제출 댓글 리스트
	public void getSubmittedReplyList(HttpServletResponse response, @RequestParam("suNo") int asNo) {

	}
	
	/******************* 과제 분류 등록, 수정, 삭제, 숨김 *******************/
	@ResponseBody
	@RequestMapping(value="/study/assignment/addGroup", method=RequestMethod.POST)
	public String asGroupRegister(HttpSession session, @ModelAttribute AssignmentGroup asGroup) {
		///////////////////////////// 분류 등록시 할당 멤버도 정해질 수 있도록 해야
		
		int stNo = ((Study)session.getAttribute("study")).getStudyNo();
		asGroup.setStNo(stNo);
		
		int grNo = asService.registerGroup(asGroup);
		if(grNo > 0) {
			return grNo + "";
		} else {
			return "fail";
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/study/assignment/modifyGroup", method=RequestMethod.POST)
	public String asGroupDelete(@ModelAttribute AssignmentGroup asGroup) {
		///////////////////////////// 분류 등록시 할당 멤버도 수정할 수 있도록 해야
		
		int result = asService.modifyGroup(asGroup);
		if(result > 0) {
			return "success";
		} else {
			return "fail";
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/study/assignment/deleteGroup", method=RequestMethod.GET)
	public String asGroupHide(@ModelAttribute AssignmentGroup asGroup) {
		///////////////////////////// 분류 등록시 할당 멤버 정보도 삭제
		
		int result = asService.removeGroup(asGroup);
		if(result > 0) {
			return "success";
		} else {
			return "fail";
		}
	}
	
	/******************* 과제 등록, 수정, 삭제 *******************/
	
	// 등록
	@RequestMapping(value="/study/assignment/registerView", method=RequestMethod.GET)
	public String assignmentWriteView() {
		return "/study/assignmentRegister";
	}
	
	@RequestMapping(value="/study/assignment/register", method=RequestMethod.POST)
	public String assignmentRegister(HttpServletRequest request,
			@ModelAttribute Assignment assignment, @RequestParam(value="uploadFile", required=false) MultipartFile uploadFile) {
		
		HttpSession session = request.getSession();
		int stNo = ((Study)session.getAttribute("study")).getStudyNo();
		
//		// 서버에 파일을 저장하는 작업
//		if(!uploadFile.getOriginalFilename().equals("")) {
//			String renameFilename = saveFile(uploadFile, request);
//			if(renameFilename != null) {
//				// 파일 테이블에 파일정보 저장 //////////////////////////////////////////////
//				
//				// board에 파일이름 저장
//				assignment.setAsFileName(renameFilename);
//			}
//		}
		
		// DB에 데이터를 저장하는 작업
		int result = 0;
		
		result = asService.registerAssignment(assignment);
		if(result > 0) {
			return new RedirectWithMsg().redirect(request, "게시글이 등록되었습니다!", "/study/assignment");
		} else {
			return new RedirectWithMsg().redirect(request, "게시글 등록 실패!!!!", "/study/assignment");
		}
	}
	
	// 수정
	public String assignmentModifyView(@RequestParam("asNo") int asNo, Model model) {
		return null;
	}
	
	public ModelAndView assignmentUpdate(HttpServletRequest request, ModelAndView mv,
			@ModelAttribute Assignment assignment, @RequestParam("reloadFile") MultipartFile reloadFile) {
		return null;
	}
	
	// 삭제
	public ModelAndView assignmentDelete(HttpServletRequest request, ModelAndView mv, @RequestParam("asNo") int asNo) {
		return null;
	}
	
	public void deleteFile(String fileName, HttpServletRequest request) {

	}
	
	/******************* 과제 댓글 등록, 수정, 삭제 *******************/
	
	// 등록
	@ResponseBody
	@RequestMapping(value="/study/assignment/addReply", method=RequestMethod.POST)
	public String replyRegister(HttpSession session, @ModelAttribute Assignment assignment) {
		/////////////////////////////
		// 세션에서 스터디정보 가져와서 넣어주기
		
		int result = 0;
		if(result > 0) {
			return "success";
		} else {
			return "fail";
		}
	}
	
	// 수정
	@ResponseBody
	@RequestMapping(value="/study/assignment/modifyReply", method=RequestMethod.POST)
	public String replyUpdate(@ModelAttribute Assignment assignment) {
		int result = asService.modifyAssignment(assignment);
		if(result > 0) {
			return "success";
		} else {
			return "fail";
		}
	}
	
	// 삭제
	@ResponseBody
	@RequestMapping(value="/study/assignment/deleteReply", method=RequestMethod.GET)
	public String replyDelete(@RequestParam("asNo") int asNo) {
		int result = asService.removeAssignment(asNo);
		if(result > 0) {
			return "success";
		} else {
			return "fail";
		}
	}

	/******************* 과제율 산정 *******************/
	
	// 개인 과제율 산정
		// 한달 과제 제출 개수 / 한달 총 과제 개수
	public String myAssignmentCheckCount(HttpSession session, @RequestParam("mbNo") int mbNo) {
		return null;
	}
}
