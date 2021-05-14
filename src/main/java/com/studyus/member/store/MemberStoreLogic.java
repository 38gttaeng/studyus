package com.studyus.member.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.studyus.member.domain.Member;

@Repository
public class MemberStoreLogic implements MemberStore {

	@Autowired
//	private SqlSessionTemplate sqlSession;
	
	@Override
	public Member selectOneMember(Member member) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertMember(Member member) {
		// TODO Auto-generated method stub
		return 0;
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
		// TODO Auto-generated method stub
		return 0;
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

}
