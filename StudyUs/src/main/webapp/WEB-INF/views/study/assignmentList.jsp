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
    <link href="/resources/css/studyus/assignment.css" rel="stylesheet">
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
        				<form id="searchForm" action="/study/board/search" method="get">
		                    <div class="customize-input float-right">
                            	<input name="searchValue" class="form-control custom-shadow custom-radius border-0 bg-white" type="text" placeholder="Search" aria-label="Search">
                            	<i class="form-control-icon" data-feather="search" onclick="$('#searchForm').submit();"></i>
	                    	</div>
	                    	<div class="customize-input float-right" style="margin-right:10px;">
		                        <select name="searchCondition" class="custom-select bg-white custom-radius border-0 custom-shadow">
		                            <option selected value="title">과제명</option>
		                            <option value="content">내용</option>
		                            <option value="writer">작성자</option>
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
                    		<div class="card-body">
	                    		<h5 class="card-title text-secondary group-title">현재 진행중인 프로젝트</h5>
								<div class="owl-carousel owl-theme">
									<c:forEach var="group" items="${ grList }">
										<div class="item">
											<div>${ group.grName }</div>
											<div></div>
										</div>
									</c:forEach>
								</div>
	                    		<label class="btn btn-outline-secondary float-right">
	                                <div class="custom-control custom-radio">
	                                    <input type="checkbox" id="customCheck" name="options" value="oldView"
	                                        class="custom-control-input">
	                                    <label class="custom-control-label" for="customCheck">
	                                    	숨김 프로젝트 보기
	                                    </label>
	                                </div>
	                            </label>
	                            <c:if test="${ loginUser.mbNo == study.leaderNo }">
	                            	<button class="btn btn-secondary float-right addGroup">추가</button>
	                            </c:if>
                    		</div>
                    	</div>
                    	
                        <div class="card">
                        	<div class="card-body">
	                            <table class="table .table-hover">
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
	                            		
	                            		<c:url var="asDetail" value="/study/assignment/detail">
											<c:param name="asNo" value="${ aOne.asNo }"></c:param>
										</c:url>
		                            	<tr onclick="location.href='${ asDetail }'">
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
                </div>
                
                <!-- 글쓰기 버튼 --> 
                <div id="float-btn">
					<button id="write-btn" onclick="location.href='/study/assignment/registerView'"><i class="fas fa-edit"></i><span>추가</span></button>          
            	</div>
            </div>
            <!-- footer -->
			<jsp:include page="../common/studyFooter.jsp"/>
        </div>
    </div>
    
     <!-- 해당 페이지 JS 파일 -->
    <script src="/resources/js/assignmentList.js"></script>
</body>
</html>