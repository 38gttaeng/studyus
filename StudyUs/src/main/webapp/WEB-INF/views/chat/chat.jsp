<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>채팅 - StudyUs</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
</head>
<body>

    <div id="main-wrapper" data-theme="light" data-layout="vertical" data-navbarbg="skin6" data-sidebartype="full" data-sidebar-position="fixed" data-header-position="fixed" data-boxed-layout="full">
    
    	<!-- menubar -->
	    <jsp:include page="../common/studyMenubar.jsp"/>
    
        <div class="page-wrapper">
            <!-- ============================================================== -->
            <!-- Bread crumb and right sidebar toggle -->
            <!-- ============================================================== -->
            <div class="page-breadcrumb">
                <div class="row">
                    <div class="col-7 align-self-center">
                        <h4 class="page-title text-truncate text-dark font-weight-medium mb-1">채팅</h4>
                        <div class="d-flex align-items-center">
                            <nav aria-label="breadcrumb">
                                <ol class="breadcrumb m-0 p-0">
                                    <li class="breadcrumb-item text-muted" aria-current="page">Apps</li>
                                    <li class="breadcrumb-item text-muted active" aria-current="page">채팅</li>
                                    <li class="breadcrumb-item text-muted" aria-current="page"><input type="button" value="받은 메세지" onclick="printReceivedMessage('{사용자명}', '{메세지}', '{시간}');"></li>
                                    <li class="breadcrumb-item text-muted" aria-current="page"><input type="button" value="보낸 메세지" onclick="printMyMessage('{메세지}', '{시간}');"></li>
                                </ol>
                            </nav>
                        </div>
                    </div>
                    <div class="col-5 align-self-center">
<!--                         <div class="customize-input float-right"> -->
<!--                             <select class="custom-select custom-select-set form-control bg-white border-0 custom-shadow custom-radius"> -->
<!--                                 <option selected>Aug 19</option> -->
<!--                                 <option value="1">July 19</option> -->
<!--                                 <option value="2">Jun 19</option> -->
<!--                             </select> -->
<!--                         </div> -->
                    </div>
                </div>
            </div>
            <!-- ============================================================== -->
            <!-- End Bread crumb and right sidebar toggle -->
            <!-- ============================================================== -->
            <!-- ============================================================== -->
            <!-- Container fluid  -->
            <!-- ============================================================== -->
            <div class="container-fluid">
            
                <div class="row">
                    <div class="col-md-12">
                        <div class="card">
                            <div class="row no-gutters">
                            	<!-- #region 채팅방 -->
<!--                                 <div class="col-lg-3 col-xl-2 border-right"> -->
<!--                                     <div class="card-body border-bottom"> -->
<!--                                         <form> -->
<!--                                             <input class="form-control" type="text" placeholder="Search Contact"> -->
<!--                                         </form> -->
<!--                                     </div> -->
<!--                                     <div class="scrollable position-relative" style="height: calc(100vh - 111px);"> -->
<!--                                         <ul class="mailbox list-style-none"> -->
<!--                                             <li> -->
<!--                                                 <div class="message-center"> -->
<!--                                                     Message -->
<!--                                                     <a href="javascript:void(0)" -->
<!--                                                         class="message-item d-flex align-items-center border-bottom px-3 py-2"> -->
<!--                                                         <div class="user-img"><img src="/resources/css/study/assets/images/users/1.jpg" -->
<!--                                                                 alt="user" class="img-fluid rounded-circle" -->
<!--                                                                 width="40px"> <span -->
<!--                                                                 class="profile-status online float-right"></span> -->
<!--                                                         </div> -->
<!--                                                         <div class="w-75 d-inline-block v-middle pl-2"> -->
<!--                                                             <h6 class="message-title mb-0 mt-1">Pavan kumar</h6> -->
<!--                                                             <span -->
<!--                                                                 class="font-12 text-nowrap d-block text-muted text-truncate">Just -->
<!--                                                                 see -->
<!--                                                                 the my new -->
<!--                                                                 admin!</span> -->
<!--                                                             <span class="font-12 text-nowrap d-block text-muted">9:30 -->
<!--                                                                 AM</span> -->
<!--                                                         </div> -->
<!--                                                     </a> -->
<!--                                                     Message -->
<!--                                                     <a href="javascript:void(0)" -->
<!--                                                         class="message-item d-flex align-items-center border-bottom px-3 py-2"> -->
<!--                                                         <div class="user-img"><img src="/resources/css/study/assets/images/users/2.jpg" -->
<!--                                                                 alt="user" class="img-fluid rounded-circle" -->
<!--                                                                 width="40px"> <span -->
<!--                                                                 class="profile-status busy float-right"></span> -->
<!--                                                         </div> -->
<!--                                                         <div class="w-75 d-inline-block v-middle pl-2"> -->
<!--                                                             <h6 class="message-title mb-0 mt-1">Sonu Nigam</h6> -->
<!--                                                             <span -->
<!--                                                                 class="font-12 text-nowrap d-block text-muted text-truncate">I've -->
<!--                                                                 sung a -->
<!--                                                                 song! See you at</span> -->
<!--                                                             <span class="font-12 text-nowrap d-block text-muted">9:10 -->
<!--                                                                 AM</span> -->
<!--                                                         </div> -->
<!--                                                     </a> -->
<!--                                                     Message -->
<!--                                                     <a href="javascript:void(0)" -->
<!--                                                         class="message-item d-flex align-items-center border-bottom px-3 py-2"> -->
<!--                                                         <div class="user-img"> <img src="/resources/css/study/assets/images/users/3.jpg" -->
<!--                                                                 alt="user" class="img-fluid rounded-circle" -->
<!--                                                                 width="40px"> <span -->
<!--                                                                 class="profile-status away float-right"></span> -->
<!--                                                         </div> -->
<!--                                                         <div class="w-75 d-inline-block v-middle pl-2"> -->
<!--                                                             <h6 class="message-title mb-0 mt-1">Arijit Sinh</h6> -->
<!--                                                             <span -->
<!--                                                                 class="font-12 text-nowrap d-block text-muted text-truncate">I -->
<!--                                                                 am a -->
<!--                                                                 singer!</span> -->
<!--                                                             <span class="font-12 text-nowrap d-block text-muted">9:08 -->
<!--                                                                 AM</span> -->
<!--                                                         </div> -->
<!--                                                     </a> -->
<!--                                                     Message -->
<!--                                                     <a href="javascript:void(0)" -->
<!--                                                         class="message-item d-flex align-items-center border-bottom px-3 py-2"> -->
<!--                                                         <div class="user-img"><img src="/resources/css/study/assets/images/users/4.jpg" -->
<!--                                                                 alt="user" class="img-fluid rounded-circle" -->
<!--                                                                 width="40px"> <span -->
<!--                                                                 class="profile-status offline float-right"></span> -->
<!--                                                         </div> -->
<!--                                                         <div class="w-75 d-inline-block v-middle pl-2"> -->
<!--                                                             <h6 class="message-title mb-0 mt-1">Nirav Joshi</h6> -->
<!--                                                             <span -->
<!--                                                                 class="font-12 text-nowrap d-block text-muted text-truncate">Just -->
<!--                                                                 see the my admin!</span> -->
<!--                                                             <span class="font-12 text-nowrap d-block text-muted">9:02 -->
<!--                                                                 AM</span> -->
<!--                                                         </div> -->
<!--                                                     </a> -->
<!--                                                     Message -->
<!--                                                     <a href="javascript:void(0)" -->
<!--                                                         class="message-item d-flex align-items-center border-bottom px-3 py-2"> -->
<!--                                                         <div class="user-img"><img src="/resources/css/study/assets/images/users/5.jpg" -->
<!--                                                                 alt="user" class="img-fluid rounded-circle" -->
<!--                                                                 width="40px"> <span -->
<!--                                                                 class="profile-status offline float-right"></span></span> -->
<!--                                                         </div> -->
<!--                                                         <div class="w-75 d-inline-block v-middle pl-2"> -->
<!--                                                             <h6 class="message-title mb-0 mt-1">Sunil Joshi</h6> -->
<!--                                                             <span -->
<!--                                                                 class="font-12 text-nowrap d-block text-muted text-truncate">Just -->
<!--                                                                 see the my admin!</span> -->
<!--                                                             <span class="font-12 text-nowrap d-block text-muted">9:02 -->
<!--                                                                 AM</span> -->
<!--                                                         </div> -->
<!--                                                     </a> -->
<!--                                                     Message -->
<!--                                                     <a href="javascript:void(0)" -->
<!--                                                         class="message-item d-flex align-items-center border-bottom px-3 py-2"> -->
<!--                                                         <div class="user-img"><img src="/resources/css/study/assets/images/users/6.jpg" -->
<!--                                                                 alt="user" class="img-fluid rounded-circle" -->
<!--                                                                 width="40px"> <span -->
<!--                                                                 class="profile-status offline float-right"></span> -->
<!--                                                         </div> -->
<!--                                                         <div class="w-75 d-inline-block v-middle pl-2"> -->
<!--                                                             <h6 class="message-title mb-0 mt-1">Akshay Kumar</h6> -->
<!--                                                             <span -->
<!--                                                                 class="font-12 text-nowrap d-block text-muted text-truncate">Just -->
<!--                                                                 see the my admin!</span> -->
<!--                                                             <span class="font-12 text-nowrap d-block text-muted">9:02 -->
<!--                                                                 AM</span> -->
<!--                                                         </div> -->
<!--                                                     </a> -->
<!--                                                     Message -->
<!--                                                     <a href="javascript:void(0)" -->
<!--                                                         class="message-item d-flex align-items-center border-bottom px-3 py-2"> -->
<!--                                                         <div class="user-img"><img src="/resources/css/study/assets/images/users/7.jpg" -->
<!--                                                                 alt="user" class="img-fluid rounded-circle" -->
<!--                                                                 width="40px"> <span -->
<!--                                                                 class="profile-status offline float-right"></span> -->
<!--                                                         </div> -->
<!--                                                         <div class="w-75 d-inline-block v-middle pl-2"> -->
<!--                                                             <h6 class="message-title mb-0 mt-1">Pavan kumar</h6> -->
<!--                                                             <span -->
<!--                                                                 class="font-12 text-nowrap d-block text-muted text-truncate">Just -->
<!--                                                                 see the my admin!</span> -->
<!--                                                             <span class="font-12 text-nowrap d-block text-muted">9:02 -->
<!--                                                                 AM</span> -->
<!--                                                         </div> -->
<!--                                                     </a> -->
<!--                                                     Message -->
<!--                                                     <a href="javascript:void(0)" -->
<!--                                                         class="message-item d-flex align-items-center px-3 py-2"> -->
<!--                                                         <div class="user-img"><img src="/resources/css/study/assets/images/users/8.jpg" -->
<!--                                                                 alt="user" class="img-fluid rounded-circle" -->
<!--                                                                 width="40px"> <span -->
<!--                                                                 class="profile-status offline float-right"></span> -->
<!--                                                         </div> -->
<!--                                                         <div class="w-75 d-inline-block v-middle pl-2"> -->
<!--                                                             <h6 class="message-title mb-0 mt-1">Varun Dhavan</h6> -->
<!--                                                             <span -->
<!--                                                                 class="font-12 text-nowrap d-block text-muted text-truncate">Just -->
<!--                                                                 see the my admin!</span> -->
<!--                                                             <span class="font-12 text-nowrap d-block text-muted">9:02 -->
<!--                                                                 AM</span> -->
<!--                                                         </div> -->
<!--                                                     </a> -->
<!--                                                 </div> -->
<!--                                             </li> -->
<!--                                         </ul> -->
<!--                                     </div> -->
<!--                                 </div> -->
									<!-- #endregion -->
                                <div class="col">
                                    <div class="chat-box scrollable position-relative"
                                        style="height: calc(100vh - 111px);">
                                        <!--chat Row -->
                                        <ul id="chat-box" class="chat-list list-style-none px-3 pt-3">
                                            <!--chat Row -->
                                            <li class="chat-item list-style-none mt-3 d-none">
                                                <div class="chat-img d-inline-block"><img
                                                        src="/resources/css/study/assets/images/users/2.jpg" alt="user"
                                                        class="rounded-circle" width="45">
                                                </div>
                                                <div class="chat-content d-inline-block pl-3">
                                                    <h6 class="font-weight-medium">Bianca Doe</h6>
                                                    <div class="msg p-2 d-inline-block mb-1">It’s
                                                        Great opportunity to
                                                        work.</div>
                                                </div>
                                                <div class="chat-time d-block font-10 mt-1 mr-0 mb-3">10:57 am
                                                </div>
                                            </li>
                                            <!--chat Row -->
                                            <li class="chat-item odd list-style-none mt-3 d-none">
                                                <div class="chat-content text-right d-inline-block pl-3">
                                                    <div class="box msg p-2 d-inline-block mb-1">I
                                                        would love to
                                                        join the team.</div>
                                                    <br>
                                                </div>
                                            </li>
                                        </ul>
                                    </div>
                                    <div class="card-body border-top">
                                        <div class="row">
                                            <div class="col-9">
                                                <div class="input-field mt-0 mb-0">
                                                    <input id="input-chat" placeholder="Type and enter"
                                                        class="form-control border-0" type="text">
                                                </div>
                                            </div>
                                            <div class="col-3">
                                                <a class="btn-circle btn-lg btn-cyan float-right text-white"
                                                    href="javascript:send()"><i class="fas fa-paper-plane"></i></a>
                                            </div>
                                        </div>
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
        <!-- ============================================================== -->
        <!-- End Page wrapper  -->
        <!-- ============================================================== -->
    </div>
</body>

<!-- <script th:inline="javascript"> -->
<script type="text/javascript">
// 로컬 유저의 닉네임
var nickname = '${loginUser.mbNickname}';
// 채팅중인 스터디의 url
var studyUrl = '${study.url}';
// 채팅 input
var chatInput = document.getElementById("input-chat");
// 채팅 메세지 출력영역
var chatBox = document.getElementById("chat-box");

// 웹소켓 연결
// var webSocket = new WebSocket("ws://" + location.hostname + (location.port ? ':' + location.port: '') + "/chat/message");
var webSocket = new SockJS("http://localhost:8080/chat/message");
webSocket.onopen = onOpen;
webSocket.onclose = onClose;
webSocket.onmessage = onMessage;
function onOpen () {
	webSocket.send(JSON.stringify({"studyUrl" : studyUrl, "nickname" : nickname, "type" : "OPEN", "message" : nickname + " 님이 입장하셨습니다."}));
}
function onClose () {
	webSocket.send(JSON.stringify({"studyUrl" : studyUrl, "nickname" : nickname, "type" : "CLOSE", "message" : nickname + " 님이 퇴장하셨습니다."}));
	webSocket.close();
}
function send () {
	webSocket.send(JSON.stringify({"studyUrl" : studyUrl, "nickname" : nickname, "type" : "SEND", "message" : chatInput.value}));
	chatInput.value = "";
}
function onMessage (message) {
	jsonMsg = JSON.parse(message.data);
	
	var msgNickname = jsonMsg["nickname"];
	var msgContents = jsonMsg["message"];
	var msgInsertDate = jsonMsg["insertDate"];
	
	console.log(jsonMsg);
	
	if (jsonMsg["nickname"] == nickname) {
		printMyMessage(msgContents, msgInsertDate);
	} else {
		printReceivedMessage(msgNickname, msgContents, msgInsertDate)
	}
}

// 로컬 유저의 메세지 출력
function printMyMessage(message, time) {
	
	var newMessage = '<li class="chat-item odd list-style-none mt-3">' + 
        				'<div class="chat-content text-right d-inline-block pl-3">' +
    						'<div class="box msg p-2 d-inline-block mb-1 box">' +
        						message + 
        					'</div>' +
					    '<br>' +
					'</div>' +
					'<div class="chat-time text-right d-block font-10 mt-1 mr-0 mb-3">' +
					    time +
					'</div>' +
					'</li>';
	
	chatBox.innerHTML += newMessage;
}

// 타인의 메세지 출력
function printReceivedMessage(username, message, time) {
	
	var newMessage = '<li class="chat-item list-style-none mt-3">' +
// 프로필 이미지 영역
// 				        '<div class="chat-img d-inline-block">' +
// 							'<img src="/resources/css/study/assets/images/users/3.jpg" alt="user" class="rounded-circle" width="45">' +
// 						'</div>' +
						'<div class="chat-content d-inline-block pl-3">' +
						    '<h6 class="font-weight-medium">' + username + '</h6>' +
						    '<div class="msg p-2 d-inline-block mb-1">' + message +
						    '</div>' +
						'</div>' +
						'<div class="chat-time d-block font-10 mt-1 mr-0 mb-3">' + time +
						'</div>' +
						'</li>';
						
	chatBox.innerHTML += newMessage;
}
</script>

</html>