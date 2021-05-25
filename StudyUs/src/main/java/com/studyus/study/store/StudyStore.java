package com.studyus.study.store;

import java.util.ArrayList;

import com.studyus.study.domain.Study;
import com.studyus.study.domain.StudyPageInfo;

public interface StudyStore {
	
	/**
	 * study를 데이터베이스에 저장한 후, 해당 스터디의 시퀀스 값을 반환합니다.
	 * @param study
	 * @return studyNo
	 * @throws Exception
	 */
	public int insertStudy(Study study) throws Exception;
	public int checkUrl(String url);
	public ArrayList<Study> selectAllBySearch(String keyword, String[] hashtags, StudyPageInfo pi);
	public ArrayList<Study> selectAll(StudyPageInfo pi);
	public ArrayList<Study> selectAllByStudyName(String studyName, StudyPageInfo pi);
	public Study selectOneByNo(int studyNo);
	public ArrayList<Study> selectAllEnrolledByMemberNo(int memberNo);
	
	// 가입한 스터디 상세 페이지
	public Study selectOneEnrolled(int studyNo, int memberNo);
	public Study selectOneByUrl(String url);
	public int updateStudy(Study study);
	public int deleteStudy(int studyNo);
	
}
