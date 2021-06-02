<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.security.SecureRandom" %>
<%@ page import="java.math.BigInteger" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/resources/css/member/login.css">
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script type="text/javascript" src="/resources/js/naverLogin.js"></script>
<title>로그인</title>
</head>
<body>
	<div class="user_card">
		<div class="img-zone">
			<img alt="#" src="#">
		</div>
		<div class="login-zone">
			<div class="login-box">
				<div class="home-link">
					<a href="/">StudyUs</a>
				</div>
				<form action="/member/login" method="post">
					<div class="input-group" id="input1">
						<div class="input-group-append">
							<span class="input-group-text">
								<img alt="#" src="../../resources/images/user.png" width="15px" height="15px">
							</span>
						</div>
						<input type="text" name="mbId" class="form-control input_user" placeholder="Username">
					</div>
					<div class="input-group" id="input2">
						<div class="input-group-append">
							<span class="input-group-text">
								<img alt="#" src="../../resources/images/key.png" width="15px" height="15px">
							</span>
						</div>
						<input type="password" name="mbPassword" class="form-control input_pass" placeholder="비밀번호">
					</div>
					<input type="submit" class="login-btn" value="로그인">
					<div class="form-group">
						<a href="/member/findView">아이디/비밀번호 찾기</a>&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="/member/enrollView">회원가입</a>
						<%
						   String clientId = "rv06dkF1i6Hm34pkpaRi";
						   String redirectURI = URLEncoder.encode("http://localhost:8888/member/callback", "UTF-8");
						   SecureRandom random = new SecureRandom();
						   String state = new BigInteger(130, random).toString();
						   String apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
						   apiURL += "&client_id=" + clientId;
						   apiURL += "&redirect_uri=" + redirectURI;
						   apiURL += "&state=" + state;
						   session.setAttribute("state", state);
						%>
						<a href="<%=apiURL%>"><img height="50" src="http://static.nid.naver.com/oauth/small_g_in.PNG"/></a>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>