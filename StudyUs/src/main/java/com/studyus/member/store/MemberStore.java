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
	
	/**
	 * 스터디에 가입한 모든 회원을 select합니다.
	 * @param studyNo
	 * @return ArrayList<Member>
	 */
	public ArrayList<Member> selectAllEnrolled(int studyNo);
	
	/**
	 * 해당 과제 그룹에 속한 모든 회원 출력
	 * @param grNo
	 * @return
	 */
	public ArrayList<Member> selectAllAssign(int grNo);
}
