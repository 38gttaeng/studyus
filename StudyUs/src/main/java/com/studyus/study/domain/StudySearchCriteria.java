package com.studyus.study.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StudySearchCriteria {
	
	/**
	 * 현재 페이지
	 */
	private int page;
	/**
	 * 페이지당 글 갯수
	 */
	private int countPerPage = 6;
	/**
	 * 검색어
	 */
	private String keyword;
	private String[] hashtags;
	
	
//	// 현재 페이지 
//	private int currentPage;
//	
//	// 게시글 갯수
//	private int boardLimit;
//	
//	// 내비게이션 갯수
//	private int pageLimit;
//	
//	// 내비게이션 첫 번째 값
//	private int startPage;
//	
//	// 내비게이션 마지막 값
//	private int endPage;
//	
//	// 전체 게시글 갯수
//	private int listCount;
//	
//	// 페이지 마지막 번호
//	private int maxPage;
	
	public StudySearchCriteria () {}
	
	public static StudySearchCriteria searchReady(int page, String[] hashtags, String keyword) {
		StudySearchCriteria sc = new StudySearchCriteria();
		sc.setPage(page);
		sc.setHashtags(hashtags);
		sc.setKeyword(keyword);
		return sc;
	}

}
