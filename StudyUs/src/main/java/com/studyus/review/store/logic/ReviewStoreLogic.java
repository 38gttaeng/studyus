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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertReview(Review review) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateReview(Review review) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteRevice(int rvNo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Review> selectAllByMemberNo(int mbNo) {
		// TODO Auto-generated method stub
		return null;
	}


}
