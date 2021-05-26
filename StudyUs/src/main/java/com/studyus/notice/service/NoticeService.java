package com.studyus.notice.service;

import java.util.ArrayList;

import com.studyus.common.PageInfo;
import com.studyus.notice.domain.Notice;
import com.studyus.notice.domain.Search;
 
public interface NoticeService {
	// 전체 조회수 
	public int getListCount();
	// 댓글관련 
	public int getListCount(Notice notice);
	// 공지사항 전체 출력  
	public ArrayList<Notice> printAll(PageInfo pi, Notice notice);
	// 조회수 증가 
	public int addReadCount(int noticeNo);
	// 검색 
	public ArrayList<Notice> printSearchAll(Search search);
	// 상세 조회 
	public Notice printOne(int noticeNo);
	
	// 글 작성, 수정 ,삭제
	public int registerNotice(Notice notice);
	public int modifyNotice(Notice notice);
	public int removeNotice(int noticeNo);
	
	// 댓글
	public ArrayList<Notice> printAllComment(PageInfo pi, int nMotherNo);
	public int registerComment(Notice notice);
	public int modifyComment(Notice notice);
	public int removeComment(Notice notice);
	public Notice printOneComment(int nMotherNo);
	
}
