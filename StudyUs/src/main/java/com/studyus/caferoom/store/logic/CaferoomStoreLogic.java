package com.studyus.caferoom.store.logic;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.studyus.caferoom.domain.Caferoom;
import com.studyus.caferoom.store.CaferoomStore;

@Repository
public class CaferoomStoreLogic implements CaferoomStore{
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public ArrayList<Caferoom> selectAll(int caNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Caferoom selectOne(int crNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertCaferoom(Caferoom caferoom) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteCaferoom(int crNo) {
		// TODO Auto-generated method stub
		return 0;
	}

}
