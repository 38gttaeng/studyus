package com.studyus.study.store;

import java.util.ArrayList;

import com.studyus.study.domain.Study;
import com.studyus.study.domain.StudySearchCriteria;
import com.studyus.study.domain.StudySearchResult; 

public interface StudyStore {
	
	/**
	 * study를 데이터베이스에 저장한 후, 해당 스터디의 시퀀스 값을 반환합니다.
	 * @param study
	 * @return studyNo
	 * @throws Exception
	 */
	public int insertStudy(Study study) throws Exception;
	public int checkUrl(String url);
	public ArrayList<StudySearchResult> selectAllBySearch(StudySearchCriteria sc) throws Exception;
	public ArrayList<Study> selectAll(); 
	public ArrayList<Study> selectAllByStudyName(String studyName, StudySearchCriteria pi);
	public Study selectOneByNo(int studyNo);
	public ArrayList<Study> selectAllEnrolledByMemberNo(int memberNo);
	
	// 가입한 스터디 상세 페이지
	public Study selectOneEnrolled(int studyNo, int memberNo);
	public Study selectOneByUrl(String url);
	public int updateStudy(Study study);
	public int deleteStudy(int studyNo);
	public int selectStudyNoByUrl(String url);
	
	// 팀장 번호로 스터디 리스트 출력
	public ArrayList<Study> getStudyListByMbNo(int leaderNo);
	// 대시보드에 스터디 날짜 출력
	public ArrayList<Study> selectStudyWeek(Study study);
}
