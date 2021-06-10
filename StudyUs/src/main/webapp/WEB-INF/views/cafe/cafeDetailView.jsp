<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스터디카페 상세</title>
</head>
 <!-- 추가 css -->
    <link href="/resources/css/studyus/reply.css" rel="stylesheet">
<body>
	<jsp:include page="../common/menubar.jsp"></jsp:include>
	<script> 
		$(".nav-item:nth-child(4)").addClass("active");
	</script>
	<br>
	<br>
	<br>
	<br>
	<img alt="카페상세" src="/resources/images/cafe_banner.png">
	<section class="ftco-section services-section bg-light">
		<div class="container">
			<div class="row justify-content-center mb-5 pb-3">
				<div class="col-md-7 text-center heading-section ftco-animate">
					<div class="overlay"></div>
					<h2 class="mb-4">스터디어스 카페</h2>
					<p>언택트를 넘어서 온택트로 만나는 스터디어스만의 특별한 공간</p>
				</div>
			</div>
		<div class="row">
			<div class="col-md-12 d-flex align-self-stretch ftco-animate">
				<div class="media block-6 services d-flex align-items-center">
					<div class="media-body pl-4">
						<h3 class="heading">${cafe.caName }</h3>
						<p>${cafe.caAddr }</p>
					</div>
					<p>
						<c:url var="cModify" value="modifyView">
							<c:param name="caNo" value="${cafe.caNo }"></c:param>
						</c:url>
						<c:url var="roomModify" value="room-modifyView">
							<c:param name="caNo" value="${cafe.caNo }"></c:param>
						</c:url>
						<c:url var="cReserv" value="reservation">
							<c:param name="caNo" value="${cafe.caNo }"></c:param>
						</c:url>
						<!-- 관리자만 카페수정, 룸수정 가능하도록 -->
						<c:if test="${loginUser.mbNo == 1}">
							<div class="btn-group">
								<a href="${cModify }" class="btn btn-primary px-4 py-3"
									style="background-color: white; color: purple">정보수정</a>
								<a href="${roomModify }" class="btn btn-primary px-4 py-3"
								style="background-color: white; color: purple">룸설정</a>
							</div>
						</c:if>
						<!-- 팀장인 경우 -->
						<c:if test="${ (loginUser.mbNo != 1) && (!empty sList) }">
							<a href="${cReserv }" class="btn btn-primary px-4 py-3"
								style="background-color: white; color: purple">예약하기</a>
						</c:if>
					</p>
				</div>
			</div>
		</div>

		<div class="row justify-content-center mb-5 pb-3">
			<div class="col-md-7 text-center heading-section ftco-animate">
				<h3 class="mt-4">상세정보</h3>
			</div>
		</div>
		<div class="row d-flex">
			<div class="col-md-12 ftco-animate">
				<div class="block-7">
					<div class="row block-9">
						<div class="col-md-12 mb-4">
							<h2 class="h4 ml-3">매장 안내</h2>
						</div>
						<div class="col-md-6 ">
							<img width="100%" alt="cafe_img"
								src="/resources/cuploadFiles/${cafe.caFiName}" />
						</div>
						<div class="col-md-6 pr-md-5">
							<form action="#">
								<div class="form-group">
									<ul class="pricing-text mb-4">
										<li><strong>소개</strong>
											<h3 class="heading-2 mb-3">${cafe.caInfo }</h3></li>
									</ul>
								</div>
								<div class="form-group">
									<ul class="pricing-text mb-4">
										<li><strong>전화번호</strong>
											<h3 class="heading-2 mb-3">${cafe.caTel }</h3></li>
									</ul>
								</div>
								<div class="form-group">
									<ul class="pricing-text mb-4">
										<li><strong>영업시간</strong>
											<h3 class="heading-2 mb-3">${cafe.caTime }</h3></li>
									</ul>
								</div>
								<div class="form-group">
									<ul class="pricing-text mb-4">
										<li><strong>오시는 길</strong>
											<h3 class="heading-2 mb-3">${cafe.caRoute }</h3></li>
									</ul>
								</div>
							</form>
						</div>

				<!-- 댓글 목록 -->
		<section class="ftco-section" id="rtb">
			<div class="col-md-12 mb-4">
				<h2 class="h4 ml-3 mt-5">리뷰</h2>
			</div>
			<div class="card-body">
			<!-- cafeReview.js 파일과 연동하기 위해서 -->
			<input type="hidden"  id="cafeNumber" value="${ cafe.caNo }"/>
			<input type="hidden"  id="memberNo" value="${ loginUser.mbNo }"/>
			
			<!-- 리뷰 등록 -->
			<div class="row block-9">
				<div class="col-md-10 pr-md-2">
					<form action="#">
						<div class="form-group">
						<c:if test="${loginUser.mbNo ne null }">
							<textarea name="" id="rvContents" cols="25" rows="7"
								class="form-control" placeholder="스터디어스가 따뜻해지는 리뷰 부탁드립니다."></textarea>
						</c:if>
						<c:if test="${loginUser.mbNo eq null }">
							<textarea name="" id="rvContents" cols="25" rows="7"
								class="form-control" placeholder="로그인 후 이용해주세요" readonly></textarea>
						</c:if>
						</div>
						<div class="star-container" id="rvRating">
						  <span class="star" id="rating1" title="1점">★</span>
						  <span class="star" id="rating2" title="2점">★</span>
						  <span class="star" id="rating3" title="3점">★</span>
						  <span class="star" id="rating4" title="4점">★</span>
						  <span class="star" id="rating5" title="5점">★</span>
						</div>
					</form>
				</div>
				<div class="col-md-2 pr-2 pt-5">
					<div class="form-group">
						<c:if test="${loginUser.mbNo ne null }">
							<input type="submit" id="rvSubmit" value="등록"
							class="btn btn-primary py-3 px-5">
						</c:if>
						<c:if test="${loginUser.mbNo eq null }">
							<a href="/member/loginView" onclick="alert('로그인 후 이용해주세요!');" 
								class="btn btn-primary py-3 px-5">등록</a>
						</c:if>
					</div>
				</div>
			</div>
			<!-- 리뷰 리스트 -->
			<div id="rList"></div>
				<div id="float-btn">
		            <!-- top으로 가는 버튼 -->
		             <button id="top-btn-pu" onclick="location.href='#'"><b>⇪</b></button>
             	</div>
			</div>
			</section>
							
	<script>
	var data = null;
	var pi = null;
	var page = 1;
	
	// 리뷰 등록 (별점+내용)
	$(function() {
		
		// 리뷰 별점 기능
		var starEls = document.querySelectorAll('#rvRating span.star');
	    var rate = 0;

	    loop(starEls, function (el, index) {
	        el.addEventListener('click', function () {
	            rating(index + 1);
	        });
	    });
	    function loop(list, func) {
	        Array.prototype.forEach.call(list, func);
	    }

	    function rating(score) {
	        loop(starEls, function (el, index) {
	            if (index < score) {
	                el.classList.add('on');
	            } else {
	                el.classList.remove('on');
	            }
	        });

	        rate = score;
	    }
	    
	 	// 리뷰 내용 등록
		getReviewList(); 
		$("#rvSubmit").on("click", function() {
//				if(loginMbId == null){
//					alert("로그인을 해주세요");
//				}else{
//				alert("test");
			var caNo = '${cafe.caNo}';
			var rvContents = $("#rvContents").val();
			if(rvContents == ""){
				alert("리뷰를 입력해주세요!");
			} else if (rate == 0) {
				alert("별점을 선택해주세요.");
				return false;
			} else{
			var rvRating = rate;
//								console.log(caNo);
//								console.log(rvContents);
			
			$.ajax({
				url : "/cafe/review/register",
				type : "post",
				data : {
					"caNo" : caNo,
					"rvContents" : rvContents,
					"rvRating" : rate
				},
				success : function(data) {
					if (data == "success") {
// 						alert("따뜻한 리뷰가 등록되었습니다!");
						getReviewList(page);
						$("#rvContents").val("");
					} else {
						alert("리뷰 등록 실패");
					}
				},
				error : function() {
						alert("로그인을 해주세요!");
						window.location.href = "/member/loginView";
				}
			});
			
			}
		});
	});
		
	// 리뷰 리스트
	function getReviewList(page, loginMbNo) {
		var caNo = $("#cafeNumber").val();
		var memberNo = $("#memberNo").val();
		$.ajax({
			url : "/cafe/review/list",
			type : "get",
			data : {
				"caNo" : caNo
			},
			dataType : "json",
			success : function(data) {
				var $rList = $("#rList");
				$rList.html(""); // 비워주기
				var $div;
				var $writer;
				var $rvContents;
				var $rvRating;
				var $rvDate;
				var $btnArea;
				var $btn;
				var $showStar;
				var $btnTool = "";

			if (data.length > 0) {
				for (var i in data) {
					$div = $("<div class='reply-box'>");
					$writer = $("<div>")
					.append("<img src='/resources/images/" + data[i].member.mbPhoto + ".png' class='rounded-circle'>&nbsp")
					.append("<span class='nickName'>" + data[i].member.mbNickname + "</span>&nbsp");
					if(memberNo == data[i].mbNo) {
						$writer.append("&nbsp<div class='writerTag'>작성자</div>");
					}
					$writer.append("<span class='insertDate'>" + data[i].rvDate + "</span>");
					
					$rvContents = $("<div class='contents-box'>").append(data[i].rvContents);
// 					console.log("rvRating: " + data[i].rvRating)
					$btnArea = $("<div>").append("&nbsp;");;
					
					$btnTool = "";
					// 수정+삭제 버튼
					if(memberNo == data[i].mbNo) {
						$btnTool =$("<div class='btn-group'>");
						$btnTool
						.append("<button class='btn btn-sm btn-outline-secondary btn-rounded' onclick='modifyReview(this," + data[i].rvNo + "," + data[i].caNo + ");'>수정</button>")
						.append("<button class='btn btn-sm btn-outline-secondary btn-rounded' onclick='removeReview(" + data[i].rvNo + ");'>삭제</button>");
					$btnArea.append($btnTool);
					}
	 				$div.attr("id", "review" + data[i].rvNo); // 리뷰 수정 버튼 기존 내용 로드
	 				
	 				// 별점 보기
					$btn = $("<div class='star-rating'>");
					$showStar = $("<div class='star-container' id='rvRating'>")
								.append("<span class='rvRating'></span>");
					for(var j = 1; j <= data[i].rvRating; j++ ){
						$showStar.append("★");
					}
					for(var j = 1; j <= 5 - data[i].rvRating; j++ ){
						$showStar.append("☆");
					}
					$btn.append($showStar);
					$writer.append($btn);

					var stars = "";
					for (var i = 0; i < rvRating; i++) {
						stars += "★";
					}

					$div.append($writer);
					$div.append($rvContents);
					$div.append($rvDate);
					$div.append($btnArea);

					$rList.append($div); 
				}
			} else {
				console.log("리뷰 없음!");
			}
		},
			error : function() {
				console.log("전송 실패");
			}
		
		});
	}
	
		// 리뷰 수정
 		function modifyReview(obj, rvNo, caNo) {
			$review = $("#review" + rvNo).children(".contents-box");
			var reviewContent = $review.html();
			$divModify = $(obj).parent().parent().prev();
			$divModify.html("");
			
			$btnArea = $divModify.next();
			$btnArea.html("");
			$btnArea.addClass("btn-group");
			$btnArea.addClass("reBtn");

			$text = $("<textarea id='editor2' style='width:100%'>"+reviewContent+"</textarea>");
			$text.val(reviewContent); // 수정할 댓글 입력
			$btnArea
			.append("<button id='modify-btn' class='btn btn-sm btn-secondary'onclick='modify-btn'>수정</button>")
			.append("<button class='btn btn-sm btn-secondary' onclick='getReviewList(page);'>취소</button>");
			
			$divModify.append($text);
			
			$(document).on("click","#modify-btn", function() {
	 			var rvContents = $("#editor2").val();
				$.ajax({
					url : "/cafe/review/update",
					type : "post",
					data : { 
						"rvNo" : rvNo,
						"caNo" : caNo,
						"rvContents" : rvContents 
						},
					success : function(data) {
						if(data == "success") {
							getReviewList(page);
						} else {
							alert("리뷰 수정 실패!");
						}
					},
					error : function() {
						console.log("전송 실패..");
					}
				});
			});
			
		}
 		
 		
 		// 리뷰삭제
		function removeReview(rvNo) {
			var result = confirm("리뷰를 삭제하시겠습니까?");
			
			if(result){
			$.ajax({
				url : "/cafe/review/delete",
				type : "get",
				data : {
					"rvNo" : rvNo
				},
				success : function(data) {
					if (data == "success") {
						getReviewList();
					} else {
						alert("리뷰 삭제 실패!");
					}
				},
				error : function() {

				}
			});
		}
	}
	</script>


						
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>

	<jsp:include page="../common/footer.jsp"></jsp:include>
<!-- 	    <script src="/resources/js/cafeReview.js"></script> -->
</body>
</html>