<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스터디카페 목록</title>
<style>
.customoverlay {
	position: relative;
	bottom: 85px;
	border-radius: 6px;
	border: 1px solid #ccc;
	border-bottom: 2px solid #ddd;
	float: left;
}

.customoverlay:nth-of-type(n) {
	border: 0;
	box-shadow: 0px 1px 2px #888;
}

.customoverlay a {
	display: block;
	text-decoration: none;
	color: #000;
	text-align: center;
	border-radius: 6px;
	font-size: 14px;
	font-weight: bold;
	overflow: hidden;
	background: #d95050;
	background: #6C20F0
		url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/arrow_white.png)
		no-repeat right 14px center;
}

.customoverlay .title {
	display: block;
	text-align: center;
	background: #fff;
	margin-right: 35px;
	padding: 10px 15px;
	font-size: 14px;
	font-weight: bold;
}

.customoverlay:after {
	content: '';
	position: absolute;
	margin-left: -12px;
	left: 50%;
	bottom: -12px;
	width: 22px;
	height: 12px;
	background:
		url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png')
}
</style>
</head>
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

		var grs80 = proj4.Proj(proj4.defs["EPSG:5179"]) 
		var wgs84 = proj4.Proj(proj4.defs["EPSG:4326"]); //경위도 
		
		var p = proj4.Point(Number(firstVal),Number(secondVal));//한국지역정보개발원 좌표 
		//firstVal = "952396.5783573401", secondVal = "1952732.0129473102
		//  945959.0381341814 , 1953851.7348996028
		p = proj4.transform( grs80, wgs84, p); 
		console.log(p.x + " " + p.y); 
		return p;
	}
	

	// 지도 api
	var mapContainer = document.getElementById('map'),  // 지도를 표시할 div
		mapOption = {
			center : new kakao.maps.LatLng(37.5507874785596, 126.98537891244527), // 지도의 중심좌표
			level : 7
		};
		 // 지도 생성
		var map = new kakao.maps.Map(mapContainer, mapOption); 
		
		var positions = [];
		<c:forEach items='${caList}' var='cafe'>
			var cafe = new Object();
			cafe.caName = "${cafe.caName }";
			cafe.lat = "${cafe.caLat }";
			cafe.lng = "${cafe.caLng }";
			positions.push(cafe);
		</c:forEach>
		
		// 지도 마커
		 var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";
			
			for (var i = 0; i < positions.length; i++) {

				var imageSize = new kakao.maps.Size(30, 41);
				// 마커 이미지를 생성
				var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);
				// 마커를 표시할 위치
				var calPosition = getLatLngProj4(positions[i].lat, positions[i].lng);
				console.log(calPosition);
	 			var latlng = new kakao.maps.LatLng(calPosition.y, calPosition.x);
	 			
				// 마커를 생성
				var marker = new kakao.maps.Marker({
					map : map, 
					position : latlng,
					image : markerImage
				});
				// 오버레이 생성
				var overlay = new kakao.maps.CustomOverlay({
					content : positions[i].content, 
					map : map,
					position : marker.getPosition()
				});
			}
			
		// 마커를 표시할 위치와 title 객체 배열
/* 		var positions = [
				{
					content : '<div class="customoverlay">' + '  <a href="#">'
							+ '    <span class="title">종로점</span>' + '  </a>'
							+ '</div>',
					latlng : new kakao.maps.LatLng(positions[i].lat, positions[i].lng)
				{
					content : '<div class="customoverlay">' + '  <a href="#">'
							+ '    <span class="title">종로점</span>' + '  </a>'
							+ '</div>',
					latlng : new kakao.maps.LatLng(37.56794439504571,
							126.98299286239576)
				},
				{
					content : '<div class="customoverlay">'
							+ '  <a href="#" target="_blank">'
							+ '    <span class="title">강남점</span>' + '  </a>'
							+ '</div>',
					latlng : new kakao.maps.LatLng(37.499012225823975,
							127.03284079298378)
				},
				{
					content : '<div class="customoverlay">'
							+ '  <a href="https://map.kakao.com/link/map/11394059" target="_blank">'
							+ '    <span class="title">성수점</span>' + '  </a>'
							+ '</div>',
					latlng : new kakao.maps.LatLng(37.544338431198774,
							127.06302820262869)
				},
				{
					content : '<div class="customoverlay">'
							+ '  <a href="#" target="_blank">'
							+ '    <span class="title">당산점</span>' + '  </a>'
							+ '</div>',
					latlng : new kakao.maps.LatLng(37.53386983751356,
							126.89681848374141)
				},
				{
					content : '<div class="customoverlay">'
							+ '  <a href="#" target="_blank">'
							+ '    <span class="title">신촌점</span>' + '  </a>'
							+ '</div>',
					latlng : new kakao.maps.LatLng(37.555031574626256,
							126.93385054302597)
				} ];

 		var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";
		
		for (var i = 0; i < positions.length; i++) {

			var imageSize = new kakao.maps.Size(30, 41);
			// 마커 이미지를 생성
			var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);
			
// 			var latlng = new kakao.maps.LatLng(positions[i].lat, positions[i].lng);
			// 마커를 생성
			var marker = new kakao.maps.Marker({
				map : map, 
				position : latlng,
				image : markerImage
			});
			// 오버레이 생성
			var overlay = new kakao.maps.CustomOverlay({
				content : positions[i].content, 
				map : map,
				position : marker.getPosition()
			});

		} */
	</script>

	<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>