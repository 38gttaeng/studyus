package com.studyus.member.store;

import java.util.ArrayList;
 
import com.studyus.enrollment.domain.Enrollment;
import com.studyus.member.domain.Member;
import com.studyus.review.domain.Review;

public interface MemberStore {

	public Member selectOneMem(Member member);
	
	public Member selectOneById(String mbId);
	
	public int insertMember(Member member);
	
	public int insertNaverMem(Member member);
	
	public int updateMember(Member member);
	
	public int deleteMember(String mbId);
	
	public int checkIdDup(String mbId);
	
	public int checkNickDup(String mbNickname);
	
	public Member findMemId(Member member);
	
	public Member findMemPw(Member member);
	
	public ArrayList<Enrollment> myStudyList(int mbNo);
	
	public Review myReviewList(String mbId);
}
