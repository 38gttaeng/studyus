package com.studyus.study.service.logic;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.studyus.hashtag.service.HashtagService;
import com.studyus.hashtag.store.HashtagStore;
import com.studyus.study.domain.Study;
import com.studyus.study.domain.StudySearchCriteria;
import com.studyus.study.domain.StudySearchResult;
import com.studyus.study.domain.StudyWithDays_dep;
import com.studyus.study.service.StudyService;
import com.studyus.study.store.StudyStore;

@Service
public class StudyServiceImpl implements StudyService { 
	
	@Autowired
	StudyStore sStore;
	
	//TODO 해시태그 store --> service 전
	@Autowired
	HashtagStore hStore;
	@Autowired
	HashtagService hService;
	
	
	@Override
	@Transactional
	public int registerStudy(Study study, String[] hashtagList) {
		try {
			// 스터디 추가 후 id값 저장
			int insertedStudyNo = sStore.insertStudy(study);
			
			if (hashtagList != null) {
				for (int i = 0; i < hashtagList.length; i ++) {
					hStore.insertOneHashtag(hashtagList[i]);
					hStore.insertOneRelation(insertedStudyNo, hashtagList[i]);
				}
			}
			
			return insertedStudyNo; // 성공
		} catch (Exception omg) {
			omg.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			
			return -1; // 실패
		}
	}
	
	@Override
	public int checkUrl(String url) {
		return sStore.checkUrl(url);
	}

	@Override
	public ArrayList<StudySearchResult> printSearchResult(StudySearchCriteria sc) throws Exception {
		return sStore.selectAllBySearch(sc);
	}
	
	@Override
	public ArrayList<Study> printAll() {
		return sStore.selectAll(); 
	}

	@Override
	public ArrayList<Study> printAllByStudyName(String studyName, StudySearchCriteria sc) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 로그인한 사용자가 가입한 모든 스터디를 출력 
	 */
	@Override
	public ArrayList<Study> printAllEnrolledByMemberNo(int memberNo) {
		return sStore.selectAllEnrolledByMemberNo(memberNo);
	}

	@Override
	public Study printOneByNo(int studyNo) {
		return sStore.selectOneByNo(studyNo); 
	}
	
	@Override
	public Study printOneByUrl(String url) {
		return sStore.selectOneByUrl(url);
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

	@Override
	public ArrayList<Study> getStudyListByMbNo(int leaderNo) {
		return sStore.getStudyListByMbNo(leaderNo);
	}
}
