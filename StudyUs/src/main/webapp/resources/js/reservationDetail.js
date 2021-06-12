var memList = new Array();
var rsNo = $("input[name=rsNo]").val();	
var loginMbNo = $("input[name=loginMbNo]").val();

$(function() {
	
	getMbList(rsNo);
	
	
	// 멤버 등록버튼 클릭시
	$("#plus-btn").on("click", function() {
		console.log("밖에서 : " + memList);
		var maxMem = $("input[name=maxCount]").val();
		
		if(memList.length > 0) {
			if(memList.length == maxMem) {
				return alert("최대 인원을 초과해서 등록할 수 없습니다."); 
			}
			
			for(var i in memList) {
				if(memList[i] == loginMbNo) {
					return alert("이미 등록된 스터디원입니다.");
				}
			}
			
			insertMem();
		} else {
			insertMem();
		}
	});

});

// 멤버 리스트
function getMbList(rsNo) {
	$td = $("#member-box");
	$td.html("");
	
	memList = [];
	
	$.ajax({
	    method: 'get',
	    url: '/study/reservation/member',
	    data: { "rsNo" : rsNo },
		dataType : "json",
	    success: function (mbList) {
			if(mbList.length > 0) {
				for(var i in mbList) {
					$td.append("<div class='del-btn btn btn-sm btn-light btn-rounded' onclick='deleteMem(this," + mbList[i].mbNo + ")'>" + mbList[i].mbNickname + "&nbsp<i class='fas fa-times'></i></div>");
					memList.push(mbList[i].mbNo);
					console.log("안에서 : " + memList);
				}
			}
		},
		error : function(){
	    	console.log("전송 실패..");
	    }
	});
}

// 멤버 등록
function insertMem() {
	$.ajax({
	    method: 'get',
	    url: '/study/reservation/member-register',
	    data: { "rsNo" : rsNo },
	    success: function (result) {
			if(result == "success") {
				alert("참여 인원에 추가되었습니다.");
				getMbList(rsNo);
			} else {
				console.log("멤버 등록 실패");
			}
		},
		error : function(){
	    	console.log("전송 실패..");
	    }
	});
}


// 멤버 삭제
function deleteMem(obj, mbNo) {
	if(mbNo == loginMbNo) {
		// 닉네임이 쓰여있는 div 삭제
		$(obj).remove();
		
		// 리스트 저장 배열에서 삭제
		memList
	
		$.ajax({
		    method: 'get',
		    url: '/study/reservation/member-remove',
		    data: { "rsNo" : rsNo },
		    success: function (result) {
				if(result == "success") {
					memList.splice( memList.indexOf(mbNo) , 1 );
					alert("참여 인원에서 삭제되었습니다.");
				} else {
					console.log("멤버 삭제 실패");
				}
			},
			error : function(){
		    	console.log("전송 실패..");
		    }
		});
	} else {
		alert("본인만 삭제할 수 있습니다!");
	}
}