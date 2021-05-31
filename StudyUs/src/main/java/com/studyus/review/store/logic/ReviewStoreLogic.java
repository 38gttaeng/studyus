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
	public ArrayList<Review> selectReviewList(int caNo) {
		return (ArrayList)sqlSession.selectList("reviewMapper.selectReviewList", caNo);
	}

	@Override
	public int insertReview(Review review) {
		return sqlSession.insert("reviewMapper.insertReview", review);
	}

	@Override
	public int updateReview(Review review) {
		return sqlSession.update("reviewMapper.updateReview", review);
	}

	@Override
	public int deleteReview(Review review) {
		return sqlSession.delete("reviewMapper.deleteReview", review);
	}

	@Override
	public ArrayList<Review> selectAllByMemberNo(int mbNo) {
		// TODO Auto-generated method stub
		return null;
	}


}
