package com.studyus.chat.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.studyus.chat.domain.ChatMessage;
import com.studyus.chat.domain.ChatRoom;
import com.studyus.chat.service.ChatService;
import com.studyus.chat.store.logic.ChatRoomStore;
import com.studyus.common.RedirectWithMsg;
import com.studyus.member.domain.Member;

@Controller
public class ChatController {
	
	@Autowired
	ChatService service;
	
	@Autowired
	ChatRoomStore chatRoomStore;
	
	// 채팅 페이지
	@RequestMapping(value="/study/{url}/chat")
	public String Chat(Model model, 
						HttpServletRequest request, 
						HttpSession session,
						@PathVariable String url) throws Exception {
		Member member = (Member)session.getAttribute("loginUser");
		if (member == null) {
			return new RedirectWithMsg().redirect(request, "로그인이 필요합니다.", "/member/loginView");
		}
		
		ChatRoom room = chatRoomStore.findRoomById(url);
		
		model.addAttribute("room", room);
		model.addAttribute("memberName", member.getMbNickname());
		return "chat/chat";
	}
	
	@RequestMapping(value="/study/{url}/chat/new")
	public String makeChatRoom(Model model) {
		return "chat/chat";
	}
}
