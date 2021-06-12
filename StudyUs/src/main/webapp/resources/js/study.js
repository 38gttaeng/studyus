var studyNo = document.getElementById("studyNo").value;
var memberNo = document.getElementById("memberNo").value;

// 출석체크 전송
// document.getElementById("attendance-check-button").addEventListener("click", insertAttendance(memberNo, studyNo));
function insertAttendance (memberNo, studyNo) {
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
                location.reload();
            } else if (result == 2) { // 이미 오늘의 출석체크가 됨
                alert('이미 오늘의 출석을 확인하였습니다.');
                location.reload();
            }
        },
        error: function(result) {
            alert('출석 확인에 실패하였습니다.');
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
			options: {
				layout: {
					padding: 30 
				}
			}
		});
    },
    error: function(result) {
    	console.log("전송 실패ㅜ");
    }
});

//TODO 출석률 순위 차트 
$.ajax({
    type: 'GET',
    url: ("/attendance/study/top"),
    data: { 
        "url" : document.getElementById("studyNo").value, 
        "studyNo" : studyNo 
    },
    dataTyle: "json",
    success: function (result) {
        result = JSON.parse(result);
        
        // 총 출석했어야 하는 횟수
        var requiredAttendance = result.requiredAttendance;

        var nicknameList = [];
        var amountList = [];

        for (var i = 0; i < result.memberList.length; i ++) {
            nicknameList.push(result.memberList[i].member.mbNickname);
            amountList.push(result.memberList[i].attendanceAmount / requiredAttendance * 100);
        }

        new Chart(document.getElementById("bar-chart-horizontal"), {
            type: 'bar',
            data: {
            labels: nicknameList,
            datasets: [
                {
                label: "출석률 (최근 30일)",
                backgroundColor: ["#6927ff", "#7538ff", "#8047ff", "#8d59ff", "#9869ff"],
                data: amountList
                }
            ]
            }
            ,
            options: {
                indexAxis: 'y',
            legend: { display: false },
            title: {
                display: true,
                text: 'Predicted world population (millions) in 2050'
            }
            }
        });
    }
});
