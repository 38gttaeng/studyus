package com.studyus.assignment.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import com.studyus.assignment.domain.Assignment;
import com.studyus.assignment.domain.AssignmentGroup;
import com.studyus.assignment.service.AssignmentService;
import com.studyus.common.PageInfo;
import com.studyus.common.Pagination5;
import com.studyus.common.RedirectWithMsg;
import com.studyus.file.controller.FileController;
import com.studyus.file.domain.FileVO;
import com.studyus.file.service.FileService;
import com.studyus.study.domain.Study;
import com.studyus.submittedAssignment.domain.SubmittedAssignment;
import com.studyus.submittedAssignment.service.SAssignmentService;

@Controller
public class AssignmentController {
	
	@Autowired
	private AssignmentService asService;
	
	@Autowired
	private SAssignmentService suService;
	
	@Autowired
	private FileService fiService;
	
	/******************* 그룹에 따른 리스트 보기 *******************/
	
	// 리스트
	@RequestMapping(value="/study/assignment/groupList", method=RequestMethod.GET)
	public void groupListView(HttpSession session, HttpServletResponse response) throws IOException {
		int stNo = ((Study)session.getAttribute("study")).getStudyNo();
		ArrayList<AssignmentGroup> grList = asService.printAllGroup(stNo);
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		gson.toJson(grList, response.getWriter());
	}
	
	@RequestMapping(value="study/assignment", method=RequestMethod.GET)
	public ModelAndView assignmentListView(HttpSession session, ModelAndView mv, 
			@RequestParam("grNo") int grNo, @RequestParam(value="page", required=false) Integer page) {
		
		// 상단에서 해당 그룹을 선택한 경우
		// 세션에 선택한 그룹 등록 (이전에 등록된 정보는 삭제)
		if(session.getAttribute("groupNo") != null) {
			session.removeAttribute("groupNo");
		}
		session.setAttribute("groupNo", grNo);
		AssignmentGroup asGroup = asService.printOneGroup(grNo);
		
		int currentPage = (page != null) ? page : 1;
		int listCount = asService.getListCount(grNo);
		PageInfo pi = Pagination5.getPageInfo(currentPage, listCount);
		ArrayList<Assignment> asList = asService.printAll(pi, grNo);
		
		mv.addObject("asGroup", asGroup);
		mv.addObject("asList", asList);
		mv.addObject("pi", pi);
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
	public ModelAndView assignmentDetail(ModelAndView mv, @RequestParam("asNo") int asNo) {
		Assignment assignment = asService.printOne(asNo);
		AssignmentGroup asGroup = asService.printOneGroup(assignment.getGrNo());

		if(assignment != null && asGroup != null) {
			ArrayList<SubmittedAssignment> suList = suService.printAllSubmittedAssignment(asNo);
			
			mv.addObject("assignment", assignment);
			mv.addObject("assignmentGroup", asGroup);
			mv.addObject("suList", suList);
			mv.setViewName("study/assignmentDetail");
		} else {
			System.out.println("과제 디테일 조회 실패");
		}
		
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
	
	@RequestMapping(value="/study/assignment/addGroup", method=RequestMethod.POST)
	public String asGroupRegister(HttpServletRequest request, @ModelAttribute AssignmentGroup asGroup) {
		///////////////////////////// 분류 등록시 할당 멤버도 정해질 수 있도록 해야
		
		HttpSession session = request.getSession();
		int stNo = ((Study)session.getAttribute("study")).getStudyNo();
		asGroup.setStNo(stNo);
		
		int grNo = asService.registerGroup(asGroup);
		if(grNo > 0) {
			return new RedirectWithMsg().redirect(request, "프로젝트가 등록되었습니다!", "/study/assignment?grNo=" + grNo);
		} else {
			return new RedirectWithMsg().redirect(request, "프로젝트 등록 실패!", "/study/assignment?grNo=0");
		}
	}
	
	@RequestMapping(value="/study/assignment/modifyGroup", method=RequestMethod.POST)
	public String asGroupDelete(@ModelAttribute AssignmentGroup asGroup) {
		///////////////////////////// 분류 등록시 할당 멤버도 수정할 수 있도록 해야
		
		int result = asService.modifyGroup(asGroup);
		if(result == 0) {
			System.out.println("프로젝트 수정 실패");
		}
		
		return "redirect:/study/assignment?grNo=" + asGroup.getGrNo();
	}
	
	@RequestMapping(value="/study/assignment/deleteGroup", method=RequestMethod.GET)
	public String asGroupHide(@RequestParam int grNo) {
		///////////////////////////// 분류 등록시 할당 멤버 정보도 삭제
		
		int result = asService.removeGroup(grNo);
		if(result == 0) {
			System.out.println("프로젝트 삭제/숨김 실패");
		}
		return "redirect:/study/assignment?grNo=0";
	}
	
	/******************* 과제 등록, 수정, 삭제 *******************/
	
	// 등록
	@RequestMapping(value="/study/assignment/registerView", method=RequestMethod.GET)
	public ModelAndView assignmentWriteView(HttpSession session, ModelAndView mv) {
		
		int grNo = (Integer)session.getAttribute("groupNo");
		AssignmentGroup asGroup = asService.printOneGroup(grNo);
		
		if(asGroup != null) {
			mv.addObject("assignmentGroup", asGroup).setViewName("/study/assignmentRegister");
		} else {
			System.out.println("과제 등록 페이지로 이동 실패");
		}
		
		return mv;
	}
	
	@RequestMapping(value="/study/assignment/register", method=RequestMethod.POST)
	public String assignmentRegister(HttpServletRequest request,
			@ModelAttribute Assignment assignment, @RequestParam(value="fList", required=false) List<MultipartFile> fList) {
		
		HttpSession session = request.getSession();
		int grNo = (Integer)session.getAttribute("groupNo");
		assignment.setGrNo(grNo);
		int mbNo = ((Study)session.getAttribute("study")).getLeaderNo();
		
		// 실제 파일 저장
		ArrayList<FileVO> asFiles = null;
		if(fList != null && !fList.isEmpty()) {
        	asFiles = new FileController().saveFile(fList, 2, request);
        }
		
		// Assignment DB 저장
		int asNo = asService.registerAssignment(assignment);
		if(asNo > 0) {
			// File DB 저장
			int fiResult = 0;
			if(asFiles != null) {
				for(FileVO file : asFiles) {
					file.setMbNo(mbNo);
					file.setFiMotherNo(asNo);
					
					fiResult = fiService.uploadFile(file);
				}
			} else {
				fiResult = 1;
			}
			
			if(fiResult > 0) {
				return new RedirectWithMsg().redirect(request, "과제가 등록되었습니다!", "/study/assignment/detail?asNo=" + asNo);
			} else {
				return new RedirectWithMsg().redirect(request, "파일 등록 실패!!!!", "/study/assignment?grNo=" + grNo);
			}
		} else {
			return new RedirectWithMsg().redirect(request, "과제 등록 실패!!!!", "/study/assignment?grNo=" + grNo);
		}
		
	}
	
	// 수정
	@RequestMapping(value="/study/assignment/modifyView", method=RequestMethod.GET)
	public ModelAndView assignmentModifyView(ModelAndView mv, @RequestParam("asNo") int asNo) {
		Assignment assignment = asService.printOne(asNo);
		if(assignment != null) {
			AssignmentGroup asGroup = asService.printOneGroup(assignment.getGrNo());
			mv.addObject("assignmentGroup", asGroup);
			mv.addObject("assignment", assignment);
			mv.setViewName("study/assignmentModify");
		} else {
			System.out.println("과제 수정 페이지로 이동 실패");
		}
		
		return mv;
	}
	
	@RequestMapping(value="/study/assignment/modify", method=RequestMethod.POST)
	public ModelAndView assignmentUpdate(HttpServletRequest request, ModelAndView mv, @ModelAttribute Assignment assignment,
			@RequestParam(value="fList", required=false) List<MultipartFile> fList,
			@RequestParam(value="delFiles", required=false) List<String> delFiles) {
		
		HttpSession session = request.getSession();
		
		// 기존 파일 삭제
		if(delFiles != null && !delFiles.isEmpty()) {
			for(int i=0; i<delFiles.size(); i++) {
				FileVO file = fiService.selectOne(Integer.parseInt(delFiles.get(i)));
				new FileController().deleteFile("\\auploadFiles", file.getFiStoredName(), request);
				fiService.removeFileByFiNo(file.getFiNo());
			}
		}
		
		// 새파일 업로드
		ArrayList<FileVO> asFiles = null;
		if(fList != null && !fList.isEmpty()) {
			asFiles = new FileController().saveFile(fList, 2, request);
		}
		
		// Assignment DB 수정
		int boResult = asService.modifyAssignment(assignment);
		if(boResult > 0) {
			// 새파일 File DB 저장
			int fiResult = 0;
			if(asFiles != null) {
				for(FileVO file : asFiles) {
					file.setMbNo(((Study)session.getAttribute("study")).getLeaderNo());
					file.setFiMotherNo(assignment.getAsNo());
					
					fiResult = fiService.uploadFile(file);
				}
			} else {
				fiResult = 1;
			}
			
			if(fiResult > 0) {
				mv.addObject("assignment", assignment).setViewName("redirect:/study/assignment/detail?asNo=" + assignment.getAsNo());
			} else {
				mv.addObject("msg", "파일 수정 오류!").setViewName("common/errorPage");
			}
		} else {
			mv.addObject("msg", "게시물 수정 오류!").setViewName("common/errorPage");
		}
			
		return mv;
	}
	
	// 삭제
	@RequestMapping(value="/study/assignment/delete", method=RequestMethod.GET)
	public String assignmentDelete(HttpServletRequest request, @RequestParam("asNo") int asNo) {
		
		HttpSession session = request.getSession();
		
		// 파일 삭제
		int fiResult = 0;
		FileVO fileVO = new FileVO(2, asNo);
		ArrayList<FileVO> asFiles = fiService.selectList(fileVO); 
		if(!asFiles.isEmpty()) {
			String folder = "\\auploadFiles";
			for(FileVO file : asFiles) {
				new FileController().deleteFile(folder, file.getFiStoredName(), request);
			}
			
			fiResult = fiService.removeFile(fileVO);
		} else {
			fiResult = 1;
		}
		
		int asResult = 0;
		if(fiResult > 0) {
			asResult = asService.removeAssignment(asNo);
			if(asResult > 0) {
				return new RedirectWithMsg().redirect(request, "게시글이 삭제되었습니다!", "/study/assignment?grNo=" + (Integer)session.getAttribute("groupNo"));
			} else {
				return new RedirectWithMsg().redirect(request, "게시글 삭제 실패!", "/study/assignment?grNo=" + (Integer)session.getAttribute("groupNo"));
			}
		} else {
			return new RedirectWithMsg().redirect(request, "파일 삭제 실패!", "/study/assignment?grNo=" + (Integer)session.getAttribute("groupNo"));
		}
	}
	
	/******************* 과제율 산정 *******************/
	
	// 개인 과제율 산정
		// 한달 과제 제출 개수 / 한달 총 과제 개수
	public String myAssignmentCheckCount(HttpSession session, @RequestParam("mbNo") int mbNo) {
		return null;
	}
}