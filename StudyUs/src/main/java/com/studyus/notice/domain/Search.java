package com.studyus.notice.domain;

public class Search {
	private String searchCondition;
	private String searchValue;
	private int stNo;
	
	public Search() {}

	public String getSearchCondition() {
		return searchCondition;
	}

	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	public int getStNo() {
		return stNo;
	}

	public void setStNo(int stNo) {
		this.stNo = stNo;
	}

	@Override
	public String toString() {
		return "Search [searchCondition=" + searchCondition + ", searchValue=" + searchValue + ", stNo=" + stNo + "]";
	}
	
}
