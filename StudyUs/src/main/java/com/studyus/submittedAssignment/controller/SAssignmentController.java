package com.studyus.submittedAssignment.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
import com.studyus.assignment.domain.Assignment;
import com.studyus.assignment.domain.AssignmentGroup;
import com.studyus.assignment.service.AssignmentService;
import com.studyus.common.PageInfo;
import com.studyus.common.Pagination5;
import com.studyus.common.RedirectWithMsg;
import com.studyus.file.controller.FileController;
import com.studyus.file.domain.FileVO;
import com.studyus.file.service.FileService;
import com.studyus.member.domain.Member;
import com.studyus.study.domain.Study;
import com.studyus.submittedAssignment.domain.SubmittedAssignment;
import com.studyus.submittedAssignment.service.SAssignmentService;

@Controller
public class SAssignmentController {
	
	@Autowired
	private SAssignmentService suService;
	
	@Autowired
	private AssignmentService asService;
	
	@Autowired
	private FileService fiService;
	
	@Autowired
	private FileController fiController;
	
	
	/******************* 과제제출 디테일 *******************/
	
	// 디테일
	@RequestMapping(value="study/sAssignment/detail", method=RequestMethod.GET)
	public ModelAndView assignmentDetail(ModelAndView mv, @RequestParam("suNo") int suNo) {
		
		SubmittedAssignment sAssignment = suService.printOneSubmittedAssignment(suNo);
		Assignment assignment = asService.printOne(sAssignment.getAsNo());
		AssignmentGroup asGroup = asService.printOneGroup(assignment.getGrNo());

		if(sAssignment != null && assignment != null && asGroup != null) {
			mv.addObject("sAssignment", sAssignment);
			mv.addObject("assignment", assignment);
			mv.addObject("assignmentGroup", asGroup);
			mv.setViewName("study/sAssignmentDetail");
		} else {
			System.out.println("과제제출 디테일 조회 실패");
		}
		
		return mv;
	}
	
	// 댓글 리스트
	@RequestMapping(value="/study/sAssignment/replyList", method=RequestMethod.GET)
	public void getReplyList(HttpServletResponse response, @RequestParam("suMotherNo") int suMotherNo, @RequestParam(value="page", required=false) Integer page) throws IOException {
		
		int listCount = suService.countSubmittedReply(suMotherNo);
		
		int currentPage = (page != null) ? page : 1;
		PageInfo pi = Pagination5.getPageInfo(currentPage, listCount);
		ArrayList<SubmittedAssignment> rList = suService.printAllSubmittedReply(pi, suMotherNo);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("page", pi);
		map.put("rList", rList);
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		gson.toJson(map, response.getWriter());
	}
	
	/******************* 과제제출 등록, 수정, 삭제 *******************/
	
	// 등록
	@RequestMapping(value="/study/sAssignment/registerView", method=RequestMethod.GET)
	public ModelAndView submittedAssignmentWriteView(ModelAndView mv, @RequestParam("asNo") int asNo) {
		
		Assignment assignment = asService.printOne(asNo);
		AssignmentGroup asGroup = asService.printOneGroup(assignment.getGrNo());
		
		if(assignment != null && asGroup != null) {
			mv.addObject("assignment", assignment);
			mv.addObject("assignmentGroup", asGroup);
			mv.setViewName("/study/sAssignmentRegister");
		} else {
			System.out.println("과제제출 등록 페이지로 이동 실패");
		}
		
		return mv;
	}
	
	@RequestMapping(value="/study/sAssignment/register", method=RequestMethod.POST)
	public String submittedAssignmentRegister(HttpServletRequest request, @ModelAttribute SubmittedAssignment sAssignment,
			@RequestParam(value="fList", required=false) List<MultipartFile> fList,
			@RequestParam(value="picList", required=false) List<String> picList) {
		
		HttpSession session = request.getSession();
		int mbNo = ((Member)session.getAttribute("loginUser")).getMbNo();
		sAssignment.setMbNo(mbNo);
		
		// 텍스트 에디터 사진 처리
		if(!picList.isEmpty()) {
			fiController.addImages("\\auploadImages", sAssignment.getSuContents(), picList, request);
		}
		
		// 실제 파일 저장
		ArrayList<FileVO> suFiles = null;
		if(fList != null && !fList.isEmpty()) {
        	suFiles = new FileController().saveFile(fList, 3, request);
        }
		
		// SubmittedAssignment DB 저장
		int suNo = suService.registerSubmittedAssignment(sAssignment);
		String url = "/study/assignment/detail?asNo=" + sAssignment.getAsNo();
		if(suNo > 0) {
			// File DB 저장
			int fiResult = 0;
			if(suFiles != null) {
				for(FileVO file : suFiles) {
					file.setMbNo(mbNo);
					file.setFiMotherNo(suNo);
					
					fiResult = fiService.uploadFile(file);
				}
			} else {
				fiResult = 1;
			}
			
			if(fiResult > 0) {
				return new RedirectWithMsg().redirect(request, "과제가 제출되었습니다!", url);
			} else {
				return new RedirectWithMsg().redirect(request, "파일 등록 실패!!!!", url);
			}
		} else {
			return new RedirectWithMsg().redirect(request, "과제 제출 실패!!!!", url);
		}
	}

	// 수정
	@RequestMapping(value="/study/sAssignment/modifyView", method=RequestMethod.GET)
	public ModelAndView submittedAssignmentModifyView(ModelAndView mv, @RequestParam("suNo") int suNo) {
		SubmittedAssignment sAssignment = suService.printOneSubmittedAssignment(suNo);
		Assignment assignment = asService.printOne(sAssignment.getAsNo());
		AssignmentGroup asGroup = asService.printOneGroup(assignment.getGrNo());
		
		if(sAssignment != null && assignment != null && asGroup != null) {
			mv.addObject("sAssignment", sAssignment);
			mv.addObject("assignment", assignment);
			mv.addObject("assignmentGroup", asGroup);
			mv.setViewName("/study/sAssignmentModify");
		} else {
			System.out.println("과제제출 수정 페이지로 이동 실패");
		}
		
		return mv;
	}
	
	@RequestMapping(value="/study/sAssignment/modify", method=RequestMethod.POST)
	public String submittedAssignmentUpdate(HttpServletRequest request,
			@ModelAttribute SubmittedAssignment sAssignment,
			@RequestParam(value="fList", required=false) List<MultipartFile> fList,
			@RequestParam(value="delFiles", required=false) List<String> delFiles,
			@RequestParam(value="picList", required=false) List<String> picList) {
		
		HttpSession session = request.getSession();
		int mbNo = ((Member)session.getAttribute("loginUser")).getMbNo();
		
		// 텍스트 에디터 사진 처리
		if(!picList.isEmpty()) {
			fiController.addImages("\\auploadImages", sAssignment.getSuContents(), picList, request);
		}
		SubmittedAssignment oldSAssignment = suService.printOneSubmittedAssignment(sAssignment.getSuNo());
		fiController.editImages("\\auploadImages", sAssignment.getSuContents(), oldSAssignment.getSuContents(), request);
		
		// 기존 파일 삭제
		if(delFiles != null && !delFiles.isEmpty()) {
			for(int i=0; i<delFiles.size(); i++) {
				FileVO file = fiService.selectOne(Integer.parseInt(delFiles.get(i)));
				new FileController().deleteFile("\\suploadFiles", file.getFiStoredName(), request);
				fiService.removeFileByFiNo(file.getFiNo());
			}
		}
		
		// 새파일 업로드
		ArrayList<FileVO> suFiles = null;
		if(fList != null && !fList.isEmpty()) {
			suFiles = new FileController().saveFile(fList, 3, request);
		}
		 
		// SubmittedAssignment DB 수정
		int suResult = suService.modifySubmittedAssignment(sAssignment);
		String url = "/study/sAssignment/detail?suNo=" + sAssignment.getSuNo();
		if(suResult > 0) {
			// 새파일 File DB 저장
			int fiResult = 0;
			if(suFiles != null) {
				for(FileVO file : suFiles) {
					file.setMbNo(mbNo);
					file.setFiMotherNo(sAssignment.getSuNo());
					
					fiResult = fiService.uploadFile(file);
				}
			} else {
				fiResult = 1;
			}
			
			if(fiResult > 0) {
				return new RedirectWithMsg().redirect(request, "제출한 과제가 수정되었습니다!", url);
			} else {
				return new RedirectWithMsg().redirect(request, "파일 수정 실패!!!!", url);
			}
		} else {
			return new RedirectWithMsg().redirect(request, "과제제출 수정 실패!!!!", url);
		}
	}
	
	// 삭제
	@RequestMapping(value="/study/sAssignment/delete", method=RequestMethod.GET)
	public String submittedAssignmentDelete(HttpServletRequest request, @ModelAttribute SubmittedAssignment sAssignment) {
		
		// 텍스트 에디터 이미지 삭제
		fiController.deleteImages("\\auploadImages", suService.printOneSubmittedAssignment(sAssignment.getSuNo()).getSuContents(), request);
		
		// 파일 삭제
		int fiResult = 0;
		FileVO fileVO = new FileVO(2, sAssignment.getSuNo());
		ArrayList<FileVO> suFiles = fiService.selectList(fileVO); 
		if(!suFiles.isEmpty()) {
			String folder = "\\suploadFiles";
			for(FileVO file : suFiles) {
				new FileController().deleteFile(folder, file.getFiStoredName(), request);
			}
			
			fiResult = fiService.removeFile(fileVO);
		} else {
			fiResult = 1;
		}
		
		int suResult = 0;
		if(fiResult > 0) {
			// 댓글과 게시물 삭제
			suResult = suService.removeSubmittedAssignment(sAssignment.getSuNo());
			if(suResult > 0) {
				// 댓글은 있을수도 없을수도 있기 때문에 0 이상
				return new RedirectWithMsg().redirect(request, "제출한 과제가 삭제되었습니다!", "/study/assignment/detail?asNo=" + sAssignment.getAsNo());
			} else {
				return new RedirectWithMsg().redirect(request, "제출한 과제 삭제 실패!", "/study/assignment/detail?asNo=" + sAssignment.getAsNo());
			}
		} else {
			return new RedirectWithMsg().redirect(request, "파일 삭제 실패!", "/study/assignment/detail?asNo=" + sAssignment.getAsNo());
		}
	}
	
	/******************* 과제제출 댓글 등록, 수정, 삭제 *******************/
		
	// 등록
	@ResponseBody
	@RequestMapping(value="/study/sAssignment/addReply", method=RequestMethod.POST)
	public String submittedReplyRegister(HttpServletRequest request, @ModelAttribute SubmittedAssignment sAssignment,
			@RequestParam("picList") List<String> picList) {
		
		// 텍스트 에디터 사진 처리
		if(!picList.isEmpty()) {
			fiController.addImages("\\auploadImages", sAssignment.getSuContents(), picList, request);
		}
		
		int result = suService.registerSubmittedAssignment(sAssignment);
		if(result > 0) {
			return "success";
		} else {
			return "fail";
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/study/sAssignment/modifyReply", method=RequestMethod.POST)
	public String submittedReplyUpdate(HttpServletRequest request, @ModelAttribute SubmittedAssignment sAssignment,
			@RequestParam("picList") List<String> picList) {
		
		// 텍스트 에디터 사진 처리
		if(!picList.isEmpty()) {
			fiController.addImages("\\auploadImages", sAssignment.getSuContents(), picList, request);
		}
		SubmittedAssignment oldSAssignment = suService.printOneSubmittedAssignment(sAssignment.getSuNo());
		fiController.editImages("\\auploadImages", sAssignment.getSuContents(), oldSAssignment.getSuContents(), request);
		
		int result = suService.modifySubmittedAssignment(sAssignment);
		if(result > 0) {
			return "success";
		} else {
			return "fail";
		}
	}
	
	// 삭제
	@ResponseBody
	@RequestMapping(value="/study/sAssignment/deleteReply", method=RequestMethod.GET)
	public String submittedReplyDelete(HttpServletRequest request, @RequestParam("suNo") int suNo) {
		
		// 텍스트 에디터 이미지 삭제
		fiController.deleteImages("\\auploadImages", suService.printOneSubmittedAssignment(suNo).getSuContents(), request);
		
		int result = suService.removeSubmittedAssignment(suNo);
		if(result > 0) {
			return "success";
		} else {
			return "fail";
		}
	}
	
	/******************* 과제제출 관리 *******************/

	// 과제제출 리스트
	@RequestMapping(value="/study/contentsList/assignment", method=RequestMethod.GET)
	public void contentsView(HttpSession session, HttpServletResponse response, @RequestParam("user") String user) throws Exception {
		
		int stNo = ((Study)session.getAttribute("study")).getStudyNo();
		
		ArrayList<HashMap<String, Object>> data = null;
		if(user.equals("l")) {
			data = suService.printAllByStNo(stNo);
		} else if(user.equals("m")) {
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			int mbNo = ((Member)session.getAttribute("loginUser")).getMbNo();
			map.put("stNo", stNo);
			map.put("mbNo", mbNo);
			data = suService.printAllByMemberNo(map);
		}
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		gson.toJson(data, response.getWriter());
	}
	
	// 과제제출 댓글 리스트
	@RequestMapping(value="/study/commentsList/assignment", method=RequestMethod.GET)
	public void myReplyListView(HttpSession session, HttpServletResponse response, @RequestParam("user") String user) throws Exception {
		
		int stNo = ((Study)session.getAttribute("study")).getStudyNo();
		
		ArrayList<HashMap<String, Object>> data = null;
		if(user.equals("l")) {
			data = suService.printAllReplyByStNo(stNo);
		} else if(user.equals("m")) {
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			int mbNo = ((Member)session.getAttribute("loginUser")).getMbNo();
			map.put("stNo", stNo);
			map.put("mbNo", mbNo);
			data = suService.printAllReplyByMemberNo(map);
		}
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		gson.toJson(data, response.getWriter());
	}
	
	// 과제제출 댓글 일괄 삭제
	@ResponseBody
	@RequestMapping(value="/study/contentsList/delete-assignment", method=RequestMethod.GET)
	public String boardListDelete(@RequestParam("deList") List<Integer> deList) {
		
		int result = 0;
		for(int delNo : deList) {
			result +=suService.removeSubmittedAssignment(delNo);
		}
		
		if(result == deList.size()) {
			return "success";
		} else {
			return "error";
		}
	}
	
}
