$(function() {

	/* 과제 */
	var asList = getAsList();
	function getAsList() {
		var result = "";
		$.ajax ({
			url : "/study/calendar/assignment",
			type : "get",
			dataType : "json",
			async: false,
			success : function(data) {
				result =  data;
				console.log(result); 
			},
			error : function() {
				console.log("데이터 없뜸");
			}
		});
		return result;
	}
	
	/* 모임 */
	
	// 0은 예약 취소된 것, 1은 예약 취소 안된  것
	var rsList0 = getRsList(0);
	var rsList1 = getRsList(1);
	
	function getRsList(check) {
		var result = "";
		$.ajax ({
			url : "/study/calendar/reservation",
			type : "get",
			dataType : "json",
			async: false,
			success : function(data) {
				var rsList = data.caList1;
				var delList = data.caList0;
				
				if(check == 1) {
					result = rsList;
				} else if(check == 0) {
					result = delList;
				}
			},
			error : function() {
				console.log("데이터 없뜸");
			}
		});
		
		return result;
	}

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
        eventSources: [asList, rsList1,
		{
        	googleCalendarId : "ko.south_korea#holiday@group.v.calendar.google.com",
			className : "koHolidays",
			color: "#ffffff"
        }],
        editable: false,
        droppable: false, // this allows things to be dropped onto the calendar !!!
        eventLimit: false, // allow "more" link when too many events
        selectable: false,
        // drop: function(date) { $this.onDrop($(this), date); },
        // select: function(start, end, allDay) { $this.onSelect(start, end, allDay); },
        eventClick: function(event) { 
		// 이벤트 클릭시
		
			// modal 창 안에 데이터 넣기
			$("#infoLabel").text(event.category);					
			
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
			$("#info").modal("show");
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
	});
	
	$("#drop-remove").on("change", function() {
		// 기존 이벤트 리스트 삭제
		$('#calendar').fullCalendar('removeEvents');
	
		if($("#drop-remove").is(":checked") == true){
		    console.log('체크된 상태');
			$("#calendar").fullCalendar('addEventSource', rsList1);
		}
		 
		if($("#drop-remove").is(":checked") == false){
		    console.log('체크 안 된 상태');
			$("#calendar").fullCalendar('addEventSource', rsList1);
			$("#calendar").fullCalendar('addEventSource', rsList0);
		}
	});
});