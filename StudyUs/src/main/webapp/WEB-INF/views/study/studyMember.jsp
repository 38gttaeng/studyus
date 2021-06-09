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
		color: #6928FF;
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
						<h4 class="page-title text-truncate text-dark font-weight-medium mb-1">회원 목록</h4>
						<div class="d-flex align-items-center">
							<nav aria-label="breadcrumb">
								<ol class="breadcrumb m-0 p-0">
									<li class="breadcrumb-item text-muted" aria-current="page"> <a href="/study">Study</a></li>
									<li class="breadcrumb-item text-muted active" aria-current="page">Member</li>
									<li class="breadcrumb-item text-primary font-weight-bold" aria-current="page">List</li>
								</ol>
							</nav>
						</div>
					</div>
					<%-- <div class="col-lg-8 align-self-center">
						<form action="/notice/noticeSearch" method="get" id="searchForm">
							<div class="customize-input float-right">
								<input class="form-control custom-shadow custom-radius border-0 bg-white" type="text" placeholder="Search" aria-label="Search" name="searchValue" value="${search.searchValue }"> 
								<i class="form-control-icon" data-feather="search" id="searchIcon"></i>
							</div>
							<div class="customize-input float-right" style="margin-right: 10px;">
								<select class="custom-select bg-white custom-radius border-0 custom-shadow" name="searchCondition">
									<option value="all" <c:if test="${search.searchCondition == 'all' }">selected</c:if>>제목+내용</option>
									<option value="title" <c:if test="${search.searchCondition == 'title' }">selected</c:if>>제목</option>
									<option value="content" <c:if test="${search.searchCondition == 'content' }">selected</c:if>>내용</option>
								</select>
							</div>
						</form>
					</div> --%>
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
					<table class="table col-lg-12" id="nTable">
						<thead class="thead-light">
							<tr>
								<th scope="col" style="width: 1%"></th>
								<th scope="col" style="width: 6%">#</th>
								<th scope="col" style="width: 10%">닉네임</th>
								<th scope="col" style="width: 23%">이메일</th>
								<th scope="col" style="width: 10%">과제율</th>
								<th scope="col" style="width: 10%">출석률</th>
								<th scope="col" style="width: 10%">관리</th>
							</tr>
						</thead>
						<tbody>
							@@@@@@@@과제율, 출석률, 추방버튼 활성 필요
							<c:forEach items="${mList }" var="member">
								<tr>
									<th>
										<input type="hidden" name="mbNo" value="${ member.mbNo }"> 
									</th>
									<th scope="row">${member.rnum } </th>
									<td class="">${ member.mbNickname }</td>
									<td>${ member.mbEmail }</td>
									<td>32%</td>
									<td>99%</td>
									<td> 
										<c:if test="${member.mbNo eq study.leaderNo }">
											
										</c:if>
										<c:if test="${ member.mbNo ne study.leaderNo }">
											<form action="/member/banish" method="get">
												<input type="submit" class="btn waves-effect waves-light btn-danger btn-sm" value="추방">
												<input type="hidden" name="mbNo" value="${ member.mbNo }"> 
												<input type="hidden" name="studyNo" value="${ study.studyNo }"> 
											</form> 
										</c:if>
									</td>
								</tr>
							</c:forEach>
						</tbody>
						<tr></tr>
					</table>
			</div>
		</div> 
		<!-- footer -->
		<jsp:include page="../common/studyFooter.jsp" />
	</div>
	<script>
		$("#sidebarnav>li:nth-child(12)").addClass("selected");
		$("#sidebarnav>li:nth-child(12) a").addClass("active");
		
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