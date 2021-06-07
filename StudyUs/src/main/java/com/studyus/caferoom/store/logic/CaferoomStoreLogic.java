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
		return (ArrayList)sqlSession.selectList("caferoomMapper.selectList", caNo);
	}

	@Override
	public Caferoom selectOne(int crNo) {
		return sqlSession.selectOne("caferoomMapper.selectOne", crNo);
	}

	@Override
	public int insertCaferoom(Caferoom caferoom) {
		sqlSession.insert("caferoomMapper.insertCaferoom", caferoom);
		return caferoom.getCrNo();
	}

	@Override
	public int updateCaferoom(Caferoom caferoom) {
		return sqlSession.update("caferoomMapper.updateCaferoom", caferoom);
	}
	
	@Override
	public int deleteCaferoom(int crNo) {
		return sqlSession.delete("caferoomMapper.deleteCaferoom", crNo);
	}
	
	@Override
	public int updateLocation(Caferoom caferoom) {
		return sqlSession.update("caferoomMapper.updateLocation", caferoom);
	}
	
}
