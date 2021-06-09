package com.studyus.review.service;

import java.util.ArrayList;

import com.studyus.common.PageInfo;
import com.studyus.review.domain.Review;

public interface ReviewService {

	public ArrayList<Review> printAllReview(PageInfo pi, int caNo); 
	public int registerReview(Review review);
	public int modifyReview(Review review);
	public int removeReview(Review review);
	public ArrayList<Review> printAllByMemberNo(PageInfo pi, int mbNo); 
	public int getListCount(int caNo);
	public int getMemListCount(int mbNo);
	
}
