$(function() {

	// 색 지정
	var colorList = new Array();
	var cafeCount = $(".cafe-name").length;
	for(var i=0; i<cafeCount; i++) {
		color = colorPicker();
		$("#" + i).attr("data-color", color);
		colorList.push(color);
	}
	function colorPicker() {
		var colors = ["#6A5ACD","#7B6ECC","#8C83CC","#9E97CC","#D8BFD8","#D9C5D9","#8A2BE2","#BB91E3"];
		var colorCode = colors[Math.floor(Math.random() * 8)];
		return colorCode;
	}
	
	/* 전체 리스트 가져오기 */
	var eventsList = getList();
	function getList() {
		var result = "";
		$.ajax({
		    method: 'get',
		    url: '/admin/reservation-list',
			dataType : "json",
			async: false,  // ajax가 서버에서 응답을 기다렸다가 응답한 후 실행하도록
		    success: function (list) {
				result = list;
				console.log(list);
			},
			error : function(){
		    	console.log("데이터 업뜸");
		    }
		});
		return result;
	}
	
	/* 구글 캘린더 : 한국 공휴일 */
	var google = 
	{
		googleCalendarId : "ko.south_korea#holiday@group.v.calendar.google.com",
		className : "koHolidays",
		color: "#ffffff"
    };
	
	// 캘린더 생성
	$('#calendar').fullCalendar({
	    minTime: '00:00:00',
		maxTime: '24:00:00',
		defaultView: 'month',
        // handleWindowResize: true,
		contentHeight: 'auto',
		allDaySlot: false,

        header: {
            left: 'prev,next today',
            center: 'title',
            right: 'month, agendaWeek, listWeek'
        },
		googleCalendarApiKey : "AIzaSyDcnW6WejpTOCffshGDDb4neIrXVUA1EAE",      // Google API KEY (대한민국 공휴일)
        eventSources: getEventSources(),
        editable: false,
        droppable: false, // this allows things to be dropped onto the calendar !!!
        eventLimit: false, // allow "more" link when too many events
        selectable: false,
        // drop: function(date) { $this.onDrop($(this), date); },
        // select: function(start, end, allDay) { $this.onSelect(start, end, allDay); },
        eventClick: function(event) { 
		// 이벤트 클릭시
		
			// modal 창 안에 데이터 넣기
			$("#infoLabel").text(event.cafeName + " / "  + event.title);
			
			$("#no").val(event.rsNo);
			
			$("#member").val(event.mbName);
			
			var date = moment(event.start).format("YYYY-MM-DD");
			$("#date").val(date);
			
			var start = moment(event.start).format("HH:mm");
			$("#start").val(start);
			var end = moment(event.end).format("HH:mm");
			$("#end").val(end);
			
			$("#price").val(event.price + "원");
			
			$("#del-btn").on("click", function() {
				result = confirm("정말 삭제하시겠습니까?");
				if(result) {
					message = prompt("취소 사유를 입력해주세요.", "");
					location.href="/study/reservation/delete?rsNo=" + event.rsNo + "&rsAlert=" + message;
				}
			});
			
			// modal 띄우기
			$("#info").modal("show");
		}
	});
	
	/* 카페별로 구분해서 보기 */
	$(".cafe-name").on("change", function() {
	
		// 기존 데이터 모두 삭제
		$('#calendar').fullCalendar('removeEvents');
		
		// 현재 선택된 소스 가져오기 
	    var sources = getEventSources();
	    
	    // 현재 선택된 소스 추가하기
		for(var i in sources) {
			$("#calendar").fullCalendar('addEventSource', sources[i]);
		}

		// 공휴일 추가하기
		$("#calendar").fullCalendar('addEventSource', google);
	});
	
	function getEventSources() {
    	var sources = [];
    	$(".cafe-name:checked").each(function() {
			sources.push({
				cafeName : $(this).val(),
				color: $(this).data("color"),
				events: eventsList[$(this).attr("id")]
			});
    	});
    	return sources;
	}
	
	
	
	
	// 왼쪽 차트 : 카페 일주일별 예약수
	$.ajax({
	    type: 'GET',
	    url: '/admin/reservation/week-chart',
	    dataType: "json",
	    success: function(list) {
			let chartData = {
			    labels: ["월", "화", "수", "목", "금", "토", "일"],
			    datasets: list
			};
			
			var ctx = document.getElementById("week-chart").getContext('2d');
			new Chart(ctx, {
				type: 'line',
				data: chartData,
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
	
	// 오른쪽 차트 : 카페별 한달동안 예약수
	$.ajax({
	    type: 'GET',
	    url: '/admin/reservation/month-chart',
	    dataType: "json",
	    success: function(data) {
			labelList = data.labelList;
			countList = data.countList;
			
			let pieChartData = {
			    labels: labelList,
			    datasets: [{
					label: "예약수 (한달 기준)",
					backgroundColor: colorList,
			        data: countList
			    }] 
			};
			
			new Chart(document.getElementById("month-chart"), {
				type: 'bar',
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
	
});