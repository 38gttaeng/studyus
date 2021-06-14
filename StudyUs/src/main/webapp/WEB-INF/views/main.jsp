<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>멀리서 가깝게, StudyUs!</title>
  </head>
  <body>
    
    <!-- menubar -->
    <jsp:include page="./common/menubar.jsp"/>
    <script>$(".nav-item:first-child").addClass("active");</script>

    <div class="hero-wrap js-fullheight">
      <div class="overlay"></div>
      <div class="container-fluid px-0">
      	<div class="row d-md-flex no-gutters slider-text align-items-center js-fullheight justify-content-end">
	      	<img class="one-third js-fullheight align-self-end order-md-last img-fluid" src="resources/css/main/images/undraw_pair_programming_njlp.svg" alt="">
	        <div class="one-forth d-flex align-items-center ftco-animate js-fullheight">
	        	<div class="text mt-5">
	        		<span class="subheading">Online Study</span>
	            <h1 class="mb-3"><span>모두가,</span> <span>안전하게,</span> <span>온라인 스터디</span></h1>
	            <p>온라인 스터디 대표 플랫폼 Study, Us!</p> 
	            <p><a href="/study/search" class="btn btn-primary px-4 py-3">시작하기</a></p>
	          </div>
	        </div>
	    	</div>
      </div>
    </div>

	<section class="ftco-domain">
		<div class="container">
			<div class="row d-flex align-items-center">
				<div
					class="col-lg-5 heading-white mb-4 mb-sm-4 mb-lg-0 ftco-animate">
					<h2>스터디 검색</h2>
					<p>내게 맞는 직무를 함께 공부해보세요!</p>
				</div>
				<div class="col-lg-7 p-5 ftco-wrap ftco-animate">
					<a href="/study/search">
						<div class="domain-form d-flex mb-3">
							<div class="form-group domain-name">
								<input type="text" class="form-control name px-4"
									placeholder="스터디를 검색해보세요">
							</div>
							<div class="form-group domain-select d-flex">
								<input type="submit"
									class="search-domain btn btn-primary text-center" value="검색">
							</div>
						</div>
					</a>
				</div>
			</div>
		</div>
	</section>

	<section class="ftco-section services-section bg-light">
      <div class="container">
      	<div class="row justify-content-center mb-5 pb-3">
          <div class="col-md-7 text-center heading-section ftco-animate">
            <h2 class="mb-4">스터디어스인 이유</h2>
            <p>5000개의 스터디 플래닛이 스터디어스를 선택한 이유!</p>
          </div>
        </div>
        <div class="row">
          <div class="col-md-6 d-flex align-self-stretch ftco-animate">
            <div class="media block-6 services d-flex align-items-center">
            	<div class="icon d-flex align-items-center justify-content-center order-md-last">
            		<span class="flaticon-customer-service"></span>
            	</div>
              <div class="media-body pl-4 pl-md-0 pr-md-4 text-md-right">
                <h3 class="heading">실시간 채팅 가능</h3>
                <p>스터디 내에서 실시간 채팅이 가능하여<br> 적극적인 의견 교류가 가능합니다.</p>
              </div>
            </div>      
          </div>
					<div class="col-md-6 d-flex align-self-stretch ftco-animate">
            <div class="media block-6 services d-flex align-items-center">
            	<div class="icon d-flex align-items-center justify-content-center">
            		<span class="flaticon-life-insurance"></span>
            	</div>
              <div class="media-body pl-4">
                <h3 class="heading">우리만의 스터디</h3>
                <p>개별 스터디를 운영하여<br> 스터디에 가입한 팀원들만 이용할 수 있습니다.</p>
              </div>
            </div>      
          </div>
          <div class="col-md-6 d-flex align-self-stretch ftco-animate">
            <div class="media block-6 services d-flex align-items-center">
            	<div class="icon d-flex align-items-center justify-content-center order-md-last">
            		<span class="flaticon-cloud-computing"></span>
            	</div>
              <div class="media-body pl-4 pl-md-0 pr-md-4 text-md-right">
                <h3 class="heading">활발한 자료공유</h3>
                <p>편리한 스터디 자료공유 플랫폼으로 스터디원들과 활발한 자료공유가 가능합니다.</p>
              </div>
            </div>    
          </div>
          <div class="col-md-6 d-flex align-self-stretch ftco-animate">
            <div class="media block-6 services d-flex align-items-center">
            	<div class="icon d-flex align-items-center justify-content-center">
            		<span class="flaticon-settings"></span>
            	</div>
              <div class="media-body pl-4">
                <h3 class="heading">스터디 개별 설정</h3>
                <p>스터디를 대표하는 이름과 이미지를 정하고<br> 프리미엄으로 업그레이드가 가능합니다</p>
              </div>
            </div>      
          </div>
        </div>
      </div>
    </section>

    <section class="ftco-section ftco-counter img" id="section-counter">
    	<div class="container">
    		<div class="row justify-content-center mb-5">
          <div class="col-md-7 text-center heading-section heading-section-white ftco-animate">
            <span class="subheading">스터디어스는 지금</span>
          </div>
        </div>
    		<div class="row justify-content-center">
    			<div class="col-md-10">
		    		<div class="row">
		          <div class="col-md-3 d-flex justify-content-center counter-wrap ftco-animate">
		            <div class="block-18 text-center">
		              <div class="text">
		                <strong class="number" data-number="5000">0</strong>
		                <span>전체 회원수</span>
		              </div>
		            </div>
		          </div>
		          <div class="col-md-3 d-flex justify-content-center counter-wrap ftco-animate">
		            <div class="block-18 text-center">
		              <div class="text">
		                <strong class="number" data-number="100">0</strong>
		                <span>등록된 스터디 수</span>
		              </div>
		            </div>
		          </div>
		          <div class="col-md-3 d-flex justify-content-center counter-wrap ftco-animate">
		            <div class="block-18 text-center">
		              <div class="text">
		                <strong class="number" data-number="2000">0</strong>
		                <span>등록된 과제 수</span>
		              </div>
		            </div>
		          </div>
		          <div class="col-md-3 d-flex justify-content-center counter-wrap ftco-animate">
		            <div class="block-18 text-center">
		              <div class="text">
		                <strong class="number" data-number="9000">0</strong>
		                <span>한 공기 밥알 개수</span>
		              </div>
		            </div>
		          </div>
		        </div>
	        </div>
        </div>
    	</div>
    </section>


    <section class="ftco-section bg-light">
    	<div class="container">
    		<div class="row justify-content-center mb-5 pb-3">
          <div class="col-md-7 text-center heading-section ftco-animate">
            <h2 class="mb-4">프리미엄 구매시</h2>
          </div>
        </div>
    		<div class="row d-flex">
    		
	        <div class="col-lg-2 col-md-12 ftco-animate">
	        &nbsp;
	        </div>
	        <div class="col-lg-8 col-md-8 ftco-animate">
	          <div class="block-7">
	            <div class="text-center">
		            <h2 class="heading">한번 결제시 평생 누리는 혜택!</h2>
		            <span class="price"><span class="number">5,900<small class="per">원</small></span></span>
		            <ul class="pricing-text mb-4">
		              <li><strong>실시간 채팅</strong>  YES!   <strong>  최대 인원 20명</strong>  YES! </li>
		            </ul>
		            <a href="/shop/premiumShopView" class="btn btn-primary d-block px-3 py-3 mb-4" style="width:40%; margin-left:28%">프리미엄 더 알아보기</a>
	            </div>
	          </div>
	        </div>
   	        <div class="col-lg-2 col-md-12 ftco-animate">
	        &nbsp;
	        </div>
	      </div>
    	</div>
    </section>

    <section class="ftco-section testimony-section">
      <div class="container">
        <div class="row justify-content-center mb-5 pb-3">
          <div class="col-md-7 text-center heading-section ftco-animate">
            <span class="subheading">DEVELOPER SAYS</span>
            <h2 class="mb-4">스터디어스 후기</h2>
          </div>
        </div>
        <div class="row ftco-animate">
          <div class="col-md-12">
            <div class="carousel-testimony owl-carousel ftco-owl">
              <div class="item">
                <div class="testimony-wrap p-4 text-center">
                  <div class="user-img mb-4" style="background-image: url(resources/css/main/images/dv.png)">
                    <span class="quote d-flex align-items-center justify-content-center">
                      <i class="icon-quote-left"></i>
                    </span>
                  </div>
                  <div class="text">
                    <p class="mb-4">프로젝트를 준비하면서 팀원들이 다 할 수 없을 거라고 생각했던 기능들까지 으쌰으쌰해서 해내서 좋았습니다. 앞으로 어떤 프로젝트든 해낼 수 있을 것만 같습니다.(ﾉ>ω<)ﾉ :｡･:*:･ﾟ’★,｡･:*:･ﾟ’☆  </p>
                    <p class="name">김다빈</p>
                    <span class="position">우리는 티목이 좋다</span>
                  </div>
                </div>
              </div>
              <div class="item">
                <div class="testimony-wrap p-4 text-center">
                  <div class="user-img mb-4" style="background-image: url(resources/css/main/images/dh.png)">
                    <span class="quote d-flex align-items-center justify-content-center">
                      <i class="icon-quote-left"></i>
                    </span>
                  </div>
                  <div class="text">
                    <p class="mb-4">팀 프로젝트 결과물이 만족스러워서 도메인도 연결했어요. 열심히 해준 팀원들에게 감사합니다. 내일부터 백수 생활이 시작되네요. 벌써부터 설레고 기대됩니다. ₍ᐢ･⚇･ᐢ₎</p>
                    <p class="name">김동현</p>
                    <span class="position">내일부터 백수</span>
                  </div>
                </div>
              </div>
              <div class="item">
                <div class="testimony-wrap p-4 text-center">
                  <div class="user-img mb-4" style="background-image: url(resources/css/main/images/ey.png)">
                    <span class="quote d-flex align-items-center justify-content-center">
                      <i class="icon-quote-left"></i>
                    </span>
                  </div>
                  <div class="text">
                    <p class="mb-4">좋은 팀원들과 함께 프로젝트를 할 수 있어서 즐거운 마음으로 임했습니다. 기획단계부터 웹 구현까지의 모든 과정을 경험하면서 부족한 부분을 보완할 수 있는 좋은 기회였습니다. (ღˇᴗˇ)</p>
                    <p class="name">박은영</p>
                    <span class="position">그것이 팀장이니까 (끄덕)</span>
                  </div>
                </div>
              </div>
              <div class="item">
                <div class="testimony-wrap p-4 text-center">
                  <div class="user-img mb-4" style="background-image: url(resources/css/main/images/hm.png)">
                    <span class="quote d-flex align-items-center justify-content-center">
                      <i class="icon-quote-left"></i>
                    </span>
                  </div>
                  <div class="text">
                    <p class="mb-4">팀원들과 함께 수업 외 시간에도 함께 이야기를 나누며 서로를 도우면서 프로젝트를 완성해나가는 과정이 정말 좋았습니다. 계획했던 기능을 거의 구현한 것 같아 만족스럽기도 합니다. (✿◡‿◡)</p>
                    <p class="name">이혜민</p>
                    <span class="position">웹 개발자</span>
                  </div>
                </div>
              </div>
              <div class="item">
                <div class="testimony-wrap p-4 text-center">
                  <div class="user-img mb-4" style="background-image: url(resources/css/main/images/IC.png)">
                    <span class="quote d-flex align-items-center justify-content-center">
                      <i class="icon-quote-left"></i>
                    </span>
                  </div>
                  <div class="text">
                    <p class="mb-4">스프링 개발의 흐름이 보이고 실력이 향상된 것을 느꼈습니다. 쉽다고 생각하니 어렵고 어렵다고 생각하니 쉬웠습니다. 벌써 교육과정이 끝나서 헤어져야한다니 아쉽군요...=≡Σ((( つ•̀ω•́)つ</p>
                    <p class="name">최인철</p>
                    <span class="position">얘들아 3년 동안 수고했고 나중에 웃으면서 보자</span>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>

	<!-- footer -->
	<jsp:include page="./common/footer.jsp"/>
	
	<script src="/resources/js/main.js"></script>

  </body>
</html>