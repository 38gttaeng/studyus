package com.studyus.member.service;

import java.util.ArrayList;
 
import com.studyus.enrollment.domain.Enrollment;
import com.studyus.member.domain.Member;
import com.studyus.review.domain.Review;

public interface MemberService {

	public Member loginMember(Member member);
	
	public Member selectOneById(String mbId);
	
	public int registerMember(Member member);
	
	public int registerNaverMem(Member member);
	
	public int modifyMember(Member member);
	
	public int removeMember(String mbId);
	
	public int checkIdDup(String mbId);
	
	public int checkNickDup(String mbNickname);
	
	public Member findMemId(Member member);
	
	public Member findMemPw(Member member);
	
	public ArrayList<Enrollment> myStudyList(int mbNo);
	
	public Review myReviewList(String mbId);
}
