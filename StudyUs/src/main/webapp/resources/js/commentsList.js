var check = "";
var leaderNo = $("#leaderNo").val();
var userNo = $("#userNo").val();
if(leaderNo == userNo) {
	check = "l"
} else {
	check = "m"
}

/****************************************
 *        게시판 댓글 목록           *
 ****************************************/
var table = $('#board-list').DataTable({
	destroy: true,
    bPaginate: true,
    bLengthChange: true,

	// 각 상황별 멘트
	language: {
	    emptyTable: '댓글이 없습니다.',
	    infoEmpty: '댓글이 없습니다.',
	    info: ' _TOTAL_ 개의 댓글이 있습니다.',
		infoFiltered: "( _MAX_건의 데이터에서 필터링됨 )",
		zeroRecords: "일치하는 댓글이 없습니다.",
	    search: "&nbsp;에서 검색: ",
	    searchPlaceholder: '검색어 입력',
	    lengthMenu: '보기 _MENU_',
		processing: "처리중...",
	    paginate: {
	      first: 'First',
	      last: 'Last',
	      next: $('html').attr('dir') == 'rtl' ? '&raquo;' : '&raquo;',
	      previous: $('html').attr('dir') == 'rtl' ? '&laquo;' : '&laquo;',
	    }
 	},

	// 검색기능 활성화
	searching: true,
	
	// 최초 정렬 기준 : no
	order: [[5, 'desc']],
	
	// 여러개 보기 옵션
	lengthMenu: [ 5, 10, 25, 50 ],
	
	// 보기 옵션 선택정보 저장여부
	// stateSave: true,
	
	// 컬럼에 값이 null인 경우 처리
	columnDefs: [{
		'targets': 0,
		'searchable': false,
		'orderable': false,
		'className': 'dt-body-center',
		'render': function (data, type, full, meta){
		    return '<input type="checkbox" name="board" value="' + $('<div/>').text(data).html() + '">';
		}
    }, {
		'targets': 1,
		'className': 'dt-body-center',
	}, {
		'targets': 2,
		'className': 'dt-reply',
	}, {
		'targets': 3,
		'className': 'dt-date',
	}, {
		'targets': 4,
		'className': 'dt-date',
	}, {
		'targets': 5,
		'visible': false,
	}, {
        defaultContent: "-",
        targets: "_all"
    }],
	
	// 선택여부 : 다중선택 가능
	select: {
    	style:    'os',
        selector: 'td:first-child'
    },
	
	// 서버에서 데이터 가져오기
		// bServerSide를  true로 할 경우 pagination 등을 서버단에서 처리해야 함
	processing: true,
	bServerSide: false,
		// 받은 json 값이 data가 아닐 경우엔는 dataSrc로 이름 변경
    ajax: {
	    'url':'/study/commentsList/board', 
	    'type': 'GET',
		'data' : {'user' : check},
	    'dataSrc':''
 	},
		// 컬럼별로 들어갈 데이터 정보를 저장
    columns: [
		{"data": "boNo"},
        {
			"data": "boMotherNo",
			"render": function(data, type, row, meta){
	            if(type === 'display'){
	                data = '<a href="/study/board/detail?boNo=' + data + '">' + data + '</a>';
	            }
	            return data;
			}
		},
		{ 
         	"data": "boContents",
			"render": function(data, type, row, meta){
	            var text = "<span>" + data.replace(/[<][^>]*[>]/gi, "") + "</span>";
	            return text;
			}
        },
        {"data": "member.mbNickname"},
		{"data": "boInsertDate"},
		{"data": "boNo"}
    ]
});

// 컬럼별 검색기능 추가
$("#board-list_filter").prepend('<select id="board-list-select" class="select"></select>');
$('#board-list > thead > tr').children().each(function (indexInArray, valueOfElement) { 
    $('#board-list-select').append('<option value="' + indexInArray + '">'+ valueOfElement.innerHTML +'</option>');
});
$('#board-list .dataTables_filter input').unbind().bind('keyup', function () {
    var colIndex = $('#board-list-select').val();
    table.column(colIndex).search(this.value).draw();
});

$("#board-select-all").on("change", function(){
    var delcheck = $("input[name=board]");
    if($(this).is(":checked")) {
        delcheck.prop("checked", true);
    }else {
        delcheck.prop("checked", false);
    }
});

$("#board-btn").on("click", function() {
	var result = confirm('정말 삭제하시겠습니까?');
	
	if(result) {
		var arr = new Array();
		$("input[name=board]:checked").each(function(){
			arr.push($(this).val());
		});
		
		$.ajax({
			url : "/study/contentsList/delete-board",
			type : "get",
			data : { "deList" : arr },
			traditional : true,
			success : function(data) {
				if(data == "success") {
					console.log("게시물 삭제 성공");
					table.ajax.reload();
				} else {
					console.log("게시물 삭제 실패");
				}
			},
			error : function() {
				console.log("전송 실패..");
			}
		});
	}
});


/****************************************
 *           과제 게시물 목록           *
 ****************************************/
var table2 = $('#assignment-list').DataTable({
	destroy: true,
    bPaginate: true,
    bLengthChange: true,

	// 각 상황별 멘트
	language: {
	    emptyTable: '댓글이 없습니다.',
	    infoEmpty: '댓글이 없습니다.',
	    info: ' _TOTAL_ 개의 댓글이 있습니다.',
		infoFiltered: "( _MAX_건의 데이터에서 필터링됨 )",
		zeroRecords: "일치하는 댓글이 없습니다.",
	    search: "&nbsp;에서 검색: ",
	    searchPlaceholder: '검색어 입력',
	    lengthMenu: '보기 _MENU_',
		processing: "처리중...",
	    paginate: {
	      first: 'First',
	      last: 'Last',
	      next: $('html').attr('dir') == 'rtl' ? '&raquo;' : '&raquo;',
	      previous: $('html').attr('dir') == 'rtl' ? '&laquo;' : '&laquo;',
	    }
 	},

	// 검색기능 활성화
	searching: true,
	
	// 최초 정렬 기준 : no
	order: [[6, 'desc']],
	
	// 여러개 보기 옵션
	lengthMenu: [ 5, 10, 25, 50 ],
	
	// 보기 옵션 선택정보 저장여부
	// stateSave: true,
	
	// 컬럼에 값이 null인 경우 처리
	columnDefs: [{
		'targets': 0,
		'searchable': false,
		'orderable': false,
		'className': 'dt-body-center',
		'render': function (data, type, full, meta){
		    return '<input type="checkbox" name="assignment" value="' + $('<div/>').text(data).html() + '">';
		}
    }, {
		'targets': 1,
		'className': 'dt-category',
	}, {
		'targets': 2,
		'className': 'dt-title',
	}, {
		'targets': 3,
		'className': 'dt-contents',
	}, {
		'targets': 4,
		'className': 'dt-date',
	}, {
		'targets': 5,
		'className': 'dt-date',
	}, {
		'targets': 6,
		'visible': false,
	}, {
        defaultContent: "-",
        targets: "_all"
    }],
	
	// 선택여부 : 다중선택 가능
	select: {
    	style:    'os',
        selector: 'td:first-child'
    },
	
	// 서버에서 데이터 가져오기
		// bServerSide를  true로 할 경우 pagination 등을 서버단에서 처리해야 함
	processing: true,
	bServerSide: false,
		// 받은 json 값이 data가 아닐 경우엔는 dataSrc로 이름 변경
    ajax: {
	    'url':'/study/commentsList/assignment', 
	    'type': 'GET',
		'data' : {'user' : check},
	    'dataSrc':''
 	},
		// 컬럼별로 들어갈 데이터 정보를 저장
    columns: [
		{"data": "SU_NO"},
        {"data": "GR_NAME"},
		{ 
         "data": "AS_NAME",
	         "render": function(data, type, row, meta){
	            if(type === 'display'){
	                data = '<a href="/study/sAssignment/detail?suNo=' + row["SU_MOTHER_NO"] + '">' + data + '</a>';
	            }
	            return data;
			}
        },
		{
		"data": "SU_CONTENTS",
		 	"render": function(data, type, row, meta){
	            var text = "<span>" + data.replace(/[<][^>]*[>]/gi, "") + "</span>";
	            return text;
			}
		},
        {"data": "MB_NICKNAME"},
		{"data": "SU_INSERTDATE"},
		{"data": "SU_NO"}
    ]
});

// 컬럼별 검색기능 추가
$("#assignment-list_filter").prepend('<select id="assignment-list-select" class="select"></select>');
$('#assignment-list > thead > tr').children().each(function (indexInArray, valueOfElement) { 
    $('#assignment-list-select').append('<option value="' + indexInArray + '">'+ valueOfElement.innerHTML +'</option>');
});
$('#assignment-list .dataTables_filter input').unbind().bind('keyup', function () {
    var colIndex = $('#assignment-list-select').val();
    table.column(colIndex).search(this.value).draw();
});

$("#assignment-select-all").on("change", function(){
    var delcheck = $("input[name=assignment]");
    if($(this).is(":checked")) {
        delcheck.prop("checked", true);
    }else {
        delcheck.prop("checked", false);
    }
});

$("#assignment-btn").on("click", function() {
	var result = confirm('정말 삭제하시겠습니까?');
	
	if(result) {
		var arr = new Array();
		$("input[name=assignment]:checked").each(function(){
			arr.push($(this).val());
		});
		
		$.ajax({
			url : "/study/contentsList/delete-assignment",
			type : "get",
			data : { "deList" : arr },
			traditional : true,
			success : function(data) {
				if(data == "success") {
					console.log("게시물 삭제 성공");
					table2.ajax.reload();
				} else {
					console.log("게시물 삭제 실패");
				}
			},
			error : function() {
				console.log("전송 실패..");
			}
		});
	}
});

