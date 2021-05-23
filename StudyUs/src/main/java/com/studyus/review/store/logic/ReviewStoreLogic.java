package com.studyus.review.store.logic;

import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.studyus.review.domain.Review;
import com.studyus.review.store.ReviewStore;

@Repository
public class ReviewStoreLogic implements ReviewStore{

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public ArrayList<Review> selectList() {
		return (ArrayList)sqlSession.selectList("cafeMapper.selectList", null);
	}

	@Override
	public int insertReview(Review review) {
		return sqlSession.insert("cafeMapper.insertReview", review);
	}

	@Override
	public int updateReview(Review review) {
		return sqlSession.update("cafeMapper.updateReview", review);
	}

	@Override
	public int deleteRevice(int rvNo) {
		return sqlSession.delete("cafeMapper.deleteReview", rvNo);
	}

	@Override
	public ArrayList<Review> selectAllByMemberNo(int mbNo) {
		// TODO Auto-generated method stub
		return null;
	}


}
