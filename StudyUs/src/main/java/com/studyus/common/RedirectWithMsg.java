package com.studyus.common;

import javax.servlet.http.HttpServletRequest;

public class RedirectWithMsg {
	
	/**
	 * 
	 * @param request
	 * @param msg
	 * alert창에 띄울 메세지
	 * @param redirectUrl
	 * 메세지를 닫은 후 redirect할 url
	 * @return String
	 */
	public String redirect(HttpServletRequest request, String msg, String redirectUrl) {
		request.setAttribute("msg", msg);
		request.setAttribute("redirectUrl", redirectUrl);
		
		return "redirectWithMsg";
	}
}
