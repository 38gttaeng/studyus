package com.studyus.review.store;

import java.util.ArrayList;

import com.studyus.review.domain.Review;

public interface ReviewStore {

	public ArrayList<Review> selectList();
	public int insertReview(Review review);
	public int updateReview(Review review);
	public int deleteRevice(int rvNo);
	public ArrayList<Review> selectAllByMemberNo(int mbNo);
	
}
