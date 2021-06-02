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
<link href="/resources/css/fullcalendar/assets/libs/fullcalendar/dist/fullcalendar.min.css" rel="stylesheet" />
<link href="/resources/css/fullcalendar/dist/css/style.min.css" rel="stylesheet">
<script>
document.addEventListener('DOMContentLoaded', function() {
	var calendarEl = document.getElementById('calendar');
	var calendar = new FullCalendar.Calendar(calendarEl, {
		initialView: 'dayGridMonth'
	});
	calendar.render();
});
</script>
</head>
<body>
	<!-- menubar -->
	<jsp:include page="/WEB-INF/views/common/menubar.jsp"/>
	<div class="grey-zone"></div>
	<div class="myPage-box">
		<div class="info-zone">
			<img src="/resources/images/${loginUser.mbPhoto}.png" class="rounded-circle" width="100" style="margin-top: -65px; margin-left: 15px;">
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
				<c:if test="">
					<c:forEach items="">
						<div class="">
							
						</div>
					</c:forEach>
				</c:if>
				<c:if test="">
					<div class="">
					
					</div>
				</c:if>
			</div>
			
			<div class="my-calender">
				<div class="menu-mark"><span>나의 일정</span></div>
				<div class="calender-box">
					<div id='calendar'></div>
				</div>
			</div>
			
			<div class="my-review">
				<div class="menu-mark"><span>나의 후기</span></div>
				<c:if test="${!myReview.isEmpty()}">
					<c:forEach items="">
					
					</c:forEach>
					
					<div class="review">
					
					</div>
				</c:if>
				
			</div>
		</div>
	</div>
	
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
	
	<!-- ============================================================== -->
    <!-- All Jquery -->
    <!-- ============================================================== -->
    <script src="/resources/css/fullcalendar/assets/libs/jquery/dist/jquery.min.js"></script>
    <script src="/resources/css/fullcalendar/assets/extra-libs/taskboard/js/jquery-ui.min.js"></script>
    <script src="/resources/css/fullcalendar/assets/libs/popper.js/dist/umd/popper.min.js"></script>
    <script src="/resources/css/fullcalendar/assets/libs/bootstrap/dist/js/bootstrap.min.js"></script>
    <!-- apps -->
    <script src="/resources/css/fullcalendar/dist/js/app-style-switcher.js"></script>
    <script src="/resources/css/fullcalendar/dist/js/feather.min.js"></script>
    <script src="/resources/css/fullcalendar/assets/libs/perfect-scrollbar/dist/perfect-scrollbar.jquery.min.js"></script>
    <script src="/resources/css/fullcalendar/dist/js/sidebarmenu.js"></script>
    <!--Custom JavaScript -->
    <script src="/resources/css/fullcalendar/dist/js/custom.min.js"></script>
    <!--This page JavaScript -->
    <script src="/resources/css/fullcalendar/assets/libs/moment/min/moment.min.js"></script>
    <script src="/resources/css/fullcalendar/assets/libs/fullcalendar/dist/fullcalendar.min.js"></script>
    <script src="/resources/css/fullcalendar/dist/js/pages/calendar/cal-init.js"></script>
</body>
</html>