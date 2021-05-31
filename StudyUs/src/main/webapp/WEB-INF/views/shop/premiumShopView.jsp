<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>StudyUs</title>
  </head>
  <body>
    <!-- menubar -->
    <jsp:include page="../common/menubar.jsp"/>
    <script>$(".nav-item:eq(2)").addClass("active");</script>

  <div class="hero-wrap hero-wrap-2" style="background-image: url('/resources/images/premiumshop.png');" data-stellar-background-ratio="0.5">
      <div class="overlay"></div>
      <div class="container">
        <div class="row no-gutters slider-text align-items-end justify-content-start">
          <div class="col-md-4 ftco-animate text-center text-md-left mb-5" style="background-color: rgba( 230, 231, 235, 0.5 );">
          	<p class="breadcrumbs mb-0"><span class="mr-3"><a href="index.html">Home <i class="ion-ios-arrow-forward"></i></a></span> <span>StudyShop</span></p>
            <h1 class="mb-3 bread">Study Shop</h1>
          </div>
        </div>
      </div>
    </div>
	<div class="text-center" style="margin-top:70px">
		<h5><b>프리미엄 이용권</b></h5>
		<p>프리미엄 이용권으로 최대 10인까지 함께 공부하세요.</p>
	</div>
    <section class="ftco-section">
    	<div class="container">
    		<div class="row">
    			<div class="col-md-4 text-center ftco-animate">
    				<div class="steps">
    					<div class="icon mb-4 d-flex justify-content-center align-items-center">
    						<span class="flaticon-cloud-computing"></span>
    					</div>
    					<h3>더 많은 팀원들과</h3>
    					<p>같은 분야의 사람들로부터 <br> 동기부여를 받을 수 있습니다.</p>
    				</div>
    			</div>
    			<div class="col-md-4 text-center ftco-animate">
    				<div class="steps">
    					<div class="icon mb-4 d-flex justify-content-center align-items-center">
    						<span class="flaticon-customer-service"></span>
    					</div>
    					<h3>더 많은 피드백</h3>
    					<p>같은 분야를 준비하는 사람들로부터 <br> 많은 피드백을 받을 수 있습니다.</p>
    				</div>
    			</div>
    			<div class="col-md-4 text-center ftco-animate">
    				<div class="steps">
    					<div class="icon mb-4 d-flex justify-content-center align-items-center">
    						<span class="flaticon-database"></span>
    					</div>
    					<h3>더 많은 자료</h3>
    					<p>더 많은 자료를 보고 <br> 새로운 아이디어를 만들 수 있습니다.</p>
    				</div>
    			</div>
    		</div>
    	</div>
    </section>

    <section class="ftco-section bg-light">
    	<div class="container">
    		<div class="row justify-content-center mb-5 pb-3">
          <div class="col-md-7 text-center heading-section ftco-animate">
            <h2 class="mb-4">와! 프리미엄 타이어보다 싸다!</h2>
          </div>
        </div>
    		<div class="row d-flex">
    		<div class="col-lg-2">&nbsp;</div>
	        <div class="col-lg-8 ftco-animate">
	          <div class="block-7">
	            <div class="text-center">
		            <h2 class="heading">Premium</h2>
		            <span class="price"><sup>₩</sup> <span class="number">3,100,000<small class="per"></small></span></span>
		            <span class="excerpt d-block">프리미엄 서비스를 무제한으로 이용하실 수 있습니다.</span>
		            <h3 class="heading-2 mb-3">Enjoy All The Features</h3>
		            
		            <ul class="pricing-text mb-4">
		              <li>최대인원 <strong>10명</strong></li>
		              <li>&nbsp;</li>
		              <li>&nbsp;</li>
		              <li>&nbsp;</li>
		            </ul>
		            <button id="check_module" type="button" class="btn btn-primary d-block px-3 py-3 mb-4" >구매하기</button>
		            <span class="excerpt d-block" style="margin-bottom: 0;">스터디 프리미엄은 교환 및 환불이 불가합니다.</span>
	            </div>
	          </div>
	          
	        </div>
	          </div>
	        </div>
    </section>

    <section class="ftco-section ftco-no-pt ftc-no-pb">
    	<div class="container">
    		<div class="row justify-content-center mb-5">
          <div class="col-md-7 text-center heading-section ftco-animate">
            <h2 class="mb-4" style="margin-top:70px; margin-bottom:0">자주 묻는 질문</h2>
            <p>결제와 관련된 궁금한 점을 답변해드립니다.</p>
          </div>
        </div>
    		<div class="row">
    			<div class="col-md-12 ftco-animate">
    				<div id="accordion">
    					<div class="row">
    						<div class="col-md-6">
    							<div class="card">
						        <div class="card-header">
										  <a class="card-link" data-toggle="collapse"  href="#menuone" aria-expanded="true" aria-controls="menuone">평생 사용 가능한가요? <span class="collapsed"><i class="ion-ios-arrow-up"></i></span><span class="expanded"><i class="ion-ios-arrow-down"></i></span></a>
						        </div>
						        <div id="menuone" class="collapse show">
						          <div class="card-body">
												<p>스터디 프리미엄은 구매 시 영구적으로 사용 가능한 상품이며, 별도의 사용 기간이 없습니다.</p>
						          </div>
						        </div>
						      </div>

						      <div class="card">
						        <div class="card-header">
										  <a class="card-link" data-toggle="collapse"  href="#menutwo" aria-expanded="false" aria-controls="menutwo">프리미엄 이용권을 양도할 수 있나요?<span class="collapsed"><i class="ion-ios-arrow-up"></i></span><span class="expanded"><i class="ion-ios-arrow-down"></i></span></a>
						        </div>
						        <div id="menutwo" class="collapse">
						          <div class="card-body">
						          				<p>프리미엄은 양도 불가능하며, 동시에 프리미엄 혜택을 받는 스터디의 모임장도 변경될 수 없습니다.</p>
						          </div>
						        </div>
						      </div>

						      <div class="card">
						        <div class="card-header">
										  <a class="card-link" data-toggle="collapse"  href="#menu3" aria-expanded="false" aria-controls="menu3">이용중인 프리미엄은 어떻게 확인하나요? <span class="collapsed"><i class="ion-ios-arrow-up"></i></span><span class="expanded"><i class="ion-ios-arrow-down"></i></span></a>
						        </div>
						        <div id="menu3" class="collapse">
						          <div class="card-body">
												<p>지레짐작으로 사용하고있구나 알고계시면 됩니다...</p>
						          </div>
						        </div>
						      </div>
    						</div>

    						<div class="col-md-6">
    							<div class="card">
						        <div class="card-header">
										  <a class="card-link" data-toggle="collapse"  href="#menu4" aria-expanded="false" aria-controls="menu4">Can I cancel a domain? <span class="collapsed"><i class="ion-ios-arrow-up"></i></span><span class="expanded"><i class="ion-ios-arrow-down"></i></span></a>
						        </div>
						        <div id="menu4" class="collapse">
						          <div class="card-body">
												<p>When she reached the first hills of the Italic Mountains, she had a last view back on the skyline of her hometown Bookmarksgrove, the headline of Alphabet Village and the subline of her own road, the Line Lane. Pityful a rethoric question ran over her cheek, then she continued her way.</p>
						          </div>
						        </div>
						      </div>

						      <div class="card">
						        <div class="card-header">
										  <a class="card-link" data-toggle="collapse"  href="#menu5" aria-expanded="false" aria-controls="menu5">How do I transfer a domain name? <span class="collapsed"><i class="ion-ios-arrow-up"></i></span><span class="expanded"><i class="ion-ios-arrow-down"></i></span></a>
						        </div>
						        <div id="menu5" class="collapse">
						          <div class="card-body">
												<p>When she reached the first hills of the Italic Mountains, she had a last view back on the skyline of her hometown Bookmarksgrove, the headline of Alphabet Village and the subline of her own road, the Line Lane. Pityful a rethoric question ran over her cheek, then she continued her way.</p>
						          </div>
						        </div>
						      </div>

						      <div class="card">
						        <div class="card-header">
										  <a class="card-link" data-toggle="collapse"  href="#menu6" aria-expanded="false" aria-controls="menu6">환불 가능한가요? <span class="collapsed"><i class="ion-ios-arrow-up"></i></span><span class="expanded"><i class="ion-ios-arrow-down"></i></span></a>
						        </div>
						        <div id="menu6" class="collapse">
						          <div class="card-body">
												<p>스터디 프리미엄은 환불이 불가한 상품으로, 상품 구매 시 이를 참고하시어 결제 부탁드립니다.</p>
						          </div>
						        </div>
						      </div>
    						</div>
    					</div>
				    </div>
    			</div>
    		</div>
    	</div>
    </section>
	
	<!-- footer -->
	<jsp:include page="../common/footer.jsp"/>
	
	<script>
	$("#check_module").click(function () {
		var IMP = window.IMP; // 생략가능
		IMP.init('imp40954906');
		// 'iamport' 대신 부여받은 "가맹점 식별코드"를 사용
		// i'mport 관리자 페이지 -> 내정보 -> 가맹점식별코드
		IMP.request_pay({
		pg: 'inicis', // version 1.1.0부터 지원.
		/*
		'kakao':카카오페이,
		html5_inicis':이니시스(웹표준결제)
		'nice':나이스페이
		'jtnet':제이티넷
		'uplus':LG유플러스
		'danal':다날
		'payco':페이코
		'syrup':시럽페이
		'paypal':페이팔
		*/
		pay_method: 'card',
		/*
		'samsung':삼성페이,
		'card':신용카드,
		'trans':실시간계좌이체,
		'vbank':가상계좌,
		'phone':휴대폰소액결제
		*/
		merchant_uid: 'merchant_' + new Date().getTime(),
		/*
		merchant_uid에 경우
		https://docs.iamport.kr/implementation/payment
		위에 url에 따라가시면 넣을 수 있는 방법이 있습니다.
		참고하세요.
		나중에 포스팅 해볼게요.
		*/
		name: '주문명:결제테스트',
		//결제창에서 보여질 이름
		amount: 1000,
		//가격
		buyer_email: 'iamport@siot.do',
		buyer_name: '구매자이름',
		buyer_tel: '010-1234-5678',
		buyer_addr: '서울특별시 강남구 삼성동',
		buyer_postcode: '123-456',
		m_redirect_url: 'https://www.yourdomain.com/payments/complete'
		/*
		모바일 결제시,
		결제가 끝나고 랜딩되는 URL을 지정
		(카카오페이, 페이코, 다날의 경우는 필요없음. PC와 마찬가지로 callback함수로 결과가 떨어짐)
		*/
		}, function (rsp) {
		console.log(rsp);
		if (rsp.success) {
		var msg = '결제가 완료되었습니다.';
		msg += '고유ID : ' + rsp.imp_uid;
		msg += '상점 거래ID : ' + rsp.merchant_uid;
		msg += '결제 금액 : ' + rsp.paid_amount;
		msg += '카드 승인번호 : ' + rsp.apply_num;
		} else {
		var msg = '결제에 실패하였습니다.';
		msg += '에러내용 : ' + rsp.error_msg;
		}
		alert(msg);
		});
		});
	</script>
  </body>
</html>