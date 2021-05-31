var idCheckPlag = false;
var pwdCheckPlag = false;
var nameCheckPlag = false;
var nickCheckPlag = false;
var emailCheckPlag = false;
var phoneCheckPlag = false;


$(document).ready(function() {
	var idPattern = /^[a-zA-Z0-9]{4,16}$/;
	var pwPattern = /^[a-zA-Z0-9|!@#&]{8,20}$/;
	var emailPattern = /^[\w]{1,12}@[0-9a-zA-Z]{2,8}.[\w]{2,3}/i;
	var phonePattern = /^01[0|1|6-9][\w]{3,4}[\w]{3,4}$/;
	
	var mbId = $("#mbId");
	var mbPassword = $("#mbPassword");
	var mbPwdCheck = $("#mbPwdCheck");
	var mbName = $("#mbName");
	var mbNickname = $("#mbNickname");
	var mbEmail = $("#mbEmail");
	var mbPhone = $("#mbPhone");
	
	mbId.on("keyup", function() {
		var id = $("#mbId").val();
		if(!idPattern.test(mbId.val())) {
			$(".id.no").show();
			$(".id.ok").hide();
			$(".id.error").hide();
			idCheckPlag = false;
		} else {
			$(".id.no").hide();
			mbId.on("blur", function() {
				var id = mbId.val();
				$.ajax({
					url : "/member/dupId",
					data : { "mbId" : id },
					success : function(result) {
					if(id != "") {
						if(result != 0) {
							$(".id.ok").hide();
							$(".id.no").hide();
							$(".id.error").show();
							idCheckPlag = false;
							console.log("아이디: "+idCheckPlag);
						}else{
							$(".id.ok").show();
							$(".id.no").hide();
							$(".id.error").hide();
							idCheckPlag = true;
							console.log("아이디: "+idCheckPlag);
						}
					}else {
						$(".id.ok").hide();
						$(".id.no").hide();
						$(".id.error").hide();
					}
				},
					error : function() {
						console.log("전송 실패");
					}
				});
			});
		}
	});	
	
	mbPassword.on("keyup", function() {
		if(!pwPattern.test(mbPassword.val())) {
			$(".pwd.no").show();
			$(".pwd.ok").hide();
			$(".pwd.error").hide();
			pwdCheckPlag = false;
		} else {
			$(".pwd.no").hide();
			mbPwdCheck.on("blur", function () {
				var pwd1 = mbPassword.val();
				var pwd2 = mbPwdCheck.val();
				if ( pwd1 != '' && pwd2 != '' ) {
					if (pwd1 != pwd2) {
						$(".pwd.ok").hide();
						$(".pwd.no").hide();
						$(".pwd.error").show();
						pwdCheckPlag = false;
					} else {
						$(".pwd.ok").show();
						$(".pwd.no").hide();
						$(".pwd.error").hide();
						pwdCheckPlag = true;
					}
				} else {
					$(".pwd.ok").hide();
					$(".pwd.no").hide();
					$(".pwd.error").hide();
				}
			});
		}
	});
	
	mbName.on("blur", function() {
		var name = mbName.val();
		if(name == '' || name == null) {
			nameCheckPlag = false;
		} else {
			nameCheckPlag = true;
		}
	});
	
	mbNickname.on("blur", function() {
		var nickname = mbNickname.val();
		$.ajax({
			url : "/member/dupNick",
			data : { "mbNickname" : nickname },
			success : function(result) {
				if(nickname != "") {
					if(result != 0) {
						$(".nick.ok").hide();
						$(".nick.error").show();
						nickCheckPlag = false;
					}else{
						$(".nick.ok").show();
						$(".nick.error").hide();
						nickCheckPlag = true;
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
	
	mbEmail.on("blur", function() {
		if(!emailPattern.test(mbEmail.val())) {
			$(".email.no").show();
			$(".email-msg").hide();
			emailCheckPlag = false;
		} else {
			$(".email.no").hide();
			$(".email-msg").hide();
			emailCheckPlag = true;
		}
	});
	
	mbPhone.on("keyup", function() {
		if(!phonePattern.test(mbPhone.val())) {
			$(".phone.no").show();
			phoneCheckPlag = false;
		} else {
			$(".phone.no").hide();
			phoneCheckPlag = true;
		}
	});
	
	$(".enroll-btn").on("click", function() {
	goEnroll();
});

function goEnroll() {
	var form = document.enrollForm;
	if(idCheckPlag == false) {
		return false;
	}
	if(pwdCheckPlag == false) {
		return false;
	}
	if(nameCheckPlag == false) {
		return false;
	}
	if(nickCheckPlag == false) {
		return false;
	}
	if(emailCheckPlag == false) {
		return false;
	}
	if(phoneCheckPlag == false) {
		return false;
	}
	else {
		form.method= "post";
		form.action = "/member/register";
		form.submit();
	}
}
	
});