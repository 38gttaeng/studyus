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
				url: '/file/upload/image',
				method: 'POST',
				name: 'uploadImage',
				withCredentials: false,
				//customUploader: () => {},
				callbackOK: (serverResponse, next) => {
			    	next(serverResponse);
			    },
				callbackKO: serverError => {
					alert(serverError);
				},
				checkBeforeSend: (file, next) => {
			    	console.log(file);
			    	next(file); // go back to component and send to the server
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
	
		// 수정파일인지 여부 체크
		if($("input[name=viewCheck]").val() == "modifyView") {
			titleCheckFlag = true;
		}
	
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
