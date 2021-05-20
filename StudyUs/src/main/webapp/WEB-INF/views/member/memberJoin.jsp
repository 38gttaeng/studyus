<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
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
	<main class="">
		<div class="">
			<c:url var="/" value="home"></c:url>
			<div class="home-link" ><a href="${home}">StudyUs</a></div>
			<div class="">
				<table>
					<tr>
						<td>아이디</td>
						<td>
							<input type="text" name="mbId" id="mbId" placeholder="아이디"><br>
							<span class="">아이디는 4~16자 이내 영문 및 숫자</span>
							<span class="id ok">이 아이디는 사용 가능합니다</span>
							<span class="id error">이미 사용 중인 아이디 입니다</span>
						</td>
					</tr>
					<tr>
						<td>비밀번호</td>
						<td>
							<input type="password" name="mbPassword" id="mbPassword" placeholder="비밀번호">
						</td>
					</tr>
					<tr>
						<td>비밀번호 확인</td>
						<td>
							<input type="password" name="mbPwdCheck" id="mbPwdCheck" placeholder="비밀번호 확인"><br>
							<span class="pwd error">비밀번호가 일치하지 않습니다.</span>
							<span class="pwd ok">비밀번호가 일치합니다.</span>
						</td>
					</tr>
					<tr>
						<td>이름</td>
						<td>
							<input type="text" name="mbName" id="mbName" placeholder="실명을 입력해주세요">
						</td>
					</tr>
					<tr>
						<td>닉네임</td>
						<td>
							<input type="text" name="mbNickname" id="mbNickname" placeholder="닉네임"><br>
							<span class="nick ok">이 닉네임은 사용 가능합니다</span>
							<span class="nick error">이미 사용 중인 닉네임 입니다</span>
						</td>
					</tr>
					<tr>
						<td>이메일</td>
						<td>
							<input type="text" name="mbEmail" id="mbEmail" placeholder="이메일"><br>
							<span class="">이메일 인증이 필요하니 정확히 입력해주세요</span>
						</td>
					</tr>
					<tr>
						<td>휴대폰 번호</td>
						<td>
							<input type="text" name="mbPhone" id="mbPhone" placeholder="'-' 구분없이 입력">
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<input type="submit" value="가입하기" class="btn submit">
						</td>
					</tr>
				</table>
				
			</div>
		</div>
	</main>
	
	<script>
		
		$("#mbId").on("blur", function() {
			var mbId = $("#mbId").val();
			$.ajax({
				url : "dupId.member",
				data : { "mbId" : mbId },
				success : function(result) {
					if(result != 0) {
						$(".id.ok").hide();
						$(".id.error").show();
					}else{
						$(".id.ok").show();
						$(".id.error").hide();
					}
				},
				error : function() {
					console.log("전송 실패");
				}
			});
			
		});
		
		$("#mbPwdCheck").on("blur", function(){
			var mbPassword=$("mbPassword").val();
			var mbPwdCheck=$("mbPwdCheck").val();
			if(mbPassword != "" || mbPwdCheck != ""){
				if(mbPassword == mbPwdCheck){
					$(".pwd.ok").show();
					$(".pwd.error").hide();
					$(".btn.submit").removeAttr("disabled");
				}else{
					$(".pwd.ok").hide();
					$(".pwd.error").show();
					$(".btn.submit").attr("disabled", "disabled");
				}
			}
		});
		
		$("#mbNickname").on("blur", function() {
			var mbNickname = $("#mbNickname").val();
			$.ajax({
				url : "dupNick",
				data : { "mbNickname" : mbNickname },
				success : function(result) {
					//console.log(result);
					if(result != 0) {
						$(".nick.ok").hide();
						$(".nick.error").show();
					}else{
						$(".nick.ok").show();
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