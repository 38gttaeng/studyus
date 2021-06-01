package com.studyus.purchase.store.logic;

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
	public int insertPremium(Purchase purchase) {
		System.out.println(purchase.toString());
		return sqlSesseion.insert("purchaseMapper.insertPremium", purchase);
	}

	@Override
	public Purchase checkPremium(int stNo) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
