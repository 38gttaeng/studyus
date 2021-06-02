<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
    <!-- 추가 css -->
    <link href="/resources/css/study/assets/extra-libs/datatables.net-bs4/css/dataTables.bootstrap4.css" rel="stylesheet">
	<style>
		.dt-body-center {
			align : center;
		}
	</style>
	<title>StudyUs : 스터디룸</title>
</head>
<body>
	<!-- ============================================================== -->
    <!-- Main wrapper -->
    <!-- ============================================================== -->
    <div id="main-wrapper" data-theme="light" data-layout="vertical" data-navbarbg="skin6" data-sidebartype="full"
        data-sidebar-position="fixed" data-header-position="fixed" data-boxed-layout="full">
        
        <!-- menubar -->
	    <jsp:include page="../common/studyMenubar.jsp"/>
        
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
                        <h4 class="page-title text-truncate text-dark font-weight-medium mb-1">게시물 관리</h4>
                        <div class="d-flex align-items-center">
                            <nav aria-label="breadcrumb">
                                <ol class="breadcrumb m-0 p-0">
                                    <li class="breadcrumb-item text-muted" aria-current="page"><a href="/study">Study</a></li>
                                    <li class="breadcrumb-item text-muted" aria-current="page">Manage</li>
                                </ol>
                            </nav>
                        </div>
                    </div>
	            </div>
            </div>

            <div class="container-fluid">
            	<!-- 게시판 게시물 목록 -->
                <div class="row">
                    <div class="col-12">
                        <div class="card">
                            <div class="card-body ">
                                <h4 class="card-title">게시판 게시물 목록</h4>
                                <div class="table-responsive">
                                    <table id="board-list" class="table table-striped table-bordered display no-wrap"
                                        style="width:100%">
                                        <thead>
                                            <tr>
                                            	<th><input type="checkbox" name="select_all" value="1" id="select-all"></th>
                                                <th>#</th>
                                                <th>카테고리</th>
                                                <th>제목</th>
                                                <th>작성자</th>
                                            </tr>
                                        </thead>
                                    </table>
                                </div>
		                    <input id="board-btn" type="button" class="btn btn-primary float-right" value="삭제">
                            </div>
                        </div>
                    </div>
                </div>
                
                <!-- 과제 게시물 목록 -->
                <div class="row">
                    <div class="col-12">
                        <div class="card">
                            <div class="card-body ">
                                <h4 class="card-title">과제 게시물 목록</h4>
                                <div class="table-responsive">
                                    <table id="assignment-list" class="table table-striped table-bordered display no-wrap"
                                        style="width:100%">
                                        <thead>
                                            <tr>
                                                <th>글번호</th>
                                                <th>카테고리</th>
                                                <th>제목</th>
                                                <th>작성자</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        	<tr>
                                                <td>Tiger Nixon</td>
                                                <td>System Architect</td>
                                                <td>Edinburgh</td>
                                                <td>61</td>
                                            </tr>
                                            <tr>
                                                <td>Garrett Winters</td>
                                                <td>Accountant</td>
                                                <td>Tokyo</td>
                                                <td>63</td>
                                            </tr>
                                    	</tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
                
            <!-- footer -->
			<jsp:include page="../common/studyFooter.jsp"/>
        </div>
    </div>
    
    <script src="/resources/css/study/assets/extra-libs/datatables.net/js/jquery.dataTables.min.js"></script>
    <script src="/resources/js/contentsList.js"></script>
</body>
</html>