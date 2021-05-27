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
	    	$("#sidebarnav>li:nth-child(6)").addClass("selected");
	    	$("#sidebarnav>li:nth-child(6) a").addClass("active");
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
                        <h4 class="page-title text-truncate text-dark font-weight-medium mb-1">게시판</h4>
                        <div class="d-flex align-items-center">
                            <nav aria-label="breadcrumb">
                                <ol class="breadcrumb m-0 p-0">
                                    <li class="breadcrumb-item text-muted" aria-current="page"><a href="/study">Study</a></li>
                                    <li class="breadcrumb-item text-muted" aria-current="page"><a href="/study/board?boCategory=${ category }">Board</a></li>
                                    <li class="breadcrumb-item text-primary font-weight-bold" aria-current="page">${ board.boNo }</li>
                                </ol>
                            </nav>
                        </div>
                    </div>
                    <div class="col-lg-8 align-self-center">
                    	<div class="float-right">
	                    	<c:if test="${ loginUser.mbNo == board.mbNo }">
                    		<div class="btn-group">
				                <c:url var="bModify" value="/study/board/modifyView">
									<c:param name="boNo" value="${ board.boNo }"></c:param>
								</c:url>
								<c:url var="bDelete" value="/study/board/delete">
									<c:param name="boNo" value="${ board.boNo }"></c:param>
								</c:url>
		                    	<a href="${ bModify }" class="btn btn-secondary">수정</a>
		                    	<a href="${ bDelete }" onclick="return confirm('정말 삭제하시겠습니까?');" class="btn btn-secondary">삭제</a>
                    		</div>
                    		</c:if>
	                    	<button onclick="location.href='/study/board?boCategory=${ category }'" class="btn btn-primary">목록</button>
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
                            		<!-- 태그 -->
                           			<c:if test="${ board.boCategory == 1 }">
                           				<span class="tags tag-free">자유</span>
                           			</c:if>
                           			<c:if test="${ board.boCategory == 2 }">
                           				<span class="tags tag-share">공유</span>
                           			</c:if>
                           			<c:if test="${ board.boCategory == 3 }">
                           				<span class="tags tag-qna">질문</span>
                           			</c:if>
                            		<!-- 제목 -->
                            		${ board.boTitle }
                            	</h4>
                            	<div class="row">
	                            	<h6 class="card-subtitle col-6"><img src="/resources/images/${ board.member.mbPhoto }.png" class="rounded-circle">&nbsp;&nbsp;${ board.member.mbNickname }</h6>
	                                <h6 class="card-subtitle col-6" style="text-align:right">${ board.boInsertDate }</h6>
                            	</div>
                            </div>
                            <c:if test="${ !empty board.boFiles }">
	                            <div class="card-body file-box">
									<c:forEach var="file" items="${ board.boFiles }" varStatus="i">
		                            	<div>
		                            		<b>첨부파일${ i.count } : </b>
		                            		<c:if test="${ file.fiRealName.substring(file.fiRealName.lastIndexOf('.') + 1) != 'pdf'}">
				                            	<c:url var="fileDownload" value="/file/download">
													<c:param name="fiNo" value="${ file.fiNo }"></c:param>
												</c:url>
		                            			<a class="text-muted" href="${ fileDownload }" onclick="return confirm('파일을 다운로드하시겠습니까?');">${ file.fiRealName }</a>
		                            		</c:if>
		                            		<c:if test="${ file.fiRealName.substring(file.fiRealName.lastIndexOf('.') + 1) == 'pdf'}">
		                            			<a class="text-muted" href="/resources/js/pdf/web/viewer.html?file=/resources/buploadFiles/${ file.fiStoredName }" target="_blank">${ file.fiRealName }</a>
		                            		</c:if>
		                            	</div>
	                            	</c:forEach>
	                            </div>
                            </c:if>
                            <div class="card-body content">
	                            <p>${ board.boContents }</p>
                            </div>
                            
                            <!-- 댓글 -->
                            <div class="card-body">
                            	<!-- board.js 파일과 연동하기 위해서 -->
                            	<input id="rMotherNo" type="hidden" value="${ board.boNo }">
                            	<input id="boMbNo" type="hidden" value="${ board.mbNo }">
                            	<input id="loginMbNo" type="hidden" value="${ loginUser.mbNo }">
                            
                                <h6 class="card-subtitle float-right">댓글 <span id="rCount"></span></h6>
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
    <script src="/resources/js/boardReply.js"></script>
</body>
</html>