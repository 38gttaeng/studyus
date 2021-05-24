var titleCheckFlag = false;

$(function() {

	// 파일
	$("#input-file").on("change", function(){
	    $filename = $(this).val();

	    if($filename == "") {
	    	$filename = "파일을 선택해주세요.";
		} else {
			$filename = $filename.split('/').pop().split('\\').pop();
		}
		
	    $("#filename").text($filename);
  	});

	// 유효성 검사
	var title = $("input[name=boTitle]");
	var titleMsg = $("#title-msg");
	
	title.on("keyup", function(){
		if(title.val() == "") {
			$(this).removeClass("is-valid");
			$(this).addClass("is-invalid");
			titleMsg.css("display", "block");
			titleCheckFlag = false;
		} else {
			$(this).removeClass("is-invalid");
			$(this).addClass("is-valid");
			titleMsg.css("display", "none");
			titleCheckFlag = true;
		}
	});
	
	$("#submit-btn").on("click", function() {
		if(titleCheckFlag) {
			$("#postForm").submit();
		}
	});
});
