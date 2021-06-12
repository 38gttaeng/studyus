package com.studyus.hashtag.store.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.studyus.hashtag.domain.Hashtag;
import com.studyus.hashtag.store.HashtagStore;

@Repository
public class HashtagStoreLogic implements HashtagStore {

	@Autowired
	SqlSession session;
	
	@Override
	public int insertOneHashtag(String hashtag) {
		
		int result = session.selectOne("hashtagMapper.selectHashtagLength", hashtag);
		
		if (result < 1) {
			result = session.insert("hashtagMapper.insertOneHashtag", hashtag);
		}
		
		return result;
	}
	
	@Override
	public int insertOneRelation(int studyNo, String hashtag) {
		
		Map<String, Object> map = new HashMap();
		map.put("studyNo", studyNo);
		map.put("hashtag", hashtag);
		
		int result = session.selectOne("hashtagMapper.selectRelationLength", map);
		
		if (result < 1) {
			result = session.insert("hashtagMapper.insertOneRelation", map);
		}
		
		return result;
	}

	@Override
	public int insertList(int studyNo, ArrayList<String> hashtagList) {
		int result = 0;
		
		for (int i = 0; i < hashtagList.size(); i ++) {
			result += session.insert("", hashtagList.get(i));
		}
		
		return result;
	}

	@Override
	public ArrayList<String> selectAllByStudyNo(int studyNo) {
		return (ArrayList) session.selectList("hashtagMapper.selectAllByStudyNo", studyNo);
	}

}
