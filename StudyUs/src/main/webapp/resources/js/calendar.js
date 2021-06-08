! function($) {
    "use strict";

    var CalendarApp = function() {
        this.$body = $("body")
        this.$calendar = $('#calendar'),
        this.$event = ('#calendar-events div.calendar-events'),
        //this.$categoryForm = $('#add-new-event form'),
        this.$extEvents = $('#calendar-events'),
        this.$modal = $('#my-event'),
        //this.$saveCategoryBtn = $('.save-category'),
        this.$calendarObj = null
    };

    /* Initializing */
    CalendarApp.prototype.init = function() {
            /*  Initialize the calendar  */ 
            var date = new Date();
            var d = date.getDate();
            var m = date.getMonth();
            var y = date.getFullYear();
            var form = '';
            var today = new Date($.now());
			
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
			var rsList = getRsList();
			function getRsList() {
				var result = "";
				$.ajax ({
					url : "/study/calendar/reservation",
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
			
            var $this = this;
            $this.$calendarObj = $this.$calendar.fullCalendar({
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
                events: rsList,
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
            
        },

        //init CalendarApp
        $.CalendarApp = new CalendarApp, $.CalendarApp.Constructor = CalendarApp
		
}(window.jQuery),

//initializing CalendarApp
$(window).on('load', function() {

    $.CalendarApp.init()

	// 구분해서 보기 #ff4f70 rgb(196, 178, 234)
	$(".calendar-events").on("click", function() {
		$(".calendar-events td:first-child").css("background-color", "#e8eaec");
		
		if($(this).hasClass("cEvents0")) {
			$(".cEvents0 td").css("background-color", "#6c757d");
		} else if($(this).hasClass("cEvents1")) {
			$(".cEvents1 td:first-child").css("background-color", "#6c757d");
		} else if($(this).hasClass("cEvents2")) {
			$(".cEvents2 td:first-child").css("background-color", "#6927ff");
		}
	});
	
	
});