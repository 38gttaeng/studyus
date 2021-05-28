<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/resources/css/member/purchase.css">
<title>결제 관리</title>
</head>
<body>
	<!-- menubar -->
	<jsp:include page="/WEB-INF/views/common/menubar.jsp"/>
	
	<div class="grey-zone"></div>
	<div class="info-zone">
		<img src="/resources/images/${loginUser.mbPhoto}.png" class="rounded-circle" width="100" style="margin-top: -35px; margin-left: 15px;">
		<div class="mem-info">
			<a style="font-weight: bold; font-size: 20px;">${loginUser.mbNickname}</a>님<br>
		</div>
	</div>
	<div class="main-zone">
		<div class="btn-zone">
			<input type="button" value="이전" onclick="location.href='/member/myPage'" class="back-btn">
		</div>
		<div class="title-zone">
			<span class="title">결제 정보</span>
		</div>
		<hr class="line">
		<div class="purchase-list">
			<div class="purchase">
				
			</div>
		</div>
	</div>
	
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>