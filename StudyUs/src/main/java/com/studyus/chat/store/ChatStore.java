package com.studyus.chat.store;

import java.util.ArrayList;

import com.studyus.chat.domain.Chat;

public interface ChatStore {

	// 채팅 입력
	public int insertChat(Chat chat) throws Exception;
	
	// 스터디 채팅 입장시 최근 amount개의 채팅 출력
	public ArrayList<Chat> selectAllRecent(int studyNo, int amount) throws Exception;
	
	// 수신된 채팅 select
	public Chat selectOne(int chatNo) throws Exception;
	
}
