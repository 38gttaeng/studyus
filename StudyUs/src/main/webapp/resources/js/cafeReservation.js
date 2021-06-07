$(function() {
	getRoomList();
	
	$("#reservation-btn").on("click", function() {
		var caNo = $("input[name=caNo]").val();
		location.href="/cafe/reservation-room?crNo" + crNo;
	});
});

// 룸 전체 리스트 출력
function getRoomList() {
	
	$("#room-wrapper").html("<div id='door'>출입구</div>");
	var caNo = $("input[name=caNo]").val();

		// 룸 리스트 가져오기
	$.ajax({
	    url: '/cafe/room-list',
	    data: { "caNo" : caNo },
		dataType : 'json',
	    type: 'GET',
	    success: function(crList){
			if(crList.length != 0) {
				for(var i in crList) {
					$("#room-wrapper")
					.append("<div id='" + crList[i].crNo + "' class='no-draggable no-gray' style='top:" + crList[i].crTop + "px; left:" + crList[i].crLeft + "px;'>" + crList[i].crName + "</div>");
					
					switch(crList[i].crMax){
					case 4:
						$("#" + crList[i].crNo).css("width", "80px");
						$("#" + crList[i].crNo).css("height", "80px");
						break;
					case 6:
						$("#" + crList[i].crNo).css("width", "100px");
						$("#" + crList[i].crNo).css("height", "100px");
						break;
					case 10:
						$("#" + crList[i].crNo).css("width", "130px");
						$("#" + crList[i].crNo).css("height", "130px");
						break;
					}
					
					// 룸 이름이 잘리지 않고 잘 들어가도록 처리
					textFit($('.no-draggable'), {
						alignHoriz: true,
						alignVert: true,
						maxFontSize: 16
					});
					
					// 룸 상세정보 불러오기 (클릭시 이벤트 발생)
					$(".no-draggable").click(function() {
						$(".no-draggable").removeClass("no-purple");
						$(this).addClass("no-purple");
						
						var crNo = $(this).attr("id");
						getOneRoom(crNo);
					});
				}
			} else if(crList.length == 1){
				// 기존에 등록한 룸이 없는 경우
				console.log("룸이 없습니다.");//////////////////////////
			}
	    },
	    error : function(){
	    	console.log("전송 실패!");
	    }
	});
}

// 룸 하나 리스트 출력해서 넣어주기
function getOneRoom(crNo) {
	$.ajax({
	    url: '/cafe/room-detail',
	    data: { "crNo" : crNo },
		dataType : 'json',
	    type: 'GET',
	    success: function(map) {
			var caferoom = map.caferoom;
			var originFilename = map.originFilename;

			// 정보 보여주기
			$("#roomName").html("<strong>" + caferoom.crName + "</strong>");
			if(caferoom.crFilename != null) {
				$("#roomFile").attr("src", "/resources/cuploadImages/" + caferoom.crFilename);
			} else {
				$("#roomFile").attr("src", "/resources/images/no-image.png");
			}
			$("#roomInfo").html(caferoom.crInfo);
			$("#roomMax").html(caferoom.crMax + " 명");
			$("#roomPrice").html(caferoom.crPrice + " 원");
			
			// 정보 보여주기
			$("#detail-box").removeClass("d-none");
			$("#detail-box > div").css("display", "inline-block");
	    },
	    error : function(){
	    	console.log("전송 실패..ㅜ");
	    }
	});
}
