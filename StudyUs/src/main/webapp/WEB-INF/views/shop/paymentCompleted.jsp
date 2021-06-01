<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>StudyUs</title>
  </head>
  <body>
    <!-- menubar -->
    <jsp:include page="../common/menubar.jsp"/>

  <div class="hero-wrap hero-wrap-2" style="background-image: url('/resources/images/premiumshop.png');" data-stellar-background-ratio="0.5">
      <div class="overlay"></div>
      <div class="container">
        <div class="row no-gutters slider-text align-items-end justify-content-start">
          <div class="col-md-4 ftco-animate text-center text-md-left mb-5" style="background-color: rgba( 230, 231, 235, 0.5 );">
          	<p class="breadcrumbs mb-0"><span class="mr-3"><a href="index.html">Home <i class="ion-ios-arrow-forward"></i></a></span> <span>StudyShop</span></p>
            <h1 class="mb-3 bread">Study Shop</h1>
          </div>
        </div>
      </div>
    </div>
	<div class="text-center" style="margin-top:70px">
		<img alt="" src="/resources/images/check.png" width="100px" height="100px">
	</div>
	
	<div class="text-center" style="margin-top:50px">
		<h5><b>결제가 정상적으로 처리되었습니다.</b></h5>
		<p>스터디어스에서 더욱 즐거운 공부시간을 보낼 수 있도록 <br>
				항상 노력하겠습니다. <br>
				서비스를 이용해주셔서 감사합니다.</p>
	</div>
	
	<div class="text-center" style="margin-top:50px"> <!-- 스터디로 이동 추가하기 -->
		<button type="button" class="btn btn-primary d-block px-3 py-3 mb-4" style="width: 20%; margin-left:40%;" >내 스터디로</button>
	</div>
	
	<!-- footer -->
	<jsp:include page="../common/footer.jsp"/>
	

	<script>
	$(".nav-item:eq(2)").addClass("active");
	</script>
  </body>
</html>