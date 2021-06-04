package com.studyus.common.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.studyus.member.domain.Member;

public class ChatHandler extends TextWebSocketHandler {
	private List<WebSocketSession> sessionList = new ArrayList<WebSocketSession>();
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		sessionList.add(session);
//		Member member = (Member) session.getAttributes().get("loginUser");
//		System.out.println(member.getMbNickname() + "의 입장");
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage msg) throws Exception {
		for (WebSocketSession s : sessionList) {
//			s.sendMessage(new TextMessage(session.getPrincipal().getName() + ": " + msg.getPayload()));
			s.sendMessage(new TextMessage(msg.getPayload()));
		}
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		sessionList.remove(session);
//		Member member = (Member) session.getAttributes().get("loginUser");
//		System.out.println(member.getMbNickname() + "의 퇴장");
	}

}
