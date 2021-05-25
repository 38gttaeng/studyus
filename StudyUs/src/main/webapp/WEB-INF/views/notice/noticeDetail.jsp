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
	    	$("#sidebarnav>li:nth-child(5)").addClass("selected");
	    	$("#sidebarnav>li:nth-child(5) a").addClass("active");
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
                        <h4 class="page-title text-truncate text-dark font-weight-medium mb-1">공지사항</h4>
                        <div class="d-flex align-items-center">
                            <nav aria-label="breadcrumb">
                                <ol class="breadcrumb m-0 p-0">
                                    <li class="breadcrumb-item text-muted" aria-current="page"><a href="/study">Study</a></li>
                                    <li class="breadcrumb-item text-muted" aria-current="page"><a href="/study/board">Notice</a></li>
                                    <li class="breadcrumb-item text-muted active" aria-current="page">Detail</li>
                                </ol>
                            </nav>
                        </div>
                    </div>
                    <div class="col-lg-8 align-self-center">
                    	<div class="float-right">
	                    	<!-- 작성자만 -->
                    		<div class="btn-group">
		                    	<button onclick="location.href='/notice/noticeModifyView?noticeNo=${notice.noticeNo}'" class="btn btn-secondary">수정</button>
		                    	<button onclick="location.href='/notice/noticeDelete?noticeNo=${notice.noticeNo}'" class="btn btn-secondary">삭제</button>
                    		</div>
	                    	<button onclick="location.href='/notice/noticeList'" class="btn btn-primary">목록</button>
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
                            	<h4 class="card-title"><span class="tags tag-free">공지</span> ${ notice.noticeTitle }</h4>
                            	<div class="row">
	                            	<h6 class="card-subtitle col-6"><img src="/resources/images/1.png" class="rounded-circle">&nbsp;&nbsp;{ 닉네임 }</h6>
	                                <h6 class="card-subtitle col-6" style="text-align:right">조회수 ${notice.noticeCount} &nbsp;&nbsp;${ notice.nInsertDate }</h6>
                            	</div>
                            </div>
                            <div class="card-body file-box">
                            	${ notice.noticeFileName }
                            </div>
                            <div class="card-body">
                            	<c:if test="${ !empty notice.noticeContents }">
	                            	<p>${ notice.noticeContents }</p>
                            	</c:if>
                            </div>
                            <div class="card-body">
                            	<!-- board.js 파일과 연동하기 위해서 -->
                            	<input id="rMotherNo" type="hidden" value="${ notice.nMotherNo }">
                            	<input id="rMbNo" type="hidden" value="${ notice.memberNo }">
                            	<%-- <input id="loginMbNo" type="hidden" value="${ 로그인 멤버넘버 }"> --%>
                            
                                <h6 class="card-subtitle" style="float:right;">댓글 <span id="rCount"></span></h6>
                                <hr>
                                
                                <div id="rList"></div>
<!--                                 댓글 예시(게시글 작성자이면서 로그인한 사람)
                                <div class="reply-box my-reply">
									<div>
										<img src="/resources/css/study/assets/images/users/profile-pic.jpg" alt="user" class="rounded-circle">&nbsp;
										<span>{ 닉네임 }</span>&nbsp;
										게시글 작성자일 경우
										<div>작성자</div>
										<span>{ 2020-05-21 }</span>
									</div>
									<div>{ 내용 }</div>
									<div>
										<button class="btn btn-sm btn-light">답글</button>
										
										댓글 작성자만
										<div class="btn-group">
											<button class="btn btn-sm btn-outline-light">수정</button>
											<button class="btn btn-sm btn-outline-light">삭제</button>
										</div>
									</div>
								</div> -->
								
								<div class="reply-enter">
									<textarea id="rContent" class="form-control" rows="3" placeholder="댓글을 입력하세요."></textarea>
									<button id="rSubmit">등록</button>
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
    
    <!-- 해당 페이지 JS 파일 -->
    <script src="/resources/js/boardReply.js"></script>
    
</body>
</html>