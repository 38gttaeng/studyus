$(function() {

	getRoomList();
	
	// 위치정보 보내기
	$("#save").on("click", function() {
		var caNo = $("input[name=caNo]").val();
		var arr = new Array();
		
		$(".draggable").each(function(){
	        var elem = $(this),
	            id = elem.attr('id');
	            pos = elem.position();
	            
			var obj = {"id" : id*1, "left" : pos.left, "top" : pos.top}
			
			arr.push(obj);
	    });

		$.ajax({
		    method: 'post',
		    url: '/cafe/modify-rooms',
		    traditional: true,
		    data: {
		      data: JSON.stringify(arr)
		    },
		    success: function (result) {
				console.log(result);
				alert("룸 정보가 모두 수정되었습니다!");
				location.href="/cafe/detail?caNo=" + caNo;
			},
			error : function(){
		    	console.log("전송 실패..");
		    }
		});
	});
	
	// 유효성 검사
	$(".crName").on("keyup", function() {
		if($(this).val() == "") {
			$(this).removeClass("is-valid");
			$(this).addClass("is-invalid");
			$(".name-msg").css("display", "block");
		} else {
			$(this).removeClass("is-invalid");
			$(this).addClass("is-valid");
			$(".name-msg").css("display", "none");
		}
	});
	
	$(".crPrice").on("change", function() {
		if($(this).val()*1 < 0) {
			$(this).removeClass("is-valid");
			$(this).addClass("is-invalid");
			$(".price-msg").css("display", "block");
		} else {
			$(this).removeClass("is-invalid");
			$(this).addClass("is-valid");
			$(".price-msg").css("display", "none");
		}
	});
	
	// 룸 추가
	$("#addRoom-btn").on("click", function() {
		var caferoom = new FormData();
		
		var caNo = $("input[name=caNo]").val();
		var crName = $("#crName").val();
		var crInfo = $("#crInfo").val();
		var crMax = $("#crMax").val();
		var crPrice = $("#crPrice").val();
		var crFilename = $("#crFilename").val();
		
		caferoom.append('caNo', caNo);
		caferoom.append('crName', crName);
		caferoom.append('crInfo', crInfo);
		caferoom.append('crMax', crMax);
		caferoom.append('crPrice', crPrice);
		caferoom.append('uploadFile', $("#crFilename")[0].files[0]);
		
		if(crName != "" && crPrice != "" && crPrice*1 >= 0) {
			$.ajax({
			    url: '/cafe/register-room',
			    processData: false,
			    contentType: false,
			    data: caferoom,
				enctype: 'multipart/form-data',
				dataType : 'json',
			    type: 'POST',
			    success: function(crNo){
					$(".form-control").val("");
					$(".form-control").removeClass("is-valid");
			        alert("스터디룸이 추가되었습니다! 위치를 설정해주세요.");
					getRoomList();
					getOneRoom(crNo);
			    },
			    error : function(){
			    	console.log("전송 실패..");
			    }
			});
		} else if(crName == "") {
			$(".crName").removeClass("is-valid");
			$(".crName").addClass("is-invalid");
			$(".name-msg").css("display", "block");
		} else if(crPrice*1 < 0 || crPrice == "") {
			$(".crPrice").removeClass("is-valid");
			$(".crPrice").addClass("is-invalid");
			$(".price-msg").css("display", "display");
		}
	});
	
	// 룸 수정
	$("#modifyRoom-btn").on("click", function() {
		var caferoom = new FormData();
		
		var crNo = $("input[name=crNo]").val();
		var caNo = $("input[name=caNo]").val();
		var crName = $("#re-crName").val();
		var crInfo = $("#re-crInfo").val();
		var crMax = $("#re-crMax").val();
		var crPrice = $("#re-crPrice").val();
		var crFilename = $("#re-crFilename").val();
		
		caferoom.append('crNo', crNo);
		caferoom.append('caNo', caNo);
		caferoom.append('crName', crName);
		caferoom.append('crInfo', crInfo);
		caferoom.append('crMax', crMax);
		caferoom.append('crPrice', crPrice);
		caferoom.append('uploadFile', $("#re-crFilename")[0].files[0]);
		
		if(crName != "" && crPrice != "" && crPrice*1 >= 0) {
			$.ajax({
			    url: '/cafe/modify-room',
			    processData: false,
			    contentType: false,
			    data: caferoom,
				dataType : 'json',
			    type: 'POST',
			    success: function(crNo){
					if(crNo*1 > 0) {
						$(".form-control").val("");
						$(".form-control").removeClass("is-valid");
				        alert("스터디룸이 수정되었습니다! 위치를 설정해주세요.");
						getRoomList();
						getOneRoom(crNo);
					}
			    },
			    error : function(){
			    	console.log("전송 실패..ㅜ");
			    }
			});
		} else if(crName == "") {
			$(".crName").removeClass("is-valid");
			$(".crName").addClass("is-invalid");
			$(".name-msg").css("display", "block");
		} else if(crPrice*1 < 0 || crPrice == "") {
			$(".crPrice").removeClass("is-valid");
			$(".crPrice").addClass("is-invalid");
			$(".price-msg").css("display", "display");
		}
	});
	
	// 룸 삭제
	$("#delete-btn").on("click", function() {
		var crNo = $("input[name=crNo]").val();
		
		var check = confirm("정말 삭제하시겠습니까?");
		if(check) {
			$.ajax({
			    url: '/cafe/delete-room',
			    data: { "crNo" : crNo },
			    type: 'GET',
			    success: function(result){
					if(result == "success") {
						console.log("삭제 성공!");
						$("#detail-box").addClass("d-none");
						getRoomList();
					} else {
						console.log("삭제 실패 : " + result);
					}
			    },
			    error : function(){
			    	console.log("전송 실패!");
			    }
			});
		}
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
					.append("<div id='" + crList[i].crNo + "' class='draggable' style='top:" + crList[i].crTopPx + "px; left:" + crList[i].crLeftPx + "px;'>" + crList[i].crName + "</div>");
					
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
					textFit($('.draggable'), {
						alignHoriz: true,
						alignVert: true,
						maxFontSize: 16
					});
					
					// 룸 위치 정보 저장
					$(".draggable").draggable({
				        containment: "#room-wrapper",
						grid: [ 20, 20 ]
				    });

					// 룸 상세정보 불러오기 (더블클릭시 이벤트 발생)
					$(".draggable").on("dblclick", function() {
						var crNo = $(this).attr("id");
						getOneRoom(crNo);
					});
				}
			} else if(crList.length == 1){
				// 기존에 등록한 룸이 없는 경우
				console.log("룸이 없습니다. 룸을 등록해주셍룡");//////////////////////////
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
			
			// 수정 modal창에 정보 넘기기
			$("#re-crName").val(caferoom.crName);
			$("#origin-filename").html("기존 파일명 : " + originFilename);
			$("#re-crInfo").val(caferoom.crInfo);
			$("#re-crMax").val(caferoom.crMax).prop("selected", true);
			$("#re-crPrice").val(caferoom.crPrice);
			
			$("input[name=crNo]").val(caferoom.crNo);
			
			// 정보 보여주기
			$("#detail-box").removeClass("d-none");
			$("#detail-box > div").css("display", "inline-block");
			
			/* 이혜성 천재야 */
	    },
	    error : function(){
	    	console.log("전송 실패..ㅜ");
	    }
	});
}
