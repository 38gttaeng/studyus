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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int registerReview(Review review) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int modifyReview(Review review) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int removeReview(int rvNo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Review> printAllByMemberNo(int mbNo) {
		// TODO Auto-generated method stub
		return null;
	}

}
