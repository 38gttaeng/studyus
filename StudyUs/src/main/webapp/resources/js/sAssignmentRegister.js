var titleCheckFlag = false;
var dateCheckFlag = false;
var timeCheckFlag = false;
const limit = 3000; // 3500자 제한

var n = 2;
var delFList = [];

$(function() {
	
	if($("input[name=viewCheck]").val() == "r") {
		var status = $("input[name=suStatus]");
		var deadLine = $("input[name=asDeadLine]").val();
		var today = moment(new Date()).format('YYYY/MM/DD HH:mm');
		
		if(today <= deadLine) {
			status.val(1);
		} else {
			alert("과제 제출 기한을 넘겨서 과제를 제출해도 과제율에 반영되지 않습니다!");
			status.val(2);
		}
	}
	
	$('#button-add-file').click(addFileForm);
	$(document).on('click', '.button-delete-file', function(event) {
		if($("input[name=viewCheck]").val() == "m") {
			var delFileNo = $(this).next().val();
			delFList.push(delFileNo);
		}
		$(this).parent().remove();
	});

	// Quill
	var picArr = new Array();
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
	
	var quill = new Quill('#editor', {
		modules: {
			imageResize: {},
			//videoResize: {}, 
			imageUpload: {
				url: '/file/upload/assignment-image',
				method: 'POST',
				name: 'uploadImage',
				withCredentials: false,
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

		if(quill.getLength() > 0) {
			$("#editor").css("border", "1px solid #ccc");
			$("#editor").css("border-top", "0px");
		}
	});
	
	// 등록버튼 클릭시
	$("#submit-btn").on("click", function() {
	
		// 수정파일인지 여부 체크
		if($("input[name=viewCheck]").val() == "m") {
			$("#delFiles").val(delFList);
		}
		
		// 전송
		if(quill.getLength() > 3) {
			var html = quill.root.innerHTML;
			$("input[name=suContents]").val(html);
			$("input[name=picList]").val(picArr);
			
			$("#postForm").submit();
		} else {
			alert("내용을 입력하세요.");
			$("#editor").css("border", "1px solid red");
		}
	});
	
	// 등록 취소버튼 클릭시
	$("#reset-btn").on("click", function() {
		var assignmentNo = $("#assignmentNo").val();
		var submitNo = $("#submitNo").val();

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
		if($("input[name=viewCheck]").val() != "m") {
			// 등록 파일인 경우
			location.href="/study/assignment/detail?asNo=" + assignmentNo;
		} else if($("input[name=viewCheck]").val() == "m") {
			// 수정 파일인 경우
			location.href="study/sAssignment/detail?suNo=" + submitNo;
		}
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