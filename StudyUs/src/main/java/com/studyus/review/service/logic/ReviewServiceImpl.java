package com.studyus.review.service.logic;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studyus.review.domain.Review;
import com.studyus.review.service.ReviewService;
import com.studyus.review.store.ReviewStore;

@Service
public class ReviewServiceImpl implements ReviewService { 
	
	@Autowired
	private ReviewStore rStore;

	@Override
	public ArrayList<Review> printAll() {
		return rStore.selectList();
	}

	@Override
	public int registerReview(Review review) {
		return rStore.insertReview(review);
	}

	@Override
	public int modifyReview(Review review) {
		return rStore.updateReview(review);
	}

	@Override
	public int removeReview(int rvNo) {
		return rStore.deleteRevice(rvNo);
	}

	@Override
	public ArrayList<Review> printAllByMemberNo(int mbNo) {
		return null;
	}

}
