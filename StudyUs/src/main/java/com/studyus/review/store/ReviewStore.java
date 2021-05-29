package com.studyus.review.store;

import java.util.ArrayList;

import com.studyus.review.domain.Review;

public interface ReviewStore {

	public ArrayList<Review> selectAllReview(int caNo);
	public int insertReview(Review review);
	public int updateReview(Review review);
	public int deleteReview(Review review);
	public ArrayList<Review> selectAllByMemberNo(int mbNo);
	
}
