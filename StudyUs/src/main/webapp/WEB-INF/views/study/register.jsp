<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>스터디 등록 - StudyUs</title>
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
            <h1 class="mb-3 bread">스터디 등록</h1>
            <p class="breadcrumbs mb-0">신규 스터디를 등록합니다.</p>
          </div>
        </div>
      </div>
    </div>

	<div class="container">
		<form class="py-5 pt-5 mx-auto" id="registerForm" style="max-width: 600px;" action="/study/register" method="post" enctype="multipart/form-data">
			<div class="form-group">
				<label for="inputEmail4">스터디명 <span style="color: red;">*</span></label> 
				<input type="text" class="form-control" id="inputName" name="studyName" required>
				<small id="nameHelp" class="form-text">4 ~ 32 자리의 한글, 영문 및 숫자만 가능합니다.</small>
			</div>
			<div class="form-group">
				<label for="inputPassword4">스터디 URL <span style="color: red;">*</span></label> 
				<input type="text" class="form-control" id="inputUrl" name="url" onkeyup="urlCheck(this);" required>
				<small id="urlHelp" class="form-text">4 ~ 32 자리의 영문 및 숫자만 가능합니다.</small>
			</div>
			<div class="form-group">
				<label for="inputIntroduce">스터디 소개 <span style="color: red;">*</span></label> <textarea class="form-control" id="inputIntroduce" name="introduce" required></textarea>
			</div>
			<div class="form-row">
				<div class="form-group col-9">
					<label for="inputHashtag">해시태그</label> 
					<input type="text" class="form-control" id="inputHashtag">
				</div>
				<div class="form group col-3" align="center">
					<input type="button" class="btn btn-primary btn-sm" onclick="onAddHashtagClicked();" value="추가하기" style="margin-top: 46px;">
				</div>
			</div>
			<div class="form-row" id="hashtagView">
				<span>현재 태그: &nbsp;</span>
				<!-- <span class="btn btn-primary btn-sm mr-2" style="margin: 4px;" onclick="onRemoveHashtagClicked(this);">수채화&nbsp;<i class="fas fa-times"></i></span> -->
			</div>
			<div class="form-group m-3">
				<label for="inputAddress">활동 일시</label> 
				<input type="button" class="btn btn-primary btn-sm" id="modalButton" data-toggle="modal" data-target="#meetingDayModal" value="설정하기">
				<small id="meetingDayHelp" class="form-text text-muted">주로 활동하는 시간을 설정하세요.</small>
			</div>
			<div class="form-row mb-3 mb-2" id="meetingDayDisplay">
				<span class="meetingDayView btn btn-light btn-sm mr-1">월</span>
				<span class="meetingDayView btn btn-light btn-sm mr-1">화</span>
				<span class="meetingDayView btn btn-primary btn-sm mr-1">수</span>
				<span class="meetingDayView btn btn-light btn-sm mr-1">목</span>
				<span class="meetingDayView btn btn-light btn-sm mr-1">금</span>
				<span class="meetingDayView btn btn-primary btn-sm mr-1">토</span>
				<span class="meetingDayView btn btn-light btn-sm mr-1">일</span>
				<span id="meetingTimeView" class="btn btn-secondary btn-sm mx-2">-- : --  ~  -- : --</span>
			</div>
			<div class="input-group mb-3">
			  <div class="input-group-prepend">
			    <span class="input-group-text" id="inputGroupFileAddon01">대표사진</span>
			  </div>
			  <div class="custom-file">
			    <input type="file" class="custom-file-input" id="inputGroupFile01" aria-describedby="inputGroupFileAddon01">
			    <label class="custom-file-label" for="inputGroupFile01">파일 선택</label>
			  </div>
			</div>
			<div class="input-group mt-5">
				<button type="submit" class="btn btn-primary">스터디 등록</button>
			</div>
			<!-- <input type="hidden" id="hashtagList" name="hashtagList" value=""> -->
			<!-- Modal -->
			<div class="modal fade" id="meetingDayModal" tabindex="-1"
				aria-labelledby="meetingDayModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="exampleModalLabel">활동 일시</h5>
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body" id="modal-study">
							<div class="row px-4 mx-auto">
								<div class="input-group">
									<div class="custom-control custom-checkbox custom-control-inline">
										<input type="checkbox" id="monday" name="monday" class="meetingDayInput custom-control-input" value="1"> 
										<label class="custom-control-label" for="monday">월</label>
									</div>
									<div class="custom-control custom-checkbox custom-control-inline">
										<input type="checkbox" id="tuesday" name="tuesday" class="meetingDayInput custom-control-input" value="1"> 
										<label class="custom-control-label" for="tuesday">화</label>
									</div>
									<div class="custom-control custom-checkbox custom-control-inline">
										<input type="checkbox" id="wednesday" name="wednesday" class="meetingDayInput custom-control-input" value="1"> 
										<label class="custom-control-label" for="wednesday">수</label>
									</div>
									<div class="custom-control custom-checkbox custom-control-inline">
										<input type="checkbox" id="thursday" name="thursday" class="meetingDayInput custom-control-input" value="1"> 
										<label class="custom-control-label" for="thursday">목</label>
									</div>
									<div class="custom-control custom-checkbox custom-control-inline">
										<input type="checkbox" id="friday" name="friday" class="meetingDayInput custom-control-input" value="1"> 
										<label class="custom-control-label" for="friday">금</label>
									</div>
									<div class="custom-control custom-checkbox custom-control-inline">
										<input type="checkbox" id="saturday" name="saturday" class="meetingDayInput custom-control-input" value="1"> 
										<label class="custom-control-label" for="saturday">토</label>
									</div>
									<div class="custom-control custom-checkbox custom-control-inline">
										<input type="checkbox" id="sunday" name="sunday" class="meetingDayInput custom-control-input" value="1"> 
										<label class="custom-control-label" for="sunday">일</label>
									</div>
								</div>
							</div>
							<div class="row p-4 mx-auto">
								<div class="input-group mx-auto">
									<select name="start-h" id="start-h" class="form-control form-control-sm">
										<option value="">--</option>
										<option value="00">0</option>
										<option value="01">1</option>
										<option value="02">2</option>
										<option value="03">3</option>
										<option value="04">4</option>
										<option value="05">5</option>
										<option value="06">6</option>
										<option value="07">7</option>
										<option value="08">8</option>
										<option value="09">9</option>
										<option value="10">10</option>
										<option value="11">11</option>
										<option value="12">12</option>
										<option value="13">13</option>
										<option value="14">14</option>
										<option value="15">15</option>
										<option value="16">16</option>
										<option value="17">17</option>
										<option value="18">18</option>
										<option value="19">19</option>
										<option value="20">20</option>
										<option value="21">21</option>
										<option value="22">22</option>
										<option value="23">23</option>
									</select>
									<select name="start-m" id="start-m" class="form-control form-control-sm">
										<option value="">--</option>
										<option value="00">00</option>
										<option value="10">10</option>
										<option value="20">20</option>
										<option value="30">30</option>
										<option value="40">40</option>
										<option value="50">50</option>
									</select>
									<span>&nbsp;&nbsp;~&nbsp;&nbsp;</span>
									<select name="end-h" id="end-h" class="form-control form-control-sm">
										<option value="">--</option>
										<option value="00">0</option>
										<option value="01">1</option>
										<option value="02">2</option>
										<option value="03">3</option>
										<option value="04">4</option>
										<option value="05">5</option>
										<option value="06">6</option>
										<option value="07">7</option>
										<option value="08">8</option>
										<option value="09">9</option>
										<option value="10">10</option>
										<option value="11">11</option>
										<option value="12">12</option>
										<option value="13">13</option>
										<option value="14">14</option>
										<option value="15">15</option>
										<option value="16">16</option>
										<option value="17">17</option>
										<option value="18">18</option>
										<option value="19">19</option>
										<option value="20">20</option>
										<option value="21">21</option>
										<option value="22">22</option>
										<option value="23">23</option>
									</select>
									<select name="end-m" id="end-m" class="form-control form-control-sm">
										<option value="">--</option>
										<option value="00">00</option>
										<option value="10">10</option>
										<option value="20">20</option>
										<option value="30">30</option>
										<option value="40">40</option>
										<option value="50">50</option>
									</select>
								
									<!-- <input type="time" class="form-control" id="start" name="start">
									<span class="px-2">~</span>
									<input type="time" class="form-control" id="end" name="end"> -->
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<!-- <button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button> -->
							<button type="button" class="btn btn-primary" data-dismiss="modal">완료</button>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>

	<footer class="ftco-footer ftco-bg-dark ftco-section">
      <div class="container">
      	<div class="row mb-5 pb-5 align-items-center d-flex">
      		<div class="col-md-6">
      			<div class="heading-section heading-section-white ftco-animate">
      				<span class="subheading">Get an easy quote</span>
	            <h2 style="font-size: 30px;">Sign Up For Web Hosting Today!</h2>
	          </div>
      		</div>
      		<div class="col-md-3 ftco-animate">
      			<div class="price">
      				<span class="subheading">Starting at Only</span>
      				<h3>$4.50<span>/mo</span></h3>
      			</div>
      		</div>
      		<div class="col-md-3 ftco-animate">
      			<p class="mb-0"><a href="#" class="btn btn-primary py-3 px-4">Get started now</a></p>
      		</div>
      	</div>
        <div class="row mb-5">
          <div class="col-md">
            <div class="ftco-footer-widget mb-4 bg-primary p-4">
              <h2 class="ftco-heading-2">CoHost</h2>
              <p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts.</p>
              <ul class="ftco-footer-social list-unstyled mb-0">
                <li class="ftco-animate"><a href="#"><span class="icon-twitter"></span></a></li>
                <li class="ftco-animate"><a href="#"><span class="icon-facebook"></span></a></li>
                <li class="ftco-animate"><a href="#"><span class="icon-instagram"></span></a></li>
              </ul>
            </div>
          </div>
          <div class="col-md">
            <div class="ftco-footer-widget mb-4 ml-md-5">
              <h2 class="ftco-heading-2">Unseful Links</h2>
              <ul class="list-unstyled">
                <li><a href="#" class="py-2 d-block">Servers</a></li>
                <li><a href="#" class="py-2 d-block">Windos Hosting</a></li>
                <li><a href="#" class="py-2 d-block">Cloud Hosting</a></li>
                <li><a href="#" class="py-2 d-block">OS Servers</a></li>
                <li><a href="#" class="py-2 d-block">Linux Servers</a></li>
                <li><a href="#" class="py-2 d-block">Policy</a></li>
              </ul>
            </div>
          </div>
          <div class="col-md">
             <div class="ftco-footer-widget mb-4">
              <h2 class="ftco-heading-2">Navigational</h2>
              <ul class="list-unstyled">
                <li><a href="#" class="py-2 d-block">Home</a></li>
                <li><a href="#" class="py-2 d-block">Domain</a></li>
                <li><a href="#" class="py-2 d-block">Hosting</a></li>
                <li><a href="#" class="py-2 d-block">About</a></li>
                <li><a href="#" class="py-2 d-block">Blog</a></li>
                <li><a href="#" class="py-2 d-block">Contact</a></li>
              </ul>
            </div>
          </div>
          <div class="col-md">
            <div class="ftco-footer-widget mb-4">
            	<h2 class="ftco-heading-2">Office</h2>
            	<div class="block-23 mb-3">
	              <ul>
	                <li><span class="icon icon-map-marker"></span><span class="text">203 Fake St. Mountain View, San Francisco, California, USA</span></li>
	                <li><a href="#"><span class="icon icon-phone"></span><span class="text">+2 392 3929 210</span></a></li>
	                <li><a href="#"><span class="icon icon-envelope"></span><span class="text">info@yourdomain.com</span></a></li>
	              </ul>
	            </div>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-md-12 text-center">

            <p><!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
  Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i class="icon-heart" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank">Colorlib</a>
  <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. --></p>
          </div>
        </div>
      </div>
    </footer>
    
  

  <!-- loader -->
  <div id="ftco-loader" class="show fullscreen"><svg class="circular" width="48px" height="48px"><circle class="path-bg" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke="#eeeeee"/><circle class="path" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke-miterlimit="10" stroke="#F96D00"/></svg></div>


  <script src="/resources/css/main/js/jquery.min.js"></script>
  <script src="/resources/css/main/js/jquery-migrate-3.0.1.min.js"></script>
  <script src="/resources/css/main/js/popper.min.js"></script>
  <script src="/resources/css/main/js/bootstrap.min.js"></script>
  <script src="/resources/css/main/js/jquery.easing.1.3.js"></script>
  <script src="/resources/css/main/js/jquery.waypoints.min.js"></script>
  <script src="/resources/css/main/js/jquery.stellar.min.js"></script>
  <script src="/resources/css/main/js/owl.carousel.min.js"></script>
  <script src="/resources/css/main/js/jquery.magnific-popup.min.js"></script>
  <script src="/resources/css/main/js/aos.js"></script>
  <script src="/resources/css/main/js/jquery.animateNumber.min.js"></script>
  <script src="/resources/css/main/js/bootstrap-datepicker.js"></script>
  <script src="/resources/css/main/js/jquery.timepicker.min.js"></script>
  <script src="/resources/css/main/js/scrollax.min.js"></script>
  <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&sensor=false"></script>
  <script src="/resources/css/main/js/google-map.js"></script>
  <script src="/resources/css/main/js/main.js"></script>
  <script src="/resources/js/studyRegister.js"></script>
    
  </body>
</html>