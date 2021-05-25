<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>StudyUs : 공지사항</title>
    <!-- 타이틀을 개별 스터디룸 이름으로 해줘도 좋을듯 ! 'StudyUs : 삼팔광땡' 이러케 -->
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
                    <div class="col-lg-4 align-self-center">
                        <h4 class="page-title text-truncate text-dark font-weight-medium mb-1">공지사항</h4>
                        <div class="d-flex align-items-center">
                            <nav aria-label="breadcrumb">
                                <ol class="breadcrumb m-0 p-0">
                                    <li class="breadcrumb-item text-muted" aria-current="page"><a href="/study">Study</a></li>
                                    <li class="breadcrumb-item text-muted active" aria-current="page">Notice</li>
                                    <li class="breadcrumb-item text-primary font-weight-bold" aria-current="page">List</li>
                                </ol>
                            </nav>
                        </div>
                    </div>
                    <div class="col-lg-8 align-self-center">
        				<form action="/notice/noticeSearch" method="get">
		                    <div class="customize-input float-right">
                            	<input class="form-control custom-shadow custom-radius border-0 bg-white" type="text" placeholder="Search" aria-label="Search" name="searchValue" value="${search.searchValue }">
                            	<i class="form-control-icon" data-feather="search"></i>
                            	<input type="submit" value="검색">
	                    	</div>
	                    	<div class="customize-input float-right" style="margin-right:10px;">
		                        <select class="custom-select bg-white custom-radius border-0 custom-shadow" name="searchCondition">
		                            <option value="all" <c:if test="${search.searchCondition == 'all' }">selected</c:if>>제목+내용</option>
									<option value="title" <c:if test="${search.searchCondition == 'title' }">selected</c:if>>제목</option>
									<option value="content" <c:if test="${search.searchCondition == 'content' }">selected</c:if>>내용</option>
		                        </select>
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
                <!-- basic table -->
                <div class="row">
                    <div class="col-12">
	                    	<div class="card">
	                    		<div class="card-body">
	                    			<h4 class="card-title">메인 공지사항</h4>
	                    			<div class="border border-3"></div>
	                    			<div class="card-body">
	                    				<p>출석 꼭 찍어주세요. 퇴출당하기전에 . . .</p>
	                    			</div>
	                    		</div>
	                    		</div>
	                    	</div>
	                    	<table class="table">
	                    		<thead class="thead-light">
	                    			<tr>
	                    				<th scope="col"></th>
	                    				<th scope="col">#</th>
	                    				<th scope="col">제목</th>
	                    				<th scope="col">작성자</th>
	                    				<th scope="col">작성일</th>
	                    				<th scope="col">조회수</th>
	                    			</tr>
	                    		</thead>	
	                    		<tbody>
	                    			<c:forEach items="${nList }" var="notice">
	                    			<tr>
	                    				<th><input type="hidden" name="noticeNo" value="${notice.noticeNo }"></th>
	                    				<th scope="row">${notice.rowNum }</th>
	                    				<td>
	                    					<c:url var="nDetail" value="/notice/noticeDetail">
	                    						<c:param name="noticeNo" value="${notice.noticeNo}"></c:param>
	                    					</c:url>
	                    					<a href="${nDetail }">${notice.noticeTitle }</a>
	                    					<a href="#" class="badge badge-pill badge-primary">3</a> 
	                    					<!-- 누르면 댓글 팝업창으로 -->
	                    					<span class="badge badge-danger">N</span>
	                    				</td>
	                    				<td>대장님</td>
	                    				<td>${notice.nInsertDate }</td>
	                    				<td>${notice.noticeCount}</td>
	                    			</tr>
	                    			</c:forEach>
	                    		</tbody>
	                    		<tr align="center" height="20">
	                    			<td colspan="6">
	                    				<nav aria-label="Page navigation example">
	                    					<ul class="pagination">
	                    						<li class="page-item">
		                    						<a class="page-link" href="javascript:void(0)" aria-label="Previous">
		                    							<span aria-hidden="true"><<</span>
		                    							<span class="sr-only">Previous</span>
		                    						</a>
 	                    						</li>
 	                    						<li class="page-item">
		                    						<a class="page-link" href="javascript:void(0)" >1</a>
 	                    						</li>
 	                    						<li class="page-item">
		                    						<a class="page-link" href="javascript:void(0)">2</a>
 	                    						</li>
 	                    						<li class="page-item">
		                    						<a class="page-link" href="javascript:void(0)" aria-label="Next">
		                    							<span aria-hidden="true">>></span>
		                    							<span class="sr-only">Next</span>
		                    						</a>
 	                    						</li>
	                    					</ul>
	                    				</nav>
	                    			</td>
	                    		</tr>
						                    		<!-- 페이징 처리 -->
							<tr align="center" height="20">
								<td colspan="6">
									<!-- 이전 -->
									<c:url var="before" value="/notice/noticeList">
										<c:param name="page" value="${pi.currentPage - 1 }"></c:param>
									</c:url>
									<c:if test="${pi.currentPage <= 1 }">
										[이전]&nbsp;
									</c:if>
									<c:if test="${pi.currentPage > 1 }">
										<a href="${before }">[이전]</a>&nbsp;
									</c:if>
									<!-- 페이지 -->
									<c:forEach var="p" begin="${pi.startPage }" end="${pi.endPage }">
										<c:url var="pagination" value="/notice/noticeList">
											<c:param name="page" value="${p }"></c:param>
										</c:url>
										<c:if test="${p eq pi.currentPage }">
											<font color="red" size="4">[${p }]</font>
										</c:if>
										<c:if test="${p ne pi.currentPage }">
											<a href="${pagination }">${p }</a>&nbsp;
										</c:if>
									</c:forEach>
									<!-- 다음 -->
									<c:url var="after" value="/notice/noticeList">
										<c:param name="page" value="${pi.currentPage + 1 }"></c:param>
									</c:url>
									<c:if test="${pi.currentPage >= pi.maxPage }">
										[다음]&nbsp;
									</c:if>
									<c:if test="${pi.currentPage < pi.maxPage }">
										<a href="${after }">[다음]</a>&nbsp;
									</c:if>
								</td>
							</tr>
	                    	</table>
                	</div>
                </div>   
				<button id="write-btn" onclick="location.href='/notice/noticeWriteView'"><i class="fas fa-edit"></i><span>글쓰기</span></button>          
			</div>
            <!-- footer -->
			<jsp:include page="../common/studyFooter.jsp"/>
        </div>
    </div>
	    <script>
		    $("#sidebarnav>li:nth-child(5)").addClass("selected");
			$("#sidebarnav>li:nth-child(5) a").addClass("active");
			
			function paging(page) {
				$('#list-body').empty();
				var startRow = (page - 1) * pageSize; // + 1 list는 0부터 시작하니깐;
				var endRow = page * pageSize;
				if (endRow > totalCount) 
				{
					endRow = totalCount;
				}  
				var startPage = ((page - 1)/visibleBlock) * visibleBlock + 1;
				var endPage = startPage + visibleBlock - 1;
				if(endPage > totalPages) {    //
				  endPage = totalPages;
				}
				for (var j = startRow; j < endRow; j++) 
				{	
					$('#list-body').append(''+ chatLogList[j].fileNo +''
							+ textLengthOverCut(chatLogList[j].fileName, '25', '...') +''+ chatLogList[j].fileDate +'');
				}
			}
			
			totalPages = totalCount/pageSize;
			if (totalCount%pageSize > 0) {
			totalPages++;
			}
			
			$('#pagination').twbsPagination({
				   totalPages: totalPages,  // 전체 page블럭 수
				   visiblePages: visibleBlock,  // 출력될 page 블럭수 상수로 지정해 줘도 되고, 변수로 지정해줘도 된다.
				   prev: "이전",
				   next: "다음",
				   first: '«',
				   last: '»',
				   onPageClick: function (event, page) {
				   $('#page-content').text('Page ' + page);
				   paging(page);
				   }
				});
			
	    </script>
</body>
</html>