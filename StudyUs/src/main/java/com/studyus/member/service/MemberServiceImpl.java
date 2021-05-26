package com.studyus.member.service;

import java.util.ArrayList;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studyus.enrollment.domain.Enrollment;
import com.studyus.member.domain.Member;
import com.studyus.member.store.MemberStore;
import com.studyus.review.domain.Review;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	public MemberStore store;
	
	@Override
	public Member loginMember(Member member) {
		Member mOne = store.selectOneMem(member);
		return mOne;
	}

	@Override
	public int registerMember(Member member) {
		int result = store.insertMember(member);
		return result;
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
		return store.checkIdDup(mbId);
	}
	
	@Override
	public int checkNickDup(String mbNickname) {
		return store.checkNickDup(mbNickname);
	}

	@Override
	public Member findMemId(Member member) {
		Member mOne = store.findMemId(member);
		return mOne;
	}

	@Override
	public Member findMemPw(Member member) {
		Member mOne = store.findMemPw(member);
		return mOne;
	}

	@Override
	public ArrayList<Enrollment> myStudyList(int mbNo) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Review myReviewList(String mbId) {
		// TODO Auto-generated method stub
		return null;
	}

}
