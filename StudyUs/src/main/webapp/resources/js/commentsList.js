/*************************************************************************************/
// -->Template Name: Bootstrap Press Admin
// -->Author: Themedesigner
// -->Email: niravjoshi87@gmail.com
// -->File: datatable_basic_init
/*************************************************************************************/

/****************************************
 *        게시판 댓글 목록             *
 ****************************************/
$('#board-list').DataTable({
	destroy: true,
    bPaginate: true,
    bLengthChange: true,
	language: {
	    emptyTable: '데이터가 없습니다.',
	    infoEmpty: '',
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
	searching: true,
	order: [],
	lengthMenu: [ 5, 10, 25, 50 ],
	stateSave: true,
    /* ajax: {
	    'url':'MOCK_DATA.json', 
	    'type': 'GET',
	    'dataSrc':''
 	},
    columns: [
        {"data": "id"},
        {"data": "first_name"},
        {"data": "last_name"}, 
        {"data": "email"}
		{"data": "email"}
    ]*/
});

/****************************************
 *           과제 댓글 목록             *
 ****************************************/

$('#assignment-list').DataTable({
    "order": [
        [3, "desc"]
    ]
});
