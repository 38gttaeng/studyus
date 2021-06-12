package com.studyus.notice.service;

import java.util.ArrayList;

import com.studyus.common.PageInfo; 
import com.studyus.notice.domain.Notice;
import com.studyus.notice.domain.Search;
 
public interface NoticeService {
	// 전체 글 수
	public int getListCount(Notice notice);
	public int getPageCount(Search search);
//	public int getNListCount(Notice notice);
//	public int getRListCount(Notice notice);
	// 공지사항 전체 출력  
	public ArrayList<Notice> printAll(PageInfo pi, Notice notice);
	// 메인 공지사항 가져오기
	public ArrayList<Notice> printMainNotice(Notice notice);
	// 메인 공지사항 업데이트 
	public int resetMainNotice(Notice notice);
	public int updateMainNotice(int noNo);
	
	// 댓글 수 
	public int updateReplyCount(int noMotherNo);
	// 조회수 증가 
	public int addReadCount(int noNo);
	// 검색 
	public ArrayList<Notice> printSearchAll(PageInfo pi, Search search);
	// 상세 조회 
	public Notice printOne(int noNo);
	
	// 글 작성, 수정 ,삭제
	public int registerNotice(Notice notice);
	public int modifyNotice(Notice notice);
	public int removeNotice(int noNo);
	
	// 댓글
	public ArrayList<Notice> printAllReply(PageInfo pi, int noMotherNo);
	public Notice printOneReply(int noMotherNo);
	public int registerReply(Notice notice);
	public int modifyReply(Notice notice);
	public int removeReply(Notice notice);
	
	// 스터디 대시보드 출력용
		// 최신 공지사항
	public ArrayList<Notice> printRecentNotice(Notice notice);
		// 오늘 올라온 공지사항 개수
	public int printRecentCount(int stNo);
}
