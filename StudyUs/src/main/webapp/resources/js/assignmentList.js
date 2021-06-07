var grStatus = 1;
var memArr = new Array();

$(document).ready(function(){

	getAllGroup();
	
	// selectBox
	$("#group-color").change(function(){
		var selected = $("#group-color option:selected").val();
		var color = "";
		
		switch(selected) {
		case "1": color = "rgb(196, 178, 234)";
			break;
		case "2": color = "rgb(165, 228, 216)";
			break;
		case "3": color = "rgb(243, 211, 244)";
			break;
		case "4": color = "rgb(188, 209, 241)";
			break;
		}
		
		$(this).css("background-color", color);
	});
	
	$("#re-group-color").change(function(){
		var selected = $("#re-group-color option:selected").val();
		var color = "";
		
		switch(selected) {
		case "1": color = "rgb(196, 178, 234)";
			break;
		case "2": color = "rgb(165, 228, 216)";
			break;
		case "3": color = "rgb(243, 211, 244)";
			break;
		case "4": color = "rgb(188, 209, 241)";
			break;
		default: color = "rgb(196, 178, 234)";
			break;
		}
		
		$(this).css("background-color", color);
	});
	
	// 추가 버튼 클릭시 스터디원 정보 가져오기
	$(".addGroup").on("click", function() {
		var select = $("#group-member");
		
		$.ajax ({
			url : "/study/assignment/mem-list",
			type : "get",
			dataType : "json",
			success : function(mbList) {
				for(var i in mbList) {
					select.append("<option value='" + mbList[i].mbNo + "'>" + mbList[i].mbNickname + "</option>");
				}
			},
			error : function() {
				alert("전송 실패..");
			}
		});
	});
	
	// 프로젝트에 할당할 멤버 설정
	$("#group-member").change(function() {
		var selectedValue = $('#group-member option:selected').val();
		var selectedNick = $('#group-member option:selected').text();
		$("#group-member option[value=" + selectedValue + "]").remove();
		
		$("#input-box").append("<div class='del-btn btn btn-sm btn-light btn-rounded' onclick='deleteMem(this," + selectedValue + ",\"" + selectedNick + "\")'>" + selectedNick + "&nbsp<i class='fas fa-times'></i></div>");
		memArr.push(selectedValue);
	});
	
	// 유효성 체크 //////////////////////////////////////////////////////////
	var nameMsg = $("#name-msg");
	
	$("#group-name").on("keyup", function() {
		if($(this).val() == "") {
			$(this).removeClass("is-valid");
			$(this).addClass("is-invalid");
			nameMsg.css("display", "block");
		} else {
			$(this).removeClass("is-invalid");
			$(this).addClass("is-valid");
			nameMsg.css("display", "none");
		}
	});
	
	// 그룹 등록
	$("#addGroup-btn").on("click", function() {
		var grName = $("#group-name").val();
		var grInfo = $("#group-info").val();
		var grColor = $("#group-color").val();
		
		if(grName == "") {
			$("#group-name").addClass("is-invalid");
			nameMsg.css("display", "block");
		} else if(memArr.length == 0) {
			alert("스터디원을 한명이라도 할당해야 합니다!");
			$("#group-member").addClass("is-invalid");
		} else {
			$("#group-name").removeClass("is-invalid");
			$("#group-member").removeClass("is-invalid");
			
			// 할당정보(memArr)도 함께 전송
			$("input[name=grMember]").val(memArr);
			
			// submit으로 그룹 등록 정보 전송
			$("#groupWriteForm").submit();
		}
	});
	
	// 취소버튼 클릭시 할당정보 초기화
	$("#resetGroup-btn").on("click", function() {
		$("#input-box").html("");
		memArr.length = 0;
		$("#group-member").html("");
		$("#group-member").appen("<option selected>선택해서 추가</option>");
	});
	
	$("#modifyGroup-btn").on("click", function() {
		var grName = $("#re-group-name").val();
		
		if(grName != "") {
			$("#groupModifyForm").submit();
		} else {
			alert("프로젝트명을 입력해주세요!");
			$("#re-group-name").addClass("is-invalid");
		}
	});
    
});

// 선택된 멤버 삭제시 실행하는 함수
function deleteMem(obj, value, nick) {
	$(obj).remove();
	$("#group-member").append("<option value='" + value + "'>" + nick + "</option>");
	
	var idx = memArr.indexOf(value + "");
	if (idx > -1) memArr.splice(idx, 1);
}

function getAllGroup() {
	
	var memberNo = $("#memberNo").val();
	var leaderNo = $("#leaderNo").val();
	
	$box = $("#group-list-box");
	$box.html("");

	$.ajax ({
		url : "/study/assignment/group-list",
		data : { "grStatus" : grStatus },
		type : "get",
		dataType : "json",
		success : function(grList) {
			var $div;
			var $dropdown;
			var $dropBtn;
			var $dropMenu;
			var $groupName;
			var $groupMem;
			
			for(var i in grList) {
				
				$div = $("<div class='item backHover" + grList[i].grColor + "'>");
				
				$dropdown = $("<div class='dropdown'>");
					$dropBtn = $("<a id='group-delete' class='btn dropdown-toggle' href='javascript:void(0)' role='button' id='dropdownMenuLink' data-toggle='dropdown' aria-haspopup='true' aria-expanded='false'>")
					.append("<i class='fas fa-times'></i>");
					$dropMenu = $("<div class='dropdown-menu text-center' aria-labelledby='dropdownMenuLink'>");
					if(memberNo == leaderNo) {
						$dropMenu.append("<a class='dropdown-item' onclick='confirm(\"정말 삭제하시겠습니까?\")' href='/study/assignment/deleteGroup?grNo=" + grList[i].grNo + "'>삭제</a>");
					}
					$dropdown.append($dropBtn).append($dropMenu);
				$groupName = $("<div class='item-name' onclick='location.href=\"/study/assignment?grNo=" + grList[i].grNo + "\"'>").append(grList[i].grName);
				$groupMem = $("<div class='item-mem'>").append("3/5");
				
				$div.append($dropdown);
				$div.append($groupName);
				$div.append($groupMem);
				
				$box.append($div);
			}
			
			// Carousel 
		    $('.owl-carousel').owlCarousel({
		        items:5,                 // 한번에 보여줄 아이템 수
		        loop:false,               // 반복여부
		        margin:30,               // 오른쪽 간격
		        autoplay:false,           // 자동재생 여부
		        autoplayHoverPause:true,  // 마우스오버시 멈출지 여부
				responsive:{
					0:{items:1},
					600:{items:3},
					1000:{items:5}
				},
				nav: true
		    });
		},
		error : function() {
			alert("전송 실패..");
		}
	});
}