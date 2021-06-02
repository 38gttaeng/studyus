package com.studyus.purchase.service.logic;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studyus.purchase.domain.Purchase;
import com.studyus.purchase.service.PurchaseService;
import com.studyus.purchase.store.PurchaseStore;
import com.studyus.study.domain.Study;

@Service
public class PurchaseServiceImpl implements PurchaseService{

	@Autowired
	public PurchaseStore pStore;

	@Override
	public int insertPremium(Purchase purchase) {
		return pStore.insertPremium(purchase);
	}

	@Override
	public Purchase checkPremium(int stNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Study> printStudyList(Study study) {
		return pStore.printStudyList(study);
	}

}
