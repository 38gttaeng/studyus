//package com.studyus.common.websocket.handler;
//
//import javax.inject.Inject;
//
//import org.springframework.stereotype.Component;
//import org.springframework.web.socket.TextMessage;
//import org.springframework.web.socket.WebSocketSession;
//import org.springframework.web.socket.handler.TextWebSocketHandler;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.studyus.chat.domain.ChatMessage;
//import com.studyus.chat.domain.ChatChannel;
//import com.studyus.chat.store.logic.ChatChannelStore;
//import com.studyus.chat.util.ChatUtils;
//
//import lombok.RequiredArgsConstructor;
//
//@Component
//@RequiredArgsConstructor
//public class WebSocketHandler extends TextWebSocketHandler {
//	
//	@Inject
//	private ChatUtils chatUtils;
//	
////	private final ChatRoomStore chatRoomStore;
////	private final ObjectMapper objectMapper;
//	
//	@Override
//	public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//		String messageString = message.getPayload();
//		System.out.println(messageString);
////		ChatMessage chatMessage = objectMapper.readValue(messageString, ChatMessage.class);
////		chatUtils.handleMessage();
////		ChatRoom chatRoom = chatRoomStore.findRoomById(chatMessage.getChatRoomId());
////		chatRoom.handleMessage(session, chatMessage, objectMapper);
//	}
//}
