<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스터디카페 등록</title>
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
					<p>언택트를 넘어서 온택트로 만나는 스터디어스만의 특별한 공간</p>
				</div>
			</div>
			<form action="cafeRegister" method="post" enctype="multipart/form-data">
			<div class="row">
				<div class="col-md-12 d-flex align-self-stretch ftco-animate">
					<div class="media block-6 services d-flex align-items-center">
						<div class="media-body pl-4">
							<h3 class="heading"><input type="text" size="50" name="caName" placeholder="카페 이름을 입력해주세요"></h3>
							<p><input type="text" size="50" name="caAddr" placeholder="주소를 입력해주세요"></p>
						</div>
						<p>
							<a href="#" class="btn btn-primary px-4 py-3"
								style="background-color: white; color: purple">등록하기</a>
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
								<input type="file" size="50" name="uploadFile">
							</div>
							<div class="col-md-6 pr-md-5">
								<form action="#">
									<div class="form-group">
										<ul class="pricing-text mb-4">
											<li><strong>소개</strong>
												<h3 class="heading-2 mb-3"><textarea rows="2" cols="50" name="caInfo"></textarea></h3></li>
										</ul>
									</div>
									<div class="form-group">
										<ul class="pricing-text mb-4">
											<li><strong>전화번호</strong>
												<h3 class="heading-2 mb-3"><input type="text" size="50" name="caTel"></h3></li>
										</ul>
									</div>
									<div class="form-group">
										<ul class="pricing-text mb-4">
											<li><strong>영업시간</strong>
												<h3 class="heading-2 mb-3"><input type="text" size="50" name="caTime"></h3></li>
										</ul>
									</div>
									<div class="form-group">
										<ul class="pricing-text mb-4">
											<li><strong>주소</strong>
												<h3 class="heading-2 mb-3"><input type="text" size="50" name="caAddr"></h3></li>
										</ul>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>

			</div>
	</section>
</form>

	<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>