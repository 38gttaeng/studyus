<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link
	href="/resources/css/study/assets/libs/fullcalendar/fullcalendar.min.css"
	rel="stylesheet" />
	<link href="//cdn.quilljs.com/1.3.6/quill.snow.css" rel="stylesheet">
<link href="//cdn.quilljs.com/1.3.6/quill.bubble.css" rel="stylesheet">

<title>StudyUs : 공지사항</title>
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
		<!-- 선택된 메뉴에 selected / active 추가해주기 -->
		<script>
			$("#sidebarnav>li:nth-child(4)").addClass("selected");
			$("#sidebarnav>li:nth-child(4) a").addClass("active");
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
					<div class="col-7 align-self-center">
						<h4
							class="page-title text-truncate text-dark font-weight-medium mb-1">공지사항</h4>
						<div class="d-flex align-items-center">
							<nav aria-label="breadcrumb">
								<ol class="breadcrumb m-0 p-0">
									<li class="breadcrumb-item text-muted active"
										aria-current="page">Study</li>
									<li class="breadcrumb-item text-muted" aria-current="page">Notice</li>
								</ol>
							</nav>
						</div>
					</div>
				</div>
			</div>

			<!-- ============================================================== -->
			<!-- Container fluid  -->
			<!-- ============================================================== -->
			<div class="container-fluid">
				<div class="row">
					<div class="col-md-12">
						<div class="card">
							<div class="row">
								<div class="col-12">
									<div class="card-body">
										<form class="" role="form" action="noticeRegister.kh" method="post"
											enctype="multipart/form-data">
											<table class="table-responsive col-9 ">
												<tr class="row" >
													<td class="col-md-1">제목</td>
													<td class="col-md-8" ><input type="text" class="form-control" name="noticeTitle"></td>
												</tr>
<%-- 												<tr class="row">
													<td class="col-3">작성자</td>
													<td class="col-9"><input type="text" size="50" class="form-control" name="noticeWriter"
														value="${loginUser.userId }" readonly></td>
												</tr> --%>
												<tr class="row">
													<td class="col-md-1">내용</td>
													<td class="col-md-8"><textarea rows="22" cols="500" class="form-control" name="noticeContents"></textarea>
													<div id="editor"></div>
													</td>
												</tr>
												<tr class="row">
													<td class="col-md-1">첨부파일</td>
													<td class="col-md-8"><input type="file" class=""  name="uploadFile"></td>
												</tr>
												<tr>
													<td colspan="2" align="center"><input type="submit"
														value="등록">&nbsp;&nbsp; <input type="reset"
														value="취소"></td>
												</tr>
											</table>
										</form>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

			<!-- footer -->
			<jsp:include page="../common/studyFooter.jsp" />
		</div>
	</div>

	<!--This page JavaScript -->
	<script
		src="/resources/css/study/assets/extra-libs/taskboard/js/jquery.ui.touch-punch-improved.js"></script>
	<script
		src="/resources/css/study/assets/extra-libs/taskboard/js/jquery-ui.min.js"></script>
	<script src="/resources/css/study/assets/libs/moment/min/moment.min.js"></script>
	<script
		src="/resources/css/study/assets/libs/fullcalendar/fullcalendar.js"></script>
	<script src="/resources/css/study/dist/js/pages/calendar/cal-init.js"></script>
	<script src="//cdn.quilljs.com/1.3.6/quill.js"></script>
<script src="//cdn.quilljs.com/1.3.6/quill.min.js"></script>

</body>
</html>