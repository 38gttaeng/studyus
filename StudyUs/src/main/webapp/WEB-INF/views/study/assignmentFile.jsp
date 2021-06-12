<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
    <link href="/resources/css/studyus/assignmentImage.css" rel="stylesheet">
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
                    		<c:url var="asList" value="/study/assignment">
								<c:param name="grNo" value="${ group.grNo }"></c:param>
							</c:url>
                    		<div onclick="location.href='${ asList }'" class="card-body backHover${ group.grColor } text-white">
                    			${ group.grName }
                    		</div>
                    		<div class="card-body grid">
                    			<div class="grid-sizer"></div>
                    			<c:forEach items="${ fiList }" var="file">
                   					<div class="files grid-item">
                    				<c:if test="${ file.fiBoardType == 2 }">
                   						<%-- ${ file.fiRealName } --%>
                   						<c:if test="${ file.fiRealName.substring(file.fiRealName.lastIndexOf('.') + 1) == 'pdf'}">
                   							<img data-pdf-thumbnail-file="/resources/auploadFiles/${ file.fiStoredName }">
                   						</c:if>
                   						<c:if test="${ file.fiRealName.substring(file.fiRealName.lastIndexOf('.') + 1) != 'pdf'}">
                   							<img src="#">
                   						</c:if>
                   						<button class="btn btn-primary btn-sm btn-rounded detail-btn" onclick="location.href='/study/assignment/detail?asNo=${ file.fiMotherNo }'">원글 보기</button>
                    				</c:if>
                    				<c:if test="${ file.fiBoardType == 3 }">
                   						<%-- ${ file.fiRealName } --%>
                   						<c:if test="${ file.fiRealName.substring(file.fiRealName.lastIndexOf('.') + 1) == 'pdf'}">
                   							<a href="/resources/js/pdf/web/viewer.html?file=/resources/suploadFiles/${ file.fiStoredName }" target="_blank">
                   								<img data-pdf-thumbnail-file="/resources/suploadFiles/${ file.fiStoredName }">
                   							</a>
                   						</c:if>
                   						<c:if test="${ file.fiRealName.substring(file.fiRealName.lastIndexOf('.') + 1) != 'pdf'}">
                   							<img src="#">
                   						</c:if>
                   						<button class="btn btn-primary btn-sm btn-rounded detail-btn" onclick="location.href='/study/sAssignment/detail?suNo=${ file.fiMotherNo }'">원글 보기</button>
                    				</c:if>
                   					</div>
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
    
    <script src="/resources/js/pdf/build/pdf.js"></script>
   <!--  <script src="/resources/js/pdf/pdfThumbnails.js"></script> -->
    <script src="/resources/js/pdf/pdfThumbnails.js" data-pdfjs-src="/resources/js/pdf/build/pdf.js"></script>
    <!-- <script src="/resources/js/assignmentImage.js"></script> -->
</body>
</html>