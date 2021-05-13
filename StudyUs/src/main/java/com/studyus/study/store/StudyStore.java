package com.studyus.study.store;

import java.util.ArrayList;

import com.studyus.study.domain.Study;
import com.studyus.study.domain.StudyPageInfo;

public interface StudyStore {
	
	public int insertStudy(Study study);
	public ArrayList<Study> selectAllBySearch(String keyword, String[] hashtags, StudyPageInfo pi);
	public ArrayList<Study> selectAll(StudyPageInfo pi);
	public ArrayList<Study> selectAllByStudyName(String studyName, StudyPageInfo pi);
	public Study selectOneByNo(int studyNo);
	public Study selectOneByUrl(String studyUrl);
	public int modifyStudy(Study study);
	public int deleteStudy(int studyNo);
	
}
