$(function() {

	// 사진파일 받아오기
	/*$.ajax({
		url : "/study/assignment/pic-list",
		type : "get",
		dataType : 'json',
		success : function(picList) {
			for(var i in picList) {
				console.log(picList[i].url);//////////////////////////////
				console.log(picList[i].pic);////////////////////////////////
				$(".grid-item").append("<img src='" + picList[i].pic + "' alt='Picture " + (i+1) + "'>");
			}
		},
		error : function() {
			alert("전송 실패!");
		}
	});*/
	
	// Masonry Layout
	var msnry = new Masonry( '.grid', {
		itemSelector: '.grid-item',
		columnWidth: '.grid-sizer',
		percentPosition: true,
		gutter : 20,
	});
	
	imagesLoaded( '.grid' ).on( 'progress', function() {
		msnry.layout();
	});
	
	var viewer = new Viewer(document.querySelector('.grid'), {
		title: false,
	});
});
