<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
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
	            <div class="col-lg-4 align-self-center">
	                <h4 class="page-title text-truncate text-dark font-weight-medium mb-1">게시판</h4>
	                <div class="d-flex align-items-center">
	                    <nav aria-label="breadcrumb">
	                        <ol class="breadcrumb m-0 p-0">
	                            <li class="breadcrumb-item text-muted" aria-current="page"><a href="/study">Study</a></li>
	                            <li class="breadcrumb-item text-muted" aria-current="page"><a href="/study/board">Board</a></li>
	                            <li class="breadcrumb-item text-muted active" aria-current="page">Write</li>
	                        </ol>
	                    </nav>
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
                            	<h4 class="card-title">글쓰기</h4>
                            		<form action="/study/board/register" method="post" enctype="multipart/form-data">
										<table align="center" border="1" cellspacing="0">
											<tr>
												<td>카테고리</td>
												<td>
													<select name="boCategory">
														<option selected value="1">자유</option>
														<option value="2">공유</option>
														<option value="3">질문</option>
													</select>
												</td>
											</tr>
											<tr>
												<td>제목</td>
												<td><input type="text" size="50" name="boTitle"></td>
											</tr>
											<tr>
												<td>작성자</td>
												<td>
													<input type="text" size="50" value="${ loginUser.mbNickname }" readonly>
													<input type="hidden" name="mbNo" value="${ loginUser.mbNo }">
												</td>
											</tr>
											<tr>
												<td>내용</td>
												<td><textarea rows="7" cols="50" name="boContents"></textarea></td>
											</tr>
											<tr>
												<td>첨부파일</td>
												<td><input type="file" size="50" name="uploadFile"></td>
											</tr>
											<tr>
												<td colspan="2" align="center">
													<input type="submit" value="등록">&nbsp;&nbsp;
													<input type="reset" value="취소">
												</td>
											</tr>
										</table>
									</form>	
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- footer -->
			<jsp:include page="../common/studyFooter.jsp"/>
        </div>
    </div>
</body>
</html>