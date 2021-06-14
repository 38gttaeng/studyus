<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스터디카페 상세 : StudyUs</title>
</head>
 <!-- 추가 css -->
    <link href="/resources/css/studyus/reply.css" rel="stylesheet">
<body>
	<jsp:include page="../common/menubar.jsp"></jsp:include>
	<script> 
		$(".nav-item:nth-child(4)").addClass("active");
	</script>
	<br>
	<br>
	<br>
	<br>
	<img alt="카페상세" src="/resources/images/cafe_banner.png">
	<section class="ftco-section services-section bg-light">
		<div class="container">
			<div class="row justify-content-center mb-5 pb-3">
				<div class="col-md-7 text-center heading-section ftco-animate">
					<div class="overlay"></div>
					<h2 class="mb-4">스터디어스 카페</h2>
					<p>언택트를 넘어서 온택트로 만나는 스터디어스만의 특별한 공간</p>
				</div>
			</div>
		<div class="row">
			<div class="col-md-12 d-flex align-self-stretch ftco-animate">
				<div class="media block-6 services d-flex align-items-center">
					<div class="media-body pl-4">
						<h3 class="heading">${cafe.caName }</h3>
						<p>${cafe.caAddr }</p>
					</div>
					<p>
						<c:url var="cModify" value="modifyView">
							<c:param name="caNo" value="${cafe.caNo }"></c:param>
						</c:url>
						<c:url var="roomModify" value="room-modifyView">
							<c:param name="caNo" value="${cafe.caNo }"></c:param>
						</c:url>
						<c:url var="cReserv" value="reservation">
							<c:param name="caNo" value="${cafe.caNo }"></c:param>
						</c:url>
						<!-- 관리자만 카페수정, 룸수정 가능하도록 -->
						<c:if test="${loginUser.mbNo == 1}">
							<div class="btn-group">
								<a href="${cModify }" class="btn btn-primary px-4 py-3"
									style="background-color: white; color: purple">정보수정</a>
								<a href="${roomModify }" class="btn btn-primary px-4 py-3"
								style="background-color: white; color: purple">룸설정</a>
							</div>
						</c:if>
						<!-- 팀장인 경우 -->
						<c:if test="${ (loginUser.mbNo != 1) && (!empty sList) }">
							<a href="${cReserv }" class="btn btn-primary px-4 py-3"
								style="background-color: white; color: purple">예약하기</a>
						</c:if>
					</p>
				</div>
			</div>
		</div>

		<div class="row justify-content-center mb-5 pb-3">
			<div class="col-md-7 text-center heading-section ftco-animate">
				<h3 class="mt-4">상세정보</h3>
			</div>
		</div>
		<div class="row d-flex">
			<div class="col-md-12 ftco-animate">
				<div class="block-7">
					<div class="row block-9">
						<div class="col-md-12 mb-4">
							<h2 class="h4 ml-3">매장 안내</h2>
						</div>
						<div class="col-md-6 ">
							<img width="100%" alt="cafe_img"
								src="/resources/cuploadFiles/${cafe.caFiName}" />
						</div>
						<div class="col-md-6 pr-md-5">
								<div class="form-group">
									<ul class="pricing-text mb-4">
										<li><strong>소개</strong>
											<h3 class="heading-2 mb-3">${cafe.caInfo }</h3></li>
									</ul>
								</div>
								<div class="form-group">
									<ul class="pricing-text mb-4">
										<li><strong>전화번호</strong>
											<h3 class="heading-2 mb-3">${cafe.caTel }</h3></li>
									</ul>
								</div>
								<div class="form-group">
									<ul class="pricing-text mb-4">
										<li><strong>영업시간</strong>
											<h3 class="heading-2 mb-3">${cafe.caTime }</h3></li>
									</ul>
								</div>
								<div class="form-group">
									<ul class="pricing-text mb-4">
										<li><strong>오시는 길</strong>
											<h3 class="heading-2 mb-3">${cafe.caRoute }</h3></li>
									</ul>
								</div>
						</div>

		<!-- 댓글 목록 -->
		<section class="ftco-section" id="rtb">
		<div class="row block-9">
			<div class="col-md-2 ml-3 mb-4">
					<h2 class="h4 ml-3 mt-5">리뷰
					<span style="color:orange">★</span> 
					<span class="h5" id="avg"></span>
					</h2>
			</div>
			</div>
			<div class="card-body">
			<!-- cafeReview.js 파일과 연동하기 위해서 -->
			<input type="hidden"  id="cafeNumber" value="${ cafe.caNo }"/>
			<input type="hidden"  id="memberNo" value="${ loginUser.mbNo }"/> 
			
			<!-- 리뷰 등록 -->
			<div class="row block-9">
				<div class="col-md-10 pr-md-2">
						<div class="form-group">
						<c:if test="${loginUser.mbNo ne null }">
							<textarea name="" id="rvContents" cols="25" rows="7"
								class="form-control" placeholder="스터디어스가 따뜻해지는 리뷰 부탁드립니다."></textarea>
						</c:if>
						<c:if test="${loginUser.mbNo eq null }">
							<textarea name="" id="rvContents" cols="25" rows="7"
								class="form-control" placeholder="로그인 후 이용해주세요" readonly></textarea>
						</c:if>
						</div>
						<div class="star-container" id="rvRating">
						  <span class="star" id="rating1" title="1점">★</span>
						  <span class="star" id="rating2" title="2점">★</span>
						  <span class="star" id="rating3" title="3점">★</span>
						  <span class="star" id="rating4" title="4점">★</span>
						  <span class="star" id="rating5" title="5점">★</span>
						</div>
				</div>
				<div class="col-md-2 pr-2 pt-5">
					<div class="form-group">
						<c:if test="${loginUser.mbNo ne null }">
							<input type="button" id="rvSubmit" value="등록"
							class="btn btn-primary py-3 px-5">
						</c:if>
						<c:if test="${loginUser.mbNo eq null }">
							<a href="/member/loginView" onclick="alert('로그인 후 이용해주세요!');" 
								class="btn btn-primary py-3 px-5">등록</a>
						</c:if>
					</div>
				</div>
			</div>
			<!-- 리뷰 리스트 -->
			<div id="rList"></div>
				<div id="float-btn">
		            <!-- top으로 가는 버튼 -->
		             <button id="top-btn-pu" onclick="location.href='#'"><b>▲</b></button>
             	</div>
			</div>
			</section>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>

	<jsp:include page="../common/footer.jsp"></jsp:include>
	<script src="/resources/js/cafeReview.js"></script>
</body>
</html>