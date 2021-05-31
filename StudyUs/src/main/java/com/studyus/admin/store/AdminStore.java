package com.studyus.admin.store;

import java.util.ArrayList;

import com.studyus.cafe.domain.Cafe;
import com.studyus.cafe.domain.Cafe;
import com.studyus.member.domain.Member;
import com.studyus.study.domain.Study;

public interface AdminStore {

	public ArrayList<Member> selectAllMember(int MbNo);
	public int updateMember(Member member);
	public int deleteMember(Member member);
	
	public ArrayList<Study> selectAllStudy(int StudyNo);
	public int updateStudy(Study study);
	public int deleteStudy(Study study);
	
	public ArrayList<Cafe> selectAllCafe(int CaNo);
	public int updateCafe(Cafe cafe);
	public int deleteCafe(Cafe cafe);
	
}
