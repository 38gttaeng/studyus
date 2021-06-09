package com.studyus.chat.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatChannel {
	private String url;
	private Map<WebSocketSession, String> sessions = new HashMap<>();
	
	public static ChatChannel create(String url) {
		ChatChannel chatChannel = new ChatChannel();
		chatChannel.url = url;
		
		return chatChannel;
	}
	
	/**
	 * 채널에 접속한 모든 session에게 chatMessage를 전송
	 * chatMessage의 MessageType에 따라 입장, 퇴장, 일반 메세지로 분류되어 처리
	 * @param session
	 * @param chatMessage
	 * @throws Exception
	 */
	public void handleMessage(WebSocketSession session, ChatMessage chatMessage) throws Exception {
		if (chatMessage.getType() == MessageType.OPEN) {
			sessions.put(session, chatMessage.getNickname());
			chatMessage.setMessage("알림:" + chatMessage.getNickname() + " 님이 입장하셨습니다.");
		} else if (chatMessage.getType() == MessageType.CLOSE) {
			sessions.remove(session);
			chatMessage.setMessage("알림:" + chatMessage.getNickname() + " 님이 퇴장하셨습니다.");
		} else {
			chatMessage.setMessage(chatMessage.getMessage());
		}
		send(chatMessage);
	}
	
	/**
	 * 채널의 모든 세션에게 message 전송
	 * @param chatMessage
	 * @throws Exception
	 */
	private void send(ChatMessage chatMessage) throws Exception {
		Gson gson = new Gson();
		TextMessage textMessage = new TextMessage(gson.toJson(chatMessage));
		for (WebSocketSession s : sessions.keySet()) {
			if (s.isOpen()) {
				s.sendMessage(textMessage);
			}
		}
	}
	
	/**
	 * 해당 세션이 사용중인 닉네임을 반환합니다.
	 * @param nickname
	 * @return
	 */
	public String getNicknameBySession (WebSocketSession session) {
		return sessions.get(session);
	}
	
	/**
	 * 채널에 연결된 모든 세션을 반환합니다.
	 * @return
	 * 연결된 세션이 0 이면 null, 1 이상이면 모든 session
	 */
	public ArrayList<WebSocketSession> getAllSessions () {
		if (sessions == null || sessions.isEmpty()) {
			return null;
		}
		return (ArrayList)sessions;
	}
	
	/**
	 * 채널에 접속중인 모든 사용자들의 닉네임을 반환합니다.
	 * @return
	 * 사용자 닉네임
	 */
	public ArrayList<String> getAllNicknames () {
		return new ArrayList<>(sessions.values());
	}
	
	public void addSession (WebSocketSession session, String nickname) {
		sessions.put(session, nickname);
	}
	
	public void removeSession (WebSocketSession session) {
		sessions.remove(session);
	}
}
