package com.studyus.purchase.store.logic;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.studyus.common.PageInfo;
import com.studyus.purchase.domain.Purchase;
import com.studyus.purchase.store.PurchaseStore;
import com.studyus.study.domain.Study;

@Repository
public class PurchaseStoreLogic implements PurchaseStore{

	@Autowired
	public SqlSession sqlSession;

	@Override
	public ArrayList<Study> printStudyList(Study study) {
		return (ArrayList)sqlSession.selectList("studyMapper.printStudyList", study);
	}
	
	@Override
	public int insertPremium(Purchase purchase) {
		System.out.println(purchase.toString());
		return sqlSession.insert("purchaseMapper.insertPremium", purchase);
	}
	
	@Override
	public int updateStudy(Study study) {
		return sqlSession.update("studyMapper.updatePreStudy", study);
	}

	@Override
	public Purchase checkPremium(int stNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int selectListCount(Purchase purchase) {
		return sqlSession.selectOne("purchaseMapper.selectListCount", purchase);
	}

	@Override
	public ArrayList<Purchase> selectAll() {
		return (ArrayList)sqlSession.selectList("purchaseMapper.selectPurchaseList");
	}
}
