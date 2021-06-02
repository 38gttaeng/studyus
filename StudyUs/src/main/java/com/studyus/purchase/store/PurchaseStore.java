package com.studyus.purchase.store;

import java.util.ArrayList;

import com.studyus.purchase.domain.Purchase;
import com.studyus.study.domain.Study;

public interface PurchaseStore {

	// 결제 완료시 DB 업데이트
	public int insertPremium(Purchase purchase);
	
	// 이용중인 프리미엄 확인
	public Purchase checkPremium(int stNo);
	
	// 가입한 스터디 목록 출력
	public ArrayList<Study> printStudyList(Study study);
}
