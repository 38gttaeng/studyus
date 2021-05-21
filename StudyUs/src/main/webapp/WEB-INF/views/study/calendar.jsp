<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
    <link href="/resources/css/study/assets/libs/fullcalendar/fullcalendar.min.css" rel="stylesheet" />
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
	    <!-- 선택된 메뉴에 selected / active 추가해주기 -->
	    <script>
	    	$("#sidebarnav>li:nth-child(4)").addClass("selected");
	    	$("#sidebarnav>li:nth-child(4) a").addClass("active");
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
            		
            		<!-- 저장하는 버튼(팀장만) -->
            		<!-- <div class="col-5 align-self-center">
                        <div class="customize-input float-right">
                            <button class="btn btn-primary"><i class="fas fa-check"></i> 저장 </button>
                        </div>
                    </div> -->
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
    <script src="/resources/css/study/dist/js/pages/calendar/cal-init.js"></script>
    
</body>
</html>