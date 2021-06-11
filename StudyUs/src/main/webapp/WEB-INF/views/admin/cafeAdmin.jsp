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
   
    <title>StudyUs : 스터디카페 관리</title>
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
                        <h4 class="page-title text-truncate text-dark font-weight-medium mb-1">스터디카페 관리</h4>
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
            	<!-- 스터디 카페 목록 -->
                <div class="row">
                    <div class="col-12">
                        <div class="card">
                            <div class="card-body ">
                                <h4 class="card-title">스터디카페 목록</h4>
                                <div class="table-responsive">
                                    <table id="caList" class="table table-striped table-bordered display no-wrap"
                                        style="width:100%">
                                        <thead>
                                            <tr>
                                            	<th><input type="checkbox" id="cafe-select-all"></th>
                                                <th>카페명</th>
                                                <th>주소</th>
                                                <th>전화번호</th>
                                                <th>영업시간</th>
                                                <th>소개</th>
                                                <th>오시는 길</th>
                                                <th>매장이미지</th>
                                            </tr>
                                        </thead>
                                    </table>
                                </div>
		                    <input id="delete-btn" type="button" class="btn btn-primary float-right delete-btn" value="삭제">
		                    <input id="register-btn" type="button" class="btn btn-primary float-right register-btn" value="등록" onclick="location.href='/cafe/registerForm'">
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
   <script src="/resources/js/cafeAdmin.js"></script>
</body>
</html>