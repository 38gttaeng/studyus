package com.studyus.member.store;

import com.studyus.member.domain.Member;
import com.studyus.review.domain.Review;

public interface MemberStore {

	public Member selectOneMember(Member member);
	
	public int insertMember(Member member);
	
	public int updateMember(Member member);
	
	public int deleteMember(String mbId);
	
	public int checkIdDup(String mbId);
	
	public int checkNickDup(String mbNickname);
	
	public Member findMemId(Member member);
	
	public Member findMemPw(Member member);
	
	public Review myReviewList(String mbId);
}
