<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/resources/css/member/findIdPwd.css">
<script type="text/javascript" src="http://code.jquery.com/jquery-3.4.1.min.js"></script>
<title>아이디/비밀번호 찾기</title>
<style type="text/css">
	.find-pwd-zone {
		display: none;
	}
	
	.result-zone.id, .result-zone.pwd {
		display: none;
	}
</style>
</head>
<body>
	<div class="find-box">
		<div class="find-zone">
			<div class="home-link">
					<a href="/">StudyUs</a>
			</div>
			<div class="select-zone">
				<input type="button" value="아이디 찾기" class="page change id">
				<input type="button" value="비밀번호 찾기" class="page change pwd">
			</div>
			<div class="line"></div>
			<div class="input-zone">
				<div class="find-id-zone">
					<form action="/member/findId">
						<span class="main-text">이메일 정보로 아이디 찾기</span><br>
						<span class="sub-text">회원가입 시 입력한 정보로 찾을 수 있습니다.</span><br>
						<input type="text" name="mbName" id="mbName1" placeholder="이름" class="form-control 1"><br>
						<input type="text" name="mbEmail" id="mbEmail1" placeholder="이메일" class="form-control 2"><br>
						<input type="button" value="확인" class="submit-btn id">
					</form>
				</div>
				<div class="result-zone id">
					<form action="/member/findIdConfirm" method="post">
						<span class="main-text">이메일 정보로 아이디 찾기</span><br> 
						<span class="sub-text">인증번호를 입력해주세요.</span><br>
						<input type="text" name="authKey" placeholder="인증번호" class="form-control"><br>
						<input type="submit" value="확인" class="submit-btn">
					</form>
				</div>
				
				<div class="find-pwd-zone">
					<form action="/member/findPwd">
						<span class="main-text">이메일 정보로 비밀번호 찾기</span><br>
						<span class="sub-text">회원가입 시 입력한 정보로 찾을 수 있습니다.</span><br>
						<input type="text" name="mbId" id="mbId" placeholder="아이디" class="form-control 1"><br>
						<input type="text" name="mbName" id="mbName2" placeholder="이름" class="form-control 2"><br>
						<input type="text" name="mbEmail" id="mbEmail2" placeholder="이메일" class="form-control 3"><br>
						<input type="button" value="확인" class="submit-btn pwd">
					</form>
				</div>
				<div class="result-zone pwd">
					<form action="/member/findPwdConfirm" method="post">
						<span class="main-text">이메일 정보로 비밀번호 찾기</span><br>
						<span class="sub-text">인증번호를 입력해주세요.</span><br>
						<input type="text" name="authKey" placeholder="인증번호" class="form-control"><br>
						<input type="submit" value="확인" class="submit-btn">
					</form>
				</div>
			</div>
		</div>
	</div>
	
	<script>
		
		$(".page.change.id").addClass("selected");
		$(".page.change.pwd").addClass("common");
	
		$(".page.change.id").click(function() {
			$(".find-id-zone").show();
			$(".find-pwd-zone").hide();
			$(".result-zone.id").hide();
			$(".result-zone.pwd").hide();
			$(".page.change").removeClass("selected");
			$(".page.change").removeClass("common");
			$(".page.change.id").addClass("selected");
			$(".page.change.pwd").addClass("common");
		});
		
		$(".page.change.pwd").click(function() {
			$(".find-id-zone").hide();
			$(".find-pwd-zone").show();
			$(".result-zone.id").hide();
			$(".result-zone.pwd").hide();
			$(".page.change").removeClass("selected");
			$(".page.change").removeClass("common");
			$(".page.change.id").addClass("common");
			$(".page.change.pwd").addClass("selected");
		});
		
		$(".submit-btn.id").click(function() {
			var mbName = $("#mbName1").val();
			var mbEmail = $("#mbEmail1").val();
			$(".find-id-zone").hide();
			$.ajax({
				url: "/member/findId",
				data: { "mbName" : mbName, "mbEmail" : mbEmail },
				type : "post",
				success: function() {
					$(".result-zone.id").show();
				}
			});
		});
		
		$(".submit-btn.pwd").click(function() {
			var mbId = $("#mbId").val();
			var mbName = $("#mbName2").val();
			var mbEmail = $("#mbEmail2").val();
			$(".find-pwd-zone").hide();
			$.ajax({
				url: "/member/findPwd",
				data: { "mbId" : mbId, "mbName" : mbName, "mbEmail" : mbEmail },
				type : "post",
				success: function() {
					$(".result-zone.pwd").show();
				}
			});
		});
	</script>
</body>
</html>