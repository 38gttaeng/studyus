<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/resources/css/member/myPage.css">
<title>마이페이지</title>
<link href="/resources/css/fullcalendar/assets/libs/fullcalendar/dist/fullcalendar.min.css" rel="stylesheet" />
<link href="/resources/css/fullcalendar/dist/css/style.min.css" rel="stylesheet">
<link href="/resources/css/study/adminmart.css" rel="stylesheet"> 
<link href="/resources/css/studyus/assignmentColor.css" rel="stylesheet">
<link href="/resources/css/studyus/calendar.css" rel="stylesheet"> 
<link href="/resources/css/studyus/reply.css" rel="stylesheet">
<script type="text/javascript" src="http://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="/resources/js/myPage.js"></script>
</head>
<body>
	<!-- menubar -->
	<jsp:include page="/WEB-INF/views/common/menubar.jsp"/>
	<div class="grey-zone"></div>
	<div class="myPage-box">
		<div class="info-zone">
			<img src="/resources/images/${loginUser.mbPhoto}.png" class="rounded-circle" width="100" style="margin-top: -65px; margin-left: 15px;">
			<div class="mem-info">
				<a style="font-weight: bold; font-size: 20px;">${loginUser.mbNickname}</a>님<br>
				스터디 포인트 ${loginUser.mbReputation}
				<input type="hidden" id="mbNo" value="${loginUser.mbNo}">
			</div>
			<div class="btn-zone">
				<input type="button" value="내 정보 수정" class="myPage-btn" onclick="location.href='/member/myInfo'">
				&nbsp;&nbsp;
				<input type="button" value="결제 관리" class="myPage-btn" onclick="location.href='/member/purchaseView'">
			</div>
		</div>
		<div class="box-zone">
			<div class="my-study">
				<div class="menu-mark"><span>나의 스터디</span></div>
				
			</div>
			
			<div class="my-calender">
				<div class="menu-mark"><span>나의 일정</span></div>
				<!-- ============================================================== -->
	            <!-- Container fluid  -->
	            <!-- ============================================================== -->
	           	<div class="container-fluid">
	                <div class="row">
	                    <div class="col-md-12">
	                        <div class="card">
	                             <div class="row">
	                                 <div class="col-lg-3 border-right pr-0">
	                                     <div class="card-body border-bottom">
	                                         <h4 class="card-title mt-2">스터디 일정</h4>
	                                     </div>
	                                     <div class="card-body">
	                                         <div class="row">
	                                             <div class="col-md-12">
	                                                	<table id="calendar-events" onclick="location.href='#'">
	                                                		<tr class="calendar-events cEvents0">
	                                                			<td colspan="2">전체보기</td>
	                                                		</tr>
	                                                		<tr class="calendar-events cEvents1">
	                                                			<td>과제 기한</td>
	                                                			<td><i class="fa fa-circle text-danger"></i></td>
	                                                		</tr>
	                                                		<tr class="calendar-events cEvents2">
	                                                			<td>&nbsp;&nbsp;스터디카페 대여정보&nbsp;&nbsp;</td>
	                                                			<td><i class="fa fa-circle text-warning"></i></td>
	                                                		</tr>
	                                                		<tr class="calendar-events cEvents3">
	                                                			<td>모임 일정</td>
	                                                			<td>
	                                                				<i class="fa fa-circle text-info"></i>&nbsp;
	                                                      	<i class="fa fa-circle text-success"></i>&nbsp;
	                                                      	<i class="fa fa-circle text-secondary"></i>
	                                                			</td>
	                                                		</tr>
	                                                	</table>
	                                             </div>
	                                         </div>
	                                         
	                                         <!-- modal창 -->
	                                         <div id="my-event">
		                                         <div class="modal fade" id="centermodal" tabindex="-1" role="dialog" aria-hidden="true">
					                             	<div class="modal-dialog modal-dialog-centered">
					                                	<div class="modal-content">
					                                    	<div class="modal-header">
					                                        	<h4 class="modal-title" id="myCenterModalLabel">일정 정보</h4>
					                                        	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					                                        </div>
					                                        <div class="modal-body">
					                                        	<h5>제목</h5>
					                                        	<p>기한</p>
					                                         	<p>닉네임들</p>
					                                        </div>
					                                        <div class="modal-footer">
				                                                <button type="button" class="btn btn-light"
				                                                    data-dismiss="modal">취소</button>
				                                                <button type="button" class="btn btn-primary">상세보기</button>
				                                            </div>
					                                	</div>
					                             	</div>
					                            </div>
				                        	</div>
		                             	</div>
	                                 </div>
	                                 <div class="col-lg-9">
	                                     <div class="card-body b-l calender-sidebar">
	                                         <div id="calendar"></div>
	                                     </div>
	                                 </div>
	                             </div>
	                        </div>
	                        
	                        <div class="modal fade" id="info" tabindex="-1" aria-labelledby="infoLabel" aria-hidden="true" data-backdrop="static">
		                        <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
							    	<div class="modal-content">
							    		<div id="modal-header" class="modal-header">
							        		<h5 class="modal-title text-white" id="infoLabel">일정</h5>
							        		<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							          			<span aria-hidden="true" class="text-white">&times;</span>
							 				</button>
							 			</div>
								      	<div class="modal-body">
								        	<div class="form-group">
								        		<label for="name">일정명</label>
								        		<input id="name" class="form-control" type="text" readonly>
								        	</div>
								        	<div class="form-group">
								        		<label for="inDate">시작일시</label>
								        		<div class="clearfix">
									        		<input id="inDate" class="form-control float-left" type="date" readonly>
									        		<input id="inTime" class="form-control float-right" type="time" readonly>
								        		</div>
								        	</div>
								        	<div class="form-group">
								        		<label for="deDate">종료일시</label>
								        		<div class="clearfix">
									        		<input id="deDate" class="form-control float-left" type="date" readonly>
									        		<input id="deTime" class="form-control float-right" type="time" readonly>
								        		</div>
								        	</div>
								      	</div>
								      	<div class="modal-footer">
								        	<button id="info-btn" type="button" class="btn btn-primary">더보기</button>
								   		</div>
							    	</div>
								</div>
	                    	</div>
	                        
	                    </div>
	                </div>
	           	</div>
			</div>
			
			<div class="my-review">
				<div class="menu-mark"><span>나의 후기</span></div>
				<div class="review-box">
					<div id="rList"></div>
				</div>
			</div>
		</div>
	</div>
	
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
	
	<!-- ============================================================== -->
	<!-- All Jquery -->
	<!-- ============================================================== -->
	<script src="/resources/css/fullcalendar/assets/libs/jquery/dist/jquery.min.js"></script>
	<script src="/resources/css/fullcalendar/assets/extra-libs/taskboard/js/jquery-ui.min.js"></script>
	<script src="/resources/css/fullcalendar/assets/libs/popper.js/dist/umd/popper.min.js"></script>
	<script src="/resources/css/fullcalendar/assets/libs/bootstrap/dist/js/bootstrap.min.js"></script>
	<!-- apps -->
	<script src="/resources/css/fullcalendar/dist/js/app-style-switcher.js"></script>
	<script src="/resources/css/fullcalendar/dist/js/feather.min.js"></script>
	<script src="/resources/css/fullcalendar/assets/libs/perfect-scrollbar/dist/perfect-scrollbar.jquery.min.js"></script>
	<script src="/resources/css/fullcalendar/dist/js/sidebarmenu.js"></script>
	<!--Custom JavaScript -->
	<script src="/resources/css/fullcalendar/dist/js/custom.min.js"></script>
	<!--This page JavaScript -->
	<script src="/resources/css/fullcalendar/assets/libs/moment/min/moment.min.js"></script>
	<script src="/resources/css/fullcalendar/assets/libs/fullcalendar/dist/fullcalendar.min.js"></script>
	<script src="/resources/css/fullcalendar/dist/js/pages/calendar/cal-init.js"></script>
	<script type="text/javascript" src="/resources/js/myPage.js"></script>
	
</body>
</html>