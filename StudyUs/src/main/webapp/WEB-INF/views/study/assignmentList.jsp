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
                                    <li class="breadcrumb-item text-muted" aria-current="page">Assignment</li>
                                </ol>
                            </nav>
                        </div>
                    </div>
                    <div class="col-lg-3 align-self-center">
        				<form id="searchForm" action="/study/board/search" method="get">
		                    <div class="customize-input float-right">
                            	<input name="searchValue" class="form-control custom-shadow custom-radius border-0 bg-white" type="text" placeholder="프로젝트 검색" aria-label="Search">
                            	<i class="form-control-icon" data-feather="search" onclick="#"></i>
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
                <div class="row">
                    <div class="col-12">
                    
                    	<!-- 프로젝트 리스트 ----------------------------------->
                    	<div class="card">
                    		<div class="card-body">
	                    		<h5 class="card-title group-title">현재 진행중인 프로젝트</h5>
								<div class="owl-carousel owl-theme">
									<%-- <c:forEach var="group" items="${ grList }"> --%>
										<div class="item" style="background-color : rgb(196, 178, 234)" onclick="location.href='/study/assignment?grNo='">
											<div class="dropdown">
												<a id="group-delete" class="btn dropdown-toggle float-right" href="javascript:void(0)" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
											    	<i class="fas fa-times"></i>
												</a>
												<div class="dropdown-menu text-center" aria-labelledby="dropdownMenuLink">
											    	<a class="dropdown-item" href="#">숨김</a>
											    	<a class="dropdown-item" href="#">삭제</a>
											  	</div>
											</div>
											<div>{ 프로젝트명 }</div>
											<div>인원수</div>
										</div>
									<%-- </c:forEach> --%>
								</div>
	                    		<label class="btn btn-outline-secondary float-right">
	                                <div class="custom-control custom-radio">
	                                    <input type="checkbox" id="customCheck" name="options" value="oldView"
	                                        class="custom-control-input">
	                                    <label class="custom-control-label" for="customCheck">
	                                    	숨김 프로젝트 보기
	                                    </label>
	                                </div>
	                            </label>
	                            <c:if test="${ loginUser.mbNo == study.leaderNo }">
                            	<button class="btn btn-secondary float-right addGroup" data-toggle="modal" data-target="#addGroup">추가</button>
                            	
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
									        		<input class="form-control" type="text" id="group-name" required placeholder="프로젝트명">
									        	</div>
									        	<div class="form-group">
									        		<label for="group-info">설명</label>
									        		<textarea class="form-control" id="group-info" rows="5" placeholder="프로젝트명 설명"></textarea>
									        	</div>
									        	<div class="form-group">
									        		<label for="group-color">색</label>
									        		<select id="group-color" class="custom-select">
							                            <option selected value="1"></option>
							                            <option value="2"></option>
							                            <option value="3"></option>
							                            <option value="4"></option>
							                        </select>
									        	</div>
									        	<div class="form-group">
									        		<label for="group-member">참여 스터디원</label>
									        		<select id="group-member" class="custom-select">
							                            <option selected value="1"></option>
							                            <option value="2"></option>
							                            <option value="3"></option>
							                            <option value="4"></option>
							                        </select>
									        	</div>
									      	</div>
									      	<div class="modal-footer">
									        	<button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
									        	<button id="addGroup-btn" type="button" class="btn btn-primary">추가</button>
									   		</div>
								    	</div>
									</div>
                            	</div>
	                            </c:if>
                    		</div>
                    	</div>
                    	
                    	<!-- 프로젝트 하나 정보 ----------------------------------->
                    	
                    	<!-- assignmentList.js에 보내주는 용도 -->
                    	<input type="hidden" id="tBox-color" value="${ asGroup.grColor }">
                    	
                    	<c:if test="${ groupNo > 0 }">
                    	<div class="oneStudy card">
                        	<div class="card-body">
                        		<h5 class="card-title text-white">${ asGroup.grName }</h5>
                        		<hr>
                        		<div class="text-white">
	                        		${ asGroup.grInfo }
                        		</div>
                        		<div>
                        			<div class="float-left">
                        			<!-- 과제 할당된 사람 정보가 들어가는 부분 -->
                        			</div>
                        			
                        			<c:if test="${ loginUser.mbNo == study.leaderNo }">
                        			<button type="button" class="btn btn-light float-right" data-toggle="modal" data-target="#modifyGroup">수정</button>
                        			
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
										      		<input type="hidden" id="re-group-no" value="${ asGroup.grNo }">
										        	<div class="form-group">
										        		<label for="re-group-name">프로젝트명</label>
										        		<input class="form-control" type="text" id="re-group-name" required value="${ asGroup.grName }">
										        	</div>
										        	<div class="form-group">
										        		<label for="re-group-info">설명</label>
										        		<textarea class="form-control" id="re-group-info" rows="5">${ asGroup.grInfo }</textarea>
										        	</div>
										        	<div class="form-group">
										        		<label for="re-group-color">색</label>
										        		<select id="re-group-color" class="custom-select">
								                            <option value="1" <c:if test="${ asGroup.grColor == 1 }">selected</c:if>></option>
								                            <option value="2" <c:if test="${ asGroup.grColor == 2 }">selected</c:if>></option>
								                            <option value="3" <c:if test="${ asGroup.grColor == 3 }">selected</c:if>></option>
								                            <option value="4" <c:if test="${ asGroup.grColor == 4 }">selected</c:if>></option>
								                        </select>
										        	</div>
										        	<!-- <div class="form-group">
										        		<label for="group-member">참여 스터디원</label>
										        		for문 돌려서 스터디원 닉네임 전부 가져오기
										        		<select id="group-member" class="custom-select">
								                            <option selected value="1"></option>
								                            <option value="2"></option>
								                            <option value="3"></option>
								                            <option value="4"></option>
								                        </select>
										        	</div> -->
										      	</div>
										      	<div class="modal-footer">
										        	<button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
										        	<button id="modifyGroup-btn" type="button" class="btn btn-primary">수정</button>
										   		</div>
									    	</div>
										</div>
	                            	</div>
	                            	</c:if>
	                            	
                        		</div>
                        	</div>
                        </div>
                        </c:if>
                    	
                    	<!-- 과제 리스트 테이블 ----------------------------------->
                        <div class="card">
                        	<div class="card-body">
                        	
	                            <table class="table .table-hover">
	                            	<thead id="theadColor">
	                            		<tr>
	                            			<td>번호</td>
	                            			<td>과제명</td>
	                            			<td>등록일</td>
	                            			<td>기한</td>
	                            		</tr>
	                            	</thead>
	                            	<tbody id="tbodyColor">
	                            		<c:forEach items="${ aList }" var="aOne">
	                            		
	                            		<c:url var="asDetail" value="/study/assignment/detail">
											<c:param name="asNo" value="${ aOne.asNo }"></c:param>
										</c:url>
		                            	<tr onclick="location.href='${ asDetail }'">
		                            		<td>${ aOne.asNo }</td>
	                            			<td>${ aOne.asName }</td>
	                            			<td>${ aOne.asInsertDate }</td>
	                            			<td>${ aOne.asDeadLine }</td>
		                            	</tr>
		                            	
		                            	</c:forEach>
	                            	</tbody>
	                            </table>
	                            
	                            <nav id='asPage'></nav>
                            </div>
                        </div>
                        
                        
                    </div>
                </div>
                
                <!-- 글쓰기 버튼 --> 
                <c:if test="${ loginUser.mbNo == study.leaderNo }">
                <div id="float-btn">
					<button id="write-btn" onclick="location.href='/study/assignment/registerView'"><i class="fas fa-edit"></i><span>추가</span></button>          
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