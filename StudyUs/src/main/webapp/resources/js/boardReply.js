var data = null;
var pi = null;
var page = 1;

const limit = 1000; // 1000자 제한

$(function() {
	var check = $("#menuCheck").val();
	getReplyList(page);
	
	// Quill
	var toolbarOptions = {
		container : [
		  ['link', 'image', 'video', 'formula', 'code-block'],
		  ['emoji'],
		]
	};
	
	var quill = new Quill('#editor', {
		modules: {
			imageResize: {},
			imageUpload: {
				url: '/file/upload/image',
				method: 'POST',
				name: 'uploadImage',
				withCredentials: false,
				callbackOK: (serverResponse, next) => {
			    	next(serverResponse);
			    },
				callbackKO: serverError => {
					alert(serverError);
				},
				checkBeforeSend: (file, next) => {
			    	console.log(file);
			    	next(file);
			    }
			},
          "toolbar": toolbarOptions,
          "emoji-toolbar": true,
		},
		placeholder: '댓글을 입력하세요.',
		theme: 'bubble'
	});
	
		// 글자수 제한
	quill.on('text-change', function (delta, old, source) {
	  if (quill.getLength() > limit) {
	    quill.deleteText(limit, quill.getLength());
	  }
	});
	
	// 등록하기
	$("#rSubmit").on("click", function() {
		var rMotherNo = $("#rMotherNo").val();
		var rContent = quill.root.innerHTML;
		var rMbNo = $("#loginMbNo").val();
		
		if(rContent != "") {
			$.ajax({
				url : "/study/board/addReply",
				type : "post",
				data : {"boMotherNo": rMotherNo , "boContents" : rContent, "mbNo" : rMbNo},
				success : function(result) {
					if(result == "success") {
						/* 댓글 불러오기 */
						getReplyList(page);
						quill.deleteText(0,100000000);
					} else if(result == "fail") {
						alert("댓글 등록 실패..");
					}
				},
				error : function() {
					alert("전송 실패!");
				}
			});
		} else {
			alert("댓글 내용을 입력하세요.");
		}
		
	});
});

// 리스트 통신
function getReplyList(page) {
	var rMotherNo = $("#rMotherNo").val();
	var rMbNo = $("#rMbNo").val();
	var loginMbNo = $("#loginMbNo").val();
	
	$.ajax({
		url : "/study/board/replyList",
		data : {"boMotherNo" : rMotherNo, "page" : page},
		type : "get",
		dataType : "json",
		success : function(map) {
			data = map.rList;
			pi = map.page;
			
			page = pi.maxPage;
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
			
			$rContent = $("<div>").append(data[i].boContents);
			
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
		$liBefore = $("<li class='page-item' onclick='getReplyList(" + (pi.currentPage - 1) + ")'>");
		$spanTag = $("<span class='page-link' aria-label='Next'>")
		.append("<span>&laquo;</span>");
		$liBefore.append($spanTag);
	}
	$ul.append($liBefore);
	
	for(var p = pi.startPage; p < pi.endPage + 1; p++) {
		// onclick='getReplyList(p)'
		if(p == pi.currentPage) {
			$liCurrent = $("<li class='page-item active'>");
		} else {
			$liCurrent = $("<li class='page-item' onclick='getReplyList(" + p + ")'>");
		}
		
		$liCurrent.append("<span onclick='getReplyList(p)' class='page-link'>" + p + "</span>");
		
		$ul.append($liCurrent);
	}
	
	if(pi.currentPage < pi.maxPage) {
		$liAfter = $("<li class='page-item' onclick='getReplyList(" + (pi.currentPage + 1) + ")'>");
		$spanTag = $("<span class='page-link' aria-label='Next'>")
		.append("<span>&raquo;</span>");
		$liAfter.append($spanTag);
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
	
	$text = $("<div id='editor'>" + replyContent + "</div>");
	$btnArea
	.append("<button class='btn btn-sm btn-secondary' onclick='modifyReplyCommit(" + boNo + ");'>수정</button>")
	.append("<button class='btn btn-sm btn-secondary' onclick='getReplyList(page);'>취소</button>");
	
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
				getReplyList(page);
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