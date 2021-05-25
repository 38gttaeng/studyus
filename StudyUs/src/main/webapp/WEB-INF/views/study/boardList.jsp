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
                                    <li class="breadcrumb-item text-muted active" aria-current="page"><a href="/study/board?boCategory=${ category }">Board</a></li>
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
	                    <div class="customize-input float-right">
                           	<input name="searchValue" class="form-control custom-shadow custom-radius border-0 bg-white" type="text" placeholder="Search" aria-label="Search">
                           	<i id="search-btn" class="form-control-icon" data-feather="search"></i>
                    	</div>
                    	<div class="customize-input float-right" style="margin-right:10px;">
	                        <select name="searchCondition" class="custom-select bg-white custom-radius border-0 custom-shadow">
	                            <option selected value="all">전체</option>
	                            <option value="title">제목</option>
	                            <option value="content">내용</option>
	                            <option value="writer">작성자</option>
	                        </select>
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
                <!-- basic table -->
                <div class="row">
                    <div id="container" class="col-12">
                    	<!-- 여기에 데이터 들어감! -->
                	</div>
                </div>  
                
                <div id="float-btn">
	                <!-- top으로 가는 버튼 -->
	                <button id="top-btn" onclick="location.href='#'"><i class="fas fa-angle-up"></i></button>
	                <!-- 글쓰기 버튼 --> 
					<button id="write-btn" onclick="location.href='/study/board/registerView'"><i class="fas fa-edit"></i><span>글쓰기</span></button>          
                </div>
			</div>
            <!-- footer -->
			<jsp:include page="../common/studyFooter.jsp"/>
        </div>
    </div>
    
    <!-- 해당 페이지 JS 파일 --> 
    <script src="/resources/js/boardList.js"></script>
</body>
</html>