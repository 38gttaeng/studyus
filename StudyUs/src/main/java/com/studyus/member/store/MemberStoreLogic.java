package com.studyus.member.store;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.studyus.member.domain.Member;
import com.studyus.review.domain.Review;

@Repository
public class MemberStoreLogic implements MemberStore {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public Member selectOneMem(Member member) {
		Member loginUser = sqlSession.selectOne("memberMapper.selectOneMember", member);
		return loginUser;
	}

	@Override
	public int insertMember(Member member) {
		int result = sqlSession.insert("memberMapper.insertMember", member);
		return result;
	}

	@Override
	public int updateMember(Member member) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteMember(String mbId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int checkIdDup(String mbId) {
		return sqlSession.selectOne("memberMapper.checkIdDup", mbId);
	}
	
	@Override
	public int checkNickDup(String mbNickname) {
		return sqlSession.selectOne("memberMapper.checkNickDup", mbNickname);
	}

	@Override
	public Member findMemId(Member member) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Member findMemPw(Member member) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Review myReviewList(String mbId) {
		// TODO Auto-generated method stub
		return null;
	}

}
