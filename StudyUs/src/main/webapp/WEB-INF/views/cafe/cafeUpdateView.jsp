<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스터디카페 수정</title>
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
					<h2 class="mb-4">스터디어스 카페</h2>
					<p>언택트를 넘어서 온택트로 만나는 스터디어스만의 특별한 공간</p>
				</div>
			</div>
			<form action="/cafe/modify" method="post"
				enctype="multipart/form-data">
				<input type="hidden" name="caNo" value="${cafe.caNo }">
				<div class="row">
					<div class="col-md-12 d-flex align-self-stretch">
						<div class="media block-6 services d-flex align-items-center">
							<div class="media-body pl-4">
								<h3 class="heading">
									<input class="form-control" type="text" size="50" name="caName"
										value="${ cafe.caName }">
								</h3>
								<p>
									<input class="form-control" type="text" size="50" id="caAddr"
										name="caAddr" value="${cafe.caAddr }" readonly> <input
										type="button" onclick="goPopup();"
										class="btn btn-primary px-3 py-2 mt-2"
										style="background-color: white; color: purple" value="검색">
									<input type="hidden" id="confmKey" name="confmKey" value="">
									<input type="hidden" id="caLat" name="caLat"
										value="${cafe.caLat }"> <input type="hidden"
										id="caLong" name="caLng" value="${cafe.caLng }">
								</p>
							</div>
						</div>
					</div>
				</div>

				<div class="row justify-content-center mb-5 pb-3">
					<div class="col-md-7 text-center heading-section">
						<h3 class="mt-4">상세정보</h3>
					</div>
				</div>
				<div class="row d-flex">
					<div class="col-md-12">
						<div class="block-7">
							<div class="row block-9">
								<div class="col-md-12 mb-4">
									<h2 class="h4 ml-3">매장 안내</h2>
								</div>
								<div class="col-md-6 ">
									<div class="form-group">
										<input type="hidden" name="caFiName" value="${cafe.caFiName }">
										<input type="file" size="50" id="image" name="reloadFile" accept="image/*"
											onchange="setThumbnail(event);" />
										<div id="image_container"></div>
									</div>
								</div>
								<div class="col-md-6 pr-md-5">
									<div class="form-group">
										<ul class="pricing-text mb-4">
											<li><strong>소개</strong>
												<h3 class="heading-2 mb-3">
													<textarea class="form-control" rows="2" cols="50"
														name="caInfo">${cafe.caInfo }</textarea>
												</h3></li>
										</ul>
									</div>
									<div class="form-group">
										<ul class="pricing-text mb-4">
											<li><strong>전화번호</strong>
												<h3 class="heading-2 mb-3">
													<input class="form-control" type="text" size="50"
														name="caTel" value="${cafe.caTel }">
												</h3></li>
										</ul>
									</div>
									<div class="form-group">
										<ul class="pricing-text mb-4">
											<li><strong>영업시간</strong>
												<h3 class="heading-2 mb-3">
													<input class="form-control" type="text" size="50"
														name="caTime" value="${cafe.caTime }">
												</h3></li>
										</ul>
									</div>
									<div class="form-group">
										<ul class="pricing-text mb-4">
											<li><strong>오시는 길</strong>
												<h3 class="heading-2 mb-3">
													<input class="form-control" type="text" size="50"
														name="caRoute" value="${cafe.caRoute }">
												</h3></li>
										</ul>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div align="center">
					<p>
						<button type="reset-btn"
							class="btn btn-primary px-4 py-3 mt-5 mr-3"
							style="background-color: white; color: purple">취소</button>
						<button type="submit" class="btn btn-primary px-4 py-3 mt-5">수정하기</button>
					</p>
				</div>
			</form>
		</div>
	</section>
	<jsp:include page="../common/footer.jsp"></jsp:include>

	<script>
		function goPopup() {
			// 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(https://www.juso.go.kr/addrlink/addrCoordUrl.do)를 호출하게 됩니다.
			var pop = window.open("/cafe/caAddrPop", "pop",
					"width=570,height=420, scrollbars=yes, resizable=yes");
		}
		function jusoCallBack(roadFullAddr, entX, entY) {
			// 팝업페이지에서 주소입력한 정보를 받아서, 현 페이지에 정보를 등록합니다.
			var addressEl = document.querySelector("#caAddr");
			addressEl.value = roadFullAddr;
			document.getElementById("caLat").value = entX;
			document.getElementById("caLong").value = entY;
		}

		function setThumbnail(event) {
			var reader = new FileReader();
			reader.onload = function(event) {
				var img = document.createElement("img");
				img.setAttribute("src", event.target.result);
				img.setAttribute('width',400);
				document.querySelector("div#image_container").appendChild(img);
			};
			reader.readAsDataURL(event.target.files[0]);
		}
	</script>
</body>
</html>