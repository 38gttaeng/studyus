package com.studyus.admin.service;

import java.util.ArrayList;

import com.studyus.cafe.domain.Cafe;
import com.studyus.common.PageInfo;
import com.studyus.member.domain.Member;
import com.studyus.study.domain.Study;

public interface AdminService {

	// 회원관리
	public ArrayList<Member> printAllMb(PageInfo pi);
	public int modifyMember(Member member);
	public int removeMember(Member member);
	
	// 스터디 관리
	public ArrayList<Study> printAllSt(PageInfo pi);
	public int modifyStudy(Study study);
	public int removeStudy(Study study);
	
	// 스터디카페 관리
	public ArrayList<Cafe> printAllCa(PageInfo pi);
	public int modifyCafe(Cafe cafe);
	public int removeCafe(Cafe cafe);
}
