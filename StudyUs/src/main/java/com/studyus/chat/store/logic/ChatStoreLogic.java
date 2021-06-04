package com.studyus.chat.store.logic;

import java.util.ArrayList;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.studyus.chat.domain.ChatMessage;
import com.studyus.chat.store.ChatStore;

@Repository
public class ChatStoreLogic implements ChatStore {
	
	@Autowired
	SqlSession session;
	
	@Override
	public int insertChat(ChatMessage chat) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<ChatMessage> selectAllRecent(int studyNo, int amount) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ChatMessage selectOne(int chatNo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
