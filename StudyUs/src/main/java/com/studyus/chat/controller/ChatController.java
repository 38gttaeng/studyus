package com.studyus.chat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ChatController {
	
	@RequestMapping(value="/chat")
	public String chat(Model model) {
		return "chat/chat-develop";
	}
	
	// 채팅 페이지
//	@RequestMapping(value="/study/{url}/chat")
//	public String Chat(Model model, 
//						HttpServletRequest request, 
//						HttpSession session,
//						@PathVariable String url) throws Exception {
//		Member member = (Member)session.getAttribute("loginUser");
//		if (member == null) {
//			return new RedirectWithMsg().redirect(request, "로그인이 필요합니다.", "/member/loginView");
//		}
//		
//		ChatRoom room = chatRoomStore.findRoomById(url);
//		
//		model.addAttribute("room", room);
//		model.addAttribute("memberName", member.getMbNickname());
//		return "chat/chat";
//	}
	
	@RequestMapping(value="/study/{url}/chat/new")
	public String makeChatRoom(Model model) {
		return "chat/new";
	}
	
}
