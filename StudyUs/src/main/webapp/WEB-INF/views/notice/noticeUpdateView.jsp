<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/resources/css/summernote/summernote-lite.css">
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
										<form class="" role="form" action="/notice/noticeUpdate" method="post" enctype="multipart/form-data">
										<input type="hidden" name="noNo" value="${notice.noNo }">
										<input type="hidden" name="noFileName" value="${notice.noFileName }">
										<input type="hidden" name="noReFileName" value="${notice.noReFileName }">
										<input type="hidden" name="noWriter" value="${notice.noWriter }">
											<table class="table-responsive col-md-12 ">
												<tr class="row" >
													<td class="col-md-2 text-center">제목</td>
													<td class="col-md-10" ><input type="text" class="form-control" name="noTitle" value="${notice.noTitle }"></td>
												</tr>
<%-- 	  											<tr class="row">
													<td class="col-3">작성자</td>
													<td class="col-9"><input type="text" size="50" class="form-control" name="noticeWriter"
														value="${loginUser.userId }" readonly></td>
												</tr> --%>
												<tr><td>&nbsp;</td></tr>
												<tr class="row">
													<td class="col-md-2 text-center">내용</td>
													<td class="col-md-10">
													<textarea class="" id="summernote" name="noContents">${notice.noContents }</textarea>
													</td>
												</tr>
												<tr><td>&nbsp;</td></tr>
												<tr class="row">
													<td class="col-md-2 text-center">첨부파일</td>
													<td>&nbsp;&nbsp;&nbsp;</td>
													<td class="col-md-9">
													<!-- <input type="file" class=""  name="uploadFile"> -->
													<input type="file" class="custom-file-input" id="customFile" name="reloadFile">
            										<label class="custom-file-label" for="customFile">
            										<c:if test="${ !empty notice.noFileName  }">
														${notice.noFileName }
													</c:if>
													<c:if test="${empty notice.noFileName }">
            											파일선택
            										</c:if>
            										</label>
													</td>
												</tr>
												<tr><td>&nbsp;</td></tr>
												<tr class="row">
													<td class="col-md-10"></td>
													<td class="col-md-2 text-center">
													<input type="reset" class="btn waves-effect waves-light btn-light" value="취소"  onclick="history.back()">&nbsp;&nbsp; 
													<input type="submit" class="btn waves-effect waves-light btn-primary" value="등록"></td>
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
	
	<script src="/resources/js/summernote/summernote-lite.js"></script>
  <script src="/resources/js/summernote/lang/summernote-ko-KR.js"></script>
  
	
	<!-- <script
		src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-bs4.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script> -->
	<script>
			$('#summernote').summernote({
				height : 400, // set editor height
				minHeight : null, // set minimum height of editor
				maxHeight : null, // set maximum height of editor
				focus : true,
				lang : 'ko-KR',
				toolbar: [
				    // [groupName, [list of button]]
				    ['fontname', ['fontname']],
				    ['fontsize', ['fontsize']],
				    ['style', ['bold', 'italic', 'underline','strikethrough', 'clear']],
				    ['color', ['forecolor','color']],
				    ['table', ['table']],
				    ['para', ['ul', 'ol', 'paragraph']],
				    ['height', ['height']],
				    ['insert',['picture','link','video']],
				    ['view', ['fullscreen', 'help']]
				  ],
				fontNames: ['Arial', 'Arial Black', 'Comic Sans MS', 'Courier New','맑은 고딕','궁서','굴림체','굴림','돋움체','바탕체'],
				fontSizes: ['8','9','10','11','12','14','16','18','20','22','24','28','30','36','50','72'] // 기본 메뉴언어 US->KR로 변경
			});

			$("#sidebarnav>li:nth-child(5)").addClass("selected");
			$("#sidebarnav>li:nth-child(5) a").addClass("active");
			
		</script>
</body>
</html>