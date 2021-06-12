<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
			<c:if test="${!empty pList}">
				<c:forEach var="purchase" items="${pList}" varStatus="i">
					<div class="purchase">
						<div class="txt-box">
							<div class="p-info">
								<div class="p-name">프리미엄 이용권 결제</div>
								<div class="p-date">${purchase.puInsertDate}</div>
							</div>
							<div class="st-info">
								<span class="st-txt">Study. </span><span class="st-name">${purchase.stName}</span>
							</div>
						</div>
					</div>	
				</c:forEach>
				
			</c:if>
			<c:if test="${empty pList}">
				<div class="purchase">
					<div class="txt-zone">
						<span class="txt">결제 내역이 없습니다.</span>
					</div>
				</div>	
			</c:if>
		</div>
	</div>

	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>