package com.studyus.purchase.service.logic;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studyus.purchase.domain.Purchase;
import com.studyus.purchase.service.PurchaseService;
import com.studyus.purchase.store.PurchaseStore;

@Service
public class PurchaseServiceImpl implements PurchaseService{

	@Autowired
	public PurchaseStore pStore;
	
	@Override
	public Purchase buyPremium(int studyNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Purchase checkPremium(int studyNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Purchase> printPremium(int studyNo) {
		// TODO Auto-generated method stub
		return null;
	}

}
