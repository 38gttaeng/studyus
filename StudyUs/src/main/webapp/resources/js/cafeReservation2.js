var duration = 1;

var caTime = $("input[name=caTime]").val();
var start = caTime.substring(0,2)*1;
var end = caTime.substring(6,8)*1;
var clicked = "";

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
						
						// 날짜 정보 저장
						clicked = date.format();
						
						// 날짜별로 선택 가능한 시간만 표시
							// ajax로 날짜별 예약된 시간 array 가져오기
						var crNo = $("input[name=crNo]").val();
						$.ajax ({
							url : "/cafe/reservation-check",
							type : "get",
							data : {"crNo" : crNo , "rsDate" : clicked },
							dataType : "json",
							success : function(a) {
								// 영업시간에 해당하는 array 만들기
								var b = new Array();
								
								$("#time-select").html("");
								$("#time-select").attr("disabled", false);
								
								for(var i=start; i<end; i++) {
									b.push(i);
								}
								
								// 배열 두개 비교해서 다른 값만 저장하고 select option으로 넣어주기
								//var optionList = new Array();
								var sum = a.concat(b); // 배열 합치기
								var union = sum.filter((item, index) => sum.indexOf(item) === index); // 합집합
								var intersec = sum.filter((item, index) => sum.indexOf(item) !== index); // 교집합
								var optionList = union.filter(x => !intersec.includes(x)); // 차집합
								
								for(var i in optionList) {
									$("#time-select").append("<option value='" + optionList[i] + "'>" + optionList[i] + ":00 ~ " + (optionList[i]+1) + ":00</option>");
								}
							},
							error : function() {
								alert("전송 실패..");
							}
						});
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
	
	// 예약정보에 선택한 스터디정보 넣어주기
	var studyCheck = $("input[name=study-check]").val();
	var study = "";
	if(studyCheck == 1) {
		study = $("select[name=studyNo] option:selected").text();
		$("#reserv-name").html(study);
	}
	$("#study-select").on("change", function() {
		study = $("#study-select option:selected").text();
		$("#reserv-name").html(study);
	});
	
	// 시간 선택값 변하면
	$("#time-select").on("change", function() {
		var arr = $(this).val();
		var reservDate = "";
		var duration = 0;
	
		// 예약정보에 선택한 날짜정보 넣어주기
		if(arr.length > 1) {
			reservDate = clicked + " / " + arr[0] + ":00 ~ " + (arr[arr.length-1]*1+1) + ":00";
			duration = (arr[arr.length-1] * 1 + 1) - (arr[0] * 1);
		} else {
			reservDate = clicked + " / " + arr[0] + ":00 ~ " + (arr[0]*1+1) + ":00";
			duration = 1;
		}
		$("#reserv-date").html(reservDate);
		
		var price = $("input[name=study-price]").val();
		price = price * duration;
		$("#reserv-price").html(price + "원 / " + duration + "시간");
	});
	
	$("#reservation-btn").on("click", function() {
		var arr = $("#time-select").val();
		var rsStart = 0;
		var rsEnd = 0;
		
		if(arr.length > 1) {
			rsStart = arr[0] * 1;
			rsEnd = ( arr[arr.length-1] * 1 + 1 );
		} else {
			rsStart = arr[0] * 1;
			rsEnd = (arr[0] * 1 + 1);
		}
		
		// input[type=hidden]에 값 넘겨주기
			// 시간
		$("input[name=startStr]").val(rsStart);
		$("input[name=endStr]").val(rsEnd);
			// 날짜
		$("input[name=rsDate]").val(clicked);
		
		// form 태그 전송
		$("#reservation-form").submit();
	});
	
});

