<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
		<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html> 
<html>
<head>
<meta charset="UTF-8">
<title>StudyUs : 스터디 카페룸</title>
    <!-- 추가 css -->
    <link href="/resources/css/studyus/roomUpdate.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="../common/menubar.jsp"></jsp:include>
	<script>
		$(".nav-item:nth-child(4)").addClass("active");
	</script>
	<br>
	<br>
	<br>
	<br>
	<section class="ftco-section services-section bg-light">
		<div class="container">
			<div class="row justify-content-center mb-5 pb-3">
				<div class="col-md-7 text-center heading-section">
					<h2 class="mb-4">스터디카페 룸설정</h2>
					<p>카페 룸을 추가, 수정, 삭제하고 정보를 입력할 수 있습니다.</p>
				</div>
			</div>
			
			<input type="hidden" name="caNo" value="${cafe.caNo }"> 
			<div class="row">
				<div class="col-md-12 d-flex align-self-stretch ftco-animate">
					<div class="media block-6 services d-flex align-items-center">
						<div class="media-body pl-4">
							<h3 class="heading">${cafe.caName }</h3>
							<p>${cafe.caAddr }</p>
						</div>
					</div>
				</div>
			</div>
			
			<div class="row justify-content-center mb-5 pb-3">
				<div class="col-md-7 text-center heading-section ftco-animate">
					<h3 class="mt-4">스터디룸 정보</h3>
				</div>
			</div>

			<div class="row d-flex">
				<div class="col-md-12">
				
					<div class="block-7 row">
						<h2 class="h4 col-md-12 ml-3">스터디카페 지도</h2>
						<div class="col-md-10">
							<p class="ml-3">드래그 앤 드랍으로 스터디룸 위치를 저장하세요.</p>
						</div>
						<div class="col-md-2">
							<button type="button" class="btn btn-secondary float-right mr-3 px-3 py-2" data-toggle="modal" data-target="#addRoom">추가하기</button>
						</div>
						
						<div id="room-wrapper">
						</div>
					</div>
					<br>
					
					<div id="detail-box" class="block-7 row d-none">
						
						<div class="col-md-12 mb-4">
							<h2 class="h4 ml-3">스터디룸 상세정보</h2>
						</div>
						<div class="col-md-8">
							<h5 id="roomName" class="ml-3"></h5>
						</div>
						<div class="col-md-4 mb-3">
							<div class="btn-group float-right">
								<button type="button" class="btn btn-secondary px-3 py-2" data-toggle="modal" data-target="#modifyRoom">수정</button>
								<button id="delete-btn" type="button" class="btn btn-secondary px-3 py-2">삭제</button>
							</div>
						</div>
						<br>
						<div class="col-md-5 ml-3" id="img-box">
							<img id="roomFile" width="100%" alt="cafe_img"/>
						</div>
						<div class="col-md-6 pr-md-5 ml-3 mb-2">
							<div class="form-group">
								<ul class="pricing-text mb-4">
									<li><strong>소개</strong>
										<h3 id="roomInfo" class="heading-2 mb-3"></h3></li>
								</ul>
							</div>
							<div class="form-group">
								<ul class="pricing-text mb-4">
									<li><strong>최대인원</strong>
										<h3 id="roomMax" class="heading-2 mb-3"></h3></li>
								</ul>
							</div>
							<div class="form-group">
								<ul class="pricing-text mb-4">
									<li><strong>가격(시간당)</strong>
										<h3 id="roomPrice" class="heading-2 mb-3"></h3></li>
								</ul>
							</div>
						</div>
					</div>
					
				</div>
			</div>
			<div align="center">
				<p>
					<c:url var="cDetail" value="/cafe/detail">
						<c:param name="caNo" value="${cafe.caNo }"></c:param>
					</c:url>
					<a href="${cDetail}" class="btn btn-primary px-4 py-3 mt-5 mr-3"
							style="background-color: white; color: #6927ff">취소하기</a>
					<button id="save" type="button" class="btn btn-primary px-4 py-3 mt-5">저장하기</button>
				</p>
			</div>
		</div>
	</section>
	
	<!-- 등록 modal창 -->
	<form class="addForm">
	<div class="modal fade" id="addRoom" tabindex="-1" aria-labelledby="addRoomLabel" aria-hidden="true" data-backdrop="static">
		<div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
	    	<div class="modal-content">
	    		<div class="modal-header bg-primary">
	        		<h5 class="modal-title text-white" id="addRoomLabel">스터디룸 추가</h5>
	        		<button type="button" class="close text-white" data-dismiss="modal" aria-label="Close">
	          			<span aria-hidden="true">&times;</span>
	 				</button>
	 			</div>
		      	<div class="modal-body">
		        	<div class="form-group">
		        		<label for="crName">룸 이름</label>
		        		<input class="form-control crName" type="text" name="crName" id="crName" required placeholder="룸 이름">
		        		<span class="invalid-feedback name-msg">룸 이름을 1자 이상 입력하세요.</span>
		        	</div>
		        	<div class="form-group">
		        		<label for="crFilename">룸 이미지</label>
		        		<input class="form-control crFile" type="file" name="crFilename" id="crFilename">
		        	</div>
		        	<div class="form-group">
		        		<label for="crInfo">소개</label>
		        		<textarea class="form-control" name="crInfo" id="crInfo" rows="5" placeholder="룸 소개글"></textarea>
		        	</div>
		        	<div class="form-group">
		        		<label for="crMax">최대 인원</label>
		        		<select name="crMax" id="crMax" class="custom-select">
                            <option selected value="2">2명</option>
                            <option value="4">4명</option>
                            <option value="6">6명</option>
                            <option value="10">10명</option>
                        </select>
		        	</div>
		        	<div class="form-group">
		        		<label for="crPrice" style="display : block">가격</label>
		        		<input class="form-control crPrice" type="number" step="1000" name="crPrice" id="crPrice" required placeholder="가격" style="width : 94%; display : inline-block">
		        		<span>&nbsp;원</span>
		        		<span class="invalid-feedback price-msg">가격을 0원 이상 입력하세요.</span>
		        	</div>
		      	</div>
		      	<div class="modal-footer">
		        	<button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
		        	<button id="addRoom-btn" type="button" class="btn btn-primary" data-dismiss="modal">추가</button>
		   		</div>
	    	</div>
		</div>
	</div>
	</form>
	
	<!-- 수정 modal창 -->
	<form class="modifyForm">
	<input type="hidden" name="crNo">
	<div class="modal fade" id="modifyRoom" tabindex="-1" aria-labelledby="modifyRoomLabel" aria-hidden="true" data-backdrop="static">
		<div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
	    	<div class="modal-content">
	    		<div class="modal-header bg-primary">
	        		<h5 class="modal-title text-white" id="modifyRoomLabel">스터디룸 수정</h5>
	        		<button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          			<span aria-hidden="true" class="text-white">&times;</span>
	 				</button>
	 			</div>
		      	<div class="modal-body">
		        	<div class="form-group">
		        		<label for="re-crName">룸 이름</label>
		        		<input class="form-control crName" type="text" name="crName" id="re-crName" required placeholder="룸 이름">
		        		<span class="invalid-feedback name-msg">룸 이름을 1자 이상 입력하세요.</span>
		        	</div>
		        	<div class="form-group">
		        		<label for="re-crFilename">룸 이미지</label>
		        		<input class="form-control crFile" type="file" name="crFilename" id="re-crFilename">
		        		<input type="hidden" id="origin-file">
		        		<span id="origin-filename"></span>
		        	</div>
		        	<div class="form-group">
		        		<label for="re-crInfo">소개</label>
		        		<textarea class="form-control" name="crInfo" id="re-crInfo" rows="5" placeholder="룸 소개글"></textarea>
		        	</div>
		        	<div class="form-group">
		        		<label for="re-crMax">최대 인원</label>
		        		<select name="crMax" id="re-crMax" class="custom-select">
                            <option value="2">2명</option>
                            <option value="4">4명</option>
                            <option value="6">6명</option>
                            <option value="10">10명</option>
                        </select>
		        	</div>
		        	<div class="form-group">
		        		<label for="re-crPrice" style="display : block">가격</label>
		        		<input class="form-control crPrice" type="number" step="1000" name="crPrice" id="re-crPrice" required placeholder="가격" style="width : 94%; display : inline-block">
		        		<span>&nbsp;원</span>
		        		<span class="invalid-feedback price-msg">가격을 0원 이상 입력하세요.</span>
		        	</div>
		      	</div>
		      	<div class="modal-footer">
		        	<button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
		        	<button id="modifyRoom-btn" type="button" class="btn btn-primary" data-dismiss="modal">수정</button>
		   		</div>
	    	</div>
		</div>
	</div>
	</form>

	<jsp:include page="../common/footer.jsp"></jsp:include>
	
	<!-- draggable -->
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <!-- textFit -->
    <script src="/resources/js/textFit.min.js"></script>
	<!-- 해당 페이지 JS 파일 -->
    <script src="/resources/js/roomUpdate.js"></script>
    
</body>
</html>