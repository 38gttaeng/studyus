$(function() {

	/* 과제 */
	var asList = getAsList();
	function getAsList() {
		var result = "";
		$.ajax ({
			url : "/member/calendar/assignment",
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
	
	/* 구글 캘린더 : 한국 공휴일 */
	var google = 
	{
		googleCalendarId : "ko.south_korea#holiday@group.v.calendar.google.com",
		className : "koHolidays",
		color: "#ffffff"
    };
	

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
        eventSources: [asList, google],
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
});