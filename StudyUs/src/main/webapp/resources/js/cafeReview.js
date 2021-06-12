/******************************************
 *        스터디카페 상세페이지 리뷰       *
 ******************************************/
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
			var caNo = $("#cafeNumber").val();
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
						alert("따뜻한 리뷰가 등록되었습니다!");
						getReviewList(page);
						$("#rvContents").val("");
					} else {
						alert("리뷰 등록 실패");
					}
				},
				error : function() {
						alert("로그인을 해주세요!");
						//window.location.href = "/member/loginView";
				}
			});
			
			}
		});
	});
		
	// 리뷰 리스트
	function getReviewList(page, loginMbNo) {
		var caNo = $("#cafeNumber").val();
		var memberNo = $("#memberNo").val();
		var count = 0;
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
	 				count += (data[i].rvRating * 1);
	 				
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
			var avg = count / (data.length); 
			console.log(avg);
			$('#avg').text(Math.round(avg*100)/100.0);
			const number1 = avg;
			
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