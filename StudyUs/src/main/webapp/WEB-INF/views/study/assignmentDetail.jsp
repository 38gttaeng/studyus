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
                                    <li class="breadcrumb-item text-muted" aria-current="page"><a href="/study/assignment">Assignment</a></li>
                                    <li class="breadcrumb-item text-primary font-weight-bold" aria-current="page">${ assignment.asNo }</li>
                                </ol>
                            </nav>
                        </div>
                    </div>
                    <div class="col-lg-8 align-self-center">
                    	<div class="float-right">
	                    	<%-- <c:if test="${ loginUser.mbNo ==  }"> --%>
	                    	<!-- 로그인한 사람이 세션에서 가져온 팀장정보랑 일치하면 -->
	                    	<%-- <c:url var="bModify" value="/study/board/modifyView">
								<c:param name="boNo" value="${ board.boNo }"></c:param>
							</c:url>
							<c:url var="bDelete" value="/study/board/delete">
								<c:param name="boNo" value="${ board.boNo }"></c:param>
								<c:param name="boFileName" value="${ board.boFileName }"></c:param>
							</c:url> --%>
                    		<div class="btn-group">
		                    	<button onclick="location.href='#'" class="btn btn-secondary">수정</button>
		                    	<button onclick="location.href='#'" class="btn btn-secondary">삭제</button>
                    		</div>
                    		<%-- </c:if> --%>
	                    	<button onclick="location.href='/study/assignment'" class="btn btn-primary">목록</button>
                    	</div>
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
                            <div class="card-body ">
                            	<h4 class="card-title">
                            		${ assignment.asName }
                            	</h4>
                            	<div>
	                                <h6 class="card-subtitle float-right">${ assignment.asInsertDate }</h6>
                            	</div>
                            </div>
                            <c:if test="${ !empty assignment.asFileName }">
	                            <div class="card-body file-box">
	                            	${ assignment.asFileName }
	                            </div>
                            </c:if>
                            <div class="card-body">
	                            <p>${ assignment.asContents }</p>
                            </div>
                            
                            <!-- 댓글 -->
                            <div class="card-body">
                            	<!-- board.js 파일과 연동하기 위해서 -->
                            	<input id="rMotherNo" type="hidden" value="${ assginment.asNo }">
                            	<input id="asMbNo" type="hidden" value="${ assignment.mbNo }">
                            	<input id="loginMbNo" type="hidden" value="${ loginUser.mbNo }">
                            
                                <h6 class="card-subtitle" style="float:right;">댓글 <span id="rCount"></span></h6>
                                <hr>
                                
								<!-- 댓글 등록 -->
								<div class="reply-enter">
									<div id="editor"></div>
									<button id="rSubmit" class="reply-enter-btn">등록</button>
								</div>
								
                                <!-- 댓글 리스트 -->
                                <div id="rList"></div>
                                <!-- 페이징 -->
                                <nav id='rPage'></nav>
								
                            </div>
                            
                        </div>
                    </div>
                </div>
            </div>

            <!-- footer -->
			<jsp:include page="../common/studyFooter.jsp"/>
        </div>
    </div>
    
    <!-- 해당 페이지 JS 파일 -->
    <script src="/resources/js/assignmentReply.js"></script>
    
</body>
</html>