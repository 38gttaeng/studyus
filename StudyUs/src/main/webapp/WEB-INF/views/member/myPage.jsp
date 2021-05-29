<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/resources/css/member/myPage.css">
<title>마이페이지</title>
</head>
<body>
	<!-- menubar -->
	<jsp:include page="/WEB-INF/views/common/menubar.jsp"/>
	<div class="grey-zone"></div>
	<div class="myPage-box">
		<div class="info-zone">
			<img src="/resources/images/${loginUser.mbPhoto}.png" class="rounded-circle" width="100" style="margin-top: -35px; margin-left: 15px;">
			<div class="mem-info">
				<a style="font-weight: bold; font-size: 20px;">${loginUser.mbNickname}</a>님<br>
				스터디 포인트 ${loginUser.mbReputation}
			</div>
			<div class="btn-zone">
				<input type="button" value="내 정보 수정" class="myPage-btn" onclick="location.href='/member/myInfo'">
				&nbsp;&nbsp;
				<input type="button" value="결제 관리" class="myPage-btn" onclick="location.href='/member/purchaseView'">
			</div>
		</div>
		<div class="box-zone">
			<div class="my-study">
				<div class="menu-mark"><span>나의 스터디</span></div>
				
			</div>
			
			<div class="my-calander">
				<div class="menu-mark"><span>나의 일정</span></div>
				
			</div>
		</div>
	</div>
	
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>