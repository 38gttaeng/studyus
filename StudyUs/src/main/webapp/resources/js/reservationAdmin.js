$(function() {
	
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
        eventSources: [google],
        editable: false,
        droppable: false, // this allows things to be dropped onto the calendar !!!
        eventLimit: false, // allow "more" link when too many events
        selectable: false,
        // drop: function(date) { $this.onDrop($(this), date); },
        // select: function(start, end, allDay) { $this.onSelect(start, end, allDay); },
        eventClick: function(event) { 
		// 이벤트 클릭시
		
			// modal 창 안에 데이터 넣기
			/*$("#infoLabel").text(event.category);					
			
			$("#name").val(event.title);
			
			var inDate = moment(event.start).format("YYYY-MM-DD");
			$("#inDate").val(inDate);
			var inTime = moment(event.start).format("HH:mm");
			$("#inTime").val(inTime);
			
			var deDate = moment(event.end).format("YYYY-MM-DD");
			$("#deDate").val(deDate);
			var deTime = moment(event.end).format("HH:mm");
			$("#deTime").val(deTime);
			
			$("#info-btn").on("click", function() {
				location.href = event.urlStr;
			});
			
			// modal 띄우기
			$("#modal-header").removeClass();
			$("#modal-header").addClass("modal-header");
			$("#modal-header").addClass(event.className);
			$("#info").modal("show");*/
		}
	});
	
	// 구분해서 보기
	$(".calendar-events").on("click", function() {
		var calendar = $('#calendar').fullCalendar('getCalendar');
		
		// 기존 이벤트 리스트 삭제
		$('#calendar').fullCalendar('removeEvents');
		
		// 각각 클릭시 이벤트 추가 + 색상 변경
		$(".calendar-events td:first-child").css("background-color", "#e8eaec");
		if($(this).hasClass("cEvents0")) {
			$(".cEvents0 td").css("background-color", "#6c757d");
			$("#checkHide").css("display", "none");
			$("#calendar").fullCalendar('addEventSource', asList);
			$("#calendar").fullCalendar('addEventSource', rsList1);
		} else if($(this).hasClass("cEvents1")) {
			$(".cEvents1 td:first-child").css("background-color", "#6c757d");
			$("#checkHide").css("display", "none");
			$("#calendar").fullCalendar('addEventSource', asList);
		} else if($(this).hasClass("cEvents2")) {
			$(".cEvents2 td:first-child").css("background-color", "#6927ff");
			$("#checkHide").css("display", "block");
			$("#calendar").fullCalendar('addEventSource', rsList0);
			$("#calendar").fullCalendar('addEventSource', rsList1);
		}
		
		$("#calendar").fullCalendar('addEventSource', google);
	});
	
	/* 카페별로 구분해서 보기 */
	$(".cafe-name").on("change", function() {
	
		// 기존 데이터 모두 삭제
		$('#calendar').fullCalendar('removeEvents');
		
		// 현재 선택된 소스 가져오기 
	    var sources = getEventSources();
	    
	    // 현재 선택된 소스 추가하기
	    sources.forEach(eventSource => {
			$("#calendar").fullCalendar('addEventSource', eventSource);
	    });
	});
	
	function getEventSources() {
    	var sources = [];
    	$(".cafe-name:checked").each(function() {
			/*sources.push({
				category : eventsList[$(this).val() - 1]
				className: $(this).data("color"),
				events: eventsList[$(this).val() - 1]
			});*/
			console.log(eventsList[$(this).val()]);
			console.log($(this).val());
     		 //console.log(sources);
    	});
    	return sources;
	}
	
	// 왼쪽 차트
	new Chart(document.getElementById("week-chart"), {
	  type: 'line',
	  data: {
		labels: [4500,3500,3200,3050,2700,2450,2200,1750,1499,2050],
		datasets: [{ 
			data: [86,114,106,106,107,111,133,221,783,2478],
			label: "Africa",
			borderColor: "#5f76e8",
			fill: false
		  }, { 
			data: [282,350,411,502,635,809,947,1402,3700,5267],
			label: "Asia",
			borderColor: "#768bf4",
			fill: false
		  }, { 
			data: [168,170,178,190,203,276,408,547,675,734],
			label: "Europe",
			borderColor: "#7385df",
			fill: false
		  }, { 
			data: [40,20,10,16,24,38,74,167,508,784],
			label: "Latin America",
			borderColor: "#b1bdfa",
			fill: false
		  }, { 
			data: [6,3,2,2,7,26,82,172,312,433],
			label: "North America",
			borderColor: "#8fa0f3", 
			fill: false
		  }
		]
	  },
	  options: {
		layout: {
			padding: 30
		}
	  }
	});
	
	// 오른쪽 차트
	new Chart(document.getElementById("day-chart"), {
		type: 'bar',
		data: {
		  labels: ["Africa", "Asia", "Europe", "Latin America", "North America"],
		  datasets: [
			{
			  label: "Population (millions)",
			  backgroundColor: ["#6174d5", "#5f76e8", "#768bf4", "#7385df", "#b1bdfa"],
			  data: [8478,6267,5734,4784,1833]
			}
		  ]
		},
		options: {
			layout: {
				padding: 30,
				indexAxis: 'y'
			},
		}
	});
});