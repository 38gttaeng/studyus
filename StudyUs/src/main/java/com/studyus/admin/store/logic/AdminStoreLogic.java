package com.studyus.admin.store.logic;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.studyus.admin.store.AdminStore;
import com.studyus.cafe.domain.Cafe;
import com.studyus.member.domain.Member;
import com.studyus.study.domain.Study;

public class AdminStoreLogic implements AdminStore{
	
	@Autowired SqlSession sqlSession;

	@Override
	public ArrayList<Member> selectAllMember(int MbNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateMember(Member member) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteMember(Member member) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Study> selectAllStudy(int StudyNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateStudy(Study study) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteStudy(Study study) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Cafe> selectAllCafe(int CaNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateCafe(Cafe cafe) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteCafe(Cafe cafe) {
		// TODO Auto-generated method stub
		return 0;
	}

}
