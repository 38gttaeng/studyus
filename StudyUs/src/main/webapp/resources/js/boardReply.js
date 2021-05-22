$(function() {
	getReplyList();
	
	$("#rSubmit").on("click", function() {
		var rMotherNo = $("#rMotherNo").val();
		var rContent = $("#rContent").val();
		
		$.ajax({
			url : "/study/board/addReply",
			type : "post",
			data : {"boMotherNo": rMotherNo , "boContents" : rContent},
			success : function(result) {
				if(result == "success") {
					/* 댓글 불러오기 */
					getReplyList();
					$("#rContent").val("");
					alert("댓글 등록 성공 : rContent");
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

function getReplyList() {
	var rMotherNo = $("#rMotherNo").val();
	var rMbNo = $("#rMbNo").val();
	//var loginMbNo = $("#loginMbNo").val();
	
	$.ajax({
		url : "/study/board/replyList",
		type : "get",
		data : {"boMotherNo" : rMotherNo},
		dataType : "json",
		success : function(data) {
			var $rList = $("#rList");
			$rList.html("");
			
			var $div;
			var $rWriter; // 사진번호랑 닉네임으로
			var $rContent;
			var $btnArea;
			
			$("#rCount").text(data.length);
			if(data.length > 0) {
				for(var i in data) {
					$div = $("<div class='reply-box'>");
					
					$rWriter = $("<div>")
					.append("<img src='/resources/images/1.png' class='rounded-circle'>&nbsp")
					.append("<span>" + data[i].mbNo + "</span>&nbsp");
					if(rMbNo == data[i].mbNo) {
						$rWriter.append("&nbsp<div>작성자</div>");
					}
					$rWriter.append("<span>" + data[i].boInsertDate + "</span>");
					
					$rContent = $("<div>").text(data[i].boContents);
					
					$btnArea = $("<div>")
					.append("<button class='btn btn-sm btn-light'>답글</button>");
					
					// 본인이 댓글 작성자인 경우 + 링크도 걸어야 함
					//if(loginMbNo == data[i].mbNo) {
					//	$btnArea.append("<div class='btn-group'><button class='btn btn-sm btn-outline-light'>수정</button>");
					//	$btnArea.append("<button class='btn btn-sm btn-outline-light'>삭제</button></div>");
					//}
					
					$div.append($rWriter);
					$div.append($rContent);
					$div.append($btnArea);
					
					$rList.append($div);
				}
			}
		},
		error : function() {
			// 댓글 없을 경우 여기로 이동
		}
	});
}

function modifyReply(obj, boardNo, replyNo, replyContent) {
	$trModify = $("<tr>");
	$trModify.append("<td colspan='3'><input id='mReply' type='text' size='50' value='" + replyContent + "'></td>");
	$trModify.append("<td align='center'><button onclick='modifyReplyCommit(" + boardNo + "," + replyNo + ");'>수정완료</button></td>");
	
	$(obj).parent().parent().after($trModify);
}

function modifyReplyCommit(boardNo, replyNo) {
	var mReply = $("#mReply").val();
	
	$.ajax({
		url : "modifyReply.kh",
		type : "post",
		data : { "refBoardNo" : boardNo, "replyNo" : replyNo, "replyContent" : mReply },
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

function removeReply(boardNo, replyNo) {
	var result = confirm("댓글을 삭제하시겠습니까?");
	
	if(result) {
		$.ajax({
			url : "deleteReply.kh",
			type : "get",
			data : { "refBoardNo" : boardNo, "replyNo" : replyNo },
			success : function(data) { 
				if(data == "success") {
					getReplyList();
				} else {
					alert("댓글 삭제 실패!");
				}
			},
			error : function() {
				console.log("전송 실패..");
			}
		});
	}
}