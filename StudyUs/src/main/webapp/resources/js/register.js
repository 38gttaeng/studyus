var titleCheckFlag = false;
const limit = 3000; // 3500자 제한

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

	// Quill
	var toolbarOptions = {
		container : [
		  ['bold', 'italic', 'underline', 'strike'],        // toggled buttons
		  ['blockquote', 'code-block'],
	
		  [{ 'list': 'ordered'}, { 'list': 'bullet' }],
		  [{ 'script': 'sub'}, { 'script': 'super' }],      // superscript/subscript
		  [{ 'indent': '-1'}, { 'indent': '+1' }],          // outdent/indent
		  [{ 'direction': 'rtl' }],                         // text direction
		  [{ 'header': [1, 2, 3, 4, 5, 6, false] }],
			
		  ['image', 'video', 'formula', 'code-block'],
		  
		  [{ 'color': [] }, { 'background': [] }],          // dropdown with defaults from theme
		  [{ 'align': [] }],
	
		  ['emoji'],
		]
	};
	
	var quill = new Quill('#editor', {
		modules: {
			//videoResize: {},
			imageResize: {},
			imageDrop: {},
          "toolbar": toolbarOptions,
          "emoji-toolbar": true,
		},
		placeholder: '내용을 입력하세요.',
		theme: 'snow'
	});
	
		// 이미지 로컬에 업로드
	quill.getModule("toolbar").addHandler("image", function() {
		selectLocalImage();
	});
	
		// 글자수 제한
	quill.on('text-change', function (delta, old, source) {
	  if (quill.getLength() > limit) {
	    quill.deleteText(limit, quill.getLength());
	  }
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
		if(titleCheckFlag && title.val() != "") {
			var html = quill.root.innerHTML;
			
			$("input[name=boContents]").val(html);
		
			$("#postForm").submit();
		} else {
			title.removeClass("is-valid");
			title.addClass("is-invalid");
			titleMsg.css("display", "block");
		}
	});
});

function selectLocalImage() {
	const input = document.createElement("input");
	input.setAttribute("type", "file");
	input.click();
	
	input.onchange = function() {
		const fd = new FormData();
		const file = $(this)[0].files[0];
		fd.append("image", file);
		
		//////////////////////mbNo도 같이 보내주기(파일 db 저장용)
		$.ajax({
			url: "/file/upload/image",
			type: "post",
			enctype: "multipart/form-data",
			data: {"uploadImage": fd},
			processData: false,
			contextData: false,
			success: function(data) {
				const range = quill.getSelection();
				quill.insertEmbed(range.index, "image", "/resources/images/" + data);
			},
			error: function() {
				alert("전송 실패!");
			}
		});
	}
}
