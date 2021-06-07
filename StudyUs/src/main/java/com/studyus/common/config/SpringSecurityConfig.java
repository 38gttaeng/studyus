//package com.studyus.common.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
//
//import com.studyus.common.handler.CustomLogoutSuccessHandler;
//
//@EnableWebSecurity
//public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
//
//	private AuthenticationProvider authenticationProvider;
//	
//	// 생성자
//	public SpringSecurityConfig(AuthenticationProvider authenticationProvider) {
//		this.authenticationProvider = authenticationProvider;
//	}
//	
//	// 스프링 시큐리티가 사용자를 인증하는 방법이 담긴 객체
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		// AuthenticationProvider 구현체
//		auth.authenticationProvider(authenticationProvider);
//	}
//	
//	/*
//	 * 스프링 시큐리티를 적용하지 않을 url
//	 */
//	@Override
//	public void configure(WebSecurity web) throws Exception {
//		web.ignoring()
//			.antMatchers("/") // 홈 페이지
//			.antMatchers("/resources/**") // js, css, 업로드된 이미지 등이 담긴 폴더
//			.antMatchers("/study/search") // 스터디 검색 페이지
//			;
//	}
//	
//	/*
//	 * 스프링 시큐리티를 적용할 url
//	 */
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests()
//			.antMatchers("/chat").hasRole("ENROLLED")
//		.and().logout()
//			.logoutUrl("/member/logout")
//			.logoutSuccessHandler(logoutSuccessHandler())
//		
//		;
//	}
//	
//	/*
//	 * 
//	 */
////	@Bean
////	public AuthenticationSuccessHandler authenticationSuccessHandler() {
////		CustomAuthenticationSuccessHandler successHandler = new CustomAuthenticationSuccessHandler();
////	}
//	
//	/*
//	 * 로그아웃 성공시 실행
//	 */
//	@Bean
//	public LogoutSuccessHandler logoutSuccessHandler() {
//		CustomLogoutSuccessHandler logoutSuccessHandler = new CustomLogoutSuccessHandler();
//		logoutSuccessHandler.setDefaultTargetUrl("/member/logout");
//		return logoutSuccessHandler;
//	}
//}
