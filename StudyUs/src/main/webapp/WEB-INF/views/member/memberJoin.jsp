<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/resources/css/member/memberJoin.css">
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script type="text/javascript" src="http://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="/resources/js/memberJoin.js"></script>
<title>회원가입</title>
<style type="text/css">
	span.ok{
		display : none;
		color:green
	}
	span.error{
		display : none;
		color:red
	}
</style>
</head>
<body>
	<div class="user_card">
		<div class="home-link" ><a href="/">StudyUs</a></div>
		<div class="enroll-box">
			<form action="/member/register" method="post">
				<div class="info-box">
					<div class="input-zone">
						<label for="mbId">아이디</label>
						<input type="text" name="mbId" id="mbId" placeholder="아이디는 4~16자 이내 영문 및 숫자" maxlength="16"><br>
						<span class="id ok">이 아이디는 사용 가능합니다</span>
						<span class="id error">이미 사용 중인 아이디 입니다</span><br>
					</div>
					<div class="input-zone">
						<label for="mbPassword">비밀번호</label>
						<input type="password" name="mbPassword" id="mbPassword" placeholder="비밀번호는 8~20자 이내 영문 및 숫자" maxlength="20">
					</div>
					<div class="input-zone">
						<label for="mbPwdCheck">비밀번호 확인</label>
						<input type="password" name="mbPwdCheck" id="mbPwdCheck" placeholder="비밀번호 확인"><br>
						<span class="pwd ok">비밀번호가 일치합니다.</span>
						<span class="pwd error">비밀번호가 일치하지 않습니다.</span><br>
					</div>
					<div class="input-zone">
						<label for="mbName">이름</label>
						<input type="text" name="mbName" id="mbName" placeholder="실명을 입력해주세요">
					</div>
					<div class="input-zone">
						<label for="mbNickname">닉네임</label>
						<input type="text" name="mbNickname" id="mbNickname" placeholder="닉네임"><br>
						<span class="nick ok">이 닉네임은 사용 가능합니다</span>
						<span class="nick error">이미 사용 중인 닉네임 입니다</span><br>
					</div>
					<div class="input-zone">
						<label for="mbEmail">이메일</label>
						<input type="text" name="mbEmail" id="mbEmail" placeholder="이메일"><br>
						<span id="email-msg">이메일 인증이 필요하니 정확히 입력해주세요</span><br>
					</div>
					<div class="input-zone">
						<label for="mbPhone">휴대폰 번호</label>
						<input type="text" name="mbPhone" id="mbPhone" placeholder="'-' 구분없이 입력"><br>
					</div>
					<div class="btn-zone">
						<input type="submit" value="가입하기" class="enroll-btn">
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>