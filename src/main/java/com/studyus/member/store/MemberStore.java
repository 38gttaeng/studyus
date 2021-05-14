package com.studyus.member.store;

import com.studyus.member.domain.Member;

public interface MemberStore {

	public Member selectOneMember(Member member);
	
	public int insertMember(Member member);
	
	public int updateMember(Member member);
	
	public int deleteMember(String mbId);
	
	public int checkIdDup(String mbId);
	
	public Member findMemId(Member member);
	
	public Member findMemPw(Member member);
}
