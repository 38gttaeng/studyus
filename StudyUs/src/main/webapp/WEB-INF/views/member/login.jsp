<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/resources/css/member/login.css">
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
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
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>