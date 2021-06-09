package com.studyus.common.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.google.gson.Gson;
import com.studyus.chat.domain.ChatChannel;
import com.studyus.chat.domain.ChatMessage;
import com.studyus.chat.domain.MessageType;
import com.studyus.chat.store.logic.ChatChannelStore;

public class ChatHandler extends TextWebSocketHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(ChatHandler.class);
	
	@Inject
	ChatChannelStore chatChannelStore;
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage msg) throws Exception {
		logger.info("handleTextMessage: " + msg.getPayload());
		
		Gson gson = new Gson();
		ChatMessage chatMessage = gson.fromJson(msg.getPayload(), ChatMessage.class);
		chatMessage.setMessage(chatMessage.getMessage().replaceFirst("알림:", ""));
		if (chatMessage.getType() == MessageType.OPEN) {
			ChatChannel channel = chatChannelStore.joinOrCreateChannel(session, chatMessage.getStudyUrl(), chatMessage.getNickname());
			channel.handleMessage(session, chatMessage);
			
			// 기존에 채팅중이던 회원들의 닉네임 전송
			ArrayList<String> nicknameList = channel.getAllNicknames();
			for (String n : nicknameList) {
				if (n == chatMessage.getNickname())
					continue;
				ChatMessage tempMessage = new ChatMessage();
				tempMessage.setNickname(n);
				tempMessage.setType(MessageType.EXISTING);
				session.sendMessage(new TextMessage(gson.toJson(tempMessage)));
			}
			
		} else {
			try {
				chatChannelStore.getChanelByUrl(chatMessage.getStudyUrl()).handleMessage(session, chatMessage);;
			} catch (NullPointerException omg) {
				omg.printStackTrace();
			}
		}
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		logger.info("afterConnectionClosed: " + status);
		
		// 해당 세션이 연결되어 있던 채널의 모든 사용자에게 퇴장 메세지를 전송합니다.
		ChatChannel channel = chatChannelStore.findChannelWithSession(session);
		ChatMessage message = new ChatMessage();
		message.setType(MessageType.CLOSE);
		message.setNickname(channel.getNicknameBySession(session));
		channel.handleMessage(session, message);
	}
}
