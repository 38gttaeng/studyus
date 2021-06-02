var titleCheckFlag = false;
var dateCheckFlag = false;
var timeCheckFlag = false;
const limit = 3000; // 3500자 제한

var n = 2;
var delFList = [];

$(function() {
	
	// 수정 파일의 경우 기존 날짜 집어넣는 역할
	if($("input[name=viewCheck]").val() == "m") {
		var deadLineBefore = $("input[name=deadLineBefore]").val();
		var today = moment(new Date()).format('YYYY/MM/DD HH:mm');
		
		var deadLineAfter = moment(deadLineBefore).format('YYYY-MM-DD');
		var deadLineAfter2 = moment(deadLineBefore).format('HH:mm');
		
		// 이미 지난 과제라면 deadline 바꿀 수 없도록
		if(deadLineBefore <= today) {
			$("input[name=asDate]").attr("readonly", true);
			$("input[name=asTime]").attr("readonly", true);
		}
	
		$("input[name=asDate]").val(deadLineAfter);
		$("input[name=asTime]").val(deadLineAfter2);
	}
	
	// 오늘 날짜 이전 선택 막기
	var out = new Date().toISOString().slice(0, 10);
	$("input[name=asDate]").attr("min", out);
	
	// 파일 추가, 삭제
	$('#button-add-file').click(addFileForm);
	$(document).on('click', '.button-delete-file', function(event) {
		if($("input[name=viewCheck]").val() == "m") {
			var delFileNo = $(this).next().val();
			delFList.push(delFileNo);
		}
		$(this).parent().remove();
	});

	// Quill
	var toolbarOptions = {
		container : [
		  ['bold', 'italic', 'underline', 'strike'],        // toggled buttons
		  ['blockquote'],
	
		  [{ 'list': 'ordered'}, { 'list': 'bullet' }],
		  [{ 'script': 'sub'}, { 'script': 'super' }],      // superscript/subscript
		  [{ 'indent': '-1'}, { 'indent': '+1' }],          // outdent/indent
		  [{ 'direction': 'rtl' }],                         // text direction
		  [{ 'header': [1, 2, 3, 4, 5, 6, false] }],
			
		  ['link', 'image', 'video', 'formula', 'code-block'],
		  
		  [{ 'color': [] }, { 'background': [] }],          // dropdown with defaults from theme
		  [{ 'align': [] }],
	
		  ['emoji'],
		]
	};
	
	var picArr = new Array();
	var quill = new Quill('#editor', {
		modules: {
			imageResize: {},
			//videoResize: {},
			imageUpload: {
				url: '/file/upload/assignment-image',
				method: 'POST',
				name: 'uploadImage',
				withCredentials: false,
				//customUploader: () => {},
				callbackOK: (serverResponse, next) => {
			    	next(serverResponse);
					picArr.push(serverResponse.substring(25));
			    },
				callbackKO: serverError => {
					alert(serverError);
				}
			},
          "toolbar": toolbarOptions,
          "emoji-toolbar": true,
		},
		placeholder: '내용을 입력하세요.',
		theme: 'snow'
	});
	
		// 글자수 제한
	quill.on('text-change', function (delta, old, source) {
	  if (quill.getLength() > limit) {
	    quill.deleteText(limit, quill.getLength());
	  }
	});
	
	// 유효성 검사
	$("input[name=asName]").on("keyup", function(){
		var title = $("input[name=asName]");
		var titleMsg = $("#title-msg");
	
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
	
	$("input[name=asDate]").on("change", function() {
		var date = $(this).val();
		var todayDate = moment(new Date()).format('YYYY-MM-DD');
		
		if(date >= todayDate) {
			$(this).removeClass("is-invalid");
			$(this).addClass("is-valid");
			$(".dMsg").css("display", "none");
			dateCheckFlag = true;
		} else if(date < todayDate) {
			$(this).removeClass("is-valid");
			$(this).addClass("is-invalid");
			$(".dMsg").css("display", "none");
			$("#date-msg2").css("display", "block");
			dateCheckFlag = false;
		}
	});
	
	$("input[name=asTime]").on("change", function() {
		var date = $("input[name=asDate]");
		var time = $("input[name=asTime]");
		var deadline = moment(date.val() + " " + time.val()).format('YYYY/MM/DD HH:mm');
		var today = moment(new Date()).format('YYYY/MM/DD HH:mm');
	
		if(date.val() != "") {
			if(deadline <= today) {
				time.removeClass("is-valid");
				time.addClass("is-invalid");
				$(".dMsg").css("display", "none");
				$("#date-msg2").css("display", "block");
				timeCheckFlag = false;
			} else {
				time.removeClass("is-invalid");
				time.addClass("is-valid");
				$(".dMsg").css("display", "none");
				timeCheckFlag = true;
			}
		} else {
			date.removeClass("is-valid");
			date.addClass("is-invalid");
			$(".dMsg").css("display", "none");
			$("#date-msg").css("display", "block");
			dateCheckFlag = false;
		}
	});
	
	// 등록버튼 클릭시
	$("#submit-btn").on("click", function() {
	
		var title = $("input[name=asName]");
		var date = $("input[name=asDate]");
		var time = $("input[name=asTime]");
		
		var deadline = moment(date.val() + " " + time.val()).format('YYYY/MM/DD HH:mm');
		var today = moment(new Date()).format('YYYY/MM/DD HH:mm');
	
		// 내용 비어있는지 체크
		if(title.val() == "") {
			title.addClass("is-invalid");
			title.removeClass("is-valid");
			$("#title-msg").css("display", "block");
			titleCheckFlag = false;
		}
		
		if(date.val() == "" && date.val() == "") {
			date.addClass("is-invalid");
			date.removeClass("is-valid");
			time.addClass("is-invalid");
			time.removeClass("is-valid");
			$(".dMsg").css("display", "none");
			$("#date-msg").css("display", "block");
			dateCheckFlag = false;
			timeCheckFlag = false;
		} else if(date.val() == "") {
			date.addClass("is-invalid");
			date.removeClass("is-valid");
			$(".dMsg").css("display", "none");
			$("#date-msg").css("display", "block");
			dateCheckFlag = false;
		} else if(time.val() == "") {
			time.addClass("is-invalid");
			time.removeClass("is-valid");
			$(".dMsg").css("display", "none");
			$("#date-msg").css("display", "block");
			timeCheckFlag = false;
		}
		
		if(deadline <= today) {
			date.removeClass("is-valid");
			date.addClass("is-invalid");
			time.removeClass("is-valid");
			time.addClass("is-invalid");
			$(".dMsg").css("display", "none");
			$("#date-msg2").css("display", "block");
			dateCheckFlag = false;
			timeCheckFlag = false;
		}
		
		// 수정파일인지 여부 체크
		if($("input[name=viewCheck]").val() == "m") {
			titleCheckFlag = true;
			dateCheckFlag = true;
			timeCheckFlag = true;
			$("#delFiles").val(delFList);
		}
	
		// 전송
		if(titleCheckFlag && dateCheckFlag && timeCheckFlag) {
			// 내용 보내기
			var html = quill.root.innerHTML;
			$("input[name=asContents]").val(html);
			$("input[name=picList]").val(picArr);
			
			// 기간 보내기
			$("input[name=asDeadLine]").val(deadline);
			
			$("#postForm").submit();
		}
	});
	
	// 등록 취소버튼 클릭시
	$("#reset-btn").on("click", function() {
		var groupNo = $("#groupNo").val();

		// 업로드된 파일들 삭제
		if(picArr.length != 0) {
			$.ajax({
				url : "/file/reset/image",
				type : "get",
				data : {"picList": picArr, "folder" : "\\auploadImages"},
				traditional : true,
				success : function() {
					console.log("전송 성공");
				},
				error : function() {
					alert("전송 실패!" + picArr);
				}
			});
		}
		
		// 이전 페이지로 이동 (해당 리스트 페이지)
		location.href="/study/assignment?grNo=" + groupNo;
	});
});

// 파일
var count = 0;
function addFileForm() {
	var html = "<div id='item_"+ count +"'>";
	html += "<input id='input" + count + "' type='file' name='fList'>";
	html += "<a class='button-delete-file btn'><i class='fas fa-minus text-primary' style='cursor:pointer'></i></a></div>";
	count++;
	$("#my-form").append(html);
}