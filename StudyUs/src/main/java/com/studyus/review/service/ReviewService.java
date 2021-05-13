package com.studyus.review.service;

import java.util.ArrayList;

import com.studyus.review.domain.Review;

public interface ReviewService {

	public ArrayList<Review> printAll();
	public int registerReview(Review review);
	public int modifyReview(Review review);
	public int removeReview(int rvNo);
	public ArrayList<Review> printAllByMemberNo(int mbNo);
	
}
