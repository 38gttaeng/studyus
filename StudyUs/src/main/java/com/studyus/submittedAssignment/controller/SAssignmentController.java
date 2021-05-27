package com.studyus.submittedAssignment.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.studyus.submittedAssignment.domain.SubmittedAssignment;
import com.studyus.submittedAssignment.service.SAssignmentService;

@Controller
public class SAssignmentController {
	
	@Autowired
	private SAssignmentService suService;
	
	/******************* 과제 제출여부 보기 *******************/
	
	// 과제당 총 과제제출 개수 보여주기
	public String getSubmittedCheckList(@RequestParam("asNo") int asNo) {
		return 0 + "";
	}
	
	// 개인당 과제제출 여부 보여주기
	public String submittedCheckByLogin(HttpSession session, @RequestParam("asNo") int asNo) {
		return null;
	}
	
	/******************* 과제제출 등록, 수정, 삭제 *******************/
	
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
	
	/******************* 과제제출 댓글 등록, 수정, 삭제 *******************/
		
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
	
}
