package com.studyus.member.store;
 
import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.studyus.common.PageInfo;
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
	public Member selectOneById(String mbId) {
		Member loginUser = sqlSession.selectOne("memberMapper.selectOneById", mbId);
		return loginUser;
	}

	@Override
	public int insertMember(Member member) {
		int result = sqlSession.insert("memberMapper.insertMember", member);
		return result;
	}
	
	@Override
	public void updateAuthKey(Member member) {
		sqlSession.update("memberMapper.updateAuthKey", member);
	}
	
	@Override
	public void updateKeyByEmail(Member member) {
		sqlSession.update("memberMapper.updateKeyByEmail", member);
	}
	
	@Override
	public Member checkAuthKey(String mbId) {
		Member checkKey = sqlSession.selectOne("memberMapper.checkAuthKey", mbId);
		return checkKey;
	}
	
	@Override
	public Member checkKeyByEmail(String mbEmail) {
		Member checkKey = sqlSession.selectOne("memberMapper.checkKeyByEmail", mbEmail);
		return checkKey;
	}
	
	@Override
	public int updateMbStatus(Member member) {
		int result = sqlSession.update("memberMapper.updateStatus", member);
		return result;
	}
	
	@Override
	public int insertNaverMem(Member member) {
		int result = sqlSession.insert("memberMapper.insertNaverMem", member);
		return result;
	}

	@Override
	public int updateMember(Member member) {
		int result = sqlSession.update("memberMapper.updateMember", member);
		return result;
	}

	@Override
	public int deleteMember(Member mOne) {
		int result = sqlSession.update("memberMapper.updateStatus", mOne);
		return result;
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
	public int checkEmailDup(String mbEmail) {
		return sqlSession.selectOne("memberMapper.checkEmailDup", mbEmail);
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
	public int selectStudyListCount(int mbNo) {
		return sqlSession.selectOne("memberMapper.selectStudyListCount", mbNo);
	}
	
	@Override
	public ArrayList<Member> selectAllEnrolled(int studyNo) {
		return (ArrayList) sqlSession.selectList("memberMapper.selectAllEnrolled", studyNo);
	}

	@Override
	public ArrayList<Member> selectAllAssign(int grNo) {
		return (ArrayList) sqlSession.selectList("memberMapper.selectAllAssign", grNo);
	}

	@Override
	public ArrayList<Member> selectList() {
		return (ArrayList)sqlSession.selectList("memberMapper.selectAllList");
	}
 
}
