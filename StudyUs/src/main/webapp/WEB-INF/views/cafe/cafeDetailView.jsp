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
<style>
.reviewbtn {
	font-size: 15px;
	color: #999;
	line-height: 50%;
	border: none;
}
/* *{margin:0; padding:0;} */

</style>
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
						<c:url var="cReserv" value="reservation">
							<c:param name="caNo" value="${cafe.caNo }"></c:param>
						</c:url>
						<c:if test="${loginUser.mbNo == 1}">
							<a href="${cModify }" class="btn btn-primary px-4 py-3"
								style="background-color: white; color: purple">수정하기</a>
						</c:if>
						<c:if test="${loginUser.mbNo != 1}">
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
					<%-- <tr>
						<td class="pl-3" width="200"><img alt="프로필" src="">${member.mbNo }</td>
						<!-- 											<td width="100">아이디</td> -->
						<td width="750"></td>
						<td>${review.rvDate }</td>
					</tr>
					<tr>
						<td class="pl-3" colspan="3" align="left">${review.rvContents }</td>
						<td><c:url var="rvModify" value="rvModifyView">
								<c:param name="rvNo" value="${review.rvNo }"></c:param>
							</c:url> <c:url var="rvDelete" value="rvDelete">
								<c:param name="rvNo" value="${review.rvNo }"></c:param>
							</c:url> <c:if test="${loginUser.mbNo != 1}">
								<a href="${rvModify }" type="button" class="reviewbtn">수정</a>
							</c:if> <a href="${rvDelete }" type="button" class="reviewbtn">삭제</a>
						</td>
					</tr> --%>
			<!-- 리뷰 등록 -->
			<div class="row block-9">
				<div class="col-md-10 pr-md-2">
					<form action="#">
					
						<div class="form-group">
							<textarea name="" id="rvContents" cols="25" rows="7"
								class="form-control" placeholder="스터디어스가 따뜻해지는 리뷰 부탁드립니다."></textarea>
						</div>
						<div class="star-box" id="rvRating">
						  <span class="star star_left"></span>
						  <span class="star star_right"></span>
						
						  <span class="star star_left"></span>
						  <span class="star star_right"></span>
						
						  <span class="star star_left"></span>
						  <span class="star star_right"></span>
						
						 <span class="star star_left"></span>
						 <span class="star star_right"></span>
						
						 <span class="star star_left"></span>
						 <span class="star star_right"></span>
						</div>
					</form>
				</div>
				<div class="col-md-2 pr-2 pt-5">
					<div class="form-group">
						<input type="submit" id="rvSubmit" value="등록"
							class="btn btn-primary py-3 px-5">
					</div>
				</div>
			</div>
			<!-- 리뷰 리스트 -->
			<div id="rList"></div>
			<!-- 페이징 -->
            <nav id='rPage'></nav>
			<%-- <div>
				<!-- 이전 -->
				<c:url var="before" value="rList">
					<c:param name="page" value="${pi.currentPage - 1 }"></c:param>
				</c:url>
				<c:if test="${pi.currentPage <= 1 }">
					[이전]&nbsp;
				</c:if>
				<c:if test="${pi.currentPage > 1 }">
					<a href="${before }">[이전]</a>&nbsp;
				</c:if>
				<!-- 페이지 -->
				<c:forEach var="p" begin="${pi.startPage }" end="${pi.endPage }">
					<c:url var="pagination" value="rList">
						<c:param name="page" value="${p }"></c:param>
					</c:url>
					<c:if test="${p eq pi.currentPage }">
						<font color="red" size="4">[${p }]</font>
					</c:if>
					<c:if test="${p ne pi.currentPage }">
						<a href="${pagination }">${p }</a>&nbsp;
					</c:if>
				</c:forEach>
				<!-- 다음 -->
				<c:url var="after" value="rList">
					<c:param name="page" value="${pi.currentPage + 1 }"></c:param>
				</c:url>
				<c:if test="${pi.currentPage >= pi.maxPage }">
					[다음]&nbsp;
				</c:if>
				<c:if test="${pi.currentPage < pi.maxPage }">
					<a href="${after }">[다음]</a>&nbsp;
				</c:if>
			</div> --%>
		</div>
			</section>
							
	<script>
	var data = null;
	var pi = null;
	var page = 1;
	
	// 리뷰 등록
	$(function() {
		getReviewList(); 
	    $(".star").on('click',function(){
	    	   var idx = $(this).index();
	    	   $(".star").removeClass("on");
	    	     for(var i=0; i<=idx; i++){
	    	        $(".star").eq(i).addClass("on");
	    	   }
	    	 });
		$("#rvSubmit").on("click", function() {
//				if(loginMbId == null){
//					alert("로그인을 해주세요");
//				}else{
//				alert("test");
			var caNo = '${cafe.caNo}';
			var rvContents = $("#rvContents").val();
//								console.log(caNo);
//								console.log(rvContents);
			$.ajax({
				url : "/cafe/review/register",
				type : "post",
				data : {
					"caNo" : caNo,
					"rvContents" : rvContents,
					"rvRating" : rvRating
				},
				success : function(data) {
					if (data == "success") {
						getReviewList(page);
						$("#rvContents").val("");
					} else {
						alert("댓글 등록 실패");
					}
				},
				error : function() {
						alert("전송 실패!");
				}
			});
	});
});
	
	// 리뷰 리스트
	function getReviewList(page) {
		var caNo = $("#cafeNumber").val();
		var mbNo = $("#memberNo").val();
		$.ajax({
			url : "/cafe/review/list",
			type : "get",
			data : {
				"caNo" : caNo
			},
			dataType : "json",
			success : function(data) {
				console.log(data);
				var $rList = $("#rList");
				$rList.html(""); // 비워주기
				var $div;
				var $mbNo;
				var $rvContents;
				var $rvDate;
				var $btnArea;
				var $btnTool = "";

		if (data.length > 0) {
			for ( var i in data) {
				$div = $("<div class='reply-box'>");
				$mbNo = $("<div>")
				.append("<img src='/resources/images/" + data[i].member.mbPhoto + ".png' class='rounded-circle'>&nbsp")
				.append("<span class='nickName'>" + data[i].member.mbNickname + "</span>&nbsp");
				if(mbNo == data[i].mbNo) {
					$mbNo.append("&nbsp<div class='writerTag'>작성자</div>");
				}
				$mbNo.append("<span class='insertDate'>" + data[i].rvDate + "</span>");
				
				$rvContents = $("<div class='contents-box'>").append(data[i].rvContents);
				
				$btnArea = $("<div>")
				.append("<button class='btn btn-sm btn-light'>답글</button>");
				
				// 수정+삭제 버튼
				if(mbNo == data[i].mbNo) {
					$btnTool =$("<div class='btn-group'>");
					$btnTool
					.append("<button class='btn btn-sm btn-outline-secondary btn-rounded' onclick='modifyReview(this," + data[i].rvNo + "," + data[i].caNo + ");'>수정</button>")
					.append("<button class='btn btn-sm btn-outline-secondary btn-rounded' onclick='reviewDelete(" + data[i].rvNo + ");'>삭제</button>");
					$btnArea.append($btnTool);
				}
				
				$div.append($mbNo);
				$div.append($rvContents);
				$div.append($rvDate);
				$div.append($btnArea);
 				$div.attr("id", "review" + data[i].rvNo);
				
				$rList.append($div); 
			}
		} else {
			console.log("댓글 없음!");
		}
	},
		error : function() {
			console.log("전송 실패");
		}
	
	});
}
	
		// 리뷰 리스트 페이징
/* 		function reviewPage(pi){
			var $rPage = $("#rPage");
			console.log(rPage);
			$rPage.html("");
			
			var $ul;
			var $liBefore="";
			var $liCurrent;
			var $liAfter="";
			
			$ul = $("<ul class='pagination pagination-sm justify-content-center'>");

			if(pi.currentPage > 1) {
				$liBefore = $("<li class='page-item' onclick='getReviewList(" + (pi.currentPage - 1) + ")'>");
				$spanTag = $("<span class='page-link' aria-label='Next'>")
				.append("<span>&laquo;</span>");
				$liBefore.append($spanTag);
			}
			$ul.append($liBefore);
			
			for(var p = pi.startPage; p < pi.endPage + 1; p++) {
				// onclick='getReviewList(p)'
				if(p == pi.currentPage) {
					$liCurrent = $("<li class='page-item active'>");
				} else {
					$liCurrent = $("<li class='page-item' onclick='getReviewList(" + p + ")'>");
				}
				
				$liCurrent.append("<span onclick='getReviewList(p)' class='page-link'>" + p + "</span>");
				
				$ul.append($liCurrent);
			}
			
			if(pi.currentPage < pi.maxPage) {
				$liAfter = $("<li class='page-item' onclick='getReviewList(" + (pi.currentPage + 1) + ")'>");
				$spanTag = $("<span class='page-link' aria-label='Next'>")
				.append("<span>&raquo;</span>");
				$liAfter.append($spanTag);
			}
			$ul.append($liAfter);
			
			$rPage.append($ul);
		} 
 */
		
		// 리뷰 수정
		function modifyReview(obj, rvNo, caNo) {
			$review = $("#review" + rvNo).children("div:eq(1)");
			var reviewContent = $review.html();
		
			$divModify = $(obj).parent().parent().prev();
			$divModify.html("");
			
			$btnArea = $divModify.next();
			$btnArea.html("");
			$btnArea.addClass("btn-group");
			$btnArea.addClass("reBtn");
			
			$text = $("<textarea id='editor2' style='width:100%'></textarea>");
			$text.val(reviewContent); // 수정할 댓글 입력
			$btnArea
			.append("<button id='modify-btn' class='btn btn-sm btn-secondary'>수정</button>")
			.append("<button class='btn btn-sm btn-secondary' onclick='getReviewList(page);'>취소</button>");
			
			$divModify.append($text);	
			
			$("#modify-btn").on("click", function() {
					$.ajax({
						url : "/cafe/review/update",
						type : "post",
						data : { 
							"caNo" : caNo,
							"rvNo" : rvNo,
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

		/* // 수정완료 버튼 눌렀을 때 동작하는 함수
		function reviewUpdate(obj, caNo) {
			var modifiedContent = $("#modifyReview").val();
			$.ajax({
				url : "/cafe/review/modify",
				type : "post",
				data : {
					"caNo" : caNo, 
					"rvNo" : rvNo, 
					"rvContents" : modifiedContent
					},
				success : function(data) {
					if(data == "success"){
						getReviewList(page);
					} else {
						alert("댓글 수정 실패!");
					}
				},
				error : function() {
					console.log("전송 실패..");
				}
			});
	}); */

// 		// 리뷰삭제
// 		function removeReview(caNo, rvNo) {
// 			$.ajax({
// 				url : "/cafe/review/delete",
// 				type : "get",
// 				data : {
// 					"caNo" : caNo,
// 					"rvNo" : rvNo
// 				},
// 				success : function(data) {
// 					if (data == "success") {
// 						getReviewList();
// 					} else {
// 						alert("댓글 조회 실패!");
// 					}
// 				},
// 				error : function() {

// 				}
// 			});
// 		}
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