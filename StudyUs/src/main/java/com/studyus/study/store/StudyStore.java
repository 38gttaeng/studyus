package com.studyus.study.store;

import java.util.ArrayList;

import com.studyus.study.domain.Study;
import com.studyus.study.domain.StudyPageInfo;

public interface StudyStore {
	
	public int insertStudy(Study study) throws Exception;
	public int checkUrl(String url);
	public ArrayList<Study> selectAllBySearch(String keyword, String[] hashtags, StudyPageInfo pi);
	public ArrayList<Study> selectAll(StudyPageInfo pi);
	public ArrayList<Study> selectAllByStudyName(String studyName, StudyPageInfo pi);
	public Study selectOneByNo(int studyNo);
	
	// 가입한 스터디 상세 페이지
	public Study selectOneEnrolled(int studyNo, int memberNo);
	public Study selectOneByUrl(String studyUrl);
	public int updateStudy(Study study);
	public int deleteStudy(int studyNo);
	
}
