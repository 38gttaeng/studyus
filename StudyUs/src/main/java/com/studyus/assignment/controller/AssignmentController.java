package com.studyus.assignment.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.studyus.assignment.domain.Assign;
import com.studyus.assignment.domain.Assignment;
import com.studyus.assignment.domain.AssignmentGroup;
import com.studyus.assignment.service.AssignmentService;
import com.studyus.common.PageInfo;
import com.studyus.common.Pagination5;
import com.studyus.common.RedirectWithMsg;
import com.studyus.file.controller.FileController;
import com.studyus.file.domain.FileList;
import com.studyus.file.domain.FileVO;
import com.studyus.file.service.FileService;
import com.studyus.member.domain.Member;
import com.studyus.member.service.MemberService;
import com.studyus.study.domain.Study;
import com.studyus.submittedAssignment.controller.SAssignmentController;
import com.studyus.submittedAssignment.domain.SubmittedAssignment;
import com.studyus.submittedAssignment.service.SAssignmentService;

@Controller
public class AssignmentController {
	
	@Autowired
	private AssignmentService asService;
	
	@Autowired
	private SAssignmentService suService;
	
	@Autowired
	private SAssignmentController suController;
	
	@Autowired
	private FileService fiService;
	
	@Autowired
	private FileController fiController;
	
	@Autowired
	private MemberService mbService;
	
	/******************* 그룹에 따른 리스트 보기 *******************/
	
	// 리스트
	@RequestMapping(value="/study/assignment/group-list", method=RequestMethod.GET)
	public void groupListView(HttpSession session, HttpServletResponse response) throws IOException {
		int stNo = ((Study)session.getAttribute("study")).getStudyNo();
		ArrayList<AssignmentGroup> grList = asService.printAllGroup(stNo);
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		gson.toJson(grList, response.getWriter());
	}
	
	// 스터디에 해당하는 스터디원 정보 모두 가져오기
	@RequestMapping(value="/study/assignment/mem-list", method=RequestMethod.GET)
	public void memberListView(HttpSession session, HttpServletResponse response) throws IOException {
		int stNo = ((Study)session.getAttribute("study")).getStudyNo();
		ArrayList<Member> mbList = mbService.printAllByStudyNo(stNo);
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		gson.toJson(mbList, response.getWriter());
	}
	
	@RequestMapping(value="study/assignment", method=RequestMethod.GET)
	public ModelAndView assignmentListView(HttpSession session, ModelAndView mv, 
			@RequestParam("grNo") int grNo, @RequestParam(value="page", required=false) Integer page) {
		
		int stNo = ((Study)session.getAttribute("study")).getStudyNo();
		
		// 상단에서 해당 그룹을 선택한 경우
		// 세션에 선택한 그룹 등록 (이전에 등록된 정보는 삭제)
		if(session.getAttribute("groupNo") != null) {
			session.removeAttribute("groupNo");
		}
		session.setAttribute("groupNo", grNo);
		
		// 선택된 그룹정보 가져오기
		AssignmentGroup asGroup = asService.printOneGroup(grNo);
		
		// 선택된 그룹에 해당하는 과제 리스트 가져오기
		int currentPage = (page != null) ? page : 1;
		int listCount = asService.getListCount(grNo, stNo);
		PageInfo pi = Pagination5.getPageInfo(currentPage, listCount);
		ArrayList<Assignment> asList = asService.printAll(pi, grNo, stNo);
		
		// 선택된 그룹에 해당하는 스터디원 정보 가져오기
		ArrayList<Member> mbList = mbService.printAllAssign(grNo);
		
		mv.addObject("asGroup", asGroup);
		mv.addObject("asList", asList);
		mv.addObject("pi", pi);
		mv.addObject("mbList", mbList);
		mv.setViewName("study/assignmentList");
		
		return mv;
	}
	
	/******************* 과제1 + 과제제출 리스트 보기 *******************/
	
	// 디테일
	@RequestMapping(value="study/assignment/detail", method=RequestMethod.GET)
	public ModelAndView assignmentDetail(ModelAndView mv, @RequestParam("asNo") int asNo) {
		Assignment assignment = asService.printOne(asNo);
		AssignmentGroup asGroup = asService.printOneGroup(assignment.getGrNo());
		ArrayList<Member> mbList = mbService.printAllAssign(assignment.getGrNo());

		if(assignment != null && asGroup != null && !mbList.isEmpty()) {
			ArrayList<SubmittedAssignment> suList = suService.printAllSubmittedAssignment(asNo);
			
			mv.addObject("assignment", assignment);
			mv.addObject("assignmentGroup", asGroup);
			mv.addObject("mbList", mbList);
			mv.addObject("suList", suList);
			mv.setViewName("study/assignmentDetail");
		} else {
			System.out.println("과제 디테일 조회 실패");
		}
		
		return mv;
	}
	
	/******************* 과제 분류 등록, 수정, 삭제, 숨김 *******************/
	
	// 등록
	@RequestMapping(value="/study/assignment/addGroup", method=RequestMethod.POST)
	public String asGroupRegister(HttpServletRequest request, @ModelAttribute AssignmentGroup asGroup, @RequestParam("grMember") List<Integer> mbList) {
		
		HttpSession session = request.getSession();
		int stNo = ((Study)session.getAttribute("study")).getStudyNo();
		asGroup.setStNo(stNo);
		
		int grNo = asService.registerGroup(asGroup);
		
		int assignResult = 0;
		for(int mem : mbList) {
			Assign assign = new Assign(grNo, mem);
			assignResult += asService.addAssign(assign);
		}
		
		if(grNo > 0 && assignResult == mbList.size()) {
			return new RedirectWithMsg().redirect(request, "프로젝트가 등록되었습니다!", "/study/assignment?grNo=" + grNo);
		} else {
			return new RedirectWithMsg().redirect(request, "프로젝트 등록 실패!", "/study/assignment?grNo=0");
		}
	}
	
	// 수정
	@RequestMapping(value="/study/assignment/modifyGroup", method=RequestMethod.POST)
	public String asGroupDelete(@ModelAttribute AssignmentGroup asGroup) {
		
		int result = asService.modifyGroup(asGroup);
		if(result == 0) {
			System.out.println("프로젝트 수정 실패");
		}
		
		return "redirect:/study/assignment?grNo=" + asGroup.getGrNo();
	}
	
	// 삭제
	@RequestMapping(value="/study/assignment/deleteGroup", method=RequestMethod.GET)
	public String asGroupHide(HttpServletRequest request, @RequestParam int grNo) {
		
		// 그룹 할당정보 삭제
		ArrayList<Member> mbList = mbService.printAllAssign(grNo);
		int assignResult = 0;
		for(Member mem : mbList) {
			Assign assign = new Assign(grNo, mem.getMbNo());
			assignResult += asService.deleteAssign(assign);
		}
		
		// 그룹 정보 삭제
		int result = asService.removeGroup(grNo);
		
		// 그룹에 해당하는 과제와 과제제출 삭제
		ArrayList<Assignment> asList = asService.printAllAssignment(grNo);
		int asResult = 0;
		for(Assignment asOne : asList) {
			assignmentDelete(request, asOne.getAsNo());
		}
		
		if(result == 0 || assignResult != mbList.size() || asResult != asList.size()) {
			System.out.println("프로젝트 삭제/숨김 실패");
		}
		return "redirect:/study/assignment?grNo=0";
	}
	
	// 사진파일 가져오기
	@RequestMapping(value="/study/assignment/pic-list", method=RequestMethod.GET)
	public void getAssignmentPics(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		int stNo = ((Study)session.getAttribute("study")).getStudyNo();
		
		Pattern pattern = Pattern.compile("<img[^>]*src=[\"']?([^>\"']+)[\"']?[^>]*>");
		String text = "";
		
		ArrayList<Assignment> asList = asService.printAllByStudyNo(stNo);
		for(Assignment asOne : asList) {
			text += asOne.getAsContents();
		}
		
		Matcher matcher = pattern.matcher(text);
		
		ArrayList<String> picList = new ArrayList<String>();
		while(matcher.find()){
			picList.add(matcher.group(1));
        }
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		gson.toJson(picList, response.getWriter());
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
			@ModelAttribute Assignment assignment,
			@RequestParam(value="fList", required=false) List<MultipartFile> fList,
			@RequestParam(value="picList", required=false) List<String> picList) {
		
		HttpSession session = request.getSession();
		int grNo = (Integer)session.getAttribute("groupNo");
		assignment.setGrNo(grNo);
		int mbNo = ((Study)session.getAttribute("study")).getLeaderNo();
		
		// 텍스트 에디터 사진 처리
		if(!picList.isEmpty()) {
			fiController.addImages("\\auploadImages", assignment.getAsContents(), picList, request);
		}
		
		// 실제 파일 저장
		ArrayList<FileVO> asFiles = null;
		if(fList != null && !fList.isEmpty()) {
        	asFiles = fiController.saveFile(fList, 2, request);
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
			@RequestParam(value="delFiles", required=false) List<String> delFiles,
			@RequestParam(value="picList", required=false) List<String> picList) {
		
		HttpSession session = request.getSession();
		
		// 텍스트 에디터 사진 처리
		if(!picList.isEmpty()) {
			fiController.addImages("\\auploadImages", assignment.getAsContents(), picList, request);
		}
		Assignment oldAssignment = asService.printOne(assignment.getAsNo());
		fiController.editImages("\\auploadImages", assignment.getAsContents(), oldAssignment.getAsContents(), request);
		
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
		
		// 텍스트 에디터 이미지 삭제
		fiController.deleteImages("\\auploadImages", asService.printOne(asNo).getAsContents(), request);
		
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
		
		// 과제에 해당하는 과제제출 삭제
		ArrayList<SubmittedAssignment> suList = suService.printAllSubmittedAssignment(asNo);
		for(SubmittedAssignment suOne : suList) {
			SubmittedAssignment sAssignment = new SubmittedAssignment();
			sAssignment.setSuNo(suOne.getSuNo());
			sAssignment.setAsNo(asNo);
			suController.submittedAssignmentDelete(request, sAssignment);
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
	
	/******************* 파일함 *******************/
	
	// 파일함으로 이동
	@RequestMapping(value="/study/assignment/image", method=RequestMethod.GET)
	public ModelAndView assignmentImageList(HttpServletRequest request, ModelAndView mv) {
		HttpSession session = request.getSession();
		int stNo = ((Study)session.getAttribute("study")).getStudyNo();
		
		List<HashMap<String, Object>> picList = new ArrayList<HashMap<String, Object>>();
		Pattern pattern = Pattern.compile("<img[^>]*src=[\"']?([^>\"']+)[\"']?[^>]*>");
		
		// 과제
		ArrayList<Assignment> asList = asService.printAllByStudyNo(stNo);
		for(Assignment asOne : asList) {
			// 글 내용에서 이미지명만 추출하기
			Matcher matcher = pattern.matcher(asOne.getAsContents());
			ArrayList<String> pics = new ArrayList<String>();
			while(matcher.find()){
				pics.add(matcher.group(1));
	        }
			
			// 이미지 있으면 정보 저장
			if(!pics.isEmpty()) {
					// 하나씩 담을 HashMap 만들기
				HashMap<String, Object> map = new HashMap<String, Object>();
				
				for(String pic : pics) {
					// 이미지 넣기
					map.put("pic", pic);
					// 링크 넣기
					map.put("url", "/study/assignment/detail?asNo=" + asOne.getAsNo());
					picList.add(map);
				}
			}
		}
		
		// 과제 제출
		ArrayList<SubmittedAssignment> suList = suService.printAllContents(stNo);
		for(SubmittedAssignment suOne : suList) {
			// 글 내용에서 이미지명만 추출하기
			Matcher matcher = pattern.matcher(suOne.getSuContents());
			ArrayList<String> pics = new ArrayList<String>();
			while(matcher.find()){
				pics.add(matcher.group(1));
	        }
			
			// 이미지 있으면 정보 저장
			if(!pics.isEmpty()) {
					// 하나씩 담을 HashMap 만들기
				HashMap<String, Object> map = new HashMap<String, Object>();
				
				for(String pic : pics) {
					// 이미지 넣기
					map.put("pic", pic);
					// 링크 넣기
					if(suOne.getSuMotherNo() == 0) {
						// 게시물인 경우
						map.put("url", "/study/sAssignment/detail?suNo=" + suOne.getSuNo());
					} else {
						// 댓글인 경우
						map.put("url", "/study/sAssignment/detail?suNo=" + suOne.getSuMotherNo());
					}
					picList.add(map);
				}
			}
		}
		
		// 파일명을 기준으로 정렬(내림차순)
		Collections.sort(picList, new Comparator<HashMap<String, Object>>() {
	        @Override
	        public int compare(HashMap<String, Object> first,
	                HashMap<String, Object> second) {

	            return ((String)second.get("pic").toString().substring(25,40)).compareTo((String)first.get("pic").toString().substring(25,40));
	        }
	    });
		
		mv.addObject("picList", picList).setViewName("study/assignmentImage"); 
		return mv;
	}
	
	// 기타 파일함으로 이동
	@RequestMapping(value="/study/assignment/file", method=RequestMethod.GET)
	public ModelAndView assignmentFileList(HttpServletRequest request, ModelAndView mv) {
		HttpSession session = request.getSession();
		int stNo = ((Study)session.getAttribute("study")).getStudyNo();
		
		// 파일 DB에서 과제(2), 과제제출(3)에 해당하는 리스트 가져오기
		ArrayList<FileList> fiList = fiService.printAllAssign(stNo);
		System.out.println(fiList); /////////////////////////////////////////////////
		
		// 파일 이름으로 최신순 정렬
//		fiList.sort(new Comparator<FileList>() {
//            @Override
//            public double compare(FileList arg0, FileList arg1) {
//               double age0 = Double.parseDouble(arg0.getFiStoredName().substring(0, 15));
//               double age1 = Double.parseDouble(arg1.getFiStoredName().substring(0, 15));
//               if (age0 == age1)
//                     return 0;
//               else if (age1 > age0)
//                     return 1;
//               else
//                     return -1;
//            }
//		});
		System.out.println(fiList); /////////////////////////////////////////////////

		mv.addObject("fiList", fiList);
		mv.setViewName("study/assignmentFile");
		return mv;
	}
	
	/******************* 스터디룸 메인페이지 차트 *******************/
	
	@RequestMapping(value="/study/assignment-chart", method=RequestMethod.GET)
	public void assignmentChart(HttpSession session, HttpServletResponse response) throws Exception {
		int stNo = ((Study)session.getAttribute("study")).getStudyNo();
		// 스터디에 해당하는 그룹리스트와 그에 해당하는 과제개수, 그룹명, 그룹색 가져오기
		ArrayList<AssignmentGroup> grList = asService.printGroupChart(stNo);
		
		// 각각에 해당하는 어레이로 나누어서 넣어주기
		ArrayList<String> labelList = new ArrayList<String>();
		ArrayList<String> colorList = new ArrayList<String>();
		ArrayList<Integer> countList = new ArrayList<Integer>();
		for(AssignmentGroup grOne : grList) {
			labelList.add(grOne.getGrName());
			colorList.add(grOne.getGrInfo());
			countList.add(grOne.getGrStatus());
		}
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("labelList", labelList);
		map.put("colorList", colorList);
		map.put("countList", countList);
		
		// 과제그룹과 과제가 아직 없는 스터디도 있기 때문에 빈값 확인 없이 전송
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		gson.toJson(map, response.getWriter());
	}
}