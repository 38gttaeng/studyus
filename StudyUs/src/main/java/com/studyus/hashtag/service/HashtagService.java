package com.studyus.hashtag.service;

import java.util.ArrayList;

public interface HashtagService {
	
	// 1개 insert
	public int insertOne(String hashtag) throws Exception;
	
	// 여러개 insert
	public int insertList(ArrayList<String> hashtagList) throws Exception;
	
	// 스터디 번호로 리스트 출력
	public ArrayList<String> printAllByStudyNo(int studyNo) throws Exception;
	
}
