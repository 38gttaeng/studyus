package com.studyus.study.store.logic;

import java.util.ArrayList;

import com.studyus.study.domain.Study;
import com.studyus.study.domain.StudyPageInfo;
import com.studyus.study.store.StudyStore;

public class StudyStoreLogic implements StudyStore {

	@Override
	public int insertStudy(Study study) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Study> selectAllBySearch(String keyword, String[] hashtags, StudyPageInfo pi) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Study> selectAll(StudyPageInfo pi) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Study> selectAllByStudyName(String studyName, StudyPageInfo pi) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Study selectOneByNo(int studyNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Study selectOneByUrl(String studyUrl) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int modifyStudy(Study study) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteStudy(int studyNo) {
		// TODO Auto-generated method stub
		return 0;
	}

}
