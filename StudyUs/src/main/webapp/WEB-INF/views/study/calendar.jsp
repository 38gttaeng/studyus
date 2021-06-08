<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
    <link href="/resources/css/study/assets/libs/fullcalendar/fullcalendar.min.css" rel="stylesheet" />
    <link href="/resources/css/studyus/assignmentColor.css" rel="stylesheet">
    <link href="/resources/css/studyus/calendar.css" rel="stylesheet"> 
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
                    <div class="col-7 align-self-center">
                        <h4 class="page-title text-truncate text-dark font-weight-medium mb-1">일정</h4>
                        <div class="d-flex align-items-center">
                            <nav aria-label="breadcrumb">
                                <ol class="breadcrumb m-0 p-0">
                                    <li class="breadcrumb-item text-muted active" aria-current="page">Study</li>
                                    <li class="breadcrumb-item text-muted" aria-current="page">Calendar</li>
                                </ol>
                            </nav>
                		</div>
            		</div>
            		
            		<!-- 검색? -->
            	</div>
            </div>
            
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
                                                	<table id="calendar-events">
                                                		<tr class="calendar-events cEvents0">
                                                			<td>전체보기</td>
                                                		</tr>
                                                		<tr class="calendar-events cEvents1">
                                                			<td>과제 기한</td>
                                                		</tr>
                                                		<tr class="calendar-events cEvents2">
                                                			<td>모임 일정</td>
                                                		</tr>
                                                		<tr>
                                                			<td>
                                                				<div id="checkHide" class="custom-control custom-checkbox">
	                                                				<input type="checkbox" class="custom-control-input" id="drop-remove">
	                                                				<label class="custom-control-label" for="drop-remove">취소된 예약 숨기기</label>
                                                				</div>
                                                			</td>
                                                		</tr>
                                                	</table>
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
            
            <!-- footer -->
			<jsp:include page="../common/studyFooter.jsp"/>
		</div>
	</div>
	
	<!--This page JavaScript -->
	<script src="/resources/css/study/assets/extra-libs/taskboard/js/jquery.ui.touch-punch-improved.js"></script>
    <script src="/resources/css/study/assets/extra-libs/taskboard/js/jquery-ui.min.js"></script>
    <script src="/resources/css/study/assets/libs/moment/min/moment.min.js"></script>
    <script src="/resources/css/study/assets/libs/fullcalendar/fullcalendar.js"></script>
    <script src="/resources/css/study/assets/libs/fullcalendar/gcal.min.js"></script>
    <script src="/resources/js/calendar.js"></script>
    
</body>
</html>