package com.studyus.notice.store;

import java.util.ArrayList;

import com.studyus.common.PageInfo; 
import com.studyus.notice.domain.Notice;
import com.studyus.notice.domain.Search;
 
public interface NoticeStore {
	public int selectListCount(Notice notice);
	public int selectPageCount(Search search);
//	public int selectNListCount(Notice notice);
//	public int selectRListCount(Notice notice);
	public ArrayList<Notice> selectList(PageInfo pi, Notice notice);
	// 메인 공지사항 가져오기
	public ArrayList<Notice> printMainNotice(Notice notice);
	// 메인 공지사항 업데이트 
	public int resetMainNotice(Notice notice);
	public int updateMainNotice(int noNo);
	
	public int updateReplyCount(int noMotherNo);
	public int addReadCount(int noNo);
	public ArrayList<Notice> selectSearchList(PageInfo pi, Search search);
	public Notice selectOne(int noNo);
	public int insertNotice(Notice notice);
	public int updateNotice(Notice notice);
	public int deleteNotice(int noNo);
	
	public ArrayList<Notice> printAllReply(PageInfo pi, int noMotherNo);
	public Notice selectOneReply(int noMotherNo);
	public int insertReply(Notice notice);
	public int updateReply(Notice notice);
	public int deleteReply(Notice notice);
	
	public ArrayList<Notice> printRecentNotice(Notice notice);
	public int printRecentCount(int stNo);
	
}
