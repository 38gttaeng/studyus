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
	<link href="/resources/css/studyus/manage.css" rel="stylesheet">
	<style>
		.dt-body-center {
			align : center;
		}
	</style>
	<title>댓글 관리 : StudyUs</title>
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
                        <h4 class="page-title text-truncate text-dark font-weight-medium mb-1">댓글 관리</h4>
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
			
			<!-- js에 보내주는 용도 -->
			<input type="hidden" id="leaderNo" value="${ study.leaderNo }">
			<input type="hidden" id="userNo" value="${ loginUser.mbNo }">
            <div class="container-fluid">
            	<!-- 게시판 게시물 목록 -->
                <div class="row">
                    <div class="col-12">
                        <div class="card">
                            <div class="card-body ">
                                <h4 class="card-title">게시판 댓글 목록</h4>
                                <div class="table-responsive">
                                    <table id="board-list" class="table table-striped table-bordered display no-wrap"
                                        style="width:100%">
                                        <thead>
                                            <tr>
                                            	<th><input type="checkbox" id="board-select-all"></th>
                                                <th>글번호</th>
                                                <th>내용</th>
                                                <th>작성자</th>
                                                <th>작성일</th>
                                            </tr>
                                        </thead>
                                    </table>
                                </div>
		                    <input id="board-btn" type="button" class="btn btn-primary float-right delete-btn" value="삭제">
                            </div>
                        </div>
                    </div>
                </div>
                
                <!-- 과제 게시물 목록 -->
                <div class="row">
                    <div class="col-12">
                        <div class="card">
                            <div class="card-body ">
                                <h4 class="card-title">과제제출 댓글 목록</h4>
                                <div class="table-responsive">
                                    <table id="assignment-list" class="table table-striped table-bordered display no-wrap"
                                        style="width:100%">
                                        <thead>
                                            <tr>
                                            	<th><input type="checkbox" name="select_all" value="1" id="assignment-select-all"></th>
                                                <th>프로젝트</th>
                                                <th>과제명</th>
                                                <th>내용</th>
                                                <th>작성자</th>
                                                <th>작성일</th>
                                            </tr>
                                        </thead>
                                    </table>
                                </div>
		                    <input id="assignment-btn" type="button" class="btn btn-primary float-right delete-btn" value="삭제">
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
    <script src="/resources/js/commentsList.js"></script>
</body>
</html>