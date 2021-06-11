<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>StudyUs : 공지사항</title>
<!-- 타이틀을 개별 스터디룸 이름으로 해줘도 좋을듯 ! 'StudyUs : 삼팔광땡' 이러케 -->
<style>
	.noTitle {
		color: #7C8798;
	}
	
	.noTitle :hover {
		color: #6927ff;
	}
</style>
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
									<li class="breadcrumb-item text-muted active"
										aria-current="page">Notice</li>
									<li class="breadcrumb-item text-primary font-weight-bold"
										aria-current="page">List</li>
								</ol>
							</nav>
						</div>
					</div>
					<div class="col-lg-8 align-self-center">
						<form action="/notice/noticeSearch" method="get" id="searchForm">
							<div class="customize-input float-right">
								<input class="form-control custom-shadow custom-radius border-0 bg-white"
									type="text" placeholder="Search" aria-label="Search"
									name="searchValue" value="${search.searchValue }"> 
									<i class="form-control-icon" data-feather="search" id="searchIcon"></i> 
							</div>
							<div class="customize-input float-right"
								style="margin-right: 10px;">
								<select
									class="custom-select bg-white custom-radius border-0 custom-shadow"
									name="searchCondition">
									<option value="all"
										<c:if test="${search.searchCondition == 'all' }">selected</c:if>>제목+내용</option>
									<option value="title"
										<c:if test="${search.searchCondition == 'title' }">selected</c:if>>제목</option>
									<option value="content"
										<c:if test="${search.searchCondition == 'content' }">selected</c:if>>내용</option>
								</select>
							</div>
						</form>
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
				<!-- basic table -->
				<div class="row">
					<div class="col-12">
						<div class="card">
							<div class="card-body">
								<h4 class="card-title">메인 공지사항</h4>
								<div class="border border-3"></div>
								<div class="card-body">
									<p>출석 꼭 찍어주세요. 퇴출당하기전에 . . .</p>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-10"></div>
					<!-- if문 추가하기 -->
					<div class=""> 
						<button onclick="location.href='#'" class="btn waves-effect waves-light btn-primary">
							<span>메인 수정</span>
						</button>
						<button id="write-btn" onclick="location.href='/notice/noticeWriteView'" class="btn waves-effect waves-light btn-primary">
							<!-- <i class="fas fa-edit"></i> -->
							<span>글쓰기</span>
						</button>
					</div>
					<br><br>
					<table class="table col-lg-12" id="nTable">
						<thead class="thead-light">
							<tr>
								<th scope="col" style="width: 1%"></th>
								<th scope="col" style="width: 6%">#</th>
								<th scope="col" style="width: 40%">제목</th>
								<th scope="col" style="width: 10%">작성자</th>
								<th scope="col" style="width: 13%">작성일</th>
								<th scope="col" style="width: 10%">조회수</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
						<div class="col-md-12" style="height : 50px;"></div>
						<div class="col-md-5"></div>
						<div class="">
							등록된 게시글이 없습니다.
					</div>
				</div>
			</div>
		<!-- footer -->
		<jsp:include page="../common/studyFooter.jsp" />
		</div> 
	</div>
	<script>
		$("#sidebarnav>li:nth-child(5)").addClass("selected");
		$("#sidebarnav>li:nth-child(5) a").addClass("active");
		
		$('#currentPage').click(function() { // 현재페이지를 클릭했을때 클릭 안되게 
			return false;
		});
		$(document).ready(function(){
			$("#searchIcon").on("click", function(){
				$("#searchForm").submit();
			});
		});
	</script>
</body>
</html>