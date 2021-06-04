<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- 추가 css -->
<link href="/resources/css/studyus/reply.css" rel="stylesheet">
<title>StudyUs : 스터디룸</title>
</head>
<body>
	<!-- ============================================================== -->
	<!-- Main wrapper -->
	<!-- ============================================================== -->
	<div id="main-wrapper" data-theme="light" data-layout="vertical"
		data-navbarbg="skin6" data-sidebartype="full"
		data-sidebar-position="fixed" data-header-position="fixed"
		data-boxed-layout="full">

		<!-- menubar -->
		<jsp:include page="../common/studyMenubar.jsp" />
		<script>
			$("#sidebarnav>li:nth-child(5)").addClass("selected");
			$("#sidebarnav>li:nth-child(5) a").addClass("active");
		</script>

		<!-- ============================================================== -->
		<!-- Page wrapper  -->
		<!-- ============================================================== -->
		<div class="page-wrapper">
			<!-- ============================================================== -->
			<!-- Bread crumb and right sidebar toggle -->
			<!-- ============================================================== -->
			<div class="page-breadcrumb">
				<div class="row">
					<div class="col-lg-4 align-self-center">
						<h4
							class="page-title text-truncate text-dark font-weight-medium mb-1">공지사항</h4>
						<div class="d-flex align-items-center">
							<nav aria-label="breadcrumb">
								<ol class="breadcrumb m-0 p-0">
									<li class="breadcrumb-item text-muted" aria-current="page"><a
										href="/study">Study</a></li>
									<li class="breadcrumb-item text-muted" aria-current="page"><a
										href="/notice/noticeList">Notice</a></li>
									<li class="breadcrumb-item text-muted active"
										aria-current="page">Detail</li>
								</ol>
							</nav>
						</div>
					</div>
					<div class="col-lg-8 align-self-center">
						<div class="float-right">
							<c:if test="${loginUser.mbNo eq notice.mbNo }">
								<div class="btn-group">
									<button
										onclick="location.href='/notice/noticeModifyView?noNo=${notice.noNo}'"
										class="btn btn-secondary">수정</button>
									<button
										onclick="location.href='/notice/noticeDelete?noNo=${notice.noNo}'"
										class="btn btn-secondary">삭제</button>
								</div>
							</c:if>
							<button onclick="location.href='/notice/noticeList'"
								class="btn btn-primary">목록</button>
						</div>
					</div>
				</div>
			</div>

			<!-- ============================================================== -->
			<!-- Container fluid  -->
			<!-- ============================================================== -->
			<div class="container-fluid">
				<!-- ============================================================== -->
				<!-- Start Page Content -->
				<!-- ============================================================== -->
				<div class="row">
					<div class="col-12">
						<div class="card">
							<div class="card-body ">
								<h4 class="card-title">
									<span class="tags tag-free">공지</span> ${ notice.noTitle }
								</h4>
								<div class="row">
									<h6 class="card-subtitle col-6">
										<img src="/resources/images/${notice.member.mbPhoto }.png" class="rounded-circle">&nbsp;&nbsp;
										${notice.noWriter }
									</h6>
									<h6 class="card-subtitle col-6" style="text-align: right">조회수
										${notice.noCount} &nbsp;&nbsp;${ notice.noInsertDate }</h6>
								</div>
							</div>
							<div class="card-body file-box">${ notice.noFileName }
							</div>
							<div class="card-body">
								<c:if test="${ !empty notice.noContents }">
									<p>${ notice.noContents }</p>
								</c:if>
							</div>


							<div class="card-body">
								<!-- js 파일과 연동하기 위해서 -->
								<input id="rMotherNo" type="hidden" value="${ notice.noNo }"> 
								<input id="rMbNo" type="hidden" value="${ notice.mbNo }">
								<input id="loginMbNo" type="hidden" value="${ loginUser.mbNo }">
								<h6 class="card-subtitle" style="float: right;">
									댓글 <span id="rCount"></span>
								</h6>
								<hr>
							</div>
						<!-- 댓글 리스트 -->
						<div id="rList"></div>
						<!-- 페이징 -->
						<nav id='rPage'></nav>
							<div class="reply-enter">
								<div id="editor"></div>
								<button id="rSubmit" class="reply-enter-btn">등록</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- footer -->
	<jsp:include page="../common/studyFooter.jsp" />


	<!-- 해당 페이지 JS 파일 -->
	<script src="/resources/js/noticeReply.js"></script>

</body>
</html>