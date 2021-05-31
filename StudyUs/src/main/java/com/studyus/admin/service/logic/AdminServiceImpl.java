package com.studyus.admin.service.logic;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studyus.admin.service.AdminService;
import com.studyus.admin.store.AdminStore;
import com.studyus.cafe.domain.Cafe;
import com.studyus.common.PageInfo;
import com.studyus.member.domain.Member;
import com.studyus.study.domain.Study;

@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	private AdminStore aStore;

	@Override
	public ArrayList<Member> printAllMb(PageInfo pi) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int modifyMember(Member member) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int removeMember(Member member) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Study> printAllSt(PageInfo pi) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int modifyStudy(Study study) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int removeStudy(Study study) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Cafe> printAllCa(PageInfo pi) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int modifyCafe(Cafe cafe) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int removeCafe(Cafe cafe) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	
}
