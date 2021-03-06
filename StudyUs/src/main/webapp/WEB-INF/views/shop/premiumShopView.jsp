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

  <div class="hero-wrap hero-wrap-2" style="background-image: url('/resources/images/illustration.png');" data-stellar-background-ratio="0.5">
      <div class="overlay"></div>
      <div class="container">
        <div class="row no-gutters slider-text align-items-end justify-content-start">
          <div class="col-md-4 ftco-animate text-center text-md-left mb-5" style="background-color: rgba( 105, 39, 255, 0 );">
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
		              <li>메인 페이지에 스터디 광고 가능</li>
		              <li>&nbsp;</li>
		              <li>&nbsp;</li>
		            </ul>
		            <c:if test="${empty loginUser }"> <!-- 스터디장만 결제 가능하게 -->
		            		<button id="empty-login" type="button" class="btn btn-primary d-block px-3 py-3 mb-4"  style="width: 70%; margin-left:15%;">구매하기</button>
		            </c:if>
		            <c:if test="${!empty loginUser }">
		            		<button id="check_module" type="button" class="btn btn-primary d-block px-3 py-3 mb-4"  style="width: 70%; margin-left:15%;">구매하기</button>
		            		<input type="hidden" id="userId" name="mbId" value="${loginUser.mbId }">
		            </c:if>
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
	
	<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-x.y.z.js"></script>
	<script type="text/javascript" src="https://service.iamport.kr/js/iamport.payment-1.1.5.js"></script>

	<script>
	$(".nav-item:eq(2)").addClass("active");
	
	// 가맹점 식별코드
	$(function(){
		IMP.init('imp40954906');
	});
	
	$("#check_module").click(function () {
		IMP.request_pay({
		    pg : 'inicis',
		    pay_method : 'card',
		    merchant_uid : 'merchant_' + new Date().getTime(),
		    name : '주문명 : 스터디 프리미엄',
		    amount : 1000,
		    buyer_email : userEmail,
		    buyer_name : userName,
		    buyer_tel : userPhone,
		}, function(rsp) {
		    if ( rsp.success ) {
		    	//[1] 서버단에서 결제정보 조회를 위해 jQuery ajax로 imp_uid 전달하기
		    	jQuery.ajax({
		    		url: "/shop/premiumShop", //cross-domain error가 발생하지 않도록 동일한 도메인으로 전송
		    		type: 'get',
		    		dataType: 'json',
		    	}).done(function(data) {
		    		//[2] 서버에서 REST API로 결제정보확인 및 서비스루틴이 정상적인 경우
		    		if ( everythings_fine ) {
		    			var msg = '결제가 완료되었습니다.';
		    			msg += '\n고유ID : ' + rsp.imp_uid;
		    			msg += '\n상점 거래ID : ' + rsp.merchant_uid;
		    			msg += '\결제 금액 : ' + rsp.paid_amount;
		    			msg += '카드 승인번호 : ' + rsp.apply_num;
		    			alert(msg);
		    		} else {
		    			//[3] 아직 제대로 결제가 되지 않았습니다.
		    			//[4] 결제된 금액이 요청한 금액과 달라 결제를 자동취소처리하였습니다.
		    		}
		    	});
		    	
		    } else {
		        var msg = '결제에 실패하였습니다. \n';
		        msg += '에러내용 : ' + rsp.error_msg;
		        alert(msg);

		    }
		    document.location.href="/shop/paymentCompleted";
		    
		});
	});

	// 로그인 안했을 때 로그인 페이지로 이동
	$("#empty-login").click(function(){
		alert("로그인 후 이용해주세요.");
		document.location.href="/member/loginView";
	});
	</script>
  </body>
</html>