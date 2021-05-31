<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	
	.meetingDayView.selected {
		color: white;
		background: #6927ff;
	}
	
	.card:hover {
		cursor: pointer;
	}
	
	#study-modal {
		min-height: 400px;
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
		<form class="py-4 pt-5 mx-auto" id="searchForm" style="max-width: 600px; min-height: 240px;">
			<div class="form-row p-4">
				<div class="form-group col-9">
					<!-- <label for="inputHashtag">해시태그</label>  -->
					<input type="text" class="form-control" id="searchInput" name="keyword" onkeyup="onSearchKeyUp(this);" placeholder="검색어 or #해시태그">
				</div>
				<div class="form group col-3" align="center">
					<input type="button" class="btn btn-primary btn-lg" id="hashtagButton" onclick="onAddHashtag();" value="추가">
					<input type="button" class="btn btn-primary btn-lg d-none" id="searchButton" onclick="onSearch();" value="검색">
				</div>
			</div>
			<div class="form-row" id="hashtagView">
				<span>현재 태그: &nbsp;</span>
				<!-- <span class="btn btn-primary btn-sm mr-2" style="margin: 4px;" onclick="removeHashtag(this);">수채화&nbsp;<i class="fas fa-times"></i></span> -->
			</div>
		</form>
		
		<div id="search-result-grid" class="row">
			<div class="study-container col-lg-4 mb-4 d-none">
				<div class="card h-100" data-toggle="modal" data-target="#exampleModal" onclick="onStudyContainerClicked(this);">
					<img src="/resources/images/sample1.jpg" class="card-img-top" alt="...">
					<div class="card-body">
						<h5 class="card-title study-name">{스터디명}</h5>
						<p class="card-text study-introduce">{스터디 입니다.}</p>
						<div class="card-text study-hashtags">{#해시태그 #해시태그}</div>
					</div>
				</div>
			</div>
		</div>
		
		<!-- 검색 끝 안내 -->		
		<div id="searchGuide" align="center"></div>
		<div class="py-4"></div>
	</div>
	
	<!-- Modal -->
	<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered">
			<div id="study-modal" class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="modalLabel">{스터디명}</h5>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="row pb-2">
						<div class="col-3">
							<b>소개</b>
						</div>
						<div id="study-introduce" class="col-9">
							{스터디 소개}
						</div>
					</div>
					<div class="row pb-2">
						<div class="col-3">
							<b>활동일시</b>
						</div>
						<div class="col-9">
							<span class="meetingDayView btn btn-light btn-sm mr-1">월</span>
							<span class="meetingDayView btn btn-light btn-sm mr-1">화</span>
							<span class="meetingDayView btn btn-light btn-sm mr-1">수</span>
							<span class="meetingDayView btn btn-light btn-sm mr-1">목</span>
							<span class="meetingDayView btn btn-light btn-sm mr-1">금</span>
							<span class="meetingDayView btn btn-light btn-sm mr-1">토</span>
							<span class="meetingDayView btn btn-light btn-sm mr-1">일</span>
						</div>
					</div>
					<div class="row pb-2">
						<div class="col-3">
						</div>
						<div class="col-9" id="meetingTimeView">
							00 : 00  ~  00 : 00
						</div>
					</div>
					<hr>
					<div class="row">
						<div class="col-3">
							<b>가입인사</b>
						</div>
						<div class="col-9">
							<textarea id="modal-greeting" class="form-control" rows="3" cols=""></textarea>							
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
					<button type="button" id="apply-button" class="btn btn-primary" onclick="applyStudy();">가입신청</button>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
	
	<script src="/resources/js/studySearch.js"></script>
    
  </body>
</html>