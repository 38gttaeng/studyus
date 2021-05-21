<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스터디카페 상세</title>
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
				<div class="col-md-7 text-center heading-section ftco-animate">
					<h2 class="mb-4">스터디어스 카페</h2>
					<p>언택트를 넘어서 온택트로 만나는 스터디어스만의 프라이빗한 공간</p>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12 d-flex align-self-stretch ftco-animate">
					<div class="media block-6 services d-flex align-items-center">
						<div class="media-body pl-4">
							<h3 class="heading">스터디어스 종로점 // 이름</h3>
							<p>서울특별시 중구 남대문로 120 대일빌딩 2F, 3F // 주소</p>
						</div>
						<p>
							<a href="#" class="btn btn-primary px-4 py-3"
								style="background-color: white; color: purple">예약하기</a>
						</p>
					</div>
				</div>
			</div>
			<!-- 보라색으로 나오는 버튼(문제) -->
			<!-- 			<div class="row">
				<div class="col-md-12 d-flex align-self-stretch ftco-animate">
					<div class="media block-6 services d-flex align-items-center">
						<div class="media-body pl-4">
							<h3 class="heading">스터디어스 종로점 // 이름</h3>
							<p>서울특별시 중구 남대문로 120 대일빌딩 2F, 3F // 주소</p>
						</div>
						<p>
							<a href="#" class="btn btn-primary px-4 py-3">예약하기</a>
						</p>
					</div>
				</div>
			</div> -->

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
							<div class="col-md-6">
								<img alt="cafe_image" src="#">
							</div>
							<div class="col-md-6 pr-md-5">
								<form action="#">
									<div class="form-group">
										<ul class="pricing-text mb-4">
											<li><strong>전화번호</strong>
												<h3 class="heading-2 mb-3">02-722-0858 // 전화번호</h3></li>
										</ul>
									</div>
									<div class="form-group">
										<ul class="pricing-text mb-4">
											<li><strong>영업시간</strong>
												<h3 class="heading-2 mb-3">10:00 ~ 20:30 // 영업시간</h3></li>
										</ul>
									</div>
									<div class="form-group">
										<ul class="pricing-text mb-4">
											<li><strong>오시는 길</strong>
												<h3 class="heading-2 mb-3">지하철 2호선 을지로입구역 3번출구 100M //
													오시는 길</h3></li>
										</ul>
									</div>
								</form>
							</div>
							<div class="col-md-12 mb-4">
								<h2 class="h4 ml-3 mt-5">리뷰</h2>
							</div>
						</div>
					</div>
				</div>

			</div>
	</section>


	<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>