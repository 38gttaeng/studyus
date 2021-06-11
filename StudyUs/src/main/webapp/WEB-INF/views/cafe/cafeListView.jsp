<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스터디카페 목록</title>
</head>
<!-- 추가 css -->
<link href="/resources/css/studyus/cafe.css" rel="stylesheet"> 
<body>
	<jsp:include page="../common/menubar.jsp"></jsp:include>
		    <script>$(".nav-item:nth-child(4)").addClass("active");</script>
	<br>
	<br>
	<br>
	<br>
	<!-- 지도를 표시할 div 입니다 -->
	<div id="map" style="width: 100%; height: 630px;"></div>
	<div align = "center">
		<c:url var="cRegister" value="registerForm">
		</c:url>
		<c:if test="${loginUser.mbNo == 1}">
			<a href="${cRegister }" class="btn btn-primary px-4 py-3" style= "width:100%; border-radius: 0px;" >
				등록하기</a>
		</c:if>
	</div> 

	<script type="text/javascript"
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=063f4122c75f35436f584eefe1993776"></script>
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
	var mapContainer = document.getElementById('map'),  // 지도를 표시할 div
		mapOption = {
			center : new kakao.maps.LatLng(37.54303872565461, 126.98412440978181), // 지도의 중심좌표
			level : 7 // 지도 확대 레벨
		};
		 // 지도 생성
		var map = new kakao.maps.Map(mapContainer, mapOption); 
		
		var positions = [];
		<c:forEach items='${caList}' var='cafe'>
			var cafe = new Object();
			cafe.caNo = "${cafe.caNo}";
			cafe.caName = "${cafe.caName }";
			cafe.lat = "${cafe.caLat }";
			cafe.lng = "${cafe.caLng }";
			positions.push(cafe);
		</c:forEach>
		
		// 지도 마커
		 var imageSrc = "/resources/images/marker.png";
			
			for (var i = 0; i < positions.length; i++) {
				// 마커 이미지 사이즈
				var imageSize = new kakao.maps.Size(30, 60);
				// 마커 이미지를 생성
				var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);
				// 마커를 표시할 위치
				var calPosition = getLatLngProj4(positions[i].lat, positions[i].lng);
	 			var latlng = new kakao.maps.LatLng(calPosition.y, calPosition.x);
	 			
				// 마커를 생성
				var marker = new kakao.maps.Marker({
					map : map, 
					position : latlng,
					image : markerImage
				});
				
				(function(marker, cafe) {
						// 마커 오버레이 생성
					    var overlay = new kakao.maps.CustomOverlay({
					      // 오버레이에 띄울 내용
					      content:  '<div class="customoverlay">' +
						    '  <a href="/cafe/detail?caNo='+cafe.caNo+'">' +
						    '    <span class="title">' + cafe.caName + '</span>' +
						    '  </a>' +
						    '</div>',
					      map: map,
					      position: marker.getPosition()
					    });
						// 오버레이 지도 위치
					    overlay.setMap(map);
					})(marker, positions[i])
			}
	</script>

	<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>