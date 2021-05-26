package com.studyus.cafe.store.logic;

import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.studyus.cafe.domain.Cafe;
import com.studyus.cafe.store.CafeStore;

@Repository
public class CafeStoreLogic implements CafeStore{
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public ArrayList<Cafe> selectList() {
		return (ArrayList)sqlSession.selectList("cafeMapper.selectAll");
	}

	@Override
	public Cafe selectOne(int caNo) {
		return sqlSession.selectOne("cafeMapper.selectOne", caNo); 
	}

	@Override
	public int insertCafe(Cafe cafe) {
		return sqlSession.insert("cafeMapper.insertCafe", cafe);
	}

	@Override
	public int updateCafe(Cafe cafe) { 
		return sqlSession.update("cafeMapper.updateCafe", cafe);
	}

	@Override
	public int deleteCafe(int caNo) {
		return sqlSession.delete("cafeMapper.deleteCafe", caNo);
	}

}
