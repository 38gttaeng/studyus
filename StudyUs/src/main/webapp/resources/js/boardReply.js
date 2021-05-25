$(function() {

	var page = 1;
	getReplyList(page);
	
	// 페이징 버튼
	$("#rPage li").on("click", function() {
		alert("클릭");
	
    	//page = $(this).text(); // 목록 페이지 번호 추출
		//alert(page);
		//getReplyList(page); 
	});
	
	// 등록하기
	$("#rSubmit").on("click", function() {
		var rMotherNo = $("#rMotherNo").val();
		var rContent = $("#rContent").val();
		var rMbNo = $("#loginMbNo").val();
		
		$.ajax({
			url : "/study/board/addReply",
			type : "post",
			data : {"boMotherNo": rMotherNo , "boContents" : rContent, "mbNo" : rMbNo},
			success : function(result) {
				if(result == "success") {
					/* 댓글 불러오기 */
					getReplyList(page);
					$("#rContent").val("");
				} else if(result == "fail") {
					alert("댓글 등록 실패..");
				}
			},
			error : function() {
				alert("전송 실패!");
			}
		});
	});
});

// 리스트 통신
function getReplyList(page) {
	var rMotherNo = $("#rMotherNo").val();
	var rMbNo = $("#rMbNo").val();
	var loginMbNo = $("#loginMbNo").val();
	
	$.ajax({
		url : "/study/board/replyList",
		type : "get",
		data : {"boMotherNo" : rMotherNo, "page" : page},
		dataType : "json",
		success : function(map) {
			var data = map.rList;
			var pi = map.page;
			
			page = pi.maxPage;///////////////////////
			replyList(data, pi.listCount, loginMbNo);
			replyPage(pi);
		},
		error : function() {
			// 댓글 없을 경우 여기로 이동
			$("#rCount").text("0");
		}
	});
}

// 댓글 리스트
function replyList(data, listCount, loginMbNo) {
	var $rList = $("#rList");
	$rList.html("");
	
	var $div;
	var $rWriter;
	var $rContent;
	var $btnArea;
	var $btnTool = "";
	
	$("#rCount").text(listCount);
	if(data.length > 0) {
		
		/* 댓글 */
		for(var i in data) {
		
			// 댓글 작성자인 경우 배경색 추가
			if(loginMbNo != data[i].mbNo) {
				$div = $("<div class='reply-box'>");
			} else {
				$div = $("<div class='reply-box my-reply'>");
			}
			
			$rWriter = $("<div>")
			.append("<img src='/resources/images/" + data[i].member.mbPhoto + ".png' class='rounded-circle'>&nbsp")
			.append("<span>" + data[i].member.mbNickname + "</span>&nbsp");
			if(rMbNo == data[i].mbNo) {
				$rWriter.append("&nbsp<div>작성자</div>");
			}
			$rWriter.append("<span>" + data[i].boInsertDate + "</span>");
			
			$rContent = $("<div>").text(data[i].boContents);
			
			$btnArea = $("<div>")
			.append("<button class='btn btn-sm btn-light'>답글</button>");
			
			// 수정+삭제 버튼
			if(loginMbNo == data[i].mbNo) {
				$btnTool =$("<div class='btn-group'>");
				$btnTool.append("<button class='btn btn-sm btn-outline-light btn-rounded' onclick='modifyReply(this," + data[i].boNo + ",\"" + data[i].boContents + "\");'>수정</button>")
				.append("<button class='btn btn-sm btn-outline-light btn-rounded' onclick='removeReply(" + data[i].boNo + ");'>삭제</button>");
				$btnArea.append($btnTool);
			}
			
			$div.append($rWriter);
			$div.append($rContent);
			$div.append($btnArea);
			
			$rList.append($div);
		}
	}
}

// 페이징
function replyPage(pi) {
	var $rPage = $("#rPage");
	$rPage.html("");
	
	var $ul;
	var $liBefore="";
	var $liCurrent;
	var $liAfter="";
	
	$ul = $("<ul class='pagination pagination-sm justify-content-center'>");

	if(pi.currentPage > 1) {
		$liBefore = $("<li class='page-item' value='" + (pi.currentPage - 1) + "'>");
		$aTag = $("<span class=page-link' aria-label='Previous'>")
		.append("<span>&laquo;</span>");
		$liBefore.append($aTag);
	}
	$ul.append($liBefore);
	
	for(var p = pi.startPage; p < pi.endPage + 1; p++) {
		if(p == pi.currentPage) {
			$liCurrent = $("<li class='page-item active' value='" + p + "'>");
		} else {
			$liCurrent = $("<li class='page-item' value='" + p + "'>");
		}
		
		$liCurrent.append("<span class='page-link'>" + p + "</span>");
		
		$ul.append($liCurrent);
	}
	
	if(pi.currentPage < pi.maxPage) {
		$liAfter = $("<li class='page-item' value='" + (pi.currentPage + 1) + "'>");
		$aTag = $("<span class='page-link' aria-label='Next'>")
		.append("<span>&raquo;</span>");
		$liAfter.append($aTag);
	}
	$ul.append($liAfter);
	
	$rPage.append($ul);
}

// 수정하기 화면
function modifyReply(obj, boNo, replyContent) {
	$divModify = $(obj).parent().parent().prev();
	$divModify.html("");
	
	$btnArea = $divModify.next();
	$btnArea.html("");
	$btnArea.addClass("btn-group");
	
	$text = $("<textarea id='mReply' class='form-control' rows='3' placeholder='댓글을 입력하세요.'>" + replyContent + "</textarea>");
	$btnArea
	.append("<button class='btn btn-sm btn-secondary' onclick='modifyReplyCommit(" + boNo + ");'>수정</button>")
	.append("<button class='btn btn-sm btn-secondary' onclick='getReplyList();'>취소</button>");
	
	$divModify.append($text);
	$divModify.parent().css("height", "190px");
}

// 수정하기
function modifyReplyCommit(boNo) {
	var mReply = $("#mReply").val();
	
	$.ajax({
		url : "/study/board/modifyReply",
		type : "post",
		data : { "boNo" : boNo, "boContents" : mReply },
		success : function(data) {
			if(data == "success") {
				getReplyList();
			} else {
				alert("댓글 수정 실패!");
			}
		},
		error : function() {
			console.log("전송 실패..");
		}
	});
}

// 삭제하기
function removeReply(boNo) {
	var result = confirm("댓글을 삭제하시겠습니까?");
	
	if(result) {
		$.ajax({
			url : "/study/board/deleteReply",
			type : "get",
			data : { "boNo" : boNo },
			success : function(data) { 
				if(data == "success") {
					getReplyList();
				} else {
					alert("댓글 삭제 실패!");
				}
			},
			error : function() {
				alert("전송 실패..");
			}
		});
	}
}