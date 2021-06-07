var duration = 1;

var caTime = $("input[name=caTime]").val();
var start = caTime.substring(0,2)*1;
var end = caTime.substring(6,8)*1;

! function($) {
    "use strict";

	$("select[name=duration-select]").on("change", function() {
		switch($(this).val()) {
		case "1": duration = 1;
			break;
		case "2": duration = 2;
			break;
		case "3": duration = 3;
			break;
		case "4": duration = 4;
			break;
		case "5": duration = 5;
			break;
		}
	});

    var CalendarApp = function() {
        this.$body = $("body"),
        this.$calendar = $('#calendar'),
        this.$event = ('#calendar-events div.calendar-events'),
        this.$extEvents = $('#calendar-events'),
        this.$modal = $('#my-event'),
        this.$calendarObj = null
    };

    /* 캘린더 초기화 */
    CalendarApp.prototype.init = function() {
            var date = new Date();
            var d = date.getDate();
            var m = date.getMonth();
            var y = date.getFullYear();
            var form = '';
            var today = new Date($.now());
			
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
				contentHeight: 'auto',
				allDaySlot: false,
                header: {
                    left: 'prev,next',
                    center: 'title',
                    right: 'today'
                },
                //events: defaultEvents,
                editable: false,
                droppable: false, // this allows things to be dropped onto the calendar !!!
                eventLimit: false, // allow "more" link when too many events
                selectable: false,
                // select: function(start, end, allDay) { $this.onSelect(start, end, allDay); },
                dayClick: function(date) {
					// 오늘 날짜 이전 선택 막기
					var check = moment(date).format("YYYY-MM-DD");
				    var today = moment(new Date()).format("YYYY-MM-DD");
					if(check < today) {
						// 오늘 이전
				        alert("오늘 이후의 날짜를 선택해주세요.");
						$("#time-select").attr("disabled", true);
						$("#time-select").html("<option>날짜를 선택해주세요.</option>");
				    } else {
						// 오늘 이후 (선택된 날짜에 표시)
						$(".fc-day").removeClass("clicked");
						$(this).addClass("clicked");
						
						// 영업시간 가져와서 선택 가능한 시간 보여주기
						$("#time-select").html("");
						$("#time-select").attr("disabled", false);
						for(var i=start; i<end; i++) {
						
							$("#time-select").append("<option value='" + i + "'>" + i + ":00 ~ " + (i+1) + ":00</option>");
						}
						
						// date별로 select 정보 보여줘야(ajax로 예약정보 가져오기)
	
						// DB에 저장된 시작 시간과 지금 예약한 시작 시간이 같을 경우
				    }
				}
            });
            
        },

        //init CalendarApp
        $.CalendarApp = new CalendarApp, $.CalendarApp.Constructor = CalendarApp
		
}(window.jQuery),

//initializing CalendarApp
$(window).on('load', function() {

    $.CalendarApp.init();
	
	$("#time-select").on("change", function() {
		console.log($(this).val());
	});
});
