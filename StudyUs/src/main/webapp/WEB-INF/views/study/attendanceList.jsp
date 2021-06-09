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
	.noTitle {
		color: #7C8798;
	}
	
	.noTitle :hover {
		color: #6928FF;
}
</style>
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.23/css/jquery.dataTables.min.css"/>
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
						<h4 class="page-title text-truncate text-dark font-weight-medium mb-1">출석확인</h4>
						<div class="d-flex align-items-center">
							<nav aria-label="breadcrumb">
								<ol class="breadcrumb m-0 p-0">
									<li class="breadcrumb-item text-muted" aria-current="page"> <a href="/study">Study</a></li>
									<li class="breadcrumb-item text-muted active" aria-current="page">Attendance</li>
									<li class="breadcrumb-item text-primary font-weight-bold" aria-current="page">All</li>
								</ol>
							</nav>
						</div>
					</div>
					<div class="col-lg-8 align-self-center">
					</div>
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
								<div class="row"  style="margin-top:1%;">
	                                <div class="col-md-1">
	                                    <h5 class="card-title">출석률</h5>
	                                </div>
	                                <div class="col-md-10">
	                                	<div class="progress mb-2">
	                                    	<div class="progress-bar bg-info" role="progressbar" style="width: 50%" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100"></div>
	                                    </div>
	                                </div>
	                                	<span id="attendance-help" class="pr-4" style="margin-left:2%;">50%</span>
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
					<div class="col-md-1">
						<input type="hidden" name="studyNo" value="${attendance.stNo }">
					</div>
					<div class="col-md-10">
						<table id="userList"  class="table table-hover">
						  <thead>
						    <tr class="text-center">
						    	  <th>#</th>
						      <th>닉네임</th>
						      <th>날짜</th>
						      <th>출석여부</th>
						    </tr>
						  </thead>
						  <tbody>
							  <c:forEach items="${ aList }" var="Attendance">
							  		<td>${attendance.rNum }</td>
							  		<td>${member.mbNickname }</td>
							  		<td>${attendance.atInsertDate }</td>
							  		<td></td>
							  </c:forEach>
						  </tbody>
						</table>
					</div>
					<!-- <table class="table col-lg-12" id="nTable">
						<thead class="thead-light">
							<tr>
								<th scope="col" style="width: 7%"></th>
								<th scope="col" style="width: 10%">날짜</th>
								<th scope="col" style="width: 10%">닉네임</th>
								<th scope="col" style="width: 10%">출석여부</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td></td>
								<td>2021-06-07</td>
								<td>동현쿤</td>
								<td>O</td>
							</tr>
						</tbody>
						<tr></tr>
					</table> -->
				</div>
			</div>
		</div>
		<!-- footer -->
		<jsp:include page="../common/studyFooter.jsp" />
	</div>
	<script type="text/javascript" src="https://cdn.datatables.net/1.10.23/js/jquery.dataTables.min.js"></script>
	<script>
		$('#userList').DataTable();
		
		/* var table = $("#userList").DataTable();
		table.destroy();

		$.ajax({
		  url:"/attendance/attList",
		  type:"get",
		  success:function(data){
		 	 $("#userList").dataTable({
		 	 data: data,
		 	 columns: [
		  		{ data: 'user_nm_ko' },
		  		{ data: 'user_id' },
		       	{ data: 'email' },
		        	{ data: 'enable' },
		        	{ data: 'pos_nm' }
		  	]
		  });

		    },error:function(request, status, error){
		    console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		    }
		}); */
		
/*	 $(document).ready(function(){
	         setDateBox();
	    });    
	 
 	    // select box 연도 , 월 표시
	    function setDateBox(){
	        var dt = new Date();
	        var year = "";
	        var com_year = dt.getFullYear();
	        // 발행 뿌려주기
	        $("#YEAR").append("<option value=''>년도</option>");
	        // -1년~현재
	        for(var y = (com_year-1); y <= (com_year); y++){
	            $("#YEAR").append("<option value='"+ y +"'>"+ y + " 년" +"</option>");
	        }
	        // 월 뿌려주기(1월부터 12월)
	        var month;
	        $("#MONTH").append("<option value=''>월</option>");
	        for(var i = 1; i <= 12; i++){
	            $("#MONTH").append("<option value='"+ i +"'>"+ i + " 월" +"</option>");
	        }
	    } */
	</script>
</body>
</html>