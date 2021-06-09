<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/resources/css/member/myPage.css">
<title>마이페이지</title>
<link href="/resources/css/studyus/fullcalendar.min.css" rel="stylesheet" />
<link href="/resources/css/study/dist/css/style.min.css" rel="stylesheet">
<link href="/resources/css/study/adminmart.css" rel="stylesheet">
<link href="/resources/css/studyus/assignmentColor.css" rel="stylesheet">
<link href="/resources/css/studyus/calendar.css" rel="stylesheet">
<link href="/resources/css/studyus/reply.css" rel="stylesheet">
<script type="text/javascript" src="http://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="/resources/js/myPageCalendar.js"></script>
<script type="text/javascript" src="/resources/js/myPageReview.js"></script>
</head>
<body>
	<!-- menubar -->
	<jsp:include page="/WEB-INF/views/common/menubar.jsp" />
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
				<input type="button" value="내 정보 수정" class="myPage-btn" onclick="location.href='/member/myInfo'"> &nbsp;&nbsp;
				<input type="button" value="결제 관리" class="myPage-btn" onclick="location.href='/member/purchaseView'">
			</div>
		</div>
		<div class="box-zone">
			<div class="my-study">
				<div class="menu-mark">
					<span>나의 스터디</span>
				</div>
				<div class="study-zone">
					<c:if test="${!empty enrolledStudyList}">
						<c:forEach var="study" items="${enrolledStudyList}" varStatus="i">
							<div class="study-box" onclick="location.href='/study/${study.url}'">
								<div class="txt-box">
									<a class="study-name">${study.studyName}</a>
									<span class="label">출석률</span><span class="value">00%</span>
									<span class="label">과제완료율</span><span class="value ">00%</span>
									<span class="label">남은 과제</span><span class="value">0개</span>
								</div>
							</div>
						</c:forEach>
					</c:if>
					
					<c:if test="${empty enrolledStudyList}">
						<div class="study-box">
							<div class="txt-box">
								<a class="study-name">가입한 스터디가 없습니다.</a>
							</div>
						</div>
					</c:if>
				</div>
			</div>

			<div class="my-calender">
				<div class="menu-mark">
					<span>나의 일정</span>
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
										<div class="card-body border-bottom" style="text-align: center;">
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
																<div id="checkHide"
																	class="custom-control custom-checkbox">
																	<input type="checkbox" class="custom-control-input"	id="drop-remove">
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

							<div class="modal fade" id="info" tabindex="-1"
								aria-labelledby="infoLabel" aria-hidden="true"
								data-backdrop="static">
								<div
									class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
									<div class="modal-content">
										<div id="modal-header" class="modal-header">
											<h5 class="modal-title text-white" id="infoLabel">일정</h5>
											<button type="button" class="close" data-dismiss="modal"
												aria-label="Close">
												<span aria-hidden="true" class="text-white">&times;</span>
											</button>
										</div>
										<div class="modal-body">
											<div class="form-group">
												<label for="name">일정명</label> <input id="name"
													class="form-control" type="text" readonly>
											</div>
											<div class="form-group">
												<label for="inDate">시작일시</label>
												<div class="clearfix">
													<input id="inDate" class="form-control float-left"
														type="date" readonly> <input id="inTime"
														class="form-control float-right" type="time" readonly>
												</div>
											</div>
											<div class="form-group">
												<label for="deDate">종료일시</label>
												<div class="clearfix">
													<input id="deDate" class="form-control float-left"
														type="date" readonly> <input id="deTime"
														class="form-control float-right" type="time" readonly>
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

				<div class="my-review">
					<div class="menu-mark">
						<span>나의 후기</span>
					</div>
					<div class="review-box">
						<div id="rList"></div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="/WEB-INF/views/common/footer.jsp" />

	<!--This page JavaScript -->
	<script src="/resources/css/study/assets/libs/jquery/dist/jquery.min.js"></script>
	<script src="/resources/css/study/assets/extra-libs/taskboard/js/jquery-ui.min.js"></script>
	<script src="/resources/css/study/assets/libs/bootstrap/dist/js/bootstrap.min.js"></script>
	<script src="/resources/css/study/dist/js/app-style-switcher.js"></script>
	<script src="/resources/css/study/dist/js/custom.min.js"></script>
	<script src="/resources/css/study/assets/libs/moment/min/moment.min.js"></script>
	<script src="/resources/css/study/assets/libs/fullcalendar/dist/fullcalendar.min.js"></script>
	<script	src="/resources/css/study/assets/extra-libs/taskboard/js/jquery-ui.min.js"></script>
	<script	src="/resources/css/study/assets/libs/fullcalendar/fullcalendar.js"></script>
	<script type="text/javascript" src="/resources/js/myPage.js"></script>

</body>
</html>