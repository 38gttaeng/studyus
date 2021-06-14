<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
    <link href="/resources/css/studyus/assignmentImage.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/viewerjs/1.9.0/viewer.css" rel="stylesheet">
	<title>과제 이미지 : StudyUs</title>
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
                        <h4 class="page-title text-truncate text-dark font-weight-medium mb-1">파일함</h4>
                        <div class="d-flex align-items-center">
                            <nav aria-label="breadcrumb">
                                <ol class="breadcrumb m-0 p-0">
                                    <li class="breadcrumb-item text-muted" aria-current="page"><a href="/study">Study</a></li>
                                    <li class="breadcrumb-item text-muted" aria-current="page"><a href="/study/assignment?grNo=0">Assignment</a></li>
                                    <li class="breadcrumb-item text-muted" aria-current="page">File</li>
                                </ol>
                            </nav>
                        </div>
                    </div>
                    <div class="col-lg-8 align-self-center">
                    	<div class="float-right">
                    		<div class="btn-group">
		                    	<a href="/study/assignment/image" class="btn btn-secondary">이미지</a>
		                    	<a href="/study/assignment/file" class="btn btn-secondary">기타파일</a>
                    		</div>
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
                    
                    	<!-- 프로젝트 리스트 ----------------------------------->
                    	<div class="card">
                    		<div class="card-body grid">
                    			<div class="grid-sizer"></div>
                    			<c:forEach items="${ picList }" var="pics">
                    				<c:if test="${ pics.url.substring(7,8) == 's' }">
                    					<div class="grid-item">
                    						<img src="${ pics.pic }">
                    						<button class="btn btn-primary btn-sm btn-rounded detail-btn" onclick="location.href='${ pics.url }'">원글 보기</button>
                    					</div>
                    				</c:if>
                    				<c:if test="${ pics.url.substring(7,8) == 'a' }">
                    					<div class="grid-item grid-item-a">
                    						<img src="${ pics.pic }">
                    						<button class="btn btn-primary btn-sm btn-rounded detail-btn" onclick="location.href='${ pics.url }'">원글 보기</button>
                    					</div>
                    				</c:if>
                    			</c:forEach>
                    		</div>
                    	</div>
                    	
                	</div>
            	</div>
            <!-- footer -->
			<jsp:include page="../common/studyFooter.jsp"/>
       		</div>
    	</div>
    </div>
    
    <script src="https://unpkg.com/masonry-layout@4/dist/masonry.pkgd.min.js"></script>
    <script src="https://unpkg.com/imagesloaded@4/imagesloaded.pkgd.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/viewerjs/1.9.0/viewer.min.js"></script>
    <script src="/resources/js/assignmentImage.js"></script>
</body>
</html>