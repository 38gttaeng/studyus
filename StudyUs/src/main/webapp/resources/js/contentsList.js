/*************************************************************************************/
// -->Template Name: Bootstrap Press Admin
// -->Author: Themedesigner
// -->Email: niravjoshi87@gmail.com
// -->File: datatable_basic_init
/*************************************************************************************/

/****************************************
 *        게시판 게시물 목록           *
 ****************************************/
var table = $('#board-list').DataTable({
	destroy: true,
    bPaginate: true,
    bLengthChange: true,

	// 각 상황별 멘트
	language: {
	    emptyTable: '데이터가 없습니다.',
	    infoEmpty: '데이터가 없습니다.',
	    info: ' _TOTAL_ 개의 게시물이 있습니다.',
	    search: '<span>검색 :</span> _INPUT_',
	    searchPlaceholder: '검색어 입력',
	    lengthMenu: '보기 _MENU_',
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
	stateSave: true,
	
	// 컬럼에 값이 null인 경우 처리
	columnDefs: [{
		'targets': 0,
		'searchable': false,
		'orderable': false,
		'className': 'dt-body-center',
		'render': function (data, type, full, meta){
		    return '<input type="checkbox" value="' + $('<div/>').text(data).html() + '">';
		}
    }, {
		'targets': 1,
		'searchable': false,
		'className': 'dt-body-center',
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
	    'url':'/study/contentsList/board', 
	    'type': 'GET',
	    'dataSrc':''
 	},
		// 컬럼별로 들어갈 데이터 정보를 저장
    columns: [
		{"data": "boNo"},
        {"data": "boNo"},
        {"data": "boContents"},
        {"data": "boTitle"
		 "render": function(data, type, row, meta) {
       			data = "<a href='/study/board/detail?boNo=" + data.boNo + "'>" + data.boTitle + "</a>";
     			return data;
		},
        {"data": "member.mbNickname"}
    ]
});

$("#select-all").on("change", function(){
    var delcheck = $("input[type=checkbox]"); 
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
		$("input[type=checkbox]:checked").each(function(){
			console.log($(this).val());//////////////////
			arr.push($(this).val());
			console.log(arr);/////////////////////
		});
		
		$.ajax({
			url : "/study/contentsList/delete",
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

/*$('#assignment-list').DataTable({
    "order": [
        [3, "desc"]
    ]
});*/

