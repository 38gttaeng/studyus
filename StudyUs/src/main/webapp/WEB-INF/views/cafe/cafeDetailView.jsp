<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
							<a href="${cModify }" class="btn btn-primary px-4 py-3"
								style="background-color: white; color: purple">수정하기</a>
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
							<div class="col-md-6 ">
								<img width="100%" alt="cafe_img"
									src="/resources/images/${cafe.caFiName}" />
<%-- 									src="<c:if test="${ !empty cafe.caFiName }"> --%>
<%-- 							${ file.fiRealName } --%>
<%-- 							</c:if>"> --%>
							</div>
							<div class="col-md-6 pr-md-5">
								<form action="#">
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
												<h3 class="heading-2 mb-3">${cafe.caTime }</h3>
											</li>
										</ul>
									</div>
									<div class="form-group">
										<ul class="pricing-text mb-4">
											<li><strong>오시는 길</strong>
												<h3 class="heading-2 mb-3">${cafe.caRoute }</h3></li>
										</ul>
									</div>
								</form>
							</div>

							<section class="ftco-section" id="rtb">
								<div class="col-md-12 mb-4">
									<h2 class="h4 ml-3 mt-5">리뷰</h2>
								</div>

								<table class="table">
									<!-- 	<tr>
											<td>프로필</td>
											<td>아이디</td>
											<td>날짜</td>
										</tr>
										<tr>
											<td colspan="3">내용</td>
											<td>수정/삭제</td>
										</tr> -->
									<tbody>
									</tbody>
								</table>

								<div class="row block-9">
									<div class="col-md-10 pr-md-2">
										<form action="#">
											<div class="form-group">
												<textarea name="" id="" cols="25" rows="7"
													class="form-control" placeholder="스터디어스가 따뜻해지는 리뷰 부탁드립니다."></textarea>
											</div>
										</form>
									</div>
									<div class="col-md-2 pr-2 pt-5">
										<div class="form-group">
											<input type="submit" value="등록"
												class="btn btn-primary py-3 px-5">
										</div>
									</div>
								</div>
	<!-- 								<script>
		// 댓글 목록
		$(function() {
			getReviewList(); 
			$("#rSubmit").on("click", function() {
				var caNo = '${cafe.caNo}';
				var rvContents = $("#rvContents").val();
				$.ajax({
					url : "reviewRegister",
					type : "post",
					data : {
						"rvNo" : rvNo,
						"rvContents" : rvContents
					},
					success : function(data) {
						if (data == "success") {
							getReviewList();
							$("#rvContents").val("");
						} else {
							alert("댓글 등록 실패");
						}
					},
					error : function() {

					}
				});
			});
		});

		function getReviewList() {
			var caNo = '${cafe.caNo}';
			$.ajax({
				url : "reviewList",
				type : "get",
				data : {
					"caNo" : caNo
				},
				dataType : "json",
				success : function(data) {
					var $tableBody = $("#rtb tbody");
					$tableBody.html(""); // 비워주기
					var $tr;
					var $mbId;
					var $rvContents;
					var $rvDate;
					var $btnArea;

					if (data.length > 0) {
						for ( var i in data) {
							$tr = $("<tr>");
							$mbId = $("<td width='100'>").text(data[i].mbId);
							$rvContents = $("<td>").text(data[i].rvContents);
							$rvDate = $("<td width='100'>").text(
									data[i].rvDate);
							$btnArea = $("<td>").append(
									"<a href='#' onclick='modifyReview(this,"
											+ rvNo + "," + data[i].rvNo
											+ ",\"" + data[i].rvContents
											+ "\");'>수정 </a>").append(
									"<a href='#' onclick='removeReview("
											+ cafeNo + "," + data[i].rvNo
											+ ");'> 삭제</a>");
							$tr.append($mbId);
							$tr.append($rvContents);
							$tr.append($rvDate);
							$tr.append($btnArea);
							$tableBody.append($tr);
						}
					}
				},
				error : function() {

				}

			});
		}

		// 댓글 수정 폼(수정버튼 눌렀을 때 폼 생기게 하는 함수)
		function modifyReview(obj, cafeNo, reviewNo, reviewContents) {
			$trModify = $("<tr>");
			$trModify
					.append("<td colspan='3'><input type='text' id='modifyReview' size='50' value='"+reviewContents+"'></td>");
			$trModify.append("<td><button onclick='modifyReviewCommit("
					+ boardNo + "," + replyNo + ")'>수정완료</button></td>");
			$(obj).parent().parent().after($trModify);
		}

		// 수정완료 버튼 눌렀을 때 동작하는 함수
		function modifyReplyCommit(caNo, rvNo) {
			var modifiedContent = $("#modifyReview").val();
			$.ajax({
				url : "modifyReview",
				type : "post",
				data : {
					"refCafeNo" : cafeNo, 
					"reviewNo" : reviewNo, 
					"reviewContents" : modifiedContent
					},
				success : function(data) {
					if(data == "success"){
						getReviewList();
					}else{
						alert("댓글 수정 실패!");
					}
				},
				error : function() {
					alert("서버 통신 실패!")

				}
			});
		}

		// 댓글삭제
		function removeReview(caNo, rvNo) {
			$.ajax({
				url : "deleteReview",
				type : "get",
				data : {
					"cafeNo" : caNo,
					"reviewNo" : reviewNo
				},
				success : function(data) {
					if (data == "success") {
						getReviewList();
					} else {
						alert("댓글 조회 실패!");
					}
				},
				error : function() {

				}
			});
		}
	</script> -->

 
							</section>
							</div>
						</div>
					</div>
				</div>
			</div>
	</section>

	<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>