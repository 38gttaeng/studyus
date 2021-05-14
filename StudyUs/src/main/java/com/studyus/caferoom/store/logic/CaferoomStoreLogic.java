package com.studyus.caferoom.store.logic;

import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.studyus.caferoom.domain.Caferoom;
import com.studyus.caferoom.store.CaferoomStore;

@Repository
public class CaferoomStoreLogic implements CaferoomStore{
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public ArrayList<Caferoom> selectList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Caferoom selectOne(int crNo) {
		// TODO Auto-generated method stub
		return null;
	}

}
