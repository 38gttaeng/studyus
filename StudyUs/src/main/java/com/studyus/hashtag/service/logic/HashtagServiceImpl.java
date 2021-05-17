package com.studyus.hashtag.service.logic;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studyus.hashtag.service.HashtagService;
import com.studyus.hashtag.store.HashtagStore;

@Service
public class HashtagServiceImpl implements HashtagService {
	
	@Autowired
	HashtagStore hStore;

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
	public ArrayList<String> printAllByStudyNo(int studyNo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
