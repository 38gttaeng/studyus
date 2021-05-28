$(document).ready(function(){

	// Carousel
    var owl = $('.owl-carousel');
    owl.owlCarousel({
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

	// Each Group
	// $(".item").css("background-color", value);////////////////////////
	
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
		default: color = "rgb(196, 178, 234)";
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
	
	// 그룹 등록
	$("#addGroup-btn").on("click", function() {
		var grName = $("#group-name").val();
		var grInfo = $("#group-info").val();
		var grColor = $("#group-color").val();
		//var list...로 멤버들 for문으로 할당 등록
		
		$.ajax ({
			url : "/study/assignment/addGroup",
			data : { "grName" : grName, "grInfo" : grInfo, "grColor" : grColor },
			type : "post",
			success : function(result) {
				if(result == "success") {
					/* 리스트 불러오기 */
					// getReplyList(page);
				} else if(result == "fail") {
					alert("그룹 등록 실패..");
				}
			},
			error : function() {
				alert("전송 실패..");
			}
		});
	});
	
	// 스터디 하나 정보와 과제 리스트 테이블 색상 변경
	var tBoxColor = $("#tBox-color").val();
	var textColor = "#fff";
	
	switch(tBoxColor) {
	case 1: 
		tBoxColor = "rgb(196, 178, 234)";
		break;
	case 2: 
		tBoxColor = "rgb(165, 228, 216)";
		break;
	case 3: 
		tBoxColor = "rgb(243, 211, 244)";
		break;
	case 4: 
		tBoxColor = "rgb(188, 209, 241)";
		break;
	default: 
		tBoxColor = "#f5f7f8";
		textColor = "#000";
		break;
	}
	
	$(".oneStudy").css("background-color", tBoxColor);
	$("#theadColor").css("background-color", tBoxColor);
	$("#theadColor").css("color", textColor);
	$("#tbodyColor").css("border", "1px solid" + tBoxColor);
    
});