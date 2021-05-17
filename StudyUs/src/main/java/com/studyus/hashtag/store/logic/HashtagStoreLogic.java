package com.studyus.hashtag.store.logic;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.studyus.hashtag.store.HashtagStore;

@Repository
public class HashtagStoreLogic implements HashtagStore {

	@Autowired
	SqlSession session;
	
	@Override
	public int insertOne(String hashtag) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertList(ArrayList<String> hashtagList) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<String> selectAllByStudyNo(int studyNo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
