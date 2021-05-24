<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
    <!-- 추가 css -->
    <link href="/resources/css/studyus/reply.css" rel="stylesheet">
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
                        <h4 class="page-title text-truncate text-dark font-weight-medium mb-1">과제</h4>
                        <div class="d-flex align-items-center">
                            <nav aria-label="breadcrumb">
                                <ol class="breadcrumb m-0 p-0">
                                    <li class="breadcrumb-item text-muted" aria-current="page"><a href="/study">Study</a></li>
                                    <li class="breadcrumb-item text-muted" aria-current="page">Assignment</li>
                                </ol>
                            </nav>
                        </div>
                    </div>
                    <div class="col-lg-8 align-self-center">
        				<form>
		                    <div class="customize-input float-right">
                            	<input class="form-control custom-shadow custom-radius border-0 bg-white" type="text" placeholder="Search" aria-label="Search">
                            	<i class="form-control-icon" data-feather="search"></i>
	                    	</div>
	                    	<div class="customize-input float-right" style="margin-right:10px;">
		                        <select class="custom-select bg-white custom-radius border-0 custom-shadow">
		                            <option selected>제목+내용</option>
		                            <option value="1">제목</option>
		                            <option value="2">내용</option>
		                            <option value="3">작성자</option>
		                        </select>
		                    </div>
                   		</form>
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
                <div class="row">
                    <div class="col-12">
                        <div class="card">
                            <table>
                            	<thead>
                            		<tr>
                            			<td>번호</td>
                            			<td>과제명</td>
                            			<td>기간</td>
                            			<td>제출여부</td>
                            		</tr>
                            	</thead>
                            	<tbody>
                            		<c:forEach items="${ aList }" var="aOne">
	                            	<tr onclick="location.href='/study/assignment/detail?asNo='${ aOne.asNo }">
	                            		<td>${ aOne.asNo }</td>
                            			<td>${ aOne.asName }</td>
                            			<td>${ aOne.asInsertDate } ~ ${ aOne.asDeadLine }</td>
                            			<td></td>
	                            	</tr>
	                            	</c:forEach>
                            	</tbody>
                            </table>
                        </div>
                    </div>
                </div>
                
                <!-- 글쓰기 버튼 --> 
				<button id="write-btn" onclick="location.href='/study/board/registerView'"><i class="fas fa-edit"></i><span>글쓰기</span></button>          
            </div>
            <!-- footer -->
			<jsp:include page="../common/studyFooter.jsp"/>
        </div>
    </div>
</body>
</html>