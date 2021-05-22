package com.studyus.notice.store.logic;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.studyus.notice.domain.Notice;
import com.studyus.notice.domain.PageInfo;
import com.studyus.notice.domain.Search;
import com.studyus.notice.store.NoticeStore;

@Repository
public class NoticeStoreLogic implements NoticeStore{

	@Autowired
	public SqlSession sqlSession;
	
	@Override
	public int selectListCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Notice> selectList(PageInfo pi, int studyNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addReadCount(int noticeNo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Notice> selectSearchList(Search search, int studyNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Notice selectOne(int noticeNo, int nMotherNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertNotice(Notice notice, int studyNo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateNotice(Notice notice) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteNotice(int noticeNo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertComment(Notice notice) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Notice> printAllComment(int noticeNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteComment(Notice notice) {
		// TODO Auto-generated method stub
		return 0;
	}

}
