package com.studyus.member.store;

import java.util.ArrayList;

import com.studyus.common.PageInfo;
import com.studyus.enrollment.domain.Enrollment;
import com.studyus.member.domain.Member;
import com.studyus.review.domain.Review;

public interface MemberStore {
	
	public Member selectOneMem(Member member);
	
	public Member selectOneById(String mbId);
	
	public int insertMember(Member member);
	
	public void updateAuthKey(Member member);
	
	public void updateKeyByEmail(Member member);
	
	public Member checkAuthKey(String mbId);
	
	public Member checkKeyByEmail(String mbEmail);
	
	public int updateMbStatus(Member member);
	
	public int insertNaverMem(Member member);
	
	public int updateMember(Member member);
	
	public int deleteMember(Member mOne);
	
	public int checkIdDup(String mbId);
	
	public int checkNickDup(String mbNickname);
	
	public int checkEmailDup(String mbEmail);
	
	public Member findMemId(Member member);
	
	public Member findMemPw(Member member);
	
	public ArrayList<Enrollment> selectAllStudyByMbNo(PageInfo pi, int mbNo);
	
	public int selectStudyListCount(int mbNo);
	
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
	
	public ArrayList<Member> selectList();

}
