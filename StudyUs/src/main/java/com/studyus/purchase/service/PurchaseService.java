package com.studyus.purchase.service;

import com.studyus.purchase.domain.Purchase;

public interface PurchaseService {
	
	// 결제 완료시 DB에 값 넣기
	public int insertPremium(Purchase purchase);
	
	// 이용중인 프리미엄 확인
	public Purchase checkPremium(int stNo);

}
