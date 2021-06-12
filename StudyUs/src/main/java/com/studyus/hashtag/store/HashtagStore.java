package com.studyus.hashtag.store;

import java.util.ArrayList;

import com.studyus.hashtag.domain.Hashtag;

public interface HashtagStore {

	// insert Hashtag
	public int insertOneHashtag(String hashtag);
	
	// insert Relation
	public int insertOneRelation(int studyNo, String hashtag);
	
	// 여러개 insert
	public int insertList(int studyNo, ArrayList<String> hashtagList);
	
	/**
	 * 스터디에 연결된 모든 해시태그를 반환합니다.
	 * @author 김동현
	 * @param studyNo
	 * @return
	 * 해시태그 리스트
	 */
	public ArrayList<String> selectAllByStudyNo(int studyNo);
	
}
