package com.studyus.notice.service.logic;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studyus.notice.domain.Notice;
import com.studyus.notice.domain.PageInfo;
import com.studyus.notice.domain.Search;
import com.studyus.notice.service.NoticeService;
import com.studyus.notice.store.NoticeStore;

@Service
public class NoticeServiceImpl implements NoticeService{
	
	@Autowired
	public NoticeStore nStore;

	@Override
	public int getListCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Notice> printAll(PageInfo pi) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addReadCount(int noticeNo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Notice> printSearchAll(Search search, int studyNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Notice printOne(int noticeNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int registerNotice(Notice notice) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int modifyNotice(Notice notice) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int removeNotice(int noticeNo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int registerComment(Notice notice) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Notice> printAllComment(int noticeNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int removeComment(Notice notice) {
		// TODO Auto-generated method stub
		return 0;
	}

}
