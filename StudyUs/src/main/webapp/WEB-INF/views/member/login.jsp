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
<script type="text/javascript" src="http://code.jquery.com/jquery-3.4.1.min.js"></script>
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
						<input type="text" name="mbId" id="mbId" class="form-control input_user" placeholder="Username">
					</div>
					<div class="input-group" id="input2">
						<div class="input-group-append">
							<span class="input-group-text">
								<img alt="#" src="../../resources/images/key.png" width="15px" height="15px">
							</span>
						</div>
						<input type="password" name="mbPassword" id="mbPassword" class="form-control input_pass" placeholder="비밀번호">
					</div>
					<input class="login-btn" value="로그인">
					<div class="form-group">
						<a href="/member/findView">아이디/비밀번호 찾기</a>&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="/member/enrollView">회원가입</a>
					</div>
				</form>
			</div>
		</div>
		<div class="social-login">
			<div class="text-zone">
				<span class="main-text">소셜 로그인</span><br>
				<span class="sub-text">번거로운 회원가입 없이 간편하게</span>
			</div>
			<div class="naver_id_login"><a href="${naver_url}">
			<img width="223" src="/resources/images/btnG_완성형.png"/></a></div>
		</div>
	</div>
	
	<script>
	$('.login-btn').on("click", function() {
		var mbId = $("#mbId").val();
		var mbPassword = $("#mbPassword").val();
		$.ajax({
			type : "POST",
			url : "/member/login",
			data : { mbId : mbId,
					 mbPassword : mbPassword},
			success : function(member) {
				if (member == null) {
					alert('아이디 또는 비밀번호가 다릅니다.')
				} else {
					window.location.href = '/';
				}
			}, error : function() {
				console.log("전송 실패");
			}
		})
	});
	</script>
</body>
</html>