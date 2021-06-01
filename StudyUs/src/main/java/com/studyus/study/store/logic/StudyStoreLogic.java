package com.studyus.study.store.logic;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.studyus.study.domain.Study;
import com.studyus.study.domain.StudySearchCriteria;
import com.studyus.study.domain.StudySearchResult;
import com.studyus.study.store.StudyStore;

@Repository
public class StudyStoreLogic implements StudyStore {
	
	@Autowired
	SqlSession sqlSession;

	@Override
	public int insertStudy(Study study) throws Exception {
		sqlSession.insert("studyMapper.insertStudy", study);
		return study.getStudyNo();
	}
	
	@Override
	public int checkUrl(String url) {
		return sqlSession.selectOne("studyMapper.checkUrl", url);
	}

	@Override
	public ArrayList<StudySearchResult> selectAllBySearch(StudySearchCriteria sc) throws Exception {
//		System.out.println(sc.toString());
		List<StudySearchResult> resultList = sqlSession.selectList("studyMapper.selectAllSearch", sc);
//		for (StudySearchResult s : resultList) {
//			System.out.println(s);
//		}
	
		return (ArrayList<StudySearchResult>) resultList;
	}

	@Override
	public ArrayList<Study> selectAll(StudySearchCriteria pi) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Study> selectAllByStudyName(String studyName, StudySearchCriteria pi) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Study selectOneByNo(int studyNo) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public ArrayList<Study> selectAllEnrolledByMemberNo(int memberNo) {
		List<Study> list = sqlSession.selectList("studyMapper.selectAllEnrolledByMemberNo", memberNo);
		return (ArrayList<Study>) list;
	}
	
	@Override
	public Study selectOneEnrolled(int studyNo, int memberNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Study selectOneByUrl(String url) {
		return sqlSession.selectOne("studyMapper.selectOneByUrl", url);
	}

	@Override
	public int updateStudy(Study study) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteStudy(int studyNo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int selectStudyNoByUrl(String url) {
		return ((Study)sqlSession.selectOne("studyMapper.selectOneByUrl", url)).getStudyNo();
	}


}
