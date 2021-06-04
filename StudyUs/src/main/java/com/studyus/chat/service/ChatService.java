package com.studyus.chat.service;

import java.util.ArrayList;

import com.studyus.chat.domain.ChatMessage;

public interface ChatService {
	
	// 채팅 입력
	public int insertChat(ChatMessage chat) throws Exception;
	
	// 스터디 채팅 입장시 최근 x개 채팅 출력
	public ArrayList<ChatMessage> printAllRecent(int studyNo) throws Exception;
	
	// 수신된 채팅 select
	public ChatMessage printOne(int chatNo) throws Exception;
	
}
