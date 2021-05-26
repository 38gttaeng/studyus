package com.studyus.notice.store;

import java.util.ArrayList;

import com.studyus.common.PageInfo;
import com.studyus.notice.domain.Notice;
import com.studyus.notice.domain.Search;
 
public interface NoticeStore {
	public int selectListCount();
	public int selectListCount(Notice notice);
	public ArrayList<Notice> selectList(PageInfo pi, Notice notice);
	public int addReadCount(int noticeNo);
	public ArrayList<Notice> selectSearchList(Search search);
	public Notice selectOne(int noticeNo);
	public int insertNotice(Notice notice);
	public int updateNotice(Notice notice);
	public int deleteNotice(int noticeNo);
	
	public ArrayList<Notice> printAllComment(PageInfo pi, int nMotherNo);
	public int insertComment(Notice notice);
	public int updateComment(Notice notice);
	public int deleteComment(Notice notice);
	public Notice selectOneComment(int nMotherNo);
	
}
