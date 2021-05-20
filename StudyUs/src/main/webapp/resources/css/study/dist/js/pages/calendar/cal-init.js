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


	// 클릭 이벤트 (팝업만 띄우면 성공이닷)
	CalendarApp.prototype.onEventClick = function(calEvent) {
		console.log(calEvent);
		console.log(calEvent.category);
		console.log(calEvent.caMotherNo);
		console.log(calEvent.title);
		console.log(calEvent.end.format('YYYY-MM-DD HH:mm'));
		console.log(calEvent.end.format('YYYY-MM-DD HH:mm'));
		
		var memberStr = calEvent.member;
		if(memberStr != null) {
			var memberArr = memberStr.split(',');
			for(var i=0; i<memberArr.length; i++) {
				console.log(memberArr[i]);
			}
		}
    }

    /* Initializing */
    CalendarApp.prototype.init = function() {
            /*  Initialize the calendar  */
            var date = new Date();
            var d = date.getDate();
            var m = date.getMonth();
            var y = date.getFullYear();
            var form = '';
            var today = new Date($.now());
			
			// 이미 있는 정보들 json 형식으로 받아오기 (링크 참고)
            var defaultEvents = [{
					id : 1,
					category : 1,
					caMotherNo : 1,
                    title: 'Meeting #3',
					member : '지수,은지,나은',
                    start: new Date($.now() - 399000000),
                    end: new Date($.now() - 219000000),
                    className: 'bg-info'
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
                    start: '2021-05-12T10:30:00',
					end : '2021-05-12T15:30:00',
                    className: 'bg-info'
				}
            ];

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
                events: defaultEvents,
                editable: false,
                droppable: false, // this allows things to be dropped onto the calendar !!!
                eventLimit: false, // allow "more" link when too many events
                selectable: false,
                // drop: function(date) { $this.onDrop($(this), date); },
                // select: function(start, end, allDay) { $this.onSelect(start, end, allDay); },
                eventClick: function(calEvent) { $this.onEventClick(calEvent); }
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