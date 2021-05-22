package com.studyus.study.service.logic;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.studyus.hashtag.store.HashtagStore;
import com.studyus.study.domain.Study;
import com.studyus.study.domain.StudyPageInfo;
import com.studyus.study.domain.StudyWithDays_dep;
import com.studyus.study.service.StudyService;
import com.studyus.study.store.StudyStore;

@Service
public class StudyServiceImpl implements StudyService {
	
	@Autowired
	StudyStore sStore;
	
	@Autowired
	HashtagStore hStore;
	
	
	@Override
	@Transactional
	public int registerStudy(Study study, ArrayList<String> hashtagList) {
		try {
			// 스터디 추가 후 id값 저장
			int insertedStudyNo = sStore.insertStudy(study);
			
			if (hashtagList != null) {
				for (int i = 0; i < hashtagList.size(); i ++) {
					hStore.insertOneHashtag(hashtagList.get(i));
					hStore.insertOneRelation(insertedStudyNo, hashtagList.get(i));
				}
			}
			
			return 1; // 성공
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
	public ArrayList<StudyWithDays_dep> printSearchResult(String keyword, String[] hashtags, StudyPageInfo pi) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public ArrayList<StudyWithDays_dep> printAll(StudyPageInfo pi) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<StudyWithDays_dep> printAllByStudyName(String studyName, StudyPageInfo pi) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Study printOneByNo(int studyNo) {
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
