<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/resources/css/member/myInfo.css">
<title>내 정보 수정 : StudyUs</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.4.1.min.js"></script>
</head>
<body>
	<!-- menubar -->
	<jsp:include page="/WEB-INF/views/common/menubar.jsp"/>
	<div class="grey-zone"></div>
	<div class="myPage-box">
		<div class="info-zone">
			<img src="/resources/images/${loginUser.mbPhoto}.png" class="rounded-circle" width="100" style="margin-top: -10px; margin-left: 15px;">
			<div class="mem-info">
				<a style="font-weight: bold; font-size: 20px;">${loginUser.mbNickname}</a>님<br>
			</div>
		</div>
		<div class="info-box">
			<div class="main-text">프로필</div>
			<hr width="470" style="margin-bottom: 30px;">
			<div class="info-view">
				<form action="/member/modify" method="post">
					<c:set var="status" value="${loginUser.mbStatus}"></c:set>
					<c:if test="${status==2}">
						<div class="naver-box">
							<img src="/resources/images/btnG_아이콘사각.png" width="50">&nbsp;&nbsp;
							<span class="naver-text">네이버 회원입니다.</span>
						</div>
						<div class="input-zone" style="display: none;">
							<label for="mbId">아이디</label>
							<input type="text" name="mbId" value="${loginUser.mbId}" readonly class="form-input">
							<label for="mbPassword">비밀번호</label>
							<input type="password" name="mbPassword" value="${loginUser.mbPassword}" class="form-input">
							<label for="mbPwdcheck">비밀번호 확인</label>
							<input type="password" name="mbPwdcheck" value="${loginUser.mbPassword}" class="form-input">
						</div>
					</c:if>
					<div class="input-profile-zone">
						<label for="mbPhoto">프로필 이미지</label>
						<img src="/resources/images/${loginUser.mbPhoto}.png" class="profile-img" id="profile-img">
						<select size="1" name="mbPhoto" class="img-select" id="img-select">
							<option value="none" disabled selected>==선택==</option>
							<option value="1">지구</option>
							<option value="2">보라 행성</option>
							<option value="3">목성</option>
							<option value="4">수성</option>
							<option value="5">화성</option>
						</select>
					</div>
					<c:if test="${status==1}">
						<div class="input-zone">
							<label for="mbId">아이디</label>
							<input type="text" name="mbId" value="${loginUser.mbId}" readonly class="form-input">
						</div>
						<div class="input-zone">
							<label for="mbPassword">비밀번호</label>
							<input type="password" name="mbPassword" value="${loginUser.mbPassword}" class="form-input">
						</div>
						<div class="input-zone">
							<label for="mbPwdcheck">비밀번호 확인</label>
							<input type="password" name="mbPwdcheck" value="${loginUser.mbPassword}" class="form-input">
						</div>
					</c:if>
					<div class="input-zone">
						<label for="mbName">이름</label>
						<input type="text" name="mbName" value="${loginUser.mbName}" class="form-input">
					</div>
					<div class="input-zone">
						<label for="mbNickname">닉네임</label>
						<input type="text" name="mbNickname" value="${loginUser.mbNickname}" class="form-input">
					</div>
					<div class="input-zone">
						<label for="mbEmail">이메일</label>
						<input type="text" name="mbEmail" value="${loginUser.mbEmail}" class="form-input">
					</div>
					<div class="input-zone">
						<label for="mbPhone">휴대폰 번호</label>
						<input type="text" name="mbPhone" value="${loginUser.mbPhone}" class="form-input">
					</div>
					<input type="text" name="mbStatus" value="${loginUser.mbStatus}" style="display: none;">
					<input type="text" name="mbPhoto" value="${loginUser.mbPhoto}" style="display: none;">
					<input type="text" name="mbPhoto" value="${loginUser.mbReputation}" style="display: none;">
					<div class="btn-box">
						<input type="button" value="이전" onclick="location.href='/member/myPage'" class="myInfo-btn1">
						<input type="submit" value="수정" class="myInfo-btn2">
						<c:if test="${loginUser.mbStatus != 2}">
							<div class="delete-zone">
								<a href="/member/deleteView">회원탈퇴</a>
							</div>
						</c:if>
					</div>
				</form>
			</div>
		</div>
	</div>
	
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>