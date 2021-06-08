package com.studyus.review.service.logic;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studyus.common.PageInfo;
import com.studyus.review.domain.Review;
import com.studyus.review.service.ReviewService;
import com.studyus.review.store.ReviewStore;

@Service
public class ReviewServiceImpl implements ReviewService {
	
	@Autowired
	private ReviewStore rStore;

	@Override
	public ArrayList<Review> printAllReview(PageInfo pi, int caNo) {
		return rStore.selectReviewList(caNo);
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
	public int removeReview(Review review) {
		return rStore.deleteReview(review);
	}

	@Override
	public ArrayList<Review> printAllByMemberNo(int mbNo) {
		return rStore.selectAllByMemberNo(mbNo);
	}

	@Override
	public int getListCount(int caNo) {
		return 0;
	}
	
}
