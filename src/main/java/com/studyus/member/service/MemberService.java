package com.studyus.member.service;

import com.studyus.member.domain.Member;

public interface MemberService {

	public Member loginMember(Member member);
	
	public int registerMember(Member member);
	
	public int modifyMember(Member member);
	
	public int removeMember(String mbId);
	
	public int checkIdDup(String mbId);
	
	public Member findMemId(Member member);
	
	public Member findMemPw(Member member);
}
