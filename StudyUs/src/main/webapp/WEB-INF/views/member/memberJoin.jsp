<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/resources/css/member/memberJoin.css">
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script type="text/javascript" src="http://code.jquery.com/jquery-3.4.1.min.js"></script>
<title>회원가입</title>
<style type="text/css">
	span.ok{
		display : none;
		color:green
	}
	span.error{
		display : none;
		color:red
	}
</style>
</head>
<body>
	<div class="user_card">
		<div class="home-link" ><a href="/">StudyUs</a></div>
		<div class="enroll-box">
			<form action="/member/register" method="post">
				<div class="info-box">
					<div class="input-zone">
						<label for="mbId">아이디</label>
						<input type="text" name="mbId" id="mbId" placeholder="아이디는 4~16자 이내 영문 및 숫자" maxlength="16"><br>
						<span class="id ok">이 아이디는 사용 가능합니다</span>
						<span class="id error">이미 사용 중인 아이디 입니다</span><br>
					</div>
					<div class="input-zone">
						<label for="mbPassword">비밀번호</label>
						<input type="password" name="mbPassword" id="mbPassword" placeholder="비밀번호는 8~20자 이내 영문 및 숫자" maxlength="20">
					</div>
					<div class="input-zone">
						<label for="mbPwdCheck">비밀번호 확인</label>
						<input type="password" name="mbPwdCheck" id="mbPwdCheck" placeholder="비밀번호 확인"><br>
						<span class="pwd ok">비밀번호가 일치합니다.</span>
						<span class="pwd error">비밀번호가 일치하지 않습니다.</span><br>
					</div>
					<div class="input-zone">
						<label for="mbName">이름</label>
						<input type="text" name="mbName" id="mbName" placeholder="실명을 입력해주세요">
					</div>
					<div class="input-zone">
						<label for="mbNickname">닉네임</label>
						<input type="text" name="mbNickname" id="mbNickname" placeholder="닉네임"><br>
						<span class="nick ok">이 닉네임은 사용 가능합니다</span>
						<span class="nick error">이미 사용 중인 닉네임 입니다</span><br>
					</div>
					<div class="input-zone">
						<label for="mbEmail">이메일</label>
						<input type="text" name="mbEmail" id="mbEmail" placeholder="이메일"><br>
						<span id="email-msg">이메일 인증이 필요하니 정확히 입력해주세요</span><br>
					</div>
					<div class="input-zone">
						<label for="mbPhone">휴대폰 번호</label>
						<input type="text" name="mbPhone" id="mbPhone" placeholder="'-' 구분없이 입력"><br>
					</div>
					<div class="btn-zone">
						<input type="submit" value="가입하기" class="enroll-btn">
					</div>
				</div>
			</form>
		</div>
	</div>
	
	<script>
		$(document).ready(function(){
			//한글입력 안되게 처리
			$("input[name=mbId]").keyup(function(event){ 
				if (!(event.keyCode >=37 && event.keyCode<=40)) {
					var inputVal = $(this).val();
					$(this).val(inputVal.replace(/[^a-z0-9]/gi,''));
				}
			});
		});
	
		$("#mbId").on("blur", function() {
			var mbId = $("#mbId").val();
			$.ajax({
				url : "/member/dupId",
				data : { "mbId" : mbId },
				success : function(result) {
					if(mbId != "") {
						if(result != 0) {
							$(".id.ok").hide();
							$(".id.error").show();
							$(".enroll-btn").attr("disabled", "disabled");
						}else{
							$(".id.ok").show();
							$(".id.error").hide();
							$(".enroll-btn").removeAttr("disabled");
						}
					}else {
						$(".id.ok").hide();
						$(".id.error").hide();
					}
				},
				error : function() {
					console.log("전송 실패");
				}
			});
			
		});
		
		 $("#mbPwdCheck").on("blur", function () {
		        var pwd1 = $("#mbPassword").val();
		        var pwd2 = $("#mbPwdCheck").val();
		        if ( pwd1 != '' && pwd2 != '' ) {
		            if (pwd1 != pwd2) {
		            	$(".pwd.ok").hide();
						$(".pwd.error").show();
						$(".enroll-btn").attr("disabled", "disabled");
		            } else {
						$(".pwd.ok").show();
						$(".pwd.error").hide();
						$(".enroll-btn").removeAttr("disabled");
		            }
		        } else {
		        	$(".pwd.ok").hide();
		        	$(".pwd.error").hide();
		        }
		    });
		
		
		$("#mbNickname").on("blur", function() {
			var mbNickname = $("#mbNickname").val();
			$.ajax({
				url : "/member/dupNick",
				data : { "mbNickname" : mbNickname },
				success : function(result) {
					if(mbNickname != "") {
						if(result != 0) {
							$(".nick.ok").hide();
							$(".nick.error").show();
							$(".enroll-btn").attr("disabled", "disabled");
						}else{
							$(".nick.ok").show();
							$(".nick.error").hide();
							$(".enroll-btn").removeAttr("disabled");
						}
					}else {
						$(".nick.ok").hide();
						$(".nick.error").hide();
					}
				},
				error : function() {
					console.log("전송 실패");
				}
			});
		});
	</script>
</body>
</html>