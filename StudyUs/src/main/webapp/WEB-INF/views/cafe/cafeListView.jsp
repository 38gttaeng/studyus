<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스터디카페</title>
<style>
.customoverlay {position:relative;bottom:85px;border-radius:6px;border: 1px solid #ccc;border-bottom:2px solid #ddd;float:left;}
.customoverlay:nth-of-type(n) {border:0; box-shadow:0px 1px 2px #888;}
.customoverlay a {display:block;text-decoration:none;color:#000;text-align:center;border-radius:6px;font-size:14px;font-weight:bold;overflow:hidden;background: #d95050;background: #6C20F0 url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/arrow_white.png) no-repeat right 14px center;}
.customoverlay .title {display:block;text-align:center;background:#fff;margin-right:35px;padding:10px 15px;font-size:14px;font-weight:bold;}
.customoverlay:after {content:'';position:absolute;margin-left:-12px;left:50%;bottom:-12px;width:22px;height:12px;background:url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png')}
</style>
</head>
<body>
	<jsp:include page="../common/menubar.jsp"></jsp:include>
	<br>
	<br>
	<br>
	<br>
	<!-- 지도를 표시할 div 입니다 -->
	<div id="map" style="width: 100%; height: 600px;"></div>

	<script type="text/javascript"
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=063f4122c75f35436f584eefe1993776"></script>
	<script>
		var mapContainer = document.getElementById('map'), // 지도를 표시할 div  
		mapOption = {
			center : new kakao.maps.LatLng(37.5334176562724, 126.97992612465082), // 지도의 중심좌표
			level : 7
		// 지도의 확대 레벨
		};

		var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

		// 마커를 표시할 위치와 title 객체 배열입니다 
		var positions = [
				{
					content :  '<div class="customoverlay">' +
				    '  <a href="#">' +
				    '    <span class="title">종로점</span>' +
				    '  </a>' +
				    '</div>',
					latlng : new kakao.maps.LatLng(37.56794439504571,
							126.98299286239576)
				},
				{
					content : '<div class="customoverlay">' +
				    '  <a href="https://map.kakao.com/link/map/11394059" target="_blank">' +
				    '    <span class="title">강남점</span>' +
				    '  </a>' +
				    '</div>',
					latlng : new kakao.maps.LatLng(37.499012225823975,
							127.03284079298378)
				},
				{
					content : '<div class="customoverlay">' +
				    '  <a href="https://map.kakao.com/link/map/11394059" target="_blank">' +
				    '    <span class="title">당산점</span>' +
				    '  </a>' +
				    '</div>',
					latlng : new kakao.maps.LatLng(37.53386983751356,
							126.89681848374141)
				},
				{
					content : '신촌점',
					latlng : new kakao.maps.LatLng(37.555031574626256,
							126.93385054302597)
				} ];

		// 마커 이미지의 이미지 주소입니다
		var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";

		for (var i = 0; i < positions.length; i++) {

			// 마커 이미지의 이미지 크기 입니다
			var imageSize = new kakao.maps.Size(30, 41);

			// 마커 이미지를 생성합니다    
			var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

				    // 마커를 생성합니다
			    var marker = new kakao.maps.Marker({
			        map: map, // 마커를 표시할 지도
			        position: positions[i].latlng, // 마커의 위치
			        image : markerImage
			    });
				    
			    
			} 
			
	</script>

	<jsp:include page="../common/studyFooter.jsp"></jsp:include>
</body>
</html>