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
<title>공지사항 : StudyUs</title>
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
						<h4 class="page-title text-truncate text-dark font-weight-medium mb-1">공지사항</h4>
						<div class="d-flex align-items-center">
							<nav aria-label="breadcrumb">
								<ol class="breadcrumb m-0 p-0">
									<li class="breadcrumb-item text-muted" aria-current="page"> <a href="/study">Study</a></li>
									<li class="breadcrumb-item text-muted active" aria-current="page">Notice</li>
									<li class="breadcrumb-item text-primary font-weight-bold" aria-current="page">List</li>
								</ol>
							</nav>
						</div>
					</div>
					<div class="col-lg-8 align-self-center">
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
								<c:forEach items="${mainNotice }" var="notice" >
									<div class="card-body">
											${notice.noContents }
									</div>
								</c:forEach>
							</div>
						</div>
					</div>
					<div class="col-md-10"></div>
					<!-- if문 추가하기 -->
					<div class="">
						<!-- <form class="" role="form" action="/notice/mainNoticeUpdate" method="post">
							<input type="hidden" name="noNo" value="${notice.noNo }"> 
							<input type="hidden" name="noContents" value="${notice.noContents }"> 
							<button onclick="history.back()" class="btn waves-effect waves-light btn-light">
								<span>취소</span>
							</button>
							<button onclick="location.href='/notice/mainNoticeUpdate?noNo=${notice.noNo}'"
										class="btn waves-effect waves-light btn-primary">수정 완료</button>
						</form> -->
					</div>
					<br> <br>
					<table class="table col-lg-12" id="nTable">
						<thead class="thead-light">
							<tr>
								<th scope="col" style="width: 1%"></th>
								<th scope="col" style="width: 6%">#</th>
								<th scope="col" style="width: 40%">제목</th>
								<th scope="col" style="width: 10%">작성자</th>
								<th scope="col" style="width: 13%">작성일</th>
								<th scope="col" style="width: 10%"> </th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${nList }" var="notice">
								<tr>
									<th>
										<input type="hidden" name="noNo" value="${notice.noNo }">
										<input type="hidden" name="noMotherNo" value="${notice.noMotherNo }">
									</th>
									<th scope="row">${notice.rowNum }</th>
									<td class="">
										<c:url var="nDetail" value="/notice/noticeDetail">
											<c:param name="noNo" value="${notice.noNo}"></c:param>
										</c:url>
										 <a href="${nDetail }" class="noTitle">${notice.noTitle }</a>
										<!-- 누르면 댓글 팝업창으로 --> 
										<a href="#" role="button" id="reply-list"> 
											<span class="">
												<c:if test="${notice.replyCnt ne 0 }">
													[${notice.replyCnt}]
												</c:if> 
												<c:if test="${notice.replyCnt eq 0}">
													[${notice.replyCnt}]
												</c:if>
											</span>
										</a> 
										<span style="color: coral;"> 
											<c:if test="${notice.noInsertDate >= nowDay }">
												<%-- <img class="upload" src="<c:url value='/resources/images/new2.png' />" style="width: 15px;height: 15px;"> --%>
		                    						N
		                    					</c:if>
										</span>
									</td>
									<td>${notice.noWriter }</td>
									<td>${notice.noInsertDate }</td>
									<td>
										<form action="/notice/mainNoticeUpdate" method="post">
											<input type="submit" class="btn waves-effect waves-light btn-primary" value="메인 설정">
											<input type="hidden" name="noNo" value="${notice.noNo }">
										</form>
									</td>
								</tr>
							</c:forEach>
						</tbody>
						<tr></tr>
					</table>
					<!-- 페이징 -->
					<div class="col-md-5"></div>
					<div class="row">
						<nav aria-label="Page navigation example">
							<c:if test="${ search.searchValue eq null}">
								<ul class="pagination" style="justify-content: center;">
									<c:url var="before" value="/notice/mainSelectView">
										<c:param name="page" value="${pi.currentPage - 1 }"></c:param>
									</c:url>
									<li class="page-item disabled">
									<c:if test="${pi.currentPage <= 1 }">
											<a href="#" class="page-link" aria-label="Previous">
												 <span aria-hidden="true"><<</span> 
												 <span class="sr-only">Previous</span>
											</a>
										</c:if>
									</li>
									<li class="page-item">
										<c:if test="${pi.currentPage > 1 }">
											<a href="${before }" class="page-link" aria-label="Previous">
												<span aria-hidden="true"><<</span> 
												<span class="sr-only">Previous</span>
											</a>
										</c:if>
									</li>

									<c:forEach var="p" begin="${pi.startPage }" end="${pi.endPage }">
										<c:url var="pagination" value="/notice/mainSelectView">
											<c:param name="page" value="${p }"></c:param>
										</c:url>
										<li class="page-item active">
											<c:if test="${p eq pi.currentPage }">
												<a href="${pagination }" class="page-link" id="currentPage">${p }</a>
											</c:if>
										</li>
										<li class="page-item">
											<c:if test="${p ne pi.currentPage }">
												<a href="${pagination }" class="page-link">${p }</a>
											</c:if>
										</li>
									</c:forEach>

									<c:url var="after" value="/notice/mainSelectView">
										<c:param name="page" value="${pi.currentPage + 1 }"></c:param>
									</c:url>
									<li class="page-item disabled">
										<c:if test="${pi.currentPage >= pi.maxPage }">
											<a href="#" class="page-link" aria-label="Next"> 
												<span aria-hidden="true">>></span> 
												<span class="sr-only">Next</span>
											</a>
										</c:if>
									</li>
									<li class="page-item">
									<c:if test="${pi.currentPage < pi.maxPage }">
											<a href="${after }" class="page-link" aria-label="Next">
												<span aria-hidden="true">>></span> 
												<span class="sr-only">Next</span>
											</a>
										</c:if>
									</li>
								</ul>
							</c:if> 
						</nav>
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
		
		$('.noTitle').click(function() {  
			return false;
		});
		
		$(document).ready(function(){
			$("#searchIcon").on("click", function(){
				$("#searchForm").submit();
			});
		});
		
		function checkOnlyOne(element) {
			  
			  const checkboxes 
			      = document.getElementsByName("mainNotice");
			  
			  checkboxes.forEach((cb) => {
			    cb.checked = false;
			  })
			  
			  element.checked = true;
			}
		
	</script>
</body>
</html>