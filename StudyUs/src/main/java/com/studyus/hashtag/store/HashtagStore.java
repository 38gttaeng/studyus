package com.studyus.hashtag.store;

import java.util.ArrayList;

public interface HashtagStore {

	// insert
	public int insertOne(String hashtag) throws Exception;
	
	// 여러개 insert
	public int insertList(ArrayList<String> hashtagList) throws Exception;
	
	// select list by study no
	public ArrayList<String> selectAllByStudyNo(int studyNo) throws Exception;
	
}
