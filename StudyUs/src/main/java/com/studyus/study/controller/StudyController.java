package com.studyus.study.controller;

import java.util.ArrayList;
import java.util.Calendar; 
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.studyus.attendance.domain.Attendance;
import com.studyus.attendance.service.AttendanceService;
import com.studyus.common.RedirectWithMsg;
import com.studyus.common.util.FileUtils;
import com.studyus.enrollment.domain.Enrollment;
import com.studyus.enrollment.domain.EnrollmentWithMember;
import com.studyus.enrollment.service.EnrollmentService;
import com.studyus.file.controller.FileController;
import com.studyus.meeting.domain.Meeting;
import com.studyus.meeting.service.MeetingService;
import com.studyus.member.domain.Member;
import com.studyus.notice.domain.Notice;
import com.studyus.notice.service.NoticeService;
import com.studyus.study.domain.Study;
import com.studyus.study.domain.StudySearchCriteria;
import com.studyus.study.domain.StudySearchResult;
import com.studyus.study.service.StudyService;
import com.studyus.study.util.StudyUtil;

@Controller
public class StudyController {
	
	// 스터디 생성 페이지 get
	// 스터디 생성 post
	// 스터디 검색 페이지 get
	// 스터디 검색 결과페이지 get
	// 스터디 상세 페이지 get
	// 스터디 수정 페이지 get
	// 스터디 수정 post
	// 스터디 삭제 post
	
	///////////////////////////////////////////
	
	@Autowired
	StudyService sService;
	
	@Autowired
	EnrollmentService eService;
	
	@Autowired
	AttendanceService aService;
	
	@Autowired
	MeetingService mService;
	
	@Autowired
	private NoticeService nService;
	
	// 스터디 생성 폼 페이지 get
	@RequestMapping(value="/study/register", method=RequestMethod.GET)
	public String registerView() {
		
		return "study/register";
	}
	
	// 스터디 생성 post
	@RequestMapping(value="/study/registerPost", method=RequestMethod.POST)
	public String registerStudy(HttpServletRequest request, 
								@ModelAttribute Study study, 
								@RequestParam(value="hashtag", required=false) String[] hashtagList,
								@RequestParam(value="start-h") String startHour,
								@RequestParam(value="start-m") String startMinute,
								@RequestParam(value="end-h") String endHour,
								@RequestParam(value="end-m") String endMinute,
								@RequestParam(value="file") MultipartFile file) throws Exception {
		
		Member loginUser = (Member)request.getSession().getAttribute("loginUser");
		
		study.setLeaderNo(loginUser.getMbNo());
		study.setStart(startHour + ":" + startMinute);
		study.setEnd(endHour + ":" + endMinute);
		String fileName = FileUtils.saveFile(file, "studyUploadFiles", request);
		study.setFilename(fileName);
		
		int registeredStudyNo = sService.registerStudy(study, hashtagList);
		
		if (0 < registeredStudyNo) { // 스터디 생성 성공
			// 생성한 스터디에 대해 자신의 enrollment 추가
			Enrollment enrollment = new Enrollment();
			enrollment.setMemberNo(loginUser.getMbNo());
			enrollment.setStudyNo(registeredStudyNo);
			enrollment.setStatus(1);
			eService.insertOne(enrollment);
			
			// 가입한 스터디 리스트를 새로 가져와서 세션에 저장
			ArrayList<Study> enrolledStudyList = sService.printAllEnrolledByMemberNo(loginUser.getMbNo());
			request.getSession().setAttribute("enrolledStudyList", enrolledStudyList);
			return new RedirectWithMsg().redirect(request, "스터디가 생성되었습니다.", "/study/" + study.getUrl());
		} else {
			String referer = request.getHeader("Referer");
			return new RedirectWithMsg().redirect(request, "스터디 생성 오류", "redirect:" + referer);
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/study/check/url", method=RequestMethod.POST)
	public String checkUrl(@RequestParam String inputUrl) {
		
		int dupCheck = sService.checkUrl(inputUrl);
		if (0 < dupCheck) {
			return String.valueOf(dupCheck);
		} else {
			return String.valueOf(dupCheck);
		}
	}
	
	// 스터디 검색 페이지 get
	@RequestMapping(value="/study/search", method=RequestMethod.GET)
	public String searchView() {
		
		return "study/search";
	}
	
	// 스터디 검색 결과페이지 get
	@RequestMapping(value="/study/search/result", method=RequestMethod.GET)
	public ModelAndView searchStudy(ModelAndView mv,
									@RequestParam(value="keyword") String keyword,
									@RequestParam(value="hashtag", required=false) String[] hashtags,
									@RequestParam(value="page", required=false) int page) throws Exception {
		
		StudySearchCriteria sc = StudySearchCriteria.searchReady(page, hashtags, keyword);
		ArrayList<StudySearchResult> searchResult = sService.printSearchResult(sc);
		
		mv.addObject("searchResult", searchResult);
		mv.addObject("page", page);
		mv.setViewName("/study/searchResult");
		
		return mv;
	}
	
	// 스터디 검색 비동기 추가로딩
	@ResponseBody
	@RequestMapping(value="/study/search/additional", method=RequestMethod.GET, produces="application/text; charset=UTF-8")
	public String searchAdditionally(HttpServletRequest request,
							@RequestParam(required=false) String keyword,
							@RequestParam(required=false) String[] hashtags,
							@RequestParam(required=false) int page) throws Exception {
		
		StudySearchCriteria sc = StudySearchCriteria.searchReady(page + 1, hashtags, keyword);
		ArrayList<StudySearchResult> searchResult = sService.printSearchResult(sc);
		
		return new Gson().toJson(searchResult);
	}
	
	// 스터디 상세 페이지 URL 미입력시 안내 출력
	@RequestMapping(value="/study", method=RequestMethod.GET)
	public String mainView(HttpServletRequest request) {
		return new RedirectWithMsg().redirect(request, "잘못된 주소입니다.", "/");
	}
	// 스터디 상세 페이지 get by Url
	@RequestMapping(value="/study/{url}", method=RequestMethod.GET)
	public String mainViewUrl(HttpServletRequest request, @ModelAttribute Notice notice, @PathVariable("url") String url) throws Exception {
		
		Study study = sService.printOneByUrl(url);
		Member member = (Member) request.getSession().getAttribute("loginUser");
		notice.setStNo(study.getStudyNo());
		// 로그인여부 확인
		if (member == null) {
			return new RedirectWithMsg().redirect(request, "로그인이 필요합니다.", "/member/loginView");
		}
		
		// 스터디 가입여부 확인
		Enrollment enrollment = new Enrollment();
		enrollment.setMemberNo(member.getMbNo());
		enrollment.setStudyNo(study.getStudyNo());
		if (eService.checkEnrollment(enrollment) < 1) {
			return new RedirectWithMsg().redirect(request, "스터디에 가입한 회원이 아닙니다.", "/");
		}
		// 최신 공지사항 출력 
		ArrayList<Notice> recentNotice = nService.printRecentNotice(notice);
		/*
		 * 출석버튼 상태를 변경하기 위한 값
		 * 0: 출석일이 아님
		 * 1: 오늘이 출석일이지만 아직 출석체크 하지않음
		 * 2: 오늘이 출석일이며 출석체크 완료함 
		 */
		int attendanceStatus = 0;
		
		// 오늘이 출석일인지 확인
		Meeting currentMeeting = mService.printCurrentOneByStudyNo(study.getStudyNo());
		
		// 오늘이 출석일일 경우 이미 출석체크 하였는지 확인.
		if (currentMeeting != null) {
			Attendance attendance = new Attendance();
			attendance.setMeetingNo(currentMeeting.getMeetingNo());
			attendance.setMemberNo(member.getMbNo());
			
			// 오늘 이미 출석하였는지 확인
			boolean attendedAlready = aService.checkAttendedAlready(attendance);
			attendanceStatus = attendedAlready ? 2 : 1;
		}
		
		request.getSession().setAttribute("study", study);
		request.setAttribute("attendanceStatus", attendanceStatus);
		request.setAttribute("recentNotice", recentNotice);
		
		return "study/study";
	}
	
	// 스터디 가입신청리스트 url오류
	@RequestMapping(value="/study/enrollment/list")
	public String enrollmentList(HttpServletRequest request) throws Exception {
		return new RedirectWithMsg().redirect(request, "잘못된 주소입니다.", "/");
	}
	// 스터디 가입신청 리스트
	@RequestMapping(value="/study/{url}/enrollment/list")
	public String enrollmentList(HttpServletRequest request,
								@PathVariable(value="url") String url) throws Exception {
		Study study = sService.printOneByUrl(url);
		Member loginMember = (Member)request.getSession().getAttribute("loginUser");
		
		// 로그인 확인
		if (loginMember == null) {
			return new RedirectWithMsg().redirect(request, "로그인이 필요합니다.", "/member/loginView");
		}
		
		// 스터디 url 확인
		if (study == null) {
			return new RedirectWithMsg().redirect(request, "스터디를 찾을 수 없습니다.", "/");
		}
		
		// 스터디 리더가 아니면 return
		if (study.getLeaderNo() != loginMember.getMbNo()) {
			return new RedirectWithMsg().redirect(request, "권한이 없습니다.", "/study/" + url);
		}
		
		ArrayList<EnrollmentWithMember> enrollmentWithMemberList = eService.printAllByStudyNo(study.getStudyNo());
		request.setAttribute("enrollmentWithMemberList", enrollmentWithMemberList);
		
		return "/enrollment/list";
	}
	
	// 스터디 수정 페이지 get
	@RequestMapping(value="/study/modify", method=RequestMethod.GET)
	public String modifyView() {
		return "";
	}
	
	// 스터디 수정 post
	@RequestMapping(value="/study/modify", method=RequestMethod.POST)
	public String modifyStudy() {
		return "";
	}
	
	// 스터디 삭제 post
	@RequestMapping(value="/study/delete", method=RequestMethod.POST)
	public String deleteStudy() {
		return "";
	}
	
}
