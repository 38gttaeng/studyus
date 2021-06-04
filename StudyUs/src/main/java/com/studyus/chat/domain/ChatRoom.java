package com.studyus.chat.domain;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatRoom {
	private String roomId;
	private String name;
	private Set<WebSocketSession> sessions = new HashSet<>();
	
	public static ChatRoom create(String name) {
		ChatRoom chatRoom = new ChatRoom();
		chatRoom.roomId = UUID.randomUUID().toString();
		chatRoom.name = name;
		
		return chatRoom;
	}
	
	public void handleMessage(WebSocketSession session, ChatMessage chatMessage, ObjectMapper objectMapper) throws Exception {
		if (chatMessage.getType() == MessageType.ENTER) {
			sessions.add(session);
			chatMessage.setMessage(chatMessage.getWriter() + " 님이 입장하셨습니다.");
		} else if (chatMessage.getType() == MessageType.LEAVE) {
			sessions.remove(session);
			chatMessage.setMessage(chatMessage.getWriter() + " 님이 퇴장하셨습니다.");
		} else {
			chatMessage.setMessage(chatMessage.getWriter() + ": " + chatMessage.getMessage());
		}
		send(chatMessage, objectMapper);
	}
	
	/*
	 * 방 안의 모든 세션에게 chatMessage 전송
	 */
	private void send(ChatMessage chatMessage, ObjectMapper objectMapper) throws Exception {
		TextMessage textMessage = new TextMessage(objectMapper.writeValueAsString(chatMessage.getMessage()));
		for (WebSocketSession s : sessions) {
			s.sendMessage(textMessage);
		}
	}
}
