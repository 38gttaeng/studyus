<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
    <!-- 추가 css -->
    <link href="/resources/css/studyus/assignment.css" rel="stylesheet">
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
                    <div class="col-lg-9 align-self-center">
                        <h4 class="page-title text-truncate text-dark font-weight-medium mb-1">과제</h4>
                        <div class="d-flex align-items-center">
                            <nav aria-label="breadcrumb">
                                <ol class="breadcrumb m-0 p-0">
                                    <li class="breadcrumb-item text-muted" aria-current="page"><a href="/study">Study</a></li>
                                    <li class="breadcrumb-item text-muted" aria-current="page"><a href="/study/assignment?grNo=0">Assignment</a></li>
                                    <c:if test="${ !empty asGroup }">
                                    <li class="breadcrumb-item text${ asGroup.grColor } font-weight-bold" aria-current="page">${ asGroup.grName }</li>
                                	</c:if>
                                </ol>
                            </nav>
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
                    		<div class="card-body">
	                    		<h5 class="card-title group-title">현재 진행중인 프로젝트</h5>
								<div id="group-list-box" class="owl-carousel owl-theme"></div>
								<input type="hidden" id="memberNo" value="${ loginUser.mbNo }">
								<input type="hidden" id="leaderNo" value="${ study.leaderNo }">
	                            <c:if test="${ loginUser.mbNo == study.leaderNo }">
                            	<button class="btn btn-secondary float-right addGroup" data-toggle="modal" data-target="#addGroup">추가</button>
                            	
	                            <form id="groupWriteForm" action="/study/assignment/addGroup" method="post">
                            	<div class="modal fade" id="addGroup" tabindex="-1" aria-labelledby="addGroupLabel" aria-hidden="true" data-backdrop="static">
                            		<div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
								    	<div class="modal-content">
								    		<div class="modal-header">
								        		<h5 class="modal-title" id="addGroupLabel">프로젝트 추가</h5>
								        		<button type="button" class="close" data-dismiss="modal" aria-label="Close">
								          			<span aria-hidden="true">&times;</span>
								 				</button>
								 			</div>
									      	<div class="modal-body">
									        	<div class="form-group">
									        		<label for="group-name">프로젝트명</label>
									        		<input name="grName" class="form-control" type="text" id="group-name" required placeholder="프로젝트명">
									        		<span id="name-msg" class="invalid-feedback">프로젝트명을 1자 이상 입력하세요.</span>
									        	</div>
									        	<div class="form-group">
									        		<label for="group-info">설명</label>
									        		<textarea name="grInfo" class="form-control" id="group-info" rows="5" placeholder="프로젝트명 설명"></textarea>
									        	</div>
									        	<div class="form-group">
									        		<label for="group-color">색</label>
									        		<select name="grColor" id="group-color" class="custom-select">
							                            <option selected value="1"></option>
							                            <option value="2"></option>
							                            <option value="3"></option>
							                            <option value="4"></option>
							                        </select>
									        	</div>
									        	<div class="form-group">
									        		<label for="group-member">참여 스터디원</label>
									        		<select id="group-member" class="custom-select">
									        			<option selected>선택해서 추가</option>
									        			<!-- 여기에 추가됨 -->
							                        </select>
							                        <div id="input-box"></div>
							                        <input type="hidden" name="grMember">
									        	</div>
									      	</div>
									      	<div class="modal-footer">
									        	<button id="resetGroup-btn" type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
									        	<button id="addGroup-btn" type="button" class="btn btn-primary">추가</button>
									   		</div>
								    	</div>
									</div>
                            	</div>
                            	</form>
	                            </c:if>
                    		</div>
                    	</div>
                    	
                    	<!-- 프로젝트 하나 정보 ----------------------------------->
                    	
                    	<c:if test="${ !empty asGroup }">
                    	
                    	<div class="oneStudy back${ asGroup.grColor } card">
                        	<div class="card-body">
                        		<h5 class="card-title text-white">${ asGroup.grName }</h5>
                        		<hr>
                        		<div class="text-white">
	                        		${ asGroup.grInfo }
                        		</div>
                        		<div id="info-bottom">
                        			<div class="float-left">
                        				<c:forEach items="${ mbList }" var="mOne">
                        					<div class='mem-btn btn btn-sm btn-rounded text${ asGroup.grColor }'>${ mOne.mbNickname }</div>
                        				</c:forEach>
                        			</div>
                        			
                        			<c:if test="${ loginUser.mbNo == study.leaderNo }">
                        			<button id="modify-btn" type="button" class="btn btn-secondary float-right" data-toggle="modal" data-target="#modifyGroup">수정</button>
                        			
                        			<form id="groupModifyForm" action="/study/assignment/modifyGroup" method="post">
                        			<div class="modal fade" id="modifyGroup" tabindex="-1" aria-labelledby="modifyGroupLabel" aria-hidden="true" data-backdrop="static">
	                            		<div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
									    	<div class="modal-content">
									    		<div class="modal-header">
									        		<h5 class="modal-title" id="modifyGroupLabel">프로젝트 수정</h5>
									        		<button type="button" class="close" data-dismiss="modal" aria-label="Close">
									          			<span aria-hidden="true">&times;</span>
									 				</button>
									 			</div>
										      	<div class="modal-body">
										      		<input type="hidden" name="grNo" value="${ asGroup.grNo }">
										        	<div class="form-group">
										        		<label for="re-group-name">프로젝트명</label>
										        		<input name="grName" class="form-control" type="text" id="re-group-name" required value="${ asGroup.grName }">
										        	</div>
										        	<div class="form-group">
										        		<label for="re-group-info">설명</label>
										        		<textarea name="grInfo" class="form-control" id="re-group-info" rows="5">${ asGroup.grInfo }</textarea>
										        	</div>
										        	<div class="form-group">
										        		<label for="re-group-color">색</label>
										        		<select name="grColor" id="re-group-color" class="custom-select back${ asGroup.grColor }">
								                            <option value="1" <c:if test="${ asGroup.grColor == 1 }">selected</c:if>></option>
								                            <option value="2" <c:if test="${ asGroup.grColor == 2 }">selected</c:if>></option>
								                            <option value="3" <c:if test="${ asGroup.grColor == 3 }">selected</c:if>></option>
								                            <option value="4" <c:if test="${ asGroup.grColor == 4 }">selected</c:if>></option>
								                        </select>
										        	</div>
										      	</div>
										      	<div class="modal-footer">
										        	<button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
										        	<button id="modifyGroup-btn" type="button" class="btn btn-primary">수정</button>
										   		</div>
									    	</div>
										</div>
	                            	</div>
	                            	</form>
	                            	</c:if>
	                            	
                        		</div>
                        	</div>
                        </div>
                        </c:if>
                    	
                    	<!-- 과제 리스트 테이블 ----------------------------------->
                        <div class="card">
                        	<div class="card-body">
                        	
	                            <table class="table table-hover tColor${ asGroup.grColor } tColor0">
	                            	<thead class="back${ asGroup.grColor } back0 text-white">
	                            		<tr>
	                            			<td>번호</td>
	                            			<td>과제명</td>
	                            			<td>등록일</td>
	                            			<td>기한</td>
	                            		</tr>
	                            	</thead>
	                            	<tbody>
	                            		<c:forEach items="${ asList }" var="aOne">
	                            		
	                            		<c:url var="asDetail" value="/study/assignment/detail">
											<c:param name="asNo" value="${ aOne.asNo }"></c:param>
										</c:url>
		                            	<tr>
		                            		<td>${ aOne.asNo }</td>
	                            			<td class="tableName" onclick="location.href='${ asDetail }'">${ aOne.asName }</td>
	                            			<td>${ aOne.asInsertDate }</td>
	                            			<td>${ aOne.asDeadLine }</td>
		                            	</tr>
		                            	
		                            	</c:forEach>
	                            	</tbody>
	                            </table>
	                            
	                            <nav id='asPage'>
	                            	<ul class='pagination pagination-sm justify-content-center'>
	                            	
		                            	<c:url var="before" value="/study/assignment">
											<c:param name="page" value="${ pi.currentPage - 1 }"/>
											<c:param name="grNo" value="${ groupNo }"/>
										</c:url>
										<c:if test="${ pi.currentPage > 1 }">
		                            		<li class="page-item" onclick="location.href='${ before }'">
		                            			<span class='page-link' aria-label='Prev'>&laquo;</span>
		                            		</li>
	                            		</c:if>
	                            		
	                            		<c:forEach var="p" begin="${ pi.startPage }" end="${ pi.endPage }">
	                            		<c:url var="pagination" value="/study/assignment">
											<c:param name="page" value="${ p }"/>
											<c:param name="grNo" value="${ groupNo }"/>
										</c:url>
										<c:if test="${ p eq pi.currentPage }">
											<li class='page-item active'>
												<span class='page-link'>${ p }</span>
											</li>
										</c:if>
										<c:if test="${ p ne pi.currentPage }">
											<li class='page-item' onclick="location.href='${ pagination }'">
												<span class='page-link'>${ p }</span>
											</li>
										</c:if>
	                            		</c:forEach>
	                            		
	                            		<c:url var="after" value="/study/assignment">
											<c:param name="page" value="${ pi.currentPage + 1 }"/>
											<c:param name="grNo" value="${ groupNo }"/>
										</c:url>
										<c:if test="${ pi.currentPage < pi.maxPage }">
		                            		<li class="page-item" onclick="location.href='${ after }'">
		                            			<span class='page-link' aria-label='Next'>&raquo;</span>
		                            		</li>
	                            		</c:if>
	                            		
	                            	</ul>
	                            </nav>
                            </div>
                        </div>
                        
                        
                    </div>
                </div>
                
                <!-- 글쓰기 버튼 --> 
                <c:if test="${ loginUser.mbNo == study.leaderNo && asGroup != null}">
                <div id="float-btn">
					<button id="write-btn" onclick="location.href='/study/assignment/registerView'"><i class="fas fa-edit"></i><span>과제 등록</span></button>          
            	</div>
            	</c:if>
            </div>
            <!-- footer -->
			<jsp:include page="../common/studyFooter.jsp"/>
        </div>
    </div>
    
     <!-- 해당 페이지 JS 파일 -->
    <script src="/resources/js/assignmentList.js"></script>
</body>
</html>