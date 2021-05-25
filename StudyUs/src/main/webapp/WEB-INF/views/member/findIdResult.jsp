<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/resources/css/member/findIdPwd.css">
<script type="text/javascript" src="http://code.jquery.com/jquery-3.4.1.min.js"></script>
<title>아이디/비밀번호 찾기</title>
</head>
<body>
	<div class="find-box">
		<div class="find-zone">
			<div class="home-link">
					<a href="/">StudyUs</a>
			</div>
			<div class="select-zone">
				<input type="button" value="아이디 찾기" class="page change id">
				<input type="button" value="비밀번호 찾기" class="page change pwd">
			</div>
			<div class="line"></div>
			<div class="result-zone id">
				<span class="main-text">이메일 정보로 아이디 찾기</span><br>
				<span class="sub-text">회원가입 시 입력한 정보로 찾을 수 있습니다.</span><br>
				<hr width="380px" style="margin-top: -10px; margin-bottom: 20px;">
				<div class="result">
					<a style="font-weight: bold">${findId.mbName}</a>님의 아이디 : <br>
					<h1 style="color: #6927FF">${findId.mbId}</h1>
					<form action="/member/loginView">
						<input type="submit" value="로그인" class="submit-btn"/>
					</form>
				</div>
			</div>
		</div>
	</div>
	
	<script>
		$(".page.change.id").addClass("selected");
		$(".page.change.pwd").addClass("common");
	</script>
</body>
</html>