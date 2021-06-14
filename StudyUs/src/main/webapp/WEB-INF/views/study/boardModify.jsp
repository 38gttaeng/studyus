<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
    <!-- 추가 css -->
    <link href="/resources/css/studyus/register.css" rel="stylesheet">
	<title>게시글 수정 : StudyUs</title>
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
	                            <li class="breadcrumb-item text-muted" aria-current="page"><a href="/study/board?boCategory=${ category }">Board</a></li>
	                            <li class="breadcrumb-item text-muted active" aria-current="page">Modify</li>
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
                	<div class="col-1"></div>
                    <div class="col-10">
                        <div class="card">
                            <div class="card-body">
	                        	<form id="postForm" action="/study/board/modify" method="post" enctype="multipart/form-data">
									
									<input type="hidden" name="boNo" value="${ board.boNo }">
									<!-- register.js 파일에 수정임을 알리기 위해서 -->
									<input type="hidden" name="viewCheck" value="m">
									
	                        		<div id="table-box">
										<table class="table-responsive">
											<tr>
												<td>카테고리</td>
												<td>
													<select id="select-box" class="form-control" name="boCategory">
														<option value="1" <c:if test="${ board.boCategory == 1 }">selected</c:if>>자유</option>
														<option value="2" <c:if test="${ board.boCategory == 2 }">selected</c:if>>공유</option>
														<option value="3" <c:if test="${ board.boCategory == 3 }">selected</c:if>>질문</option>
													</select>
												</td>
											</tr>
											<tr>
												<td>제목</td>
												<td>
													<input type="text" class="form-control" name="boTitle" value="${ board.boTitle }">
													<span id="title-msg" class="invalid-feedback">제목을 1자 이상 입력하세요.</span>
												</td>
											</tr>
											<tr>
												<td>작성자</td>
												<td>
													<input type="text" class="form-control" size="50" value="${ loginUser.mbNickname }" readonly>
													<input type="hidden" name="mbNo" value="${ loginUser.mbNo }">
												</td> 
											</tr>
											<tr>
												<td valign=top>내용</td>
												<td>
													<input type="hidden" name="boContents">
													<input type="hidden" name="picList">
      												<div id="editor" style="min-height:400px;">${ board.boContents }</div>
												</td>
											</tr>
											<tr>
												<td>첨부파일</td>
												<td>
													<c:if test="${ !empty board.boFiles }">
														<c:forEach var="file" items="${ board.boFiles }" varStatus="i">
															<div id="file" class="text-muted">
																<b>첨부파일${ i.count } : </b>
																${ file.fiRealName }&nbsp;&nbsp;
																<i class='fas fa-minus text-primary button-delete-file' style='cursor:pointer'></i>
																<input type="hidden" value="${ file.fiNo }">
															</div>
														</c:forEach>
														<input id="delFiles" type="hidden" name="delFiles">
													</c:if>
													<button type="button" id='button-add-file' class="btn text-primary"><i class="fas fa-plus"></i>  파일 추가</button>
													<div id="my-form"></div>
												</td>
											</tr>
											<tr>
												<td colspan="2" align="center">
													<input id="reset-btn" type="button" class="btn waves-effect waves-light btn-light" value="취소">
													<input id="submit-btn" type="button" class="btn waves-effect waves-light btn-primary" value="수정">
												</td>
											</tr>
										</table>
									</div>
								</form>	
                    		</div>
                		</div>
            		</div>
            		<div class="col-1"></div>
            	</div>
           	</div>

            <!-- footer -->
			<jsp:include page="../common/studyFooter.jsp"/>
        </div>
    </div>
    
    <!-- 해당 페이지 JS 파일 -->
    <script src="/resources/js/boardRegister.js"></script>
</body>
</html>