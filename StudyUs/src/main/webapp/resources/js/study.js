// 출석체크 전송
function insertAttendance(memberNo, studyNo) {
    $.ajax({
        type: 'GET',
        url: '/attendance/check',
        contentType: 'application/text; charset=UTF-8',
        // 인자
        data: {
            'memberNo': memberNo,
            'studyNo' : studyNo
            // , 구분자로 추가
        },
        dataType: "text",
        success: function(result) {
            if (result == 1) { // 출석체크 성공
                alert('출석이 확인되었습니다.');
            } else if (result == 2) { // 이미 오늘의 출석체크가 됨
                alert('이미 오늘의 출석을 확인하였습니다.');
            }
        },
        error: function(result) {
            alert('출석 확인에 실패하였습니다.');
        },
        complete: function(result) {
            // alert(result);
        }
    });
}


// 과제 현황
$.ajax({
    type: 'GET',
    url: '/study/assignment-chart',
    dataType: "json",
    success: function(data) {
		labelList = data.labelList;
		colorList = data.colorList;
		countList = data.countList;

		let pieChartData = {
		    labels: labelList,
		    datasets: [{
		        data: countList,
		        backgroundColor: colorList
		    }] 
		};
		
		var ctx = document.getElementById("group-chart").getContext('2d');
		var myChart = new Chart(ctx, {
			type: 'doughnut',
			data: pieChartData,
		});
    },
    error: function(result) {
    	console.log("전송 실패ㅜ");
    }
});