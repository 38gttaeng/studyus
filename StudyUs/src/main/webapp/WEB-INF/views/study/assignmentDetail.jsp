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
            	<div class="row">
                    <div class="col-lg-4 align-self-center">
                        <h4 class="page-title text-truncate text-dark font-weight-medium mb-1">과제</h4>
                        <div class="d-flex align-items-center">
                            <nav aria-label="breadcrumb">
                                <ol class="breadcrumb m-0 p-0">
                                    <li class="breadcrumb-item text-muted" aria-current="page"><a href="/study">Study</a></li>
		                            <li class="breadcrumb-item text-muted" aria-current="page"><a href="/study/assignment?grNo=0">Assignment</a></li>
		                            <li class="breadcrumb-item font-weight-bold" aria-current="page"><a class="text${ assignmentGroup.grColor }" href="/study/assignment?grNo=${ assignmentGroup.grNo }">${ assignmentGroup.grName }</a></li>
		                            <li class="breadcrumb-item text-muted" aria-current="page">${ assignment.asNo }</li>
                                </ol>
                            </nav>
                        </div>
                    </div>
                    <div class="col-lg-8 align-self-center">
                    	<div class="float-right">
	                    	<c:if test="${ study.leaderNo == loginUser.mbNo }">
                    		<div class="btn-group">
				                <c:url var="asModify" value="/study/assignment/modifyView">
									<c:param name="asNo" value="${ assignment.asNo }"></c:param>
								</c:url>
								<c:url var="asDelete" value="/study/assignment/delete">
									<c:param name="asNo" value="${ assignment.asNo }"></c:param>
								</c:url>
		                    	<a href="${ asModify }" class="btn btn-secondary">수정</a>
		                    	<a href="${ asDelete }" onclick="return confirm('정말 삭제하시겠습니까?');" class="btn btn-secondary">삭제</a>
                    		</div>
                    		</c:if>
	                    	<button onclick="location.href='/study/assignment?grNo=${ assignmentGroup.grNo }'" class="btn btn-primary">목록</button>
                    	</div>
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
                    <div class="col-12">
                        <div class="card">
                        	<div class="card-body back${ assignmentGroup.grColor } text-white text-right">${ assignmentGroup.grName }</div>
                            <div class="card-body ">
                            	<h4 class="card-title">
                            		<!-- 제목 -->
                            		${ assignment.asName }
                            	</h4>
                            	<div class="row">
	                            	<h6 class="card-subtitle col-3">${ assignment.asInsertDate }</h6>
	                            	<h6 class="card-subtitle col-1"> - </h6>
	                                <h6 class="card-subtitle col-3">${ assignment.asDeadLine }</h6>
                            	</div>
                            </div>
                            <c:if test="${ !empty assignment.asFiles }">
	                            <div class="card-body file-box">
									<c:forEach var="file" items="${ assignment.asFiles }" varStatus="i">
		                            	<div>
		                            		<b>첨부파일${ i.count } : </b>
		                            		<c:if test="${ file.fiRealName.substring(file.fiRealName.lastIndexOf('.') + 1) != 'pdf'}">
				                            	<c:url var="fileDownload" value="/file/download">
													<c:param name="fiNo" value="${ file.fiNo }"></c:param>
												</c:url>
		                            			<a class="text-muted" href="${ fileDownload }" onclick="return confirm('파일을 다운로드하시겠습니까?');">${ file.fiRealName }</a>
		                            		</c:if>
		                            		<c:if test="${ file.fiRealName.substring(file.fiRealName.lastIndexOf('.') + 1) == 'pdf'}">
		                            			<a class="text-muted" href="/resources/js/pdf/web/viewer.html?file=/resources/auploadFiles/${ file.fiStoredName }" target="_blank">${ file.fiRealName }</a>
		                            		</c:if>
		                            	</div>
	                            	</c:forEach>
	                            </div>
                            </c:if>
                            <div class="card-body content">
	                            <p>${ assignment.asContents }</p>
                            </div>
                            
                        </div>
                    </div>
                </div>
            <!-- footer -->
			<jsp:include page="../common/studyFooter.jsp"/>
			
            </div>
        </div>
    </div>
    
    <!-- 해당 페이지 JS 파일 -->
    <script src="/resources/js/assignmentReply.js"></script>
</body>
</html>