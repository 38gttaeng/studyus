<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
		<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html> 
<html>
<head>
<meta charset="UTF-8">
    <link href="/resources/css/studyus/fullcalendar.min.css" rel="stylesheet" />
    <link href="/resources/css/studyus/cafeReservation.css" rel="stylesheet">
	<title>스터디카페 예약 : StudyUs</title>
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
	
	<form id="reservation-form" action="/cafe/reservation-register" method="post">
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
			
			<!-- 스터디 선택 -->
			<div class="row justify-content-center pt-3 pb-2">
				<div class="col-md-7 text-center heading-section">
					<strong>STEP1</strong><h3 class="mt-1"> <strong>스터디 선택</strong></h3>
				</div>
			</div>
			<div id="section1" class="row d-flex ftco-animate mb-4">
				<div class="col-md-12">
					<div class="block-7 row">
						<div class="col-md-12 text-center heading-section mt-3 mb-3">
							<p>예약을 적용할 스터디를 선택해주세요.<br>
							해당 스터디 페이지에서 예약정보를 확인할 수 있습니다.</p>
						</div>
						<div class="col-md-3"></div>
						<div class="col-md-5">
							<!-- 스터디 개수가 하나일 경우 -->
							<input type="hidden" name="study-check" value="${ stList.size() }">
							<c:if test="${ stList.size() == 1 }">
								<select name="studyNo" class="custom-select" disabled>
									<option selected>${ stList[0].studyName }</option>
								</select>
								<input type="hidden" name="stNo" value="${ stList[0].studyNo }">
							</c:if>
							<!-- 스터디 개수가 여러개일 경우 -->
							<c:if test="${ stList.size() > 1 }">
								<select id="study-select" name="stNo" class="custom-select">	
									<c:forEach items="${ stList }" var="study">
									<option value="${ study.studyNo }">${ study.studyName }</option>
									</c:forEach>
								</select>
							</c:if>
						</div>
						<div class="col-md-4 mb-3">
							<a href="#section2" class="btn btn-primary px-4 px-4">선택완료</a>
						</div>
					</div>
				</div>
			</div>
			&nbsp;
			
			<!-- 날짜 선택 -->
			<div id="section2" class="row justify-content-center pt-3 pb-3">
				<div class="col-md-7 text-center heading-section">
					<strong>STEP2</strong><h3 class="mt-1"><strong>날짜 선택</strong></h3>
					<p>예약을 원하는 날짜를 클릭하면 예약 가능한 시간을 확인할 수 있습니다.</p>
				</div>
			</div>
			<div class="row d-flex ftco-animate">
				<div class="col-md-12">
					<div class="block-7 row">
						<div id="calendar" class="col-md-9 mb-3"></div>
						<div id="time-box" class="form-group col-md-3">
			        		<label for="time-select">* 1시간 이상 선택 가능</label>
			        		<select name="time" id="time-select" multiple="multiple" class="custom-select" disabled>
			        			<option>날짜를 선택해주세요.</option>
			        		</select>
							<a id="time-btn" href="#section3" class="btn btn-primary px-4 px-4">선택완료</a>
						</div>
					</div>
				</div>
			</div>
			&nbsp;
		
			<!-- 예약확인 -->
			<div id="section3" class="row justify-content-center pt-5 pb-3">
				<div class="col-md-7 text-center heading-section">
					<strong>STEP3</strong><h3 class="mt-1"><strong> 예약 확인</strong></h3>
					<p>예약 신청 전 예약 정보를 확인하세요.</p>
				</div>
			</div>
			<div class="row d-flex ftco-animate">
				<div class="col-md-12">
					<div class="block-7 row">
						<table class="table table-borderless">
							<tr>
								<th colspan="2"><h4 class="d-inline">${ caferoom.crName }</h4> (최대 ${ caferoom.crMax }명)</th>
							</tr>
							<tr>
								<td>예약한 스터디</td>
								<td id="reserv-name"></td>
							</tr>
							<tr>
								<td>예약 일정</td>
								<td id="reserv-date">일정을 선택하세요.</td>
							</tr>
							<tr>
								<td>가격</td>
								<td id="reserv-price"></td>
							</tr>
						</table>
						<input type="hidden" name="study-price" value="${ caferoom.crPrice }">
					</div>
				</div>
			</div>
			
			<!-- form태그 넘겨주기 -->
			<input type="hidden" name="crNo" value="${ caferoom.crNo }">
			<input type="hidden" name="mbNo" value="${ loginUser.mbNo }">
			<input type="hidden" name="rsDate">
			<input type="hidden" name="startStr">
			<input type="hidden" name="endStr">
			<div align="center">
				<p>
					<c:url var="cReserv" value="/cafe/reservation">
						<c:param name="caNo" value="${cafe.caNo }"></c:param>
					</c:url>
					<a href="${cReserv}" class="btn btn-primary px-4 py-3 mt-5 mr-3"
							style="background-color: white; color: #6927ff">이전단계</a>
					<button id="reservation-btn" class="btn btn-primary px-4 py-3 mt-5">예약완료</button>
				</p>
			</div>
		</div>
	</section>
	</form>
	
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