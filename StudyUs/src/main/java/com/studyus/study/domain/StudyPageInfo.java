package com.studyus.study.domain;

public class StudyPageInfo {
	
	// 현재 페이지 
	private int currentPage;
	
	// 게시글 갯수
	private int boardLimit;
	
	// 내비게이션 갯수
	private int pageLimit;
	
	// 내비게이션 첫 번째 값
	private int startPage;
	
	// 내비게이션 마지막 값
	private int endPage;
	
	// 전체 게시글 갯수
	private int listCount;
	
	// 페이지 마지막 번호
	private int maxPage;
	
	public StudyPageInfo () {}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getBoardLimit() {
		return boardLimit;
	}

	public void setBoardLimit(int boardLimit) {
		this.boardLimit = boardLimit;
	}

	public int getPageLimit() {
		return pageLimit;
	}

	public void setPageLimit(int pageLimit) {
		this.pageLimit = pageLimit;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getListCount() {
		return listCount;
	}

	public void setListCount(int listCount) {
		this.listCount = listCount;
	}

	public int getMaxPage() {
		return maxPage;
	}

	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}

	@Override
	public String toString() {
		return "StudyPageInfo [currentPage=" + currentPage + ", boardLimit=" + boardLimit + ", pageLimit=" + pageLimit
				+ ", startPage=" + startPage + ", endPage=" + endPage + ", listCount=" + listCount + ", maxPage="
				+ maxPage + "]";
	}
}
