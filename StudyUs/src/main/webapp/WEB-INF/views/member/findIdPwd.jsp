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
					<form action="/member/findId" method="post">
						<span class="main-text">이메일 정보로 아이디 찾기</span><br>
						<span class="sub-text">회원가입 시 입력한 정보로 찾을 수 있습니다.</span><br>
						<input type="text" name="mbName" placeholder="이름" class="form-control 1"><br>
						<input type="text" name="mbEmail" placeholder="이메일" class="form-control 2"><br>
						<input type="submit" value="확인" class="submit-btn id">
					</form>
				</div>
				<div class="find-pwd-zone">
					<form action="/member/findPwd" method="post">
						<span class="main-text">이메일 정보로 비밀번호 찾기</span><br>
						<span class="sub-text">회원가입 시 입력한 정보로 찾을 수 있습니다.</span><br>
						<input type="text" name="mbId" placeholder="아이디" class="form-control 1"><br>
						<input type="text" name="mbName" placeholder="이름" class="form-control 2"><br>
						<input type="text" name="mbEmail" placeholder="이메일" class="form-control 3"><br>
						<input type="submit" value="확인" class="submit-btn pwd">
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
			$(".page.change").removeClass("selected");
			$(".page.change").removeClass("common");
			$(".page.change.id").addClass("selected");
			$(".page.change.pwd").addClass("common");
		});
		
		$(".page.change.pwd").click(function() {
			$(".find-id-zone").hide();
			$(".find-pwd-zone").show();
			$(".page.change").removeClass("selected");
			$(".page.change").removeClass("common");
			$(".page.change.id").addClass("common");
			$(".page.change.pwd").addClass("selected");
		});
		
		$(".submit-btn.id").click(function() {
			$(".find-id-zone").hide();
			$(".result-zone.id").show();
		});
		
		$(".submit-btn.pwd").click(function() {
			$(".find-pwd-zone").hide();
			$(".result-zone.pwd").show();
		});
	</script>
</body>
</html>