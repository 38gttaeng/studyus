$(document).ready(function(){
    var owl = $('.owl-carousel');
    
    owl.owlCarousel({
        items:5,                 // 한번에 보여줄 아이템 수
        loop:false,               // 반복여부
        margin:30,               // 오른쪽 간격
        autoplay:false,           // 자동재생 여부
        autoplayHoverPause:true,  // 마우스오버시 멈출지 여부
		responsive:{
			0:{items:1},
			600:{items:3},
			1000:{items:5}
		},
		nav: true
    });    
    
});