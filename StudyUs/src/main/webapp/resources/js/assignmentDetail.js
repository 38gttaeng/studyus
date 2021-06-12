$(function() {
	
	$('.chart').easyPieChart({
	    barColor: '#6927ff',  //차트가 그려질 색
	    trackColor: '#e9ecef',  // 차트가 그려지는 트랙의 기본 배경색(chart1 의 회색부분)
	    scaleColor: '#fff', // 차트 테두리에 그려지는 기준선 (chart2	의 테두리 선)
	    lineCap: 'round', // 차트 선의 모양 chart1 butt / chart2 round / chart3 square
	    lineWidth: 15, // 차트 선의 두께
	    size: 220, // 차트크기
	    animate: 1000, // 그려지는 시간 
	    onStart: $.noop,
	    onStop: $.noop
  	});

	var insertDate = new Date(moment($("#insertDate").text()).format('YYYY/MM/DD HH:mm'));
	var deadLine = new Date(moment($("#deadLine").text()).format('YYYY/MM/DD HH:mm'));
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
	
	
	var suList = $("#suList").val();
});

/*function getSubmittedCheck() {
	var asNo = $("#asNo").val();
	var table = $("table");
	
	$.ajax ({
		url : "/study/assignment/submitted-check",
		type : "get",
		data : {"asNo" : asNo},
		dataType : "json",
		success : function(list) {
			console.log(list);
			
			var submitted = new Array();
			for(var i in list) {
				$tr = $("<tr>");
				$td = $("<td>").append(list[i].mbNickname);
				if(list[i].suNo == 0) {
					// 제출 안한 사람
					$td2 = $("<td>").append("<div class='btn btn-sm btn-outline-secondary btn-rounded'>미제출</div>");
					$td3 = $("<td>")
				} else {
					// 제출 한 사람
					$td2 = $("<td>").append("<div class='btn btn-sm btn-outline-primary btn-rounded'>&nbsp;제출&nbsp;</div>");
					$td3 = $("<td>").append(list[i].suInsertDate);
					submitted.push(list[i].mbNo);
				}
				$tr.append($td);
				$tr.append($td2);
				$tr.append($td3);
				table.append($tr);
			}
			
			var all = list.length;
			var sLength = submitted.length;
			var result = (sLength/all)*100;
			
			$("#chart-box").append("<div class='chart' data-percent='" + result + "'><p class='chart-p'>" + result + "%</p></div>");
			
			$('.chart').easyPieChart({
			    barColor: '#6927ff',  //차트가 그려질 색
			    trackColor: '#e9ecef',  // 차트가 그려지는 트랙의 기본 배경색(chart1 의 회색부분)
			    scaleColor: '#fff', // 차트 테두리에 그려지는 기준선 (chart2	의 테두리 선)
			    lineCap: 'round', // 차트 선의 모양 chart1 butt / chart2 round / chart3 square
			    lineWidth: 15, // 차트 선의 두께
			    size: 200, // 차트크기
			    animate: 1000, // 그려지는 시간 
			    onStart: $.noop,
			    onStop: $.noop
		  	});
		},
		error : function() {
			alert("전송 실패..");
		}
	});
}*/