package com.studyus.member.service;

import java.util.ArrayList;
import java.util.Map;

import com.studyus.enrollment.domain.Enrollment;
import com.studyus.member.domain.Member;
import com.studyus.review.domain.Review;

public interface MemberService { 

	public Member loginMember(Member member);
	
	public Member selectOneById(String mbId);
	
	public int registerMember(Member member);
	
	public void updateAuthKey(Member member);
	
	public void updateKeyByEmail(Member member);
	
	public Member checkAuthKey(String mbId);
	
	public Member checkKeyByEmail(String mbEmail);
	
	public int updateMbStatus(Member member);
	
	public int registerNaverMem(Member member);
	
	public int modifyMember(Member member);
	
	public int removeMember(String mbId);
	
	public int checkIdDup(String mbId);
	
	public int checkNickDup(String mbNickname);
	
	public int checkEmailDup(String mbEmail);
	
	public Member findMemId(Member member);
	
	public Member findMemPw(Member member);
	
	public ArrayList<Enrollment> myStudyList(int mbNo);
	
	public ArrayList<Review> myReviewList(int mbNo);
	
	/**
	 * 해당 번호의 스터디에 가입한 모든 회원을 출력합니다.
	 * @param studyNo
	 * @return ArrayList<Member>
	 */
	public ArrayList<Member> printAllByStudyNo(int studyNo);

	public ArrayList<Member> printAllAssign(int grNo);
	
	public ArrayList<Member> printAll();
}
