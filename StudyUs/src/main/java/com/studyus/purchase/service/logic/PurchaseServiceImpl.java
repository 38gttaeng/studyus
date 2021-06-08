package com.studyus.purchase.service.logic;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studyus.common.PageInfo;
import com.studyus.purchase.domain.Purchase;
import com.studyus.purchase.service.PurchaseService;
import com.studyus.purchase.store.PurchaseStore;
import com.studyus.study.domain.Study;

@Service
public class PurchaseServiceImpl implements PurchaseService{

	@Autowired
	public PurchaseStore pStore;

	@Override
	public ArrayList<Study> printStudyList(Study study) {
		return pStore.printStudyList(study);
	}
	
	@Override
	public int insertPremium(Purchase purchase) {
		return pStore.insertPremium(purchase);
	}

	@Override
	public int updateStudy(Study study) {
		return pStore.updateStudy(study);
	}
	
	@Override
	public Purchase checkPremium(int stNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getListCount(Purchase purchase) {
		return pStore.selectListCount(purchase);
	}

	@Override
	public ArrayList<Purchase> printAll(PageInfo pi, Purchase purchase) {
		return pStore.selectAll(pi, purchase);
	}
}
