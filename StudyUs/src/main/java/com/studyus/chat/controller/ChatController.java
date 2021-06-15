package com.studyus.chat.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.studyus.common.RedirectWithMsg;

@Controller
public class ChatController {
	
	@RequestMapping(value="/study/{url}/chat")
	public String chatView(Model model,
							HttpServletRequest request,
							HttpSession session,
							@PathVariable String url) {
		if (session.getAttribute("loginUser") == null) {
			return new RedirectWithMsg().redirect(request, "로그인이 필요합니다.", "/");
		}
		
		if (session.getAttribute("study") == null)  {
			return new RedirectWithMsg().redirect(request, "접속 경로가 올바르지 않습니다.", "/");
		}
		
		return "chat/chat";
	}
	
}
