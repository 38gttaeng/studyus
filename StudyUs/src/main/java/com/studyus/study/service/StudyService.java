package com.studyus.study.service;

import java.util.ArrayList;

import com.studyus.study.domain.Study;
import com.studyus.study.domain.StudyPageInfo;
import com.studyus.study.domain.StudyWithDays_dep;

public interface StudyService {
	
	// TODO throws Exception 추가
	
	// 스터디 생성 post
	public int registerStudy(Study study);
	
	// url 중복확인
	public int checkUrl(String name);
	
	// 스터디 검색 결과페이지 get
	public ArrayList<StudyWithDays_dep> printSearchResult(String keyword, String[] hashtags, StudyPageInfo pi);
	
	// 관리자 페이지 스터디 리스트 get
	public ArrayList<StudyWithDays_dep> printAll(StudyPageInfo pi);
	
	// 관리자 페이지 스터디 리스트 이름검색 get
	public ArrayList<StudyWithDays_dep> printAllByStudyName(String studyName, StudyPageInfo pi);
	
	// 스터디 상세 페이지 get
	public Study printOneByNo(int studyNo);
	
	// 스터디 수정 post
	public int modifyStudy(Study study);
	
	// 스터디 삭제 post
	public int deleteStudy(int studyNo);
	
}
