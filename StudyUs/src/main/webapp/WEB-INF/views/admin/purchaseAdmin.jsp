<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
    .delete-btn {
    margin-top : 0px !important ;
    }
    </style>
        <!-- 추가 css -->
    <link href="/resources/css/study/assets/extra-libs/datatables.net-bs4/css/dataTables.bootstrap4.css" rel="stylesheet">
	<link href="/resources/css/studyus/manage.css" rel="stylesheet">
   
    <title>StudyUs : 결제관리</title>
</head>
<body>
	<!-- ============================================================== -->
    <!-- Main wrapper -->
    <!-- ============================================================== -->
    <div id="main-wrapper" data-theme="light" data-layout="vertical" data-navbarbg="skin6" data-sidebartype="full"
        data-sidebar-position="fixed" data-header-position="fixed" data-boxed-layout="full">
        
        <!-- menubar -->
	    <jsp:include page="../common/adminMenubar.jsp"/>
	    <!-- 선택된 메뉴에 selected / active 추가해주기 -->
	    <script>
	    	$("#sidebarnav>li:first-child").addClass("selected");
	    	$("#sidebarnav>li:first-child a").addClass("active");
	    </script>
        
        <!-- ============================================================== -->
        <!-- Page wrapper  -->
        <!-- ============================================================== -->
        <div class="page-wrapper">
            <!-- ============================================================== -->
            <!-- Bread crumb and right sidebar toggle -->
            <!-- ============================================================== -->
            <div class="page-breadcrumb">
            	<div class="row">
                    <div class="col-9 align-self-center">
                        <h4 class="page-title text-truncate text-dark font-weight-medium mb-1">결제 관리</h4>
                        <div class="d-flex align-items-center">
                            <nav aria-label="breadcrumb">
                                <ol class="breadcrumb m-0 p-0">
                                    <li class="breadcrumb-item"><a href="#"></a>
                                    </li>
                                </ol>
                            </nav>
                        </div>
                    </div>
				<div class="col-md-3 align-self-center d-none d-lg-block">
                    	<form>
	                    	<div class="customize-input">
                            	<input class="form-control custom-shadow custom-radius border-0 bg-white"
                                           type="search" placeholder="Search" aria-label="Search">
                            	<i class="form-control-icon" data-feather="search"></i>
	                    	</div>
                    	</form>
                	</div>
            	</div>
            </div>

            <!-- ============================================================== -->
            <!-- Container fluid  -->
            <!-- ============================================================== -->
            <div class="container-fluid">
            	<!-- 게시판 게시물 목록 -->
                <div class="row">
                    <div class="col-12">
                        <div class="card">
                            <div class="card-body ">
                                <h4 class="card-title">결제 목록</h4>
                                <div class="table-responsive">
                                    <table id="pList" class="table table-striped table-bordered display no-wrap"
                                        style="width:100%">
                                        <thead>
                                            <tr>
                                            	<th><input type="checkbox" id="purchase-select-all"></th>
                                                <th>결제번호</th>
                                                <th>결제자 번호</th>
                                                <th>스터디번호</th>
                                                <th>스터디명</th>
                                                <th>결제자 이메일</th>
                                                <th>결제일시</th>
                                            </tr>
                                        </thead>
                                    </table>
                                </div>
		                    <input id="delete-btn" type="button" class="btn btn-primary float-right delete-btn" value="삭제">
<!-- 		                    <input id="modify-btn" type="button" class="btn btn-primary float-right modify-btn" value="수정"> -->
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- footer -->
			<jsp:include page="../common/studyFooter.jsp"/>
        </div>
    </div>
   
   <!--This page JavaScript -->
   <script src="/resources/css/study/assets/extra-libs/datatables.net/js/jquery.dataTables.min.js"></script>
   <script src="/resources/js/purchaseAdmin.js"></script>
</body>
</html>