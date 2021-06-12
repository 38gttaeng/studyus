<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>스터디 정보수정 : StudyUs</title>
<style>
	.hashtag-knob:hover {
		cursor: pointer;
	}
</style>
</head>
<body>
	<!-- ============================================================== -->
	<!-- Main wrapper -->
	<!-- ============================================================== -->
	<div id="main-wrapper" data-theme="light" data-layout="vertical"
		data-navbarbg="skin6" data-sidebartype="full"
		data-sidebar-position="fixed" data-header-position="fixed"
		data-boxed-layout="full">

		<!-- menubar -->
		<jsp:include page="../common/studyMenubar.jsp" />

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
						<h4 class="page-title text-truncate text-dark font-weight-medium mb-1">스터디 정보수정</h4>
						<div class="d-flex align-items-center">
							<nav aria-label="breadcrumb">
								<ol class="breadcrumb m-0 p-0">
									<li class="breadcrumb-item text-muted" aria-current="page"> <a href="/study">${study.studyName }</a></li>
									<li class="breadcrumb-item text-primary font-weight-bold" aria-current="page">수정</li>
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
				<!-- basic table -->
				<div class="row">
					<div class="col-md-2"></div>
					<div class="col-sm-12 col-md-8">
                        <div class="card">
                            <div class="card-body">
                                <form action="/study/modify/post" method="post" enctype="multipart/form-data">
                                    <div class="form-group col-md-10 mx-auto my-4">
										<label for="inputEmail4">스터디명 <span style="color: red;">*</span></label> 
										<input type="text" class="form-control" id="inputName" name="studyName" onkeyup="nameCheck(this);" value="${study.studyName }" required>
										<small id="nameHelp" class="form-text">4 ~ 32 자리의 한글, 영문 및 숫자만 가능합니다.</small>
									</div>
									<div class="form-group col-md-10 mx-auto mb-4">
										<label for="inputPassword4">스터디 URL <span style="color: red;">*</span></label> 
										<input type="text" class="form-control" id="inputUrl" name="url" onkeyup="urlCheck(this);" value="${study.url }" required>
										<small id="urlHelp" class="form-text">4 ~ 32 자리의 영문 및 숫자만 가능합니다.</small>
									</div>
									<div class="form-group col-md-10 mx-auto mb-4">
										<label for="inputIntroduce">스터디 소개 <span style="color: red;">*</span></label> <textarea class="form-control" id="inputIntroduce" name="introduce" required>${study.introduce }</textarea>
									</div>
									<div class="form-group col-md-10 mx-auto mb-4">
										<div class="form-row">
											<div class="form-group col-9">
												<label for="inputHashtag">해시태그</label> 
												<input type="text" class="form-control" id="inputHashtag">
											</div>
											<div class="form group col-3" align="center">
												<input type="button" class="btn btn-primary btn-sm" onclick="onAddHashtagClicked();" value="추가하기" style="margin-top: 46px;">
											</div>
											<div class="form-group">
												<input type="hidden" id="hashtagsTemp" name="hashtagsTemp" value="">
											</div>
										</div>
									</div>
									<div class="form-group col-md-10 mx-auto mb-4">
										<div class="form-row" id="hashtagView">
											<span>현재 태그: &nbsp;</span>
	<!-- 										<span class="btn btn-primary btn-sm mr-2 hashtag-knob" style="margin: 4px;" onclick="onRemoveHashtagClicked(this);">수채화&nbsp;<i class="fas fa-times"></i></span> -->
											<c:forEach var="hashtag" items="${hashtagList }">
												<span class="btn btn-primary btn-sm mr-2 hashtag-knob" style="margin: 4px;" onclick="onRemoveHashtagClicked(this);"><c:out value="${hashtag }"></c:out>&nbsp;<i class="fas fa-times"></i></span>
											</c:forEach>
										</div>
									</div>
									<br>
									<div class="form-group col-md-10 mx-auto mb-4">
										<label for="inputAddress">활동 일시</label> 
										<input type="button" class="btn btn-primary btn-sm" id="modalButton" data-toggle="modal" data-target="#meetingDayModal" value="설정하기">
										<small id="meetingDayHelp" class="form-text text-muted">주로 활동하는 시간을 설정하세요.</small>
									</div>
									<div class="form-group col-md-10 mx-auto mb-4">
									<div class="form-row mb-3 mb-2" id="meetingDayDisplay">
										<c:if test="${study.monday eq 0 }">
											<span class="meetingDayView btn btn-light btn-sm mr-1">월</span>
										</c:if>
										<c:if test="${study.monday ne 0 }">
											<span class="meetingDayView btn btn-primary btn-sm mr-1">월</span>
										</c:if>
										<c:if test="${study.tuesday eq 0 }">
											<span class="meetingDayView btn btn-light btn-sm mr-1">화</span>
										</c:if>
										<c:if test="${study.tuesday ne 0 }">
											<span class="meetingDayView btn btn-primary btn-sm mr-1">화</span>
										</c:if>
										<c:if test="${study.wednesday eq 0 }">
											<span class="meetingDayView btn btn-light btn-sm mr-1">수</span>
										</c:if>
										<c:if test="${study.wednesday ne 0 }">
											<span class="meetingDayView btn btn-primary btn-sm mr-1">수</span>
										</c:if>
										<c:if test="${study.thursday eq 0 }">
											<span class="meetingDayView btn btn-light btn-sm mr-1">목</span>
										</c:if>
										<c:if test="${study.thursday ne 0 }">
											<span class="meetingDayView btn btn-primary btn-sm mr-1">목</span>
										</c:if>
										<c:if test="${study.friday eq 0 }">
											<span class="meetingDayView btn btn-light btn-sm mr-1">금</span>
										</c:if>
										<c:if test="${study.friday ne 0 }">
											<span class="meetingDayView btn btn-primary btn-sm mr-1">금</span>
										</c:if>
										<c:if test="${study.saturday eq 0 }">
											<span class="meetingDayView btn btn-light btn-sm mr-1">토</span>
										</c:if>
										<c:if test="${study.saturday ne 0 }">
											<span class="meetingDayView btn btn-primary btn-sm mr-1">토</span>
										</c:if>
										<c:if test="${study.sunday eq 0 }">
											<span class="meetingDayView btn btn-light btn-sm mr-1">일</span>
										</c:if>
										<c:if test="${study.sunday ne 0 }">
											<span class="meetingDayView btn btn-primary btn-sm mr-1">일</span>
										</c:if>
										<span id="meetingTimeView" class="btn btn-secondary btn-sm mx-2">
											${study.start }  ~  ${study.end }
										</span>
									</div>
									</div>
									<br>
									<div class="input-group col-md-10 mx-auto mb-4">
									  <div class="input-group-prepend">
									    <span class="input-group-text" id="inputGroupFileAddon01">대표사진</span>
									  </div>
									  <div class="custom-file">
									    <input type="file" class="custom-file-input" id="study-file" name="file" aria-describedby="inputGroupFileAddon01" value="${study.filename }">
									    <label class="custom-file-label" for="inputGroupFile01">파일 선택</label>
									  </div>
									</div>
									<div class="input-group col-md-10 mx-auto mb-4">
										<button type="submit" class="btn btn-primary">완료</button>
									</div>
									<!-- <input type="hidden" id="hashtagList" name="hashtagList" value=""> -->
									<!-- Modal -->
									<div class="modal fade" id="meetingDayModal" tabindex="-1"
										aria-labelledby="meetingDayModalLabel" aria-hidden="true">
										<div class="modal-dialog">
											<div class="modal-content">
												<div class="modal-header">
													<h5 class="modal-title" id="exampleModalLabel">활동 일시</h5>
													<button type="button" class="close" data-dismiss="modal" aria-label="Close">
														<span aria-hidden="true">&times;</span>
													</button>
												</div>
												<div class="modal-body" id="modal-study">
													<div class="row px-4 mx-auto mb-4">
														<div class="input-group">
															<div class="custom-control custom-checkbox custom-control-inline">
																<c:if test="${study.monday eq 0 }">
																	<input type="checkbox" id="monday" name="monday" class="meetingDayInput custom-control-input" value="1">
																</c:if>
																<c:if test="${study.monday ne 0 }">
																	<input type="checkbox" id="monday" name="monday" class="meetingDayInput custom-control-input" value="1" checked>
																</c:if>
																<label class="custom-control-label" for="monday">월</label>
															</div>
															<div class="custom-control custom-checkbox custom-control-inline">
																<c:if test="${study.tuesday eq 0 }">
																	<input type="checkbox" id="tuesday" name="tuesday" class="meetingDayInput custom-control-input" value="1">
																</c:if>
																<c:if test="${study.tuesday ne 0 }">
																	<input type="checkbox" id="tuesday" name="tuesday" class="meetingDayInput custom-control-input" value="1" checked>
																</c:if>
																<label class="custom-control-label" for="tuesday">화</label>
															</div>
															<div class="custom-control custom-checkbox custom-control-inline">
																<c:if test="${study.wednesday eq 0 }">
																	<input type="checkbox" id="wednesday" name="wednesday" class="meetingDayInput custom-control-input" value="1">
																</c:if>
																<c:if test="${study.wednesday ne 0 }">
																	<input type="checkbox" id="wednesday" name="wednesday" class="meetingDayInput custom-control-input" value="1" checked>
																</c:if>
																<label class="custom-control-label" for="wednesday">수</label>
															</div>
															<div class="custom-control custom-checkbox custom-control-inline">
																<c:if test="${study.thursday eq 0 }">
																	<input type="checkbox" id="thursday" name="thursday" class="meetingDayInput custom-control-input" value="1">
																</c:if>
																<c:if test="${study.thursday ne 0 }">
																	<input type="checkbox" id="thursday" name="thursday" class="meetingDayInput custom-control-input" value="1" checked>
																</c:if>
																<label class="custom-control-label" for="thursday">목</label>
															</div>
															<div class="custom-control custom-checkbox custom-control-inline">
																<c:if test="${study.friday eq 0 }">
																	<input type="checkbox" id="friday" name="friday" class="meetingDayInput custom-control-input" value="1">
																</c:if>
																<c:if test="${study.friday ne 0 }">
																	<input type="checkbox" id="friday" name="friday" class="meetingDayInput custom-control-input" value="1" checked>
																</c:if>
																<label class="custom-control-label" for="friday">금</label>
															</div>
															<div class="custom-control custom-checkbox custom-control-inline">
																<c:if test="${study.saturday eq 0 }">
																	<input type="checkbox" id="saturday" name="saturday" class="meetingDayInput custom-control-input" value="1">
																</c:if>
																<c:if test="${study.saturday ne 0 }">
																	<input type="checkbox" id="saturday" name="saturday" class="meetingDayInput custom-control-input" value="1" checked>
																</c:if>
																<label class="custom-control-label" for="saturday">토</label>
															</div>
															<div class="custom-control custom-checkbox custom-control-inline">
																<c:if test="${study.sunday eq 0 }">
																	<input type="checkbox" id="sunday" name="sunday" class="meetingDayInput custom-control-input" value="1">
																</c:if>
																<c:if test="${study.sunday ne 0 }">
																	<input type="checkbox" id="sunday" name="sunday" class="meetingDayInput custom-control-input" value="1" checked>
																</c:if>
																<label class="custom-control-label" for="sunday">일</label>
															</div>
														</div>
													</div>
													<div class="row p-4 mx-auto">
														<div class="input-group mx-auto">
															<select name="start-h" id="start-h" class="form-control form-control-sm">
																<option value="">--</option>
																<c:forEach var="i" begin="0" end="23">
																	<option value="${i}">${i}</option>
																</c:forEach>
															</select>
															<select name="start-m" id="start-m" class="form-control form-control-sm">
																<option value="">--</option>
																<c:forEach var="i" begin="0" end="5">
																	<option value="${i * 10}">${i * 10}</option>
																</c:forEach>
															</select>
															<span>&nbsp;&nbsp;~&nbsp;&nbsp;</span>
															<select name="end-h" id="end-h" class="form-control form-control-sm">
																<option value="">--</option>
																<c:forEach var="i" begin="0" end="23">
																	<option value="${i}">${i}</option>
																</c:forEach>
															</select>
															<select name="end-m" id="end-m" class="form-control form-control-sm">
																<option value="">--</option>
																<c:forEach var="i" begin="0" end="5">
																	<option value="${i * 10}">${i * 10}</option>
																</c:forEach>
															</select>
														
															<!-- <input type="time" class="form-control" id="start" name="start">
															<span class="px-2">~</span>
															<input type="time" class="form-control" id="end" name="end"> -->
														</div>
													</div>
												</div>
												<div class="modal-footer">
													<!-- <button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button> -->
													<button type="button" class="btn btn-primary" data-dismiss="modal">완료</button>
												</div>
											</div>
										</div>
									</div>
									<input type="hidden" name="studyNo" value="${study.studyNo }">
									<input type="hidden" name="studyNo" value="${study.studyNo }">
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-2"></div>
				</div>
			</div>
		<!-- footer -->
		<jsp:include page="../common/studyFooter.jsp" />
		</div> 
	</div>
	
	<script src="/resources/js/studyModify.js"></script>
	
</body>
</html>