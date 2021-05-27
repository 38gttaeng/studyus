package com.studyus.notice.service.logic;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studyus.common.PageInfo;
import com.studyus.notice.domain.Notice;
import com.studyus.notice.domain.Search;
import com.studyus.notice.service.NoticeService;
import com.studyus.notice.store.NoticeStore;

@Service
public class NoticeServiceImpl implements NoticeService{
	
	@Autowired
	public NoticeStore nStore;
	
	@Override
	public int getListCount() {
		return nStore.selectListCount();
	}

	@Override
	public int getListCount(Notice notice) {
		return nStore.selectListCount(notice);
	}
  
	@Override
	public ArrayList<Notice> printAll(PageInfo pi, Notice notice) {
		return nStore.selectList(pi, notice);
	}

	@Override
	public int addReadCount(int noticeNo) {
		return nStore.addReadCount(noticeNo);
	}

	@Override
	public ArrayList<Notice> printSearchAll(Search search) {
		return nStore.selectSearchList(search);
	}

	@Override
	public Notice printOne(int noticeNo) {
		return nStore.selectOne(noticeNo);
	}

	@Override
	public int registerNotice(Notice notice) {
		return nStore.insertNotice(notice);
	}

	@Override
	public int modifyNotice(Notice notice) {
		return nStore.updateNotice(notice);
	}

	@Override
	public int removeNotice(int noticeNo) {
		return nStore.deleteNotice(noticeNo);
	}

	@Override
	public ArrayList<Notice> printAllComment(PageInfo pi, int nMotherNo) {
		return nStore.printAllComment(pi, nMotherNo);
	}
	
	@Override
	public Notice printOneComment(int nMotherNo) {
		return nStore.selectOneComment(nMotherNo);
	}
	
	@Override
	public int registerComment(Notice notice) {
		return nStore.insertComment(notice);
	}

	@Override
	public int modifyComment(Notice notice) {
		return nStore.updateComment(notice);
	}

	@Override
	public int removeComment(Notice notice) {
		return nStore.deleteComment(notice);
	}




}
