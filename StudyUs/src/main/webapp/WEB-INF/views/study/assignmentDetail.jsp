<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
                    
                    	<!-- 과제 ---------------------------------------------->

                        <div class="card">
                        	<div style="background-color : #e9ecef">
	                        	<div class="dateProgress card-body back${ assignmentGroup.grColor } text-white">
		                        	<span><b>${ assignment.asName }</b></span>
	                        		<span class="float-right">${ assignmentGroup.grName }</span>
	                        	</div>
                        	</div>
                            <div class="card-body ">
                            	<div>
	                            	<h6 id="insertDate" class="date card-subtitle float-left">${ assignment.asInsertDate }</h6>
	                                <h6 id="deadLine" class=" date card-subtitle float-right text-primary font-weight-bolder">${ assignment.asDeadLine }</h6>
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
                            
                            <div class="card-body">
                            	<a id="collapseBtn" class="btn btn-sm btn-light btn-rounded float-right" data-toggle="collapse" href="#collapseDiv" role="button" aria-expanded="false" aria-controls="collapseExample">
								    <span class="collapsed">제출 정보</span>
								</a>
								<hr>
								<br>
								<div class="collapse show row" id="collapseDiv">
									<div id="chart-box" class="col-4">
										<c:if test="${ (fn:length(suList)) != 0 }">
										<c:set var="pages" value="${ ((fn:length(suList)) / (fn:length(mbList))) * 100 }" />
										<div class='chart' data-percent="${ ((fn:length(suList)) / (fn:length(mbList))) * 100 }"></div>
										<p class='chart-p'>${pages+((pages%1>0.5)?(1-(pages%1))%1:-(pages%1))} %</p>
										</c:if>
										<c:if test="${ (fn:length(suList)) == 0 }">
										<div class='chart' data-percent="0"></div>
										<p class='chart-p'>0.0 %</p>
										</c:if>
									</div>
									<div class="col-8">
										<table class="table tColor${ assignmentGroup.grColor }">
											<thead class="back${ assignmentGroup.grColor } back0 text-white">
												<tr>
			                            			<td>닉네임</td>
			                            			<td>제출 여부</td>
			                            			<td>제출일</td>
	                            				</tr>
											</thead>
											<tbody>
												<c:forEach var="mOne" items="${ mbList }">
													<tr>
														<td>${ mOne.mbNickname }</td>
														<c:forEach var="sAssignment" items="${ suList }">
															<c:if test="${ sAssignment.mbNo == mOne.mbNo && sAssignment.suStatus == 1 }">
																<td><div class='btn btn-sm btn-outline-primary btn-rounded'>&nbsp;제출&nbsp;</div></td>
																<td>${ sAssignment.suInsertDate }</td>
															</c:if>
															<c:if test="${ sAssignment.mbNo == mOne.mbNo && sAssignment.suStatus == 2 }">
																<td><div class='btn btn-sm btn-outline-secondary btn-rounded'>미제출</div></td>
																<td>${ sAssignment.suInsertDate }</td>
															</c:if>
															<c:if test="${ sAssignment.mbNo != mOne.mbNo }">
																<td><div class='btn btn-sm btn-outline-secondary btn-rounded'>미제출</div></td>
																<td></td>
															</c:if>
														</c:forEach>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
								</div>
                            </div>
                        </div>
                        
                        <!-- 과제 제출 ---------------------------------------------->
                       	<input id="loginMbNo" type="hidden" value="${ loginUser.mbNo }">
                        <c:forEach var="sAssignment" items="${ suList }" varStatus="i">
                        <c:url var="suDetail" value="/study/sAssignment/detail">
                        	<c:param name="suNo" value="${ sAssignment.suNo }"></c:param>
                        </c:url>
                        <div class="card" onclick="location.href='${ suDetail }'">
                            <div class="card-body ">
                            	<h4 class="card-title">
                            		<c:if test="${ sAssignment.suStatus == 1 }">
                            			<span class="tags btn-primary">제출</span>
                            		</c:if>
                            		<c:if test="${ sAssignment.suStatus == 2 }">
                            			<span class="tags tag-free">미제출</span>
                            		</c:if>
                            	</h4>
                            	<div class="row">
	                            	<h6 class="card-subtitle col-6"><img src="/resources/images/${ sAssignment.member.mbPhoto }.png" class="rounded-circle">&nbsp;&nbsp;${ sAssignment.member.mbNickname }</h6>
	                                <h6 class="date card-subtitle col-6 text-right">${ sAssignment.suInsertDate }</h6>
                            	</div>
                            </div>
                            <c:if test="${ !empty sAssignment.suFiles }">
	                            <div class="card-body file-box">
									<c:forEach var="file" items="${ sAssignment.suFiles }" varStatus="j">
		                            	<div>
		                            		<b>첨부파일${ j.count } : </b>
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
	                            <p>${ sAssignment.suContents }</p>
                            </div>
                            
                            <!-- 댓글 -->
                            <c:if test="${ !empty sAssignment.reply }">
                            <div class="card-body">
                                <h6 class="card-subtitle float-right">댓글 <span id="rCount"></span></h6>
                                <hr>
                                
                                <div class='reply-box list-reply'>
                                	<div>
                                		<img src='/resources/images/${ sAssignment.reply.member.mbPhoto }.png' class='rounded-circle'>&nbsp;
	                                	<span class='nickName'>${ sAssignment.reply.member.mbNickname }</span>&nbsp;
	                                	<span class='insertDate'>${ sAssignment.reply.suInsertDate }</span>
                                	</div>
                                	<div class='contents-box'>${ sAssignment.reply.suContents }</div>
                                </div>
                            </div>
                            </c:if>
                        </div>
                        </c:forEach>
                        
                    </div>
                    <!-- 글쓰기 버튼 --> 
	                <div id="float-btn">
						<button id="write-btn" onclick="location.href='/study/sAssignment/registerView?asNo=${ assignment.asNo }'"><i class="fas fa-edit"></i><span>과제 제출</span></button>          
	            	</div>
                </div>
		        <!-- footer -->
				<jsp:include page="../common/studyFooter.jsp"/>
            </div>
        </div>
    </div>
    
     <!-- 해당 페이지 JS 파일 -->
    <script src="/resources/js/assignmentDetail.js"></script>
    <script src="/resources/css/study/assets/libs/moment/min/moment.min.js"></script>
    <script src="/resources/js/jquery.easy-pie-chart.js"></script>
</body>
</html>