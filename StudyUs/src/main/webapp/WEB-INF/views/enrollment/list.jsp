<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>가입신청 목록</title>

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
                    <div class="col-12">
                        <div class="card">
                            <div class="card-body">
                                <h4 class="card-title">가입신청 목록</h4>
                                <%-- <h6 class="card-subtitle">Add <code>.table-hover</code> to enable a hover state on table
                                    rows within a <code>&lt;tbody&gt;</code>.</h6> --%>
                            </div>
                            <div class="table-responsive">
                                <table class="table table-hover">
                                    <thead>
                                        <tr>
                                            <th scope="col">아이디</th>
                                            <th scope="col">닉네임</th>
                                            <th scope="col">신청일시</th>
                                            <th scope="col">가입인사</th>
                                            <th scope="col"></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<c:forEach var="enrollment" items="${enrollmentWithMemberList }" varStatus="i">
                                    	<tr>
                                            <td><c:out value="${enrollment.mbId }"></c:out></td>
                                            <td><c:out value="${enrollment.mbNickname }"></c:out></td>
                                            <td><c:out value="${enrollment.insertDate }"></c:out></td>
                                            <td><c:out value="${enrollment.message }"></c:out></td>
                                            <td>
                                            	<!-- <input type="button" class="btn btn-sm btn-light" value="세부정보" onclick="modalOpen();" data-toggle="modal" data-target="#enrollmentModal"> -->
                                            	<input type="button" class="btn btn-sm btn-primary" value="수락" onclick="updateEnrollment(${enrollment.enrollmentNo}, 1);">
                                            	<input type="button" class="btn btn-sm btn-danger" value="거부" onclick="updateEnrollment(${enrollment.enrollmentNo}, 2);">
                                            </td>
                                        </tr>
                                    	</c:forEach>
                                        <tr class="d-none">
                                            <td>{mbId}</td>
                                            <td>{mbNickname}</td>
                                            <td>{insertDate}</td>
                                            <td>{message}</td>
                                            <td>
                                            	<input type="button" class="btn btn-sm btn-light" value="세부정보">
                                            	<input type="button" class="btn btn-sm btn-primary" value="수락">
                                            	<input type="button" class="btn btn-sm btn-danger" value="거부">
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
	            </div>
            </div>

			<!-- <div class="modal fade" id="enrollmentModal" tabindex="-1" aria-labelledby="enrollmentModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="enrollmentModalLabel">가입신청 세부정보</h5>
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body">
							<div class="row mb-2">
								<div class="col-3"><b>아이디</b></div>
								<div class="col" id="modal-id">{아이디}</div>
							</div>
							<div class="row mb-2">
								<div class="col-3"><b>닉네임</b></div>
								<div class="col" id="modal-nickname">{닉네임}</div>
							</div>
							<div class="row mb-2">
								<div class="col-3"><b>신청일시</b></div>
								<div class="col" id="modal-datetime">{신청일시}</div>
							</div>
							<div class="row mb-2">
								<div class="col-3"><b>평판</b></div>
								<div class="col" id="modal-reputation">{평판}</div>
							</div>
							<div class="row mb-2">
								<div class="col-3"><b>가입인사</b></div>
								<div class="col" id="modal-greeting">{가입인사}</div>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
							<button type="button" class="btn btn-danger" onclick="updateEnrollment(1);">거부</button>
							<button type="button" class="btn btn-primary" onclick="updateEnrollment(2);">수락</button>
						</div>
					</div>
				</div>
			</div> -->

			<!-- footer -->
			<jsp:include page="../common/studyFooter.jsp"/>
        </div>
    </div>
    
    <script src="/resources/js/enrollmentList.js"></script>

</body>
</html>