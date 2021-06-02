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
						alert("전송 실패..");
					}
				});
				return result;
			}
			
            /*var defaultEvents = [{
					id : 1,
					category : 1,
					caMotherNo : 1,
                    title: 'Meeting #3',
					member : '지수,은지,나은',
                    start: new Date($.now() - 399000000),
                    end: new Date($.now() - 219000000),
                    className: 'backHover1'
                }, {
                    title: 'Submission #1',
                    start: today,
                    end: today,
                    className: 'bg-danger'
                }, {
					id : 2,
					category : 1,
					caMotherNo : 30,
                    title: 'Meetup #6',
                    start: '2021/05/08 15:33',
					end : '2021/05/12 15:33',
                    className: 'bg-info'
				}
            ];*/

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
                events: asList,
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

	// 구분해서 보기
	$(".calendar-events").on("click", function() {
		$(".calendar-events td:first-child").css("background-color", "#e8eaec");
		
		if($(this).hasClass("cEvents0")) {
			$(".cEvents0 td").css("background-color", "#6c757d");
		} else if($(this).hasClass("cEvents1")) {
			$(".cEvents1 td:first-child").css("background-color", "#ff4f70");
		} else if($(this).hasClass("cEvents2")) {
			$(".cEvents2 td:first-child").css("background-color", "#fdc16a");
		} else if($(this).hasClass("cEvents3")) {
			$(".cEvents3 td:first-child").css("background-color", "#6927ff");
		}
	});
});