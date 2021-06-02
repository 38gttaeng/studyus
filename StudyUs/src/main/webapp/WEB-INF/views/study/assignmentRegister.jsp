<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
    <!-- 추가 css -->
    <link href="/resources/css/studyus/register.css" rel="stylesheet">
    <link href="/resources/css/studyus/assignmentColor.css" rel="stylesheet">
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
	    <script>
	    	$("#sidebarnav>li:nth-child(7)").addClass("selected");
	    	$("#sidebarnav>li:nth-child(7) a").addClass("active");
	    </script>
        
        <!-- ============================================================== -->
        <!-- Page wrapper  -->
        <!-- ============================================================== -->
        <div class="page-wrapper">
            <!-- ============================================================== -->
            <!-- Bread crumb and right sidebar toggle -->
            <!-- ============================================================== -->
            <div class="page-breadcrumb">
	            <div class="col-lg-10 align-self-center">
	                <h4 class="page-title text-truncate text-dark font-weight-medium mb-1">과제</h4>
	                <div class="d-flex align-items-center">
	                    <nav aria-label="breadcrumb">
	                        <ol class="breadcrumb m-0 p-0">
	                            <li class="breadcrumb-item text-muted" aria-current="page"><a href="/study">Study</a></li>
	                            <li class="breadcrumb-item text-muted" aria-current="page"><a href="/study/assignment?grNo=${ assignmentGroup.grNo }">Assignment</a></li>
	                            <li class="breadcrumb-item font-weight-bold" aria-current="page"><a class="text${ assignmentGroup.grColor }" href="/study/assignment?grNo=${ assignmentGroup.grNo }">${ assignmentGroup.grName }</a></li>
	                            <li class="breadcrumb-item text-muted" aria-current="page">Write</li>
	                        </ol>
	                    </nav>
	                </div>
	            </div>
            </div>

            <!-- ============================================================== -->
            <!-- Container fluid  -->
            <!-- ============================================================== -->
            <div class="container-fluid gradient${ assignmentGroup.grColor }">
            	<!-- ============================================================== -->
                <!-- Start Page Content -->
                <!-- ============================================================== -->
                <div class="row">
                	<div class="col-1"></div>
                    <div class="col-10">
                        <div class="card">
                           	<div class="card-body back${ assignmentGroup.grColor } text-white text-right">${ assignmentGroup.grName }</div>
                            <div class="card-body">
	                        	<form id="postForm" action="/study/assignment/register" method="post" enctype="multipart/form-data">
	                        		<div id="table-box">
										<table class="table-responsive">
											<tr>
												<td>기한</td>
												<td>
													<div class="dateBox">
														<input class="form-control" type="date" name="asDate">
														<input class="form-control" type="time" name="asTime">
														<div class="clearfix"></div>
														<span id="date-msg" class="invalid-feedback dMsg">기한 입력값을 모두 입력하세요.</span>
														<span id="date-msg2" class="invalid-feedback dMsg">기한은 현재 시간 이후를 선택해주세요.</span>
														
														<input type="hidden" name="asDeadLine">
													</div>
												</td>
											</tr>
											<tr>
												<td>제목</td>
												<td>
													<input type="text" class="form-control" name="asName">
													<span id="title-msg" class="invalid-feedback">제목을 1자 이상 입력하세요.</span>
												</td>
											</tr>
											<tr>
												<td valign=top>내용</td>
												<td>
													<input type="hidden" name="asContents">
													<input type="hidden" name="picList">
      												<div id="editor" style="min-height:400px;"></div>
												</td>
											</tr>
											<tr>
												<td>첨부파일</td>
												<td>
													<button type="button" id='button-add-file' class="btn text-primary"><i class="fas fa-plus"></i>  파일 추가</button>
													<div id="my-form"></div>
												</td>
											</tr>
											<tr>
												<td colspan="2" align="center">
													<input id="groupNo" type="hidden" value="${ assignmentGroup.grNo }">												
													<input id="reset-btn" type="button" class="btn waves-effect waves-light btn-light" value="취소">
													<input id="submit-btn" type="button" class="btn waves-effect waves-light btn-primary" value="등록">
												</td>
											</tr>
										</table>
									</div>
								</form>	
                    		</div>
                		</div>
            		</div>
            		<div class="col-1"></div>
           		</div>

            	<!-- footer -->
				<jsp:include page="../common/studyFooter.jsp"/>
       		</div>
   		</div>
	</div>
    
    <!-- 해당 페이지 JS 파일 -->
    <script src="/resources/js/assignmentRegister.js"></script>
    <script src="/resources/css/study/assets/libs/moment/min/moment.min.js"></script>
</body>
</html>