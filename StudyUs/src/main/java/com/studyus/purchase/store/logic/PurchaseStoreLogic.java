package com.studyus.purchase.store.logic;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.studyus.purchase.domain.Purchase;
import com.studyus.purchase.store.PurchaseStore;

@Repository
public class PurchaseStoreLogic implements PurchaseStore{

	@Autowired
	public SqlSession sqlSesseion;
	
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
