package com.studyus.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studyus.member.domain.Member;
import com.studyus.member.store.MemberStore;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	public MemberStore store;
	
	@Override
	public Member loginMember(Member member) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int registerMember(Member member) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int modifyMember(Member member) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int removeMember(String mbId) {
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
