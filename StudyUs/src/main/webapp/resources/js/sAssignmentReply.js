var data = null;
var pi = null;
var page = 1;

var toolbarOptions = {
	container : [
	  ['link', 'image', 'video', 'formula', 'code-block'],
	  ['emoji'],
	]
};
const limit = 1000; // 1000자 제한

$(function() {
	
	// progress bar
	var insertDate = new Date(moment($("#insertDate").val()).format('YYYY/MM/DD HH:mm'));
	var deadLine = new Date(moment($("#deadLine").val()).format('YYYY/MM/DD HH:mm'));
	var today = new Date();
	
	var total = (deadLine - insertDate);
	var now = (today - insertDate);
	var percent = (now/total)*100;
	
	if(percent > 100) {
		percent = 100;
	} else if(percent < 30) {
		percent = 30;
	}
	$(".dateProgress").css("width", percent + "%");

	getReplyList(page);
	
	// Quill
	var picArr = new Array();
	var quill = new Quill('#editor', {
		modules: {
			imageResize: {},
			imageUpload: {
				url: '/file/upload/assignment-image',
				method: 'POST',
				name: 'uploadImage',
				withCredentials: false,
				callbackOK: (serverResponse, next) => {
			    	next(serverResponse);
					picArr.push(serverResponse.substring(25));
			    },
				callbackKO: serverError => {
					alert(serverError);
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
		var asNo = $("#suAsNo").val();
		var rMbNo = $("#loginMbNo").val();
		var rContent = quill.root.innerHTML;
		
		if(picArr.length == 0) {
			picArr.push("");
		}
		if(quill.getLength() > 3) {
			$.ajax({
				url : "/study/sAssignment/addReply",
				type : "post",
				traditional : true,
				data : { "suMotherNo": rMotherNo , "suContents" : rContent, "mbNo" : rMbNo, "asNo" : asNo, "picList" : picArr },
				success : function(result) {
					if(result == "success") {
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
	var loginMbNo = $("#loginMbNo").val();
	
	$.ajax({
		url : "/study/sAssignment/replyList",
		data : {"suMotherNo" : rMotherNo, "page" : page},
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
	
	var suMbNo = $("#suMbNo").val();
	
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
			.append("<span class='nickName'>" + data[i].member.mbNickname + "</span>&nbsp");
			if(suMbNo == data[i].mbNo) {
				$rWriter.append("&nbsp<div class='writerTag'>작성자</div>");
			}
			$rWriter.append("<span class='insertDate'>" + data[i].suInsertDate + "</span>");
			
			$rContent = $("<div class='contents-box'>").append(data[i].suContents);
			
			$btnArea = $("<div>")
			.append("<button class='btn btn-sm btn-light'>답글</button>");
			
			// 수정+삭제 버튼
			if(loginMbNo == data[i].mbNo) {
				$btnTool =$("<div class='btn-group'>");
				$btnTool.append("<button class='btn btn-sm btn-outline-light btn-rounded' onclick='modifyReply(this," + data[i].suNo + ");'>수정</button>")
				.append("<button class='btn btn-sm btn-outline-light btn-rounded' onclick='removeReply(" + data[i].suNo + ");'>삭제</button>");
				$btnArea.append($btnTool);
			}
			
			$div.append($rWriter);
			$div.append($rContent);
			$div.append($btnArea);
			$div.attr("id", "suReply" + data[i].suNo);
			
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
		if(p == pi.currentPage) {
			$liCurrent = $("<li class='page-item active'>");
		} else {
			$liCurrent = $("<li class='page-item' onclick='getReplyList(" + p + ")'>");
		}
		
		$liCurrent.append("<span class='page-link'>" + p + "</span>");
		
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

// 수정하기
function modifyReply(obj, suNo) {
	$reply = $("#suReply" + suNo).children("div:eq(1)");
	var replyContent = $reply.html();

	$divModify = $(obj).parent().parent().prev();
	$divModify.html("");
	
	$btnArea = $divModify.next();
	$btnArea.html("");
	$btnArea.addClass("btn-group");
	$btnArea.addClass("reBtn");
	
	$text = $("<div id='editor2' style='width:100%'>" + replyContent + "</div>");
	$btnArea
	.append("<button id='modify-btn' class='btn btn-sm btn-secondary'>수정</button>")
	.append("<button class='btn btn-sm btn-secondary' onclick='getReplyList(page);'>취소</button>");
	
	$divModify.append($text);
	
	var picArr2 = new Array();
	var quill2 = new Quill('#editor2', {
		modules: {
			imageResize: {},
			imageUpload: {
				url: '/file/upload/assignment-image',
				method: 'POST',
				name: 'uploadImage',
				withCredentials: false,
				callbackOK: (serverResponse, next) => {
			    	next(serverResponse);
					picArr2.push(serverResponse.substring(25));
			    },
				callbackKO: serverError => {
					alert(serverError);
				}
			},
	      "toolbar": toolbarOptions,
	      "emoji-toolbar": true,
		},
		theme: 'bubble'
	});
	
	$("#modify-btn").on("click", function() {
		var mContents = quill2.root.innerHTML;
		
		if(picArr2.length == 0) {
			picArr2.push("");
		}
		if(quill2.getLength() > 3) {
			$.ajax({
				url : "/study/sAssignment/modifyReply",
				type : "post",
				traditional : true,
				data : { "suNo" : suNo, "suContents" : mContents, "picList" : picArr2 },
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
		} else {
			alert("댓글 내용을 입력해주세요.");
		}
	});
}

// 삭제하기
function removeReply(suNo) {
	var result = confirm("댓글을 삭제하시겠습니까?");
	
	if(result) {
		$.ajax({
			url : "/study/sAssignment/deleteReply",
			type : "get",
			data : { "suNo" : suNo },
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
