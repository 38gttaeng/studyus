<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="/resources/css/studyus/reservationDetail.css" rel="stylesheet">
	<title>StudyUs : 스터디룸</title>
</head>
<body>
	<!-- ============================================================== -->
    <!-- Main wrapper -->
    <!-- ============================================================== -->
    <div id="main-wrapper" data-theme="light" data-layout="vertical" data-navbarbg="skin6" data-sidebartype="full"
        data-sidebar-position="fixed" data-header-position="fixed" data-boxed-layout="full">
        
        <!-- menubar -->
	    <jsp:include page="../common/studyMenubar.jsp"/>
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
                    <div class="col-lg-4 align-self-center">
                        <h4 class="page-title text-truncate text-dark font-weight-medium mb-1">일정</h4>
                        <div class="d-flex align-items-center">
                            <nav aria-label="breadcrumb">
                                <ol class="breadcrumb m-0 p-0">
                                    <li class="breadcrumb-item text-muted active" aria-current="page">Study</li>
                                    <li class="breadcrumb-item text-muted" aria-current="page"><a href="/study/calendar">Calendar</a></li>
                                    <li class="breadcrumb-item text-primary font-weight-bold" aria-current="page">모임</li>
                                </ol>
                            </nav>
                        </div>
                    </div>
                    <div class="col-lg-8 align-self-center">
                    	<div class="float-right">
	                    	<c:if test="${ loginUser.mbNo == reservation.mbNo }">
		                    	<button id="delete-btn" class="btn btn-secondary">예약취소</button>
                    		</c:if>
	                    	<button onclick="location.href='/study/calendar'" class="btn btn-primary">목록</button>
                    	</div>
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
                <div class="row">
                    <div class="col-12">
                        <div class="card">
                        
                        	<input type="hidden" name="rsNo" value="${ reservation.rsNo }">
                        	<input type="hidden" name="loginMbNo" value="${ loginUser.mbNo }">
                        
                        	<!-- 상태 -->
                        	<c:if test="${ reservation.rsStatus == 0 }">
                        	<div class="status card-body" style="background-color : #858d96">
                        		예약취소 : ${ reservation.rsAlert }
                        	</div>
                        	</c:if>
                        	<c:if test="${ reservation.rsStatus == 1 }">
                        	<div class="status card-body" style="background-color : #6927ff">
                        		예약완료
                        	</div>
                        	</c:if>
                        	
                            <div class="card-body content">
                            	<h4 class="card-title pt-3 pb-4 text-center">${ cafe.caName } / <span class="text-primary">${ caferoom.crName }</span></h4>
	                            <table class="table">
									<tr>
										<td>예약 일시</td>
										<td colspan="2"><b>${ reservation.rsDate }</b>&nbsp;&nbsp;${ reservation.rsStart }:00 ~ ${ reservation.rsEnd }:00</td>
									</tr>
									<tr>
										<td>결제정보</td>
										<td colspan="2"><b>${ caferoom.crPrice * (reservation.rsEnd - reservation.rsStart) }</b> 원 (${ reservation.rsEnd - reservation.rsStart }시간)</td>
									</tr>
									<tr>
										<td>참여 스터디원 (최대 ${ caferoom.crMax }명)</td>
										<td id="member-box"></td>
										<td class="btn-area"><button id="plus-btn" class="btn btn-circle btn-primary"><i class="fas fa-plus"></i></button></td>
									</tr>
								</table>
                            </div>
                            
                            <div class="card-body row m-2">
	                            <!-- 지도를 표시할 div -->
								<div id="map" class="col-md-7"></div>
								
								<!-- 룸 정보 : 룸 사진, 룸 정보 -->
								<input type="hidden" name="maxCount" value="${ caferoom.crMax }"> 
								<div class="col-md-5">
									<div id="img-box">
										<c:if test="${ !empty caferoom.crFilename }">
										<img id="roomFile" src="/resources/cuploadImages/${ caferoom.crFilename }" width="100%" alt="cafe_img" />
										</c:if>
										<c:if test="${ empty caferoom.crFilename }">
										<img id="roomFile" src="/resources/images/no-image.png" width="100%" alt="cafe_img" />
										</c:if>
									</div>
									
									<p class="ml-2 text-center"><strong>${ caferoom.crName }</strong></p>
									<p class="ml-2">${ caferoom.crInfo }</p>
								</div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- footer -->
			<jsp:include page="../common/studyFooter.jsp"/>
        </div>
    </div>

<script src="/resources/js/reservationDetail.js"></script>
<!-- 지도 js -->
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=063f4122c75f35436f584eefe1993776"></script>
<script src="/resources/js/proj4.js"></script>
<script>
// 좌표 -> 경위도 변환
function getLatLngProj4(firstVal, secondVal) {
	proj4.defs["EPSG:5179"] = "+proj=tmerc +lat_0=38 +lon_0=127.5 +k=0.9996 +x_0=1000000 +y_0=2000000 +ellps=GRS80 +units=m +no_defs";//제공되는 좌표 

	var grs80 = proj4.Proj(proj4.defs["EPSG:5179"]); // api 좌표 유형
	var wgs84 = proj4.Proj(proj4.defs["EPSG:4326"]); // 경위도 
	
	// 좌표 값 넣어주기 
	var p = proj4.Point(Number(firstVal),Number(secondVal)); 
	// 좌표 유형 바꿔주기
	p = proj4.transform( grs80, wgs84, p); 
	console.log(p.x + " " + p.y); 
	return p;
}

// 지도 api
$(function() {
	var positions = [];
	var cafe = new Object();
	cafe.caNo = "${cafe.caNo}";
	cafe.caName = "${cafe.caName }";
	cafe.lat = "${cafe.caLat }";
	cafe.lng = "${cafe.caLng }";
	cafe.caAddr = "${cafe.caAddr }";
	positions.push(cafe);
	var calPosition = getLatLngProj4(cafe.lat, cafe.lng);
	
	var mapContainer = document.getElementById('map'),  // 지도를 표시할 div
		mapOption = {
			center : new kakao.maps.LatLng(calPosition.y+0.0007, calPosition.x), // 지도의 중심좌표
			level : 3 // 지도 확대 레벨
		};
	
	 // 지도 생성
	var map = new kakao.maps.Map(mapContainer, mapOption); 
	
	// 마커 이미지
	var imageSrc = "/resources/images/marker.png";
	var imageSize = new kakao.maps.Size(30, 60);
	// 마커 이미지를 생성
	var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);
	// 마커를 표시할 위치
	var latlng = new kakao.maps.LatLng(calPosition.y, calPosition.x);
		
	// 마커를 생성
	var marker = new kakao.maps.Marker({
		map : map, 
		position : latlng,
		image : markerImage
	});
	
    // 오버레이에 띄울 내용
    var content =   '<div class="wrap">' + 
           '    <div class="info">' + 
           '        <div class="title">' + 
           '<a href="/cafe/detail?caNo=' + cafe.caNo + '">' + cafe.caName + '</a>' +
           '            <div class="close"></div>' + 
           '        </div>' + 
           '        <div class="body">' + 
           '            <div class="desc">' + 
           '                <div class="ellipsis">' + cafe.caAddr + '</div>' + 
           '                <div class="search"><a href="https://map.kakao.com/link/map/' + calPosition.y + ',' + calPosition.x + '" target="_blank" class="btn btn-primary btn-rounded">길찾기</a></div>' + 
           '            </div>' + 
           '        </div>' + 
           '    </div>' +    
           '</div>';
           
	// 마커 위에 커스텀오버레이를 표시합니다
	// 마커를 중심으로 커스텀 오버레이를 표시하기위해 CSS를 이용해 위치를 설정했습니다
	var overlay = new kakao.maps.CustomOverlay({
		content: content,
		map: map,
		position: marker.getPosition()
	});
      
	// 마커를 클릭했을 때 커스텀 오버레이를 표시합니다
	kakao.maps.event.addListener(marker, 'click', function() {
		overlay.setMap(map);
	});

	// 커스텀 오버레이를 닫기 위해 호출되는 함수입니다
	$(".close").on("click", function() {
		overlay.setMap(null);
	});
	
	// 예약취소 버튼 클릭시
	$("#delete-btn").on("click", function() {
		// 현재 일시
		var today = new Date();
		// 예약 일시
		var rsDate = "${ reservation.rsDate } ${ reservation.rsStart}:00";
		var reservDate = new Date(rsDate);
		// 예약 일시보다 하루 전
		var checkDate = new Date(reservDate.setDate(reservDate.getDate() - 1));
		
		var result;
		if(today >= reservDate) {
			// 예약일정보다 하루 전이거나 그 이후이면
			alert("예약 하루 전에는 예약을 취소할 수 없습니다!");
		} else {
			// 예약일정보다 하루 전보다 더 전이면
			result = confirm("예약 취소하시겠습니까?");
			if(result) {
				message = prompt("취소 사유를 입력해주세요.", "");
				location.href="/study/reservation/delete?rsNo=${ reservation.rsNo }&rsAlert=" + message;
			}
		}
	})
});
</script>
</body>
</html>