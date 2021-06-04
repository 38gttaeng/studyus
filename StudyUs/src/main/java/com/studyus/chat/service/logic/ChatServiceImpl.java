package com.studyus.chat.service.logic;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studyus.chat.domain.ChatMessage;
import com.studyus.chat.service.ChatService;
import com.studyus.chat.store.ChatStore;

@Service
public class ChatServiceImpl implements ChatService {
	
	@Autowired
	ChatStore cStore;

	@Override
	public int insertChat(ChatMessage chat) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<ChatMessage> printAllRecent(int studyNo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ChatMessage printOne(int chatNo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
