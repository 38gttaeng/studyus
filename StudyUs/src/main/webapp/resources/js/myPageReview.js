$(document).ready(function(){
	var mbNo = $("#mbNo").val();
	$.ajax({
		url : "/member/myReview",
		type : "get",
		data : { "mbNo" : mbNo },
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
					$div = $("<div class='reply-box' height=''>");
					$writer = $("<div>")
					.append("<img src='/resources/images/" + data[i].member.mbPhoto + ".png' class='rounded-circle' width='30'>&nbsp")
					.append("<span class='nickName'>" + data[i].member.mbNickname + "</span>&nbsp");
					$writer.append("<span class='insertDate'>" + data[i].rvDate + "</span>");
					$rvContents = $("<div class='contents-box'>").append(data[i].rvContents);
 					console.log("rvRating: " + data[i].rvRating)
					$btnArea = $("<div>").append("&nbsp;");;
					
					$btnTool = "";
	 				
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
});
