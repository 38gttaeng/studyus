<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
		<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html> 
<html>
<head>
<meta charset="UTF-8">
<title>StudyUs : 스터디카페 예약</title>
    <!-- 추가 css -->
    <link href="/resources/css/studyus/roomUpdate.css" rel="stylesheet">
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
			
			<input type="hidden" name="caNo" value="${cafe.caNo }"> 
			<div class="row">
				<div class="col-md-12 d-flex align-self-stretch ftco-animate">
					<div class="media block-6 services d-flex align-items-center">
						<div class="media-body pl-4">
							<h3 class="heading">${cafe.caName }</h3>
							<p>${cafe.caAddr }</p>
						</div>
					</div>
				</div>
			</div>
			
			<div class="row justify-content-center pb-5">
				<div class="col-md-7 text-center heading-section ftco-animate">
					<h3 class="mt-4">스터디룸 선택</h3>
					<p>지도에서 원하는 스터디룸을 클릭하면 상세정보를 확인할 수 있습니다.</p>
				</div>
			</div>

			<div class="row d-flex">
				<div class="col-md-12">
				
					<div class="block-7 row">
						<div id="room-wrapper" class="mb-3"></div>
					</div>
					<br>
					
					<div id="detail-box" class="block-7 row d-none">
						<div class="col-md-12">
							<h5 id="roomName" class="ml-3"><strong>룸 이름</strong></h5>
						</div>
						<br>
						<div class="col-md-5 ml-3" id="img-box">
							<img id="roomFile" width="100%" alt="cafe_img" />
						</div>
						<div class="col-md-6 pr-md-5 ml-3 mb-2">
							<div class="form-group">
								<ul class="pricing-text mb-4">
									<li><strong>소개</strong>
										<h3 id="roomInfo" class="heading-2 mb-3"></h3></li>
								</ul>
							</div>
							<div class="form-group">
								<ul class="pricing-text mb-4">
									<li><strong>최대인원</strong>
										<h3 id="roomMax" class="heading-2 mb-3"></h3></li>
								</ul>
							</div>
							<div class="form-group">
								<ul class="pricing-text mb-4">
									<li><strong>가격(시간당)</strong>
										<h3 id="roomPrice" class="heading-2 mb-3"></h3></li>
								</ul>
							</div>
						</div>
					</div>
					
				</div>
			</div>
			<div align="center">
				<p>
					<input type="hidden" name="crNo">
					<c:url var="cDetail" value="/cafe/detail">
						<c:param name="caNo" value="${cafe.caNo }"></c:param>
					</c:url>
					<a href="${cDetail}" class="btn btn-primary px-4 py-3 mt-5 mr-3"
							style="background-color: white; color: #6927ff">취소하기</a>
					<button id="reservation-btn" type="button" class="btn btn-primary px-4 py-3 mt-5">다음단계</button>
				</p>
			</div>
		</div>
	</section>
	
	<jsp:include page="../common/footer.jsp"></jsp:include>
	
    <!-- textFit -->
    <script src="/resources/js/textFit.min.js"></script>
	<!-- 해당 페이지 JS 파일 -->
    <script src="/resources/js/cafeReservation.js"></script>
    
</body>
</html>