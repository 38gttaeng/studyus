package com.studyus.member.store;
 
import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.studyus.enrollment.domain.Enrollment;
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
		int result = sqlSession.update("memberMapper.updateMember", member);
		return result;
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
		Member findId = sqlSession.selectOne("memberMapper.findMemId", member);
		return findId;
	}

	@Override
	public Member findMemPw(Member member) {
		Member findPwd = sqlSession.selectOne("memberMapper.findMemPwd", member);
		return findPwd;
	}

	@Override
	public ArrayList<Enrollment> myStudyList(int mbNo) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public Review myReviewList(String mbId) {
		
		return null; 
	}

	@Override
	public ArrayList<Member> selectAllEnrolled(int studyNo) {
		return (ArrayList) sqlSession.selectList("memberMapper.selectAllEnrolled", studyNo);
	}
}
