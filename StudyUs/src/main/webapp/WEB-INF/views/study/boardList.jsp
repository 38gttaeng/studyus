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
                        <h4 class="page-title text-truncate text-dark font-weight-medium mb-1">게시판</h4>
                        <div class="d-flex align-items-center">
                            <nav aria-label="breadcrumb">
                                <ol class="breadcrumb m-0 p-0">
                                    <li class="breadcrumb-item text-muted" aria-current="page"><a href="/study">Study</a></li>
                                    <li class="breadcrumb-item text-muted active" aria-current="page">Board</li>
                                    <!-- 카테고리명에 따라서 다르게 -->
                                    <li class="breadcrumb-item text-primary font-weight-bold" aria-current="page">
                                    	<c:if test="${ category == 0 }">
	                            			전체
                            			</c:if>
                                    	<c:if test="${ category == 1 }">
	                            			자유
                            			</c:if>
                            			<c:if test="${ category == 2 }">
                            				공유
                            			</c:if>
                            			<c:if test="${ category == 3 }">
                            				질문
                            			</c:if>
                                    </li>
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
                <!-- basic table -->
                <div class="row">
                    <div class="col-12">
                    
	                    <c:forEach items="${ bList }" var="board">
							<!-- 게시물 하나 --------------------------------------------------->
							<c:url var="detail" value="/study/board/detail">
								<c:param name="boNo" value="${ board.boNo }"/>
							</c:url>
	                        <div class="card" onclick="location.href='${ detail }'" style="cursor:pointer;">
	                            <div class="card-body">
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
		                            	<h6 class="card-subtitle col-6"><img src="/resources/images/1.png" class="rounded-circle">&nbsp;&nbsp;{ 닉네임 }</h6>
		                                <h6 class="card-subtitle col-6" style="text-align:right">${ board.boInsertDate }</h6>
	                            	</div>
	                            </div>
	                            <c:if test="${ !empty board.boFileName }">
		                            <div class="card-body file-box">
		                            	${ board.boFileName }
		                            </div>
	                            </c:if>
	                            <div class="card-body">
	                            	<p>${ board.boContents }</p>
	                            </div>
	                            
	                            <!-- 댓글 -->
	                            <div class="card-body">
	                                <h6 class="card-subtitle" style="float:right;">댓글 { 2 }</h6>
	                                <hr>
	                                
	                                <!-- 댓글 하나 -->
	                                <div class="reply-box list-reply">
										<div>
											<img src="/resources/css/study/assets/images/users/profile-pic.jpg" alt="user" class="rounded-circle">&nbsp;
											<span>{ 닉네임 }</span>&nbsp;
											<span>{ 2020-05-21 }</span>
										</div>
										<div>{ 내용 }</div>
									</div>
	                            </div>
	                        </div>
                        	<!-- 게시물 하나 끝 --------------------------------------->
						</c:forEach>
                        
                        <!-- 페이징 --------------------------------------->
                        <!-- 이전 -->
						<c:url var="before" value="/study/board">
							<c:param name="page" value="${ pi.currentPage - 1 }"/>
						</c:url>
						<!-- 1페이지거나 1보다 작으면 안보이게 -->
						<c:if test="${ pi.currentPage > 1 }">
							<a href="${ before }">[이전]</a>&nbsp;
						</c:if>
						<!-- 페이지 -->
						<c:forEach var="p" begin="${ pi.startPage }" end="${ pi.endPage }">
							<c:url var="pagination" value="/study/board">
								<c:param name="page" value="${ p }"/>
							</c:url>
							<c:if test="${ p eq pi.currentPage }">
								<font color="red" size="4">[${ p }]</font>
							</c:if>
							<c:if test="${ p ne pi.currentPage }">
								<a href="${ pagination }">${ p }</a>&nbsp;
							</c:if>
						</c:forEach>
						<!-- 다음 -->
						<c:url var="after" value="/study/board">
							<c:param name="page" value="${ pi.currentPage + 1 }"/>
						</c:url>
						<!-- 마지막페이지이거나 마이페이지보다 크면 안보이게 -->
						<c:if test="${ pi.currentPage < pi.maxPage }">
							<a href="${ after }">[다음]</a>&nbsp;
						</c:if>
						<!-- 페이징 끝 --------------------------------------->
						
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