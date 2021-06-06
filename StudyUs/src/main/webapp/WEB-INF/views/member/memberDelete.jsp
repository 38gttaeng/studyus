<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/resources/css/member/deleteMem.css">
<script type="text/javascript" src="http://code.jquery.com/jquery-3.4.1.min.js"></script>
<title>회원탈퇴</title>
</head>
<body>
	<div class="main-box">
		<div class="link-zone">
			<a href="/" class="home-link">StudyUs</a>
		</div>
		<div class="notice-zone">
			<span class="notice">
				회원탈퇴에 앞서 아래의 사항을<br>
				반드시 확인해주세요.
			</span>
		</div>
		<hr class="line">
		<div class="delete-box">
			<div class="study-zone">
				<span class="check">활동 중인 스터디 확인</span>
				<div class="study-box">
					
				</div>
			</div>
			<form action="/member/delete" name="deleteForm">
				<div class="reason-zone">
					<span class="check">회원탈퇴 이유를 선택해주세요</span>
					<div class="reason-box">
						<div class="check-radio">
							<div class="check-radio1">
								<div class="check-radio3">
									<input type="radio" name="drop-reason" value="서비스 이용 불편">서비스 이용 불편
								</div>
								<div class="check-radio4">
									<input type="radio" name="drop-reason" value="방문 횟수 거의 없음">방문 횟수 거의 없음
								</div>
							</div>
							<div class="check-radio2">
								<div class="check-radio3">
									<input type="radio" name="drop-reason" value="공부에 흥미를 잃음">공부에 흥미를 잃음
								</div>
								<div class="check-radio4">
									<input type="radio" name="drop-reason" value="콘텐츠 부족">콘텐츠 부족
								</div>
							</div>
						</div>
						<ul>
							<li class="caution">탈퇴 시 보유 중인 프리미엄 및 회원 정보는 모두 소멸되며,<br>
							프리미엄 및 회원 정보의 복원은 불가능합니다.</li>
						</ul>
					</div>
				</div>
				<div class="check-zone">
					<input type="checkbox" value="agree" id="agree">위 내용을 확인했으며, 탈퇴를 진행합니다.
				</div>
				<div class="btn-zone">
					<input type="button" value="취소" onclick="location.href='/member/myInfo'" class="cancel-btn">
					<input type="button" value="회원탈퇴" class="delete-btn">
				</div>
			</form>
		</div>
	</div>
	
	<script>
		var agreeCheckflag = false;
		
		$("#agree").on("click", function() {
			var check = $("#agree").is(":checked");
			if(check ==  true) {
				agreeCheckflag = true;
			}
		});
		
		
		
		$(".delete-btn").on("click", function() {
			console.log(agreeCheckflag);
			goDelete();
		});
		
		function goDelete() {
			var form = document.deleteForm;
			if(agreeCheckflag == false) {
				return false;
			}
			else {
				form.method= "post";
				form.action = "/member/delete";
				form.submit();
			}
		}
	</script>
</body>
</html>