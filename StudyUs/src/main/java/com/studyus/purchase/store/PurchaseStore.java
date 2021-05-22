package com.studyus.purchase.store;

import java.util.ArrayList;

import com.studyus.purchase.domain.Purchase;

public interface PurchaseStore {
	// 프리미엄 구매 페이지 
	public Purchase buyPremium(int studyNo);
	
	// 결제
	
	// 이용중인 프리미엄 확인
	public Purchase checkPremium(int studyNo);

	// 프리미엄 이용 기록
	public ArrayList<Purchase> printPremium(int studyNo);
}
