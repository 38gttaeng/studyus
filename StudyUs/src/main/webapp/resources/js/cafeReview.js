	$(function() {
		getReviewList(); 
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
					"rvContents" : rvContents
				},
				success : function(data) {
					if (data == "success") {
						getReviewList();
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
	function getReviewList(data, loginMbNo) {
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
				
				// 수정+삭제 버튼
				if(loginMbNo == data[i].mbNo) {
					$btnTool =$("<div class='btn-group'>");
					$btnTool.append("<button class='btn btn-sm btn-outline-light btn-rounded' onclick='modifyReview(this," + data[i].rvNo + ");'>수정</button>")
					.append("<button class='btn btn-sm btn-outline-light btn-rounded' onclick='reviewDelete(" + data[i].rvNo + ");'>삭제</button>");
					$btnArea.append($btnTool);
				}
				
				$div.append($mbNo);
				$div.append($rvContents);
				$div.append($rvDate);
				$div.append($btnArea);
// 				$div.attr("id", "review" + data[i].caNo);
				
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
		function reviewPage(pi){
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

		
		// 리뷰 수정
// 		function modifyReview(obj, caNo) {
// 	$review = $("#review" + caNo).children("div:eq(1)");
// 	var reviewContent = $review.html();

// 	$divModify = $(obj).parent().parent().prev();
// 	$divModify.html("");
	
// 	$btnArea = $divModify.next();
// 	$btnArea.html("");
// 	$btnArea.addClass("btn-group");
// 	$btnArea.addClass("reBtn");
	
// 	$text = $("<div id='editor2' style='width:100%'>" + reviewContent + "</div>");
// 	$btnArea
// 	.append("<button id='modify-btn' class='btn btn-sm btn-secondary'>수정</button>")
// 	.append("<button class='btn btn-sm btn-secondary' onclick='getReviewList(page);'>취소</button>");
	
// 	$divModify.append($text);	
	
// 	$("#modify-btn").on("click", function() {
// 			$.ajax({
// 				url : "/cafe/review/update",
// 				type : "post",
// 				data : { "caNo" : caNo, "rvContents" : rvContents },
// 				success : function(data) {
// 					if(data == "success") {
// 						getReviewList(page);
// 					} else {
// 						alert("댓글 수정 실패!");
// 					}
// 				},
// 				error : function() {
// 					console.log("전송 실패");
// 				}
// 			});
// 		} else {
// 			alert("댓글 내용을 입력해주세요.");
// 		}
// 	});

		
/* 		// 리뷰 수정 폼(수정버튼 눌렀을 때 폼 생기게 하는 함수)
 		function modifyReview(obj, caNo, rvNo, rvContents) {
			$trModify = $("<tr>");
			$trModify
					.append("<td colspan='3'><input type='text' id='modifyReview' size='50' value='"+reviewContents+"'></td>");
			$trModify.append("<td><button onclick='modifyReviewCommit("
					+ caNo + "," + rvNo + ")'>수정완료</button></td>");
			$(obj).parent().parent().after($trModify);
		}

		// 수정완료 버튼 눌렀을 때 동작하는 함수
		function modifyReplyCommit(caNo, rvNo) {
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
						getReviewList();
					}else{
						alert("댓글 수정 실패!");
					}
				},
				error : function() {
					alert("서버 통신 실패!")

				}
			});
		} */

		// 리뷰삭제
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