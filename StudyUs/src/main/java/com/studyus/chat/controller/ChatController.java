package com.studyus.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.studyus.chat.domain.Chat;
import com.studyus.chat.service.ChatService;

@Controller
public class ChatController {
	
	@Autowired
	ChatService service;

	// 채팅방 입장시, 최근 채팅 리스트 x개 불러오
	@RequestMapping(value="/chat", method=RequestMethod.GET)
	public String getChat(@ModelAttribute int studyNo) {
		return "";
	}
	
	// 채팅 보내기 
	@ResponseBody
	@RequestMapping(value="/chat/send", method=RequestMethod.POST)
	public String sendChat(@ModelAttribute Chat chat) {
		return "";
	}
	
	// 채팅 받기
	@ResponseBody
	@RequestMapping(value="/chat/receive", method=RequestMethod.POST)
	public String receiveChat() {
		return "";
	}
	
}
