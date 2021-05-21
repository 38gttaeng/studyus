package com.studyus.purchase.service;

import java.util.ArrayList;

import com.studyus.purchase.domain.Purchase;

public interface PurchaseService {
	// 프리미엄 구매 페이지 
	public Purchase buyPremium(int studyNo);
	
	// 결제
	
	// 이용중인 프리미엄 확인
	public Purchase checkPremium(int studyNo);

	// 프리미엄 이용 기록
	public ArrayList<Purchase> printPremium(int studyNo);
}
