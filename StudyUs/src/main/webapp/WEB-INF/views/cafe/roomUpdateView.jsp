<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
		<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html> 
<html>
<head>
<meta charset="UTF-8">
<title>StudyUs : 스터디 카페룸</title>

	<!-- Quill -->
    <link href="/resources/js/quill/quill-emoji.css" rel="stylesheet" type="text/css">
    <link href="//cdn.quilljs.com/1.3.6/quill.snow.css" rel="stylesheet">
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
					<h2 class="mb-4">카페 룸설정</h2>
					<p>카페 룸을 추가, 삭제하고 정보를 입력할 수 있습니다.</p>
				</div>
			</div>
			<form action="/cafe/modify" method="post" enctype="multipart/form-data">
			<input type="hidden" name="caNo" value="${cafe.caNo }"> 
				<div class="row">
					<div class="col-md-12 d-flex align-self-stretch">
						<div class="media block-6 services d-flex align-items-center">
							<div class="media-body pl-4">
								<h3 class="heading">
									<input class="form-control" type="text" size="50" name="caName"
										value="${ cafe.caName }" readonly>
								</h3>
								<p>
									<input class="form-control" type="text" size="50" name="caAddr"
										value="${cafe.caAddr }" readonly>
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
							<!-- 룸 for문으로 보여주는 부분 -->
						</div>
					</div>
					<div class="col-md-12">
						<div class="block-7">
							<!-- 룸 전체 정보 등록하는 부분 -->
							<div id="editor" style="min-height:600px;"></div>
						</div>
					</div>
				</div>
				<div align="center">
					<p>
					<c:url var="cDelete" value="/cafe/delete">
							<c:param name="caNo" value="${cafe.caNo }"></c:param>
							<c:param name="caFiName" value="${cafe.caFiName }"></c:param>
						</c:url>
							<a href="${cDelete}" class="btn btn-primary px-4 py-3 mt-5 mr-3"
								style="background-color: white; color: purple">삭제하기</a>
						<button type="submit" class="btn btn-primary px-4 py-3 mt-5">수정하기</button>
					</p>
				</div>
			</form>
		</div>
	</section>

	<jsp:include page="../common/footer.jsp"></jsp:include>
	
	<!-- 해당 페이지 JS 파일 -->
    <script src="/resources/js/roomUpdate.js"></script>
    <!-- Quill -->
	<script src="//cdn.quilljs.com/1.3.6/quill.js"></script>
	<script src="//cdn.quilljs.com/1.3.6/quill.min.js"></script>
	<script src="/resources/js/quill/image-resize.min.js"></script>
	<script src="/resources/js/quill/image-upload.min.js"></script>
	<!-- <script src="/resources/js/quill/video-resize.min.js"></script> -->
	<script src="/resources/js/quill/quill-emoji.js"></script>
</body>
</html>