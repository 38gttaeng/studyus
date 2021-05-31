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

		if(quill.getLength() > 0) {
			$("#editor").css("border", "1px solid #ccc");
			$("#editor").css("border-top", "0px");
		}
	});
	
	$("#submit-btn").on("click", function() {
	
		// 수정파일인지 여부 체크
		if($("input[name=viewCheck]").val() == "m") {
			$("#delFiles").val(delFList);
		}
		
		// 전송
		if(quill.getLength() > 3) {
			var html = quill.root.innerHTML;
			$("input[name=suContents]").val(html);
			
			$("#postForm").submit();
		} else {
			alert("내용을 입력하세요.");
			$("#editor").css("border", "1px solid red");
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