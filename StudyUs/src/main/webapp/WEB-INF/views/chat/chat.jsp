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
                                    <li class="breadcrumb-item text-muted" aria-current="page">${study.studyName }</li>
                                    <li class="breadcrumb-item text-muted active" aria-current="page">채팅</li>
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
                                <div class="col-lg-3 col-xl-2 border-right">
<!--                                     <div class="card-body border-bottom"> -->
<!--                                         <form> -->
<!--                                             <input class="form-control" type="text" placeholder="Search Contact"> -->
<!--                                         </form> -->
<!--                                     </div> -->
                                    <div class="card-body border-bottom">
                                        <div>
                                                                              참여중인 사람들
                                        </div>
                                    </div>
                                    <div class="scrollable position-relative" style="height: calc(100vh - 111px);">
                                        <ul class="mailbox list-style-none">
                                            <li>
                                                <div id="user-list" class="message-center">
<!--                                                 	<div id="user-{nickname}" class="message-item d-flex align-items-center px-3 py-2 d-none"> -->
<!--                                                         <div class="user-img"> -->
<!--                                                         	<img src="/resources/css/study/assets/images/users/8.jpg" alt="user"  -->
<!--                                                         		class="img-fluid rounded-circle" width="40px">  -->
<!--                                                        		<span class="profile-status offline float-right"></span> -->
<!--                                                         </div> -->
<!--                                                         <div class="w-75 d-inline-block v-middle pl-2"> -->
<!--                                                             <h6 class="message-title mb-0 mt-1">Varun Dhavan</h6> -->
<!--                                                             <span -->
<!--                                                                 class="font-12 text-nowrap d-block text-muted text-truncate">Just -->
<!--                                                                 see the my admin!</span> -->
<!--                                                             <span class="font-12 text-nowrap d-block text-muted">9:02 -->
<!--                                                                 AM</span> -->
<!--                                                         </div> -->
<!--                                                     </div> -->
                                                </div>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
									<!-- #endregion -->
                                <div class="col-lg-9">
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
                                                    <input id="input-chat" placeholder="내용을 입력하세요."
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
var localNickname = '${loginUser.mbNickname}';
// 채팅중인 스터디의 url
var studyUrl = '${study.url}';
// 채팅 input
var chatInput = document.getElementById("input-chat");
// 채팅 메세지 출력영역
var chatBox = document.getElementById("chat-box");
// 참여중인 사용자를 출력하는 영역
var userList = document.getElementById("user-list");

// 웹소켓 연결
// var webSocket = new WebSocket("ws://" + location.hostname + (location.port ? ':' + location.port: '') + "/chat/message");
var webSocket = new SockJS("http://localhost:9999/chat/message");
webSocket.onopen = onOpen;
webSocket.onclose = onClose;
webSocket.onmessage = onMessage;
function onOpen () {
	webSocket.send(JSON.stringify({"studyUrl" : studyUrl, "nickname" : localNickname, "type" : "OPEN", "message" : localNickname + " 님이 입장하셨습니다."}));
}
function onClose () {
	webSocket.send(JSON.stringify({"studyUrl" : studyUrl, "nickname" : localNickname, "type" : "CLOSE", "message" : localNickname + " 님이 퇴장하셨습니다."}));
	webSocket.close();
}
function send () {
	webSocket.send(JSON.stringify({"studyUrl" : studyUrl, "nickname" : localNickname, "type" : "SEND", "message" : chatInput.value}));
	chatInput.value = "";
}
function onMessage (message) {
	jsonMsg = JSON.parse(message.data);
	
	var msgNickname = jsonMsg["nickname"];
	var msgContents = jsonMsg["message"];
	var msgInsertDate = jsonMsg["insertDate"];
	var msgType = jsonMsg["type"];
	console.log(msgType);
	
	// 채팅을 입장한 사람의 메세지 수신
	if (msgType == "OPEN") {
		console.log(msgNickname + "님 참가요.");
		addUser (msgNickname);
	} 
	// 채팅을 퇴장한 사람의 메세지 수신
	else if (msgType == "CLOSE") {
		console.log(msgNickname + "님 나가요");
		removeUser (msgNickname);
	} 
	// 기존에 채팅중이던 사람의 닉네임 수신
	else if (msgType == "EXISTING") {
		addUser (msgNickname);
	}
	// 채팅 수신
	else if (msgType == "SEND") {
		if (jsonMsg["nickname"] == localNickname) {
			printMyMessage(msgContents, msgInsertDate);
		} else {
			printReceivedMessage(msgNickname, msgContents, msgInsertDate)
		}
	}
}

function addUser (userName) {
	userList.innerHTML += '<div id="user-' + userName + '" class="message-item d-flex align-items-center px-3 py-2">' +
							    '<div class="user-img">' +
// 이미지 필요시 추가
// 									'<img src="/resources/css/study/assets/images/users/8.jpg" alt="user"' + 
// 										'class="img-fluid rounded-circle" width="40px">' + 
										'<span class="profile-status offline float-right"></span>' +
								'</div>' +
								'<div class="w-75 d-inline-block v-middle pl-2">' +
								    '<h6 class="message-title mb-0 mt-1">' + userName + '</h6>' +
								'</div>' +
						   '</div>';
}

function removeUser (userName) {
	document.getElementById("user-" + userName).remove();
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