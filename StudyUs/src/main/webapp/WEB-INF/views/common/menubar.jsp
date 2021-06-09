<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
   <meta charset="UTF-8"> 
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    
    <!-- favicon -->
	<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon">
	<link rel="icon" href="/favicon.ico" type="image/x-icon">
	
	<!-- CSS -->
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
    <link rel="stylesheet" href="/resources/css/main/cohost.css">
    <link rel="stylesheet" href="/resources/css/main/css/style.css">
    
    <!-- JS -->
    <script src="/resources/css/main/js/jquery.min.js"></script>
	<script src="/resources/css/main/js/jquery-migrate-3.0.1.min.js"></script>
<title>menubar</title>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light" id="ftco-navbar">
	    <div class="container">
	      <a class="navbar-brand" href="/">
	      	<img src="/resources/css/study/assets/images/logo-icon2.png"/>
	      	<img src="/resources/css/study/assets/images/logo-text2.png" alt="StudyUs"/>
	      </a>
	      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#ftco-nav" aria-controls="ftco-nav" aria-expanded="false" aria-label="Toggle navigation">
	        <span class="oi oi-menu"></span> Menu
	      </button>
	      <div class="collapse navbar-collapse" id="ftco-nav">
	        <ul class="navbar-nav ml-auto">
	        	<li class="nav-item"><a href="/" class="nav-link">홈</a></li>
	        	<li class="nav-item dropdown">
		          	<a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button">스터디</a>
	              	<div class="dropdown-menu">
	                  <a class="dropdown-item" href="/study/search">스터디 검색</a>
	                  <a class="dropdown-item" href="#">Another action</a>
	                  <a class="dropdown-item" href="#">Something else here</a>
	                  <div class="dropdown-divider"></div>
	                  <a class="dropdown-item" href="/study/register">스터디 등록</a>
	                </div>
	          	</li>
	          	<li class="nav-item"><a href="/shop/premiumShopView" class="nav-link">스터디샵</a></li>
	          	<li class="nav-item"><a href="/cafe/list" class="nav-link">스터디카페</a></li>
	          	<!-- 로그인 전 --> 
	          	<c:if test="${ empty loginUser }">
		          	<li class="nav-item cta">
		          		<a href="/member/loginView" class="nav-link">
							<span>로그인</span>
						</a>
					</li>
				</c:if>
	          	<!-- 로그인 후 -->
	          	<c:if test="${ !empty loginUser }">
	          	<li class="nav-item dropdown">
		          	<a class="nav-link dropdown-toggle" href="javascript:void(0)" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	                    <img src="/resources/images/${ loginUser.mbPhoto }.png" class="rounded-circle" width="25">
	                    <span class="ml-2 d-none d-lg-inline-block">
	                    	<span class="text-dark">${ loginUser.mbNickname }</span>
	                    	<i data-feather="chevron-down" class="svg-icon"></i>
	                    </span>
	                </a>
	              	<div class="dropdown-menu">
	                  <a class="dropdown-item" href="/study/search">
	                  	<c:if test="${ !empty enrolledStudyList }">
	                		<c:forEach var="study" items="${ enrolledStudyList }" varStatus="i">
	                        	<a class="dropdown-item" href="/study/${study.url }">
				                	<c:out value="${study.studyName }"></c:out>
				                </a>
		                    </c:forEach>
	                	</c:if>
	                  </a>
	                  <div class="dropdown-divider"></div>
	                  <a class="dropdown-item" href="/member/myPage">마이페이지</a>
		              <div class="dropdown-divider"></div>
		              <a class="dropdown-item" href="/member/logout">로그아웃 </a>
	                </div>
	          	</li>
	            </c:if>
	            </ul>
	      </div>
	    </div>
	</nav>
</body>
</html>