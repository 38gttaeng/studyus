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

	<script type="text/javascript"
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=063f4122c75f35436f584eefe1993776"></script>
	<script>
		var mapContainer = document.getElementById('map'), 
		mapOption = {
			center : new kakao.maps.LatLng(37.5507874785596, 126.98537891244527), 
			level : 7
		};

		var map = new kakao.maps.Map(mapContainer, mapOption); 

		var positions = [
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

			var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

			var marker = new kakao.maps.Marker({
				map : map,
				position : positions[i].latlng,
				image : markerImage
			});

			var overlay = new kakao.maps.CustomOverlay({
				content : positions[i].content, 
				map : map,
				position : marker.getPosition()
			});

		}
	</script>

	<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>