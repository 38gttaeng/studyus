$(function() {
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
