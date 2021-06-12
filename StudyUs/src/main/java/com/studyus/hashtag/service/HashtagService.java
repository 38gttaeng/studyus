package com.studyus.hashtag.service;

import java.util.ArrayList;

import com.studyus.hashtag.domain.Hashtag;

public interface HashtagService {
	
	// 1개 insert
	public int insertOne(String hashtag) throws Exception;
	
	// 여러개 insert
	public int insertList(ArrayList<String> hashtagList) throws Exception;
	
	/**
	 * 스터디에 연결된 모든 해시태그를 반환합니다.
	 * @author 김동현
	 * @param studyNo
	 * @return
	 * ArrayList<<String>>
	 * 해시태그 리스트
	 * @throws Exception
	 */
	public ArrayList<String> printAllByStudyNo(int studyNo) throws Exception;
	
}
