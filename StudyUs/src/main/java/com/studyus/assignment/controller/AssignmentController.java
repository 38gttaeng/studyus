package com.studyus.assignment.controller;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.studyus.assignment.domain.Assignment;
import com.studyus.assignment.domain.SubmittedAssignment;
import com.studyus.assignment.service.AssignmentService;
import com.studyus.common.PageInfo;
import com.studyus.common.Pagination5;

@Controller
public class AssignmentController {
	
	@Autowired
	private AssignmentService asService;
	
	/////////////////// 과제 + 과제제출 보기 ///////////////////
	
	// 리스트
	@RequestMapping(value="study/assignment", method=RequestMethod.GET)
	public ModelAndView assignmentListView(HttpSession session, ModelAndView mv, @RequestParam(value="page", required=false) Integer page) {
		
		//////////////////////////////////세션에서 스터디번호 가져와서 넣어주기
		int stNo = 1;
		
		//////////////////////////////////// 과제제출 확인 관련 메소드도 함께 호출
		
		int currentPage = (page != null) ? page : 1;
		int listCount = asService.getListCount(stNo);
		PageInfo pi = Pagination5.getPageInfo(currentPage, listCount);
		
		ArrayList<Assignment> aList = asService.printAll(pi, stNo);
		mv.addObject("aList", aList);
		mv.addObject("pi", pi);
		mv.setViewName("study/assignmentList");
		
		return mv;
	}
	
	// 일정
	public ModelAndView assignmentCalendar(HttpSession session, ModelAndView mv) {
		return null;
	}
	
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
	
	/////////////////// 과제 제출여부 보기 ///////////////////
	
	// 과제당 총 과제제출 개수 보여주기
	public String getSubmittedCheckList(@RequestParam("asNo") int asNo) {
		return 0 + "";
	}
	
	// 개인당 과제제출 여부 보여주기
	public String submittedCheckByLogin(HttpSession session, @RequestParam("asNo") int asNo) {
		return null;
	}
	
	/////////////////// 과제 등록, 수정, 삭제 ///////////////////
	
	// 등록
	public String assignmentWriteView() {
		return null;
	}
	
	public ModelAndView assignmentRegister(HttpServletRequest request, ModelAndView mv, 
			@ModelAttribute Assignment assignment, @RequestParam(value="uploadFile", required=false) MultipartFile uploadFile) {
		return null;
	}
	
	public String saveFile(MultipartFile file, HttpServletRequest request) {
		return null;
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
	
	/////////////////// 과제 댓글 등록, 수정, 삭제 ///////////////////
	
	// 등록
	public String replyRegister(HttpSession session, @ModelAttribute Assignment assignment) {
		return null;
	}
	
	// 수정
	public void replyModifyView(@RequestParam("asNo") int asNo) {
		
	}
	
	public String replyUpdate(@ModelAttribute Assignment assignment) {
		return null;
	}
	
	// 삭제
	public String replyDelete(@RequestParam("asNo") int asNo) {
		return null;
	}
	
	/////////////////// 과제제출 등록, 수정, 삭제 ///////////////////
	
	// 등록 (파일은 saveFile 동일하게 사용)
	public String submittedAssignmentWriteView() {
		return null;
	}
	
	public ModelAndView submittedAssignmentRegister(HttpServletRequest request, ModelAndView mv, 
			@ModelAttribute SubmittedAssignment sAssignment, @RequestParam(value="uploadFile", required=false) MultipartFile uploadFile) {
		return null;
	}

	// 수정
	public String submittedAssignmentModifyView(@RequestParam("suNo") int suNo, Model model) {
		return null;
	}
	
	public ModelAndView submittedAssignmentUpdate(HttpServletRequest request, ModelAndView mv,
			@ModelAttribute SubmittedAssignment sAssignment, @RequestParam("reloadFile") MultipartFile reloadFile) {
		return null;
	}
	
	// 삭제
	public ModelAndView submittedAssignmentDelete(HttpServletRequest request, ModelAndView mv, @RequestParam("suNo") int suNo) {
		return null;
	}
	
	/////////////////// 과제제출 댓글 등록, 수정, 삭제 ///////////////////
		
	// 등록
	public String submittedReplyRegister(HttpSession session, @ModelAttribute SubmittedAssignment sAssignment) {
		return null;
	}
	
	// 수정
	public void submittedReplyModifyView(@RequestParam("suNo") int suNo) {
	
	}
	
	public String submittedReplyUpdate(@ModelAttribute SubmittedAssignment sAssignment) {
		return null;
	}
	
	// 삭제
	public String submittedReplyDelete(@RequestParam("suNo") int suNo) {
		return null;
	}
	
	/////////////////// 과제율 산정 ///////////////////
	
	// 개인 과제율 산정
		// 한달 과제 제출 개수 / 한달 총 과제 개수
	public String myAssignmentCheckCount(HttpSession session, @RequestParam("mbNo") int mbNo) {
		return null;
	}
}
