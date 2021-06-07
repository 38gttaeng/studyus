//package com.studyus.common.filter;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.AuthenticationServiceException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.util.StringUtils;
//
//public class LoginAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
//	private boolean postOnly = true;
//	
//	public LoginAuthenticationFilter (AuthenticationManager authenticationManager) {
//		super.setAuthenticationManager(authenticationManager);
//	}
//	
//	/*
//	 * 사용자 요청으로부터 정보를 가져온 후, Authentication 객체를 인증 프로세스 객체에게 전달
//	 */
//	@Override
//	public Authentication attemptAuthentication (HttpServletRequest request, HttpServletResponse response) {
//		
//		// post 인지 확인
//		if (postOnly && !request.getMethod().equals("POST")) {
//			throw new AuthenticationServiceException("POST 방식만 Authentication 가능합니다. " + this.getClass().toString());
//		}
//		
//		String username = obtainUsername(request);
//		String password = obtainPassword(request);
//		
//		if (StringUtils.isEmpty(username))
//			username = "";
//		if (StringUtils.isEmpty(password))
//			password = "";
//		
//		username = username.trim();
//		
//		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
//		
//		setDetails(request, authRequest);
//		
//		return this.getAuthenticationManager().authenticate(authRequest);
//	}
//}
