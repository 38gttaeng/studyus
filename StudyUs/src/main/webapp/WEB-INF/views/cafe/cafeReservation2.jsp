<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
		<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html> 
<html>
<head>
<meta charset="UTF-8">
    <link href="/resources/css/studyus/fullcalendar.min.css" rel="stylesheet" />
    <link href="/resources/css/studyus/cafeReservation.css" rel="stylesheet">
	<title>StudyUs : 스터디카페 예약</title>
</head>
<body>
	<jsp:include page="../common/menubar.jsp"></jsp:include>
	<script>
		$(".nav-item:nth-child(4)").addClass("active");
	</script>
	<br>
	<br>
	<br>
	<br>
	<section class="ftco-section services-section bg-light">
		<div class="container">
			<div class="row justify-content-center mb-5 pb-3">
				<div class="col-md-7 text-center heading-section">
					<h2 class="mb-4">스터디카페 예약하기</h2>
					<p>원하는 룸을 선택하고 시간별로 예약할 수 있습니다.</p>
				</div>
			</div>
			
			<input type="hidden" name="caTime" value="${cafe.caTime }"> 
			
			<div class="row">
				<div class="col-md-12 d-flex align-self-stretch ftco-animate">
					<div class="media block-6 services d-flex align-items-center">
						<div class="media-body pl-4">
							<h3 class="heading">${cafe.caName }</h3>
							<p><b>영업시간</b>&nbsp;&nbsp;${cafe.caTime}</p>
						</div>
					</div>
				</div>
			</div>
			
			<!-- 날짜 선택 -->
			<div class="row justify-content-center pt-5 pb-3">
				<div class="col-md-7 text-center heading-section">
					<h3 class="mt-4"><strong>날짜 선택</strong></h3>
					<p>예약을 원하는 날짜를 클릭하면 예약 가능한 시간을 확인할 수 있습니다.</p>
				</div>
			</div>
			<div class="row d-flex ftco-animate">
				<div class="col-md-12">
					<div class="block-7 row">
						<div id="calendar" class="col-md-9 mb-3"></div>
						<div id="time-box" class="form-group col-md-3">
			        		<label for="time-select">* 1시간 이상 선택 가능</label>
			        		<select name="time-select" id="time-select" multiple="multiple" class="custom-select" disabled>
			        			<option>날짜를 선택해주세요.</option>
			        		</select>
							<a id="time-btn" href="#section3" class="btn btn-primary px-4 px-4">선택완료</a>
						</div>
					</div>
				</div>
			</div>
			<br><br>
		
			<!-- 예약확인 -->
			<div id="section3" class="row justify-content-center pt-5 pb-3">
				<div class="col-md-7 text-center heading-section">
					<h3 class="mt-4"><strong>예약 확인</strong></h3>
					<p>예약 신청 전 예약 정보를 확인하세요.</p>
				</div>
			</div>
			<div class="row d-flex ftco-animate">
				<div class="col-md-12">
					<div class="block-7 row">
						<div></div>
					</div>
				</div>
			</div>
			
			<div align="center">
				<p>
					<c:url var="cReserv" value="/cafe/reservation">
						<c:param name="caNo" value="${cafe.caNo }"></c:param>
					</c:url>
					<a href="${cReserv}" class="btn btn-primary px-4 py-3 mt-5 mr-3"
							style="background-color: white; color: #6927ff">이전단계</a>
					<button id="reservation-btn" type="button" class="btn btn-primary px-4 py-3 mt-5">예약완료</button>
				</p>
			</div>
		</div>
	</section>
	
	<jsp:include page="../common/footer.jsp"></jsp:include>
	
	<!-- 해당 페이지 JS 파일 -->
    <script src="/resources/js/cafeReservation2.js"></script>
    <!-- 캘린더 -->
	<script src="/resources/css/study/assets/extra-libs/taskboard/js/jquery.ui.touch-punch-improved.js"></script>
    <script src="/resources/css/study/assets/extra-libs/taskboard/js/jquery-ui.min.js"></script>
    <script src="/resources/css/study/assets/libs/moment/min/moment.min.js"></script>
    <script src="/resources/css/study/assets/libs/fullcalendar/fullcalendar.js"></script>
    
</body>
</html>