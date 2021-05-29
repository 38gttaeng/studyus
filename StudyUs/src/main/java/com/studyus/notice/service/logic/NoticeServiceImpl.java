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
	public int updateReplyCount(int noMotherNo) {
		return nStore.updateReplyCount(noMotherNo);
	}
	
	@Override
	public int addReadCount(int noticeNo) {
		return nStore.addReadCount(noticeNo);
	}

	@Override
	public ArrayList<Notice> printSearchAll(PageInfo pi, Search search) {
		return nStore.selectSearchList(pi, search);
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
