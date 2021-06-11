package com.studyus.purchase.store;

import java.util.ArrayList;

import com.studyus.common.PageInfo;
import com.studyus.purchase.domain.Purchase;
import com.studyus.study.domain.Study;

public interface PurchaseStore {

	// 가입한 스터디 목록 출력
	public ArrayList<Study> printStudyList(Study study);
	
	// 결제 완료시 DB 업데이트
	public int insertPremium(Purchase purchase);
	
	// 결제 완료시 Study 테이블 업데이트 
	public int updateStudy(Study study);
	
	// 이용중인 프리미엄 확인
	public Purchase checkPremium(int stNo);
	
	// 관리자용 @@@@
	public int selectListCount(Purchase purchase);
	//  결제 리스트 출력 
	public ArrayList<Purchase> selectAll();

	public ArrayList<Purchase> selectOnePuByMbNo(int mbNo);  
	
}
