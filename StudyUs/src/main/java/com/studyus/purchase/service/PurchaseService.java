package com.studyus.purchase.service;

import java.util.ArrayList;

import com.studyus.purchase.domain.Purchase;
import com.studyus.study.domain.Study;

public interface PurchaseService {
	
	// 결제 완료시 DB에 값 넣기
	public int insertPremium(Purchase purchase);
	
	// 이용중인 프리미엄 확인
	public Purchase checkPremium(int stNo);
	
	// 가입한 스터디 목록 출력
	public ArrayList<Study> printStudyList(Study study);

}
