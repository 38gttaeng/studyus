<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 추가 css -->
    <link href="/resources/css/study/assets/extra-libs/datatables.net-bs4/css/dataTables.bootstrap4.css" rel="stylesheet">
	<link href="/resources/css/study/assets/libs/fullcalendar/fullcalendar.min.css" rel="stylesheet" />
	<link href="/resources/css/studyus/reservationManage.css" rel="stylesheet">
    <title>예약관리 : StudyUs</title>
</head>
<body>
	<!-- ============================================================== -->
    <!-- Main wrapper -->
    <!-- ============================================================== -->
    <div id="main-wrapper" data-theme="light" data-layout="vertical" data-navbarbg="skin6" data-sidebartype="full"
        data-sidebar-position="fixed" data-header-position="fixed" data-boxed-layout="full">
        
        <!-- menubar -->
	    <jsp:include page="../common/adminMenubar.jsp"/>
        
        <!-- ============================================================== -->
        <!-- Page wrapper  -->
        <!-- ============================================================== -->
        <div class="page-wrapper">
            <!-- ============================================================== -->
            <!-- Bread crumb and right sidebar toggle -->
            <!-- ============================================================== -->
            <div class="page-breadcrumb">
	            <div>
	                <h4 class="page-title text-truncate text-dark font-weight-medium mb-1">예약 관리</h4>
	                <div class="d-flex align-items-center">
	                    <nav aria-label="breadcrumb">
	                        <ol class="breadcrumb m-0 p-0">
	                            <li class="breadcrumb-item"><a href="#"></a>
	                            </li>
	                        </ol>
	                    </nav>
	                </div>
	            </div>
            </div>

            <!-- ============================================================== -->
            <!-- Container fluid  -->
            <!-- ============================================================== -->
            <div class="container-fluid">
                <div class="row">
                    <div class="col-12">
                    
                    	<!-- 차트 -->
                    	<div class="card">
                    		<div class="card-body border-bottom">
                                <h4 class="card-title mt-2">예약 현황</h4>
                            </div>
                    		<div class="card-body">
								<div class="row">
									<canvas id="week-chart" class="col-6" height="200px"></canvas>
									<canvas id="month-chart" class="col-6" height="200px"></canvas>
								</div>
                            </div>
                    	</div>
                    	
                    	<!-- 캘린더 -->
                        <div class="card">
                      		<div class="row">
                                 <div class="col-lg-3 border-right pr-0">
                                     <div class="card-body border-bottom">
                                         <h4 class="card-title mt-2">예약 관리</h4>
                                     </div>
                                     <div class="card-body">
                                         <div class="row">
                                             <div class="col-md-12">
                                             	<input type="hidden" name="cafe" value="${ caList }">
                                             	<c:forEach items="${ caList }" var="cafe" varStatus="i">
	                                   				<div class="custom-control custom-checkbox">
	                                    				<input type="checkbox" class="custom-control-input cafe-name" id="${ i.index }" value="${ cafe.caName }" checked>
	                                    				<label class="custom-control-label" for="${ i.index }">${ cafe.caName }</label> 
	                                   				</div>
                                             	</c:forEach>
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
                        
                        <!-- 모달 -->
                        <div class="modal fade" id="info" tabindex="-1" aria-labelledby="infoLabel" aria-hidden="true" data-backdrop="static">
	                        <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
						    	<div class="modal-content">
						    		<div id="modal-header" class="modal-header">
						        		<h5 class="modal-title text-white" id="infoLabel"></h5>
						        		<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						          			<span aria-hidden="true" class="text-white">&times;</span>
						 				</button>
						 			</div>
							      	<div class="modal-body">
							        	<div class="form-group">
							        		<label for="no">예약번호</label>
							        		<input id="no" class="form-control" type="text" readonly>
							        	</div>
							        	<div class="form-group">
							        		<label for="member">예약자</label>
							        		<input id="member" class="form-control" type="text" readonly>
							        	</div>
							        	<div class="form-group">
							        		<label for="date">예약일</label>
							        		<input id="date" class="form-control" type="date" readonly>
							        	</div>
							        	<div class="form-group">
							        		<label for="start">예약시간</label>
							        		<div class="clearfix">
								        		<input id="start" class="form-control float-left" type="time" readonly>
								        		<input id="end" class="form-control float-right" type="time" readonly>
							        		</div>
							        	</div>
							        	<div class="form-group">
							        		<label for="price">가격</label>
							        		<input id="price" class="form-control" type="text" readonly>
							        	</div>
							      	</div>
							      	<div class="modal-footer">
							        	<button id="del-btn" type="button" class="btn btn-primary">예약취소</button>
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
   
   <!-- chart.js -->
   <script src="https://cdn.jsdelivr.net/npm/chart.js@3.3.2/dist/chart.min.js"></script>
   <!-- datatable -->
   <script src="/resources/css/study/assets/extra-libs/datatables.net/js/jquery.dataTables.min.js"></script>
   <!-- fullcalendar -->
   <script src="/resources/css/study/assets/extra-libs/taskboard/js/jquery.ui.touch-punch-improved.js"></script>
   <script src="/resources/css/study/assets/extra-libs/taskboard/js/jquery-ui.min.js"></script>
   <script src="/resources/css/study/assets/libs/moment/min/moment.min.js"></script>
   <script src="/resources/css/study/assets/libs/fullcalendar/fullcalendar.js"></script>
   <script src="/resources/css/study/assets/libs/fullcalendar/gcal.min.js"></script>
   <!-- 해당 js 파일 -->
   <script src="/resources/js/reservationAdmin.js"></script>
</body>
</html>