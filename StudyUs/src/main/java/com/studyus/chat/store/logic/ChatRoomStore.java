package com.studyus.chat.store.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.studyus.chat.domain.ChatRoom;

@Repository
public class ChatRoomStore {
	private Map<String, ChatRoom> chatRoomMap;
	
	@PostConstruct
	private void init() {
		chatRoomMap = new LinkedHashMap<>();
	}
	
	public ArrayList<ChatRoom> getAllRooms() {
		ArrayList chatRooms = new ArrayList<>(chatRoomMap.values());
		
		// 최근에 만든 것이 앞으로 오도록 역순 정렬
		Collections.reverse(chatRooms);
		return chatRooms;
	}
	
	public ChatRoom findRoomById(String roomId) {
		return chatRoomMap.get(roomId);
	}
	
	public ChatRoom createChatRoom(String name) {
		ChatRoom chatRoom = ChatRoom.create(name);
		chatRoomMap.put(chatRoom.getRoomId(), chatRoom);
		return chatRoom;
	}
}
