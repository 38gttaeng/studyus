<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>StudyUs : 공지사항</title>
<!-- 타이틀을 개별 스터디룸 이름으로 해줘도 좋을듯 ! 'StudyUs : 삼팔광땡' 이러케 -->
<style>
.paginate_button {
	padding: 0 !important;
	border: 0 !important;
	outline: 0 !important;
}
</style> 
<!-- 추가 css -->
<link
	href="/resources/css/study/assets/extra-libs/datatables.net-bs4/css/dataTables.bootstrap4.css"
	rel="stylesheet">
<link href="/resources/css/studyus/manage.css" rel="stylesheet">
</head>
<body>
	<!-- ============================================================== -->
	<!-- Main wrapper -->
	<!-- ============================================================== -->
	<div id="main-wrapper" data-theme="light" data-layout="vertical"
		data-navbarbg="skin6" data-sidebartype="full"
		data-sidebar-position="fixed" data-header-position="fixed"
		data-boxed-layout="full">

		<!-- menubar -->
		<jsp:include page="../common/studyMenubar.jsp" />

		<!-- ============================================================== -->
		<!-- Page wrapper  -->
		<!-- ============================================================== -->
		<div class="page-wrapper">
			<!-- ============================================================== -->
			<!-- Bread crumb and right sidebar toggle -->
			<!-- ============================================================== -->
			<div class="page-breadcrumb">
				<div class="row">
					<div class="col-lg-4 align-self-center">
						<h4
							class="page-title text-truncate text-dark font-weight-medium mb-1">출석확인</h4>
						<div class="d-flex align-items-center">
							<nav aria-label="breadcrumb">
								<ol class="breadcrumb m-0 p-0">
									<li class="breadcrumb-item text-muted" aria-current="page">
										<a href="/study">Study</a>
									</li>
									<li class="breadcrumb-item text-muted active"
										aria-current="page">Attendance</li>
									<li class="breadcrumb-item text-primary font-weight-bold"
										aria-current="page">All</li>
								</ol>
							</nav>
						</div>
					</div>
					<div class="col-lg-8 align-self-center"></div>
				</div>
			</div>

			<!-- ============================================================== -->
			<!-- Container fluid  -->
			<!-- ============================================================== -->
			<div class="container-fluid">
				<!-- ============================================================== -->
				<!-- Start Page Content -->
				<!-- ============================================================== -->
				<!-- basic table -->
				<div class="row">
					<div class="col-12">
						<div class="card">
							<div class="card-body">
								<div class="row" style="margin-top: 1%;">
									<div class="col-md-1">
										<h5 class="card-title">출석률</h5>
									</div>
									<div class="col-md-10">
										<div class="progress mb-2">
											<div class="progress-bar bg-info" role="progressbar" style="width: ${studyAttendanceRate}%" aria-valuenow="${studyAttendanceRate}" aria-valuemin="0" aria-valuemax="100"></div>
										</div>
									</div>
									<span id="attendance-help" class="pr-4"
										style="margin-left: 2%;">${studyAttendanceRate }%</span>
								</div>
							</div>
						</div>
					</div>

					<br> <br>
					<!-- <div>
						<select  id="YEAR" class="custom-select custom-select-lg mb-3" name="year">
						</select>
					</div>
					<div>
						<select  id=MONTH class="custom-select custom-select-lg mb-3" name="month">
						</select>
					</div> -->
					<div class="card col-md-12">
						<div class="card-body">
					<div class="table-responsive">
						<table id="userList" class="table table-striped table-bordered no-wrap dataTable"
							style="width: 100%">
							<thead>
								<tr>
									<th class="text-center"><input type="checkbox"
										id="user-select-all"></th>
									<th>날짜</th>
									<th>닉네임</th>
								</tr>
							</thead>
						</table>
					</div>
					</div>
					</div> 
				</div>
			</div>
			<!-- footer -->
			<jsp:include page="../common/studyFooter.jsp" />
		</div>
	</div>
	<script type="text/javascript"
		src="https://cdn.datatables.net/1.10.23/js/jquery.dataTables.min.js"></script>
	<script
		src="/resources/css/study/assets/extra-libs/datatables.net/js/jquery.dataTables.min.js"></script>
	<script>
		// $('#userList').DataTable();

		var table = $("#userList")
				.DataTable(
						{
							destroy : true,
							bPaginate : true,
							bLengthChange : true,
							// 각 상황별 멘트
							language : {
								emptyTable : '게시물이 없습니다.',
								infoEmpty : '게시물이 없습니다.',
								info : ' _TOTAL_ 개의 게시물이 있습니다.',
								infoFiltered : "( _MAX_건의 데이터에서 필터링됨 )",
								zeroRecords : "일치하는 게시물이 없습니다.",
								search : "&nbsp;에서 검색: ",
								searchPlaceholder : '검색어 입력',
								lengthMenu : '보기 _MENU_',
								processing : "처리중...",
								paginate : {
									first : 'First',
									last : 'Last',
									next : $('html').attr('dir') == 'rtl' ? '&raquo;'
											: '&raquo;',
									previous : $('html').attr('dir') == 'rtl' ? '&laquo;'
											: '&laquo;',
								}
							},

							// 검색기능 활성화
							searching : true,

							// 최초 정렬 기준 : no
							order : [ [ 1, 'desc' ] ],

							// 여러개 보기 옵션
							lengthMenu : [ 10, 25, 50 ],

							// 보기 옵션 선택정보 저장여부
							// stateSave: true,

							// 컬럼에 값이 null인 경우 처리
							columnDefs : [
									{
										'targets' : 0,
										'searchable' : false,
										'orderable' : false,
										'className' : 'dt-body-center',
										'render' : function(data, type, full,
												meta) {
											return '<input type="checkbox" name="board" value="'
													+ $('<div/>').text(data)
															.html() + '">';
										}
									}, {
										'targets' : 1,
										'className' : 'dt-date',
									}, {
										'targets' : 2,
										'className' : 'dt-date',
									}, {
										defaultContent : "-",
										targets : "_all"
									} ],

							// 선택여부 : 다중선택 가능
							select : {
								style : 'os',
								selector : 'td:first-child'
							},

							// 서버에서 데이터 가져오기
							// bServerSide를  true로 할 경우 pagination 등을 서버단에서 처리해야 함
							processing : true,
							bServerSide : false,
							// 받은 json 값이 data가 아닐 경우엔는 dataSrc로 이름 변경
							ajax : {
								'url' : '/attendance/attList',
								'type' : 'GET',
								'data' : '',
								'dataSrc' : ''
							},
							// 컬럼별로 들어갈 데이터 정보를 저장
							columns : [ {
								"data" : "mbNo"
							}, {
								"data" : "atInsertDate"
							}, {
								"data" : "member.mbNickname"
							} ]
						});

		// 컬럼별 검색기능 추가
		$("#userList_filter").prepend(
				'<select id="userList-select" class="select"></select>');
		$('#userList > thead > tr').children().each(
				function(indexInArray, valueOfElement) {
					$('#userList-select').append(
							'<option value="' + indexInArray + '">'
									+ valueOfElement.innerHTML + '</option>');
				});
		$('#userList .dataTables_filter input').unbind().bind('keyup',
				function() {
					var colIndex = $('#userList-select').val();
					table.column(colIndex).search(this.value).draw();
				});

		$("#user-select-all").on("change", function() {
			var delcheck = $("input[name=board]");
			if ($(this).is(":checked")) {
				delcheck.prop("checked", true);
			} else {
				delcheck.prop("checked", false);
			}
		});

	</script>
</body>
</html>