package com.studyus.hashtag.store;

import java.util.ArrayList;

public interface HashtagStore {

	// insert Hashtag
	public int insertOneHashtag(String hashtag);
	
	// insert Relation
	public int insertOneRelation(int studyNo, String hashtag);
	
	// 여러개 insert
	public int insertList(int studyNo, ArrayList<String> hashtagList);
	
	// select list by study no
	public ArrayList<String> selectAllByStudyNo(int studyNo);
	
}
