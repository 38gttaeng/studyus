var check = "";
var leaderNo = $("#leaderNo").val();
var userNo = $("#userNo").val();
if(leaderNo == userNo) {
	check = "l"
} else {
	check = "m"
}

/****************************************
 *        관리자 회원관리  목록           *
 ****************************************/
var table = $('#mList').DataTable({
	destroy: true,
    bPaginate: true,
    bLengthChange: true,

	// 각 상황별 멘트
	language: {
	    emptyTable: '회원정보가 없습니다.',
	    infoEmpty: '회원정보가 없습니다.',
	    info: ' _TOTAL_ 개의 게시물이 있습니다.',
		infoFiltered: "( _MAX_건의 데이터에서 필터링됨 )",
		zeroRecords: "일치하는 회원정보가 없습니다.",
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
	order: [[1, 'desc']],
	
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
		'className': 'dt-category',
	}, {
		'targets': 4,
		'className': 'dt-date',
	}, {
		'targets': 5,
		'className': 'dt-date',
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
	    'url':'/admin/member/list', 
	    'type': 'GET',
		'data' : {'mbNo' : mbNo},
	    'dataSrc':''
 	},
		// 컬럼별로 들어갈 데이터 정보를 저장
    columns: [
		{"data": "mbNo"},
        {"data": "mbNo"},
        {
		"data": "mbAdCategory",
			"render": function(data, type, row, meta){
				switch(data) {
				case 1: data = '자유';
					break;
				case 2: data = '공유';
					break;
				case 2: data = '질문';
					break;
				}
	            
	            return data;
			}
		},
		{ 
         "data": "boTitle",
	         "render": function(data, type, row, meta){
	            if(type === 'display'){
	                data = '<a href="/study/board/detail?boNo=' + row["boNo"] + '">' + data + '</a>';
	            }
	            
	            return data;
			}
        },
        {"data": "member.mbNickname"},
		{"data": "boInsertDate"}
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