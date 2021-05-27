package com.studyus.study.service;

import java.util.ArrayList;

import com.studyus.study.domain.Study;
import com.studyus.study.domain.StudySearchCriteria;
import com.studyus.study.domain.StudyWithDays_dep;

public interface StudyService {
	
	// TODO throws Exception 추가
	
	// 스터디 생성 post
	public int registerStudy(Study study, String[] hashtagList);
	
	// url 중복확인
	public int checkUrl(String name);
	
	// 스터디 검색 결과페이지 get
	public StudySearchCriteria printSearchResult(StudySearchCriteria sc);
	
	// 관리자 페이지 스터디 리스트 get
	public ArrayList<Study> printAll(StudySearchCriteria sc);
	
	// 관리자 페이지 스터디 리스트 이름검색 get
	public ArrayList<Study> printAllByStudyName(String studyName, StudySearchCriteria sc);
	
	// 가입한 스터디의 번호, 이름, url을 불러옴
	public ArrayList<Study> printAllEnrolledByMemberNo(int memberNo);
	
	// 스터디 상세 페이지 get
	public Study printOneByNo(int studyNo);
	
	// 스터디 상세 페이지 get by url
	public Study printOneByUrl(String url);
	
	// 스터디 수정 post
	public int modifyStudy(Study study);
	
	// 스터디 삭제 post
	public int deleteStudy(int studyNo);
	
}
