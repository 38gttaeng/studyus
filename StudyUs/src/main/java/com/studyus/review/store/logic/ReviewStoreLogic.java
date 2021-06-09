package com.studyus.review.store.logic;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.studyus.common.PageInfo;
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
	public ArrayList<Review> selectAllByMemberNo(PageInfo pi,int mbNo) { 
		// RowBounds는 쿼리문을 변경하지 않고도 페이징을 처리할 수있게 해주는 클래스
		// RowBounds의 동작은 offset값과 limit값을 이용해서 동작함
		// offset값은 변하는 값이고 limit값은 고정값
		// limit값이 한 페이지당 보여주고 싶은 게시물의 갯수이고
		// offset값은 건너뛰어야 할 값임
		// ex) currentPage가 1일 때 offset은 0, limit는 10이 되어 10개값을 보여줌
		//     currentPage가 2일 때 offset은 10이 되어서 10개를 건너뛰고 11개부터 20까지의 10개의 값을 보여줌
		RowBounds rowBounds = new RowBounds(0, pi.getBoardLimit());
		return (ArrayList)sqlSession.selectList("reviewMapper.selectAllByMbNo", mbNo, rowBounds);
	}

	@Override
	public int selectMemListCount(int mbNo) {
		return sqlSession.selectOne("reviewMapper.selectMemListCount", mbNo);
	}

}
