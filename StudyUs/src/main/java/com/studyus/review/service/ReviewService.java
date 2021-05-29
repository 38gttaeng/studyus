package com.studyus.review.service;

import java.util.ArrayList;

import com.studyus.review.domain.Review;

public interface ReviewService {

	public ArrayList<Review> printAllReview(int caNo);
	public int registerReview(Review review);
	public int modifyReview(Review review);
	public int removeReview(Review review);
	public ArrayList<Review> printAllByMemberNo(int mbNo);
	
}
