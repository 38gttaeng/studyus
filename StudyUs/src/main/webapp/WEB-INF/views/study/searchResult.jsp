<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<title>스터디 검색 - StudyUs</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	<link href="https://fonts.googleapis.com/css?family=Work+Sans:300,400,,500,600,700" rel="stylesheet">
	
	<link rel="stylesheet" href="/resources/css/main/css/open-iconic-bootstrap.min.css">
	<link rel="stylesheet" href="/resources/css/main/css/animate.css">
	
	<link rel="stylesheet" href="/resources/css/main/css/owl.carousel.min.css">
	<link rel="stylesheet" href="/resources/css/main/css/owl.theme.default.min.css">
	<link rel="stylesheet" href="/resources/css/main/css/magnific-popup.css">
	
	<link rel="stylesheet" href="/resources/css/main/css/aos.css">
	
	<link rel="stylesheet" href="/resources/css/main/css/ionicons.min.css">
	
	<link rel="stylesheet" href="/resources/css/main/css/bootstrap-datepicker.css">
	<link rel="stylesheet" href="/resources/css/main/css/jquery.timepicker.css">
	
	<link rel="stylesheet" href="/resources/css/main/css/flaticon.css">
	<link rel="stylesheet" href="/resources/css/main/css/icomoon.css">
	<link rel="stylesheet" href="/resources/css/main/css/style.css">
	<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css" rel="stylesheet">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<style>
	#meetingDayDisplay.btn:hover {
		cursor: normal;
	}
</style>
<body>
	
	<jsp:include page="../common/menubar.jsp"/>
	
    <div class="hero-wrap hero-wrap-2" style="background-image: url('/resources/images/study_banner.png'); " data-stellar-background-ratio="0.5">
      <div class="overlay"></div>
      <div class="container">
        <div class="row no-gutters slider-text align-items-end justify-content-start">
          <div class="col-md-8 ftco-animate text-center text-md-left mb-5">
            <h1 class="mb-3 bread">스터디 검색</h1>
            <!-- <p class="breadcrumbs mb-0">스터디를 검색합니다.</p> -->
          </div>
        </div>
      </div>
    </div>

	<div class="container">
		<form class="py-4 pt-5 mx-auto" id="searchForm" style="max-width: 600px; min-height:240px;">
			<div class="form-row p-4">
				<div class="form-group col-9">
					<!-- <label for="inputHashtag">해시태그</label>  -->
					<input type="text" class="form-control" id="searchInput" name="keyword" onkeyup="onSearchKeyUp(this);" value="${keyword }" placeholder="검색어 or #해시태그">
				</div>
				<div class="form group col-3" align="center">
					<input type="button" class="btn btn-primary btn-lg d-none" id="hashtagButton" onclick="onAddHashtag();" value="추가">
					<input type="button" class="btn btn-primary btn-lg" id="searchButton" onclick="onSearch();" value="검색">
				</div>
			</div>
			<div class="form-row" id="hashtagView">
				<span>현재 태그: &nbsp;</span>
				<!-- <span class="btn btn-primary btn-sm mr-2" style="margin: 4px;" onclick="onRemoveHashtagClicked(this);">수채화&nbsp;<i class="fas fa-times"></i></span> -->
			</div>
			<input type="hidden" name="page" value="1">
		</form>
		
		<div class="row">
		  <!-- <div class="studyContainer col-lg-4 mb-4">
		    <div class="card h-100">
		      <img src="/resources/images/sample1.jpg" class="card-img-top" alt="...">
		      <div class="card-body">
		        <h5 class="card-title">자바 스터디</h5>
		        <p class="card-text">자바 스터디 입니다. 스프링 프레임워크를 이용해 웹 사이트 제작하실 분 오세요.</p>
		      </div>
		    </div>
		  </div> -->
			<c:forEach var="study" items="${studies }" varStatus="i">
				<div class="studyContainer col-lg-4 mb-4">
					<div class="card h-100">
						<img src="/resources/images/sample1.jpg" class="card-img-top" alt="...">
						<div class="card-body">
							<h5 class="card-title"><c:out value="${study.studyName }"></c:out></h5>
							<p class="card-text"><c:out value="${study.introduce}"></c:out></p>
						</div>
					</div>
					<input type="hidden" class="url" value="${study.url }">
				</div>
			</c:forEach>
		</div>
		
		<div id="additionalLoadProperties">
			<input type="hidden" id="currentPage" value="${page }"/>
		</div>
		
		<div class="py-4"></div>
		
	</div>
	
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
	
	<script src="/resources/js/studySearchResult.js"></script>
    
  </body>
</html>