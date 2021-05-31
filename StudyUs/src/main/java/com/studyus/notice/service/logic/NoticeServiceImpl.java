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
	public int getListCount(Notice notice) {
		return nStore.selectListCount(notice);
	}

	@Override
	public int getPageCount(Search search) {
		return nStore.selectPageCount(search);
	}
	
//	@Override
//	public int getNListCount(Notice notice) {
//		return nStore.selectNListCount(notice);
//	}
//
//	@Override
//	public int getRListCount(Notice notice) {
//		return nStore.selectRListCount(notice);
//	}
  
	@Override
	public ArrayList<Notice> printAll(PageInfo pi, Notice notice) {
		return nStore.selectList(pi, notice);
	}
	
	@Override
	public ArrayList<Notice> printMainNotice(Notice notice) {
		return nStore.printMainNotice(notice);
	}

	@Override
	public int resetMainNotice(Notice notice) {
		return nStore.resetMainNotice(notice);
	}
	
	@Override
	public int updateMainNotice(int noNo) {
		return nStore.updateMainNotice(noNo);
	}

	@Override
	public int updateReplyCount(int noMotherNo) {
		return nStore.updateReplyCount(noMotherNo);
	}
	
	@Override
	public int addReadCount(int noNo) {
		return nStore.addReadCount(noNo);
	}

	@Override
	public ArrayList<Notice> printSearchAll(PageInfo pi, Search search) {
		return nStore.selectSearchList(pi, search);
	}

	@Override
	public Notice printOne(int noNo) {
		return nStore.selectOne(noNo);
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
	public int removeNotice(int noNo) {
		return nStore.deleteNotice(noNo);
	}

	@Override
	public ArrayList<Notice> printAllReply(PageInfo pi, int noMotherNo) {
		return nStore.printAllReply(pi, noMotherNo);
	}
	
	@Override
	public Notice printOneReply(int noMotherNo) {
		return nStore.selectOneReply(noMotherNo);
	}
	
	@Override
	public int registerReply(Notice notice) {
		return nStore.insertReply(notice);
	}

	@Override
	public int modifyReply(Notice notice) {
		return nStore.updateReply(notice);
	}

	@Override
	public int removeReply(Notice notice) {
		return nStore.deleteReply(notice);
	}


}
