package com.studyus.common.handler;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
		
		if (chatMessage.getType() == MessageType.OPEN) {
			ChatChannel channel = chatChannelStore.joinOrCreateChannel(session, chatMessage.getStudyUrl());
			channel.handleMessage(session, chatMessage);
		} else if (chatMessage.getType() == MessageType.CLOSE) {
			ChatChannel channel = chatChannelStore.leaveOrRemoveChannel(session, chatMessage.getStudyUrl());
			channel.handleMessage(session, chatMessage);
		} else {
			try {
				chatChannelStore.getChanelByUrl(chatMessage.getStudyUrl()).handleMessage(session, chatMessage);;
			} catch (NullPointerException omg) {
				omg.printStackTrace();
			}
		}
	}
}
