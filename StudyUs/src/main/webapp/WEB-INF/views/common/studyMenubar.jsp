<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	
	<!-- favicon -->
	<link rel="shortcut icon" type="image/x-icon" href="/favicon.ico">
	<link rel="icon" type="image/x-icon" href="/favicon.ico">
    <!-- Tell the browser to be responsive to screen width -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Custom CSS -->
    <link href="/resources/css/study/assets/extra-libs/c3/c3.min.css" rel="stylesheet">
    <link href="/resources/css/study/assets/libs/chartist/dist/chartist.min.css" rel="stylesheet">
    <link href="/resources/css/study/assets/extra-libs/jvector/jquery-jvectormap-2.0.2.css" rel="stylesheet" />
    <link rel="stylesheet" href="/resources/css/main/css/owl.carousel.min.css">
    <link rel="stylesheet" href="/resources/css/main/css/owl.theme.default.min.css">
    	<!-- Quill -->
    <link href="/resources/js/quill/quill-emoji.css" rel="stylesheet" type="text/css">
    <link href="//cdn.quilljs.com/1.3.6/quill.snow.css" rel="stylesheet">
    <link href="//cdn.quilljs.com/1.3.6/quill.bubble.css" rel="stylesheet">
    <!-- Custom CSS -->
	<link href="/resources/css/study/dist/css/style.min.css" rel="stylesheet" />

	<script src="/resources/css/study/assets/libs/jquery/dist/jquery.min.js"></script>
	<link href="/resources/css/study/adminmart.css" rel="stylesheet">
	
	<title>study menubar</title>
</head>
<body>

<!-- 	    ==============================================================
	    Preloader - style you can find in spinners.css
	    ==============================================================
	    <div class="preloader">
	        <div class="lds-ripple">
	            <div class="lds-pos"></div>
	            <div class="lds-pos"></div>
	        </div>
	    </div> -->
    
        <!-- ============================================================== -->
        <!-- Topbar header
        <!-- ============================================================== -->
        <header class="topbar" data-navbarbg="skin6">
            <nav class="navbar top-navbar navbar-expand-md">
                <div class="navbar-header" data-logobg="skin6">
                    <!-- This is for the sidebar toggle which is visible on mobile only -->
                    <a class="nav-toggler waves-effect waves-light d-block d-md-none" href="#"><i
                            class="ti-menu ti-close"></i></a>
                    <!-- ============================================================== -->
                    <!-- Logo -->
                    <!-- ============================================================== -->
                    <div class="navbar-brand">
                        <!-- Logo icon -->
                        <a href="/">
                            <b class="logo-icon">
                                <img src="/resources/css/study/assets/images/logo-icon2.png" alt="homepage" class="dark-logo" />
                                <img src="/resources/css/study/assets/images/logo-icon2.png" alt="homepage" class="light-logo" />
                            </b>
                            <!--End Logo icon -->
                            <!-- Logo text -->
                            <span class="logo-text text-dark">
                                <!-- dark Logo text -->
                                <img src="/resources/css/study/assets/images/logo-text2.png" alt="homepage" class="dark-logo" />
                                <!-- Light Logo text -->
                                <img src="/resources/css/study/assets/images/logo-text2.png" class="light-logo" alt="homepage" />
                            </span>
                        </a>
                    </div>
                    <!-- ============================================================== -->
                    <!-- Toggle which is visible on mobile only -->
                    <!-- ============================================================== -->
                    <a class="topbartoggler d-block d-md-none waves-effect waves-light" href="javascript:void(0)"
                        data-toggle="collapse" data-target="#navbarSupportedContent"
                        aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><i
                            class="ti-more"></i></a>
                </div>
                
                <div class="navbar-collapse collapse" id="navbarSupportedContent">
                    <!-- ============================================================== -->
                    <!-- Left toggle and nav items -->
                    <!-- ============================================================== -->
                    <ul class="navbar-nav float-left mr-auto ml-3 pl-1">
                        <!-- 스터디 신청 알림 / 일반인은 과제알람? -->
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle pl-md-3 position-relative" href="javascript:void(0)"
                                id="bell" role="button" data-toggle="dropdown" aria-haspopup="true"
                                aria-expanded="false">
                                <span><i data-feather="bell" class="svg-icon"></i></span>
                                <span class="badge badge-primary notify-no rounded-circle">5</span>
                            </a>
                            <div class="dropdown-menu dropdown-menu-left mailbox animated bounceInDown">
                                <ul class="list-style-none">
                                    <li>
                                        <div class="message-center notifications position-relative">
                                            <!-- Message -->
                                            <a href="javascript:void(0)" class="message-item d-flex align-items-center border-bottom px-3 py-2">
                                                <div class="btn btn-danger rounded-circle btn-circle">
                                                	<i data-feather="airplay" class="text-white"></i>
                                                </div>
                                                <div class="w-75 d-inline-block v-middle pl-2">
                                                    <h6 class="message-title mb-0 mt-1">Luanch Admin</h6>
                                                    <span class="font-12 text-nowrap d-block text-muted">Just see
                                                        the my new
                                                        admin!</span>
                                                    <span class="font-12 text-nowrap d-block text-muted">9:30 AM</span>
                                                </div>
                                            </a>
                                            <!-- Message -->
                                            <a href="javascript:void(0)"
                                                class="message-item d-flex align-items-center border-bottom px-3 py-2">
                                                <span class="btn btn-success text-white rounded-circle btn-circle"><i
                                                        data-feather="calendar" class="text-white"></i></span>
                                                <div class="w-75 d-inline-block v-middle pl-2">
                                                    <h6 class="message-title mb-0 mt-1">Event today</h6>
                                                    <span
                                                        class="font-12 text-nowrap d-block text-muted text-truncate">Just
                                                        a reminder that you have event</span>
                                                    <span class="font-12 text-nowrap d-block text-muted">9:10 AM</span>
                                                </div>
                                            </a>
                                            <!-- Message -->
                                            <a href="javascript:void(0)"
                                                class="message-item d-flex align-items-center border-bottom px-3 py-2">
                                                <span class="btn btn-info rounded-circle btn-circle"><i
                                                        data-feather="settings" class="text-white"></i></span>
                                                <div class="w-75 d-inline-block v-middle pl-2">
                                                    <h6 class="message-title mb-0 mt-1">Settings</h6>
                                                    <span
                                                        class="font-12 text-nowrap d-block text-muted text-truncate">You
                                                        can customize this template
                                                        as you want</span>
                                                    <span class="font-12 text-nowrap d-block text-muted">9:08 AM</span>
                                                </div>
                                            </a>
                                            <!-- Message -->
                                            <a href="javascript:void(0)"
                                                class="message-item d-flex align-items-center border-bottom px-3 py-2">
                                                <span class="btn btn-primary rounded-circle btn-circle"><i
                                                        data-feather="box" class="text-white"></i></span>
                                                <div class="w-75 d-inline-block v-middle pl-2">
                                                    <h6 class="message-title mb-0 mt-1">Pavan kumar</h6> <span
                                                        class="font-12 text-nowrap d-block text-muted">Just
                                                        see the my admin!</span>
                                                    <span class="font-12 text-nowrap d-block text-muted">9:02 AM</span>
                                                </div>
                                            </a>
                                        </div>
                                    </li>
                                    <li>
                                        <a class="nav-link pt-3 text-center text-dark" href="javascript:void(0);">
                                            <strong>Check all notifications</strong>
                                            <i class="fa fa-angle-right"></i>
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </li>
                        <!-- 스터디 정보 페이지로 이동 -->
                        <li class="nav-item">
                            <a class="nav-link" href="#" id="navbarDropdown" role="button">
                                <i data-feather="settings" class="svg-icon"></i>
                            </a>
                        </li>
                    </ul>
                    <!-- ============================================================== -->
                    <!-- Right side toggle and nav items -->
                    <!-- ============================================================== -->
                    <ul class="navbar-nav float-right">
                        <!-- ============================================================== -->
                        <!-- navi -->
                        <!-- ============================================================== -->
                        <div class="collapse navbar-collapse" id="ftco-nav">
					        <ul class="navbar-nav ml-auto">
					          <li class="nav-item"><a href="/" class="nav-link study">홈</a></li>
					          <li class="nav-item"><a href="#" class="nav-link study">스터디</a></li>
					          <li class="nav-item"><a href="#" class="nav-link study">스터디샵</a></li>
					          <li class="nav-item"><a href="#" class="nav-link study">스터디카페</a></li>
					        </ul>
					   	</div>
                        <!-- ============================================================== -->
                        <!-- User profile -->
                        <!-- ============================================================== -->
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="javascript:void(0)" data-toggle="dropdown"
                                aria-haspopup="true" aria-expanded="false">
                                <img src="/resources/images/${ loginUser.mbPhoto }.png" class="rounded-circle"
                                    width="30">
                                <span class="ml-2 d-none d-lg-inline-block"><span
                                        class="text-dark">${ loginUser.mbNickname }</span> <i data-feather="chevron-down"
                                        class="svg-icon"></i></span>
                            </a>
                            <div class="dropdown-menu dropdown-menu-right user-dd animated flipInY">
                                <a class="dropdown-item" href="/member/myPage"><i data-feather="user"
                                        class="svg-icon mr-2 ml-1"></i>
                                    마이페이지</a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="/member/logout"><i data-feather="power"
                                        class="svg-icon mr-2 ml-1"></i>
                                    로그아웃</a>
                            </div>
                        </li>
                    </ul>
                </div>
                
            </nav>
        </header>
        
        
        <!-- ============================================================== -->
        <!-- Left Sidebar - style you can find in sidebar.scss  -->
        <!-- ============================================================== -->
        <aside class="left-sidebar" data-sidebarbg="skin6">
        	<div class="scroll-sidebar" data-sidebarbg="skin6">
            	<!-- Sidebar navigation-->
	            <nav class="sidebar-nav">
	                <ul id="sidebarnav">
	                    <li class="sidebar-item"> <a class="sidebar-link sidebar-link" href="/study"
	                            aria-expanded="false"><i data-feather="home" class="feather-icon"></i><span
	                                class="hide-menu">${ study.studyName } </span></a></li>
	                    <li class="list-divider"></li>
	                    
	                	<!-- STUDY -->
	                    <li class="nav-small-cap"><span class="hide-menu">study</span></li>
	
	                    <li class="sidebar-item"> <a class="sidebar-link sidebar-link" href="/study/calendar"
	                            aria-expanded="false"><i data-feather="calendar" class="feather-icon"></i><span
	                                class="hide-menu">일정 </span></a></li>
	                    <li class="sidebar-item"> <a class="sidebar-link sidebar-link" href="/notice/noticeList"
	                            aria-expanded="false"><i data-feather="sidebar" class="feather-icon"></i><span
	                                class="hide-menu">공지사항 
	                            </span></a>
	                    </li>
	                    <li class="sidebar-item"> <a class="sidebar-link has-arrow" href="javascript:void(0)"
	                            aria-expanded="false"><i data-feather="file-text" class="feather-icon"></i><span
	                                class="hide-menu">게시판 </span></a>
	                        <ul aria-expanded="false" class="collapse  first-level base-level-line">
	                            <li class="sidebar-item"><a href="/study/board?boCategory=0" class="sidebar-link"><span
	                                        class="hide-menu"> 전체
	                                    </span></a>
	                            </li>
	                            <li class="sidebar-item"><a href="/study/board?boCategory=1" class="sidebar-link"><span
	                                        class="hide-menu"> 자유
	                                    </span></a>
	                            </li>
	                            <li class="sidebar-item"><a href="/study/board?boCategory=2" class="sidebar-link"><span
	                                        class="hide-menu"> 공유
	                                    </span></a>
	                            </li>
	                            <li class="sidebar-item"><a href="/study/board?boCategory=3" class="sidebar-link"><span
	                                        class="hide-menu"> 질문
	                                    </span></a> 
	                            </li>
	                        </ul>
	                    </li>
	                    <li class="sidebar-item"> <a class="sidebar-link has-arrow" href="javascript:void(0)"
	                            aria-expanded="false"><i data-feather="grid" class="feather-icon"></i><span
	                                class="hide-menu">과제 </span></a>
	                    	<ul aria-expanded="false" class="collapse  first-level base-level-line">
	                            <li class="sidebar-item"><a href="/study/assignment?grNo=0" class="sidebar-link"><span
	                                        class="hide-menu"> 과제
	                                    </span></a>
	                            </li>
	                            <li class="sidebar-item"><a href="/study/assignment/file" class="sidebar-link"><span
	                                        class="hide-menu"> 파일함
	                                    </span></a>
	                            </li>
	                        </ul>
	                    </li>
	                    <li class="sidebar-item"> <a class="sidebar-link sidebar-link" href="#"
	                            aria-expanded="false"><i data-feather="message-square" class="feather-icon"></i><span
	                                class="hide-menu">채팅 </span></a></li>
			
						<!-- MANAGEMENT -->
	                    <li class="list-divider"></li>
	                    <li class="nav-small-cap"><span class="hide-menu">management</span></li>
	                    
	                    <li class="sidebar-item"> <a class="sidebar-link has-arrow" href="javascript:void(0)"
	                            aria-expanded="false"><i data-feather="bar-chart" class="feather-icon"></i><span
	                                class="hide-menu">출석 확인 </span></a>
	                        <ul aria-expanded="false" class="collapse  first-level base-level-line">
	                            <li class="sidebar-item"><a href="#" class="sidebar-link"><span
	                                        class="hide-menu"> 개인 출석
	                                    </span></a>
	                            </li>
	                            <li class="sidebar-item"><a href="#" class="sidebar-link"><span
	                                        class="hide-menu"> 전체 출석
	                                    </span></a>
	                            </li>
	                        </ul>
	                    </li>
	                    <li class="sidebar-item"> <a class="sidebar-link sidebar-link" href="#"
	                            aria-expanded="false"><i data-feather="box" class="feather-icon"></i><span
	                                class="hide-menu">회원 목록 </span></a>
	                    </li>
	                    <li class="sidebar-item"> <a class="sidebar-link sidebar-link" href="/study/boardManage"
	                            aria-expanded="false"><i data-feather="edit-3" class="feather-icon"></i><span
	                                class="hide-menu">게시물 관리 </span></a>
	                    </li>
	                </ul>
	            </nav>
        	</div>
            <!-- End Sidebar navigation -->
        </aside>
        <!-- ============================================================== -->
        <!-- End Left Sidebar - style you can find in sidebar.scss  -->
        <!-- ============================================================== -->

</body>
</html>