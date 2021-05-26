package com.studyus.common;

public class Pagination10 {
	// PageInfo 객체를 리턴해주는 메소드
	// 한번만 생성하여 정보를 저장해서 가지고 있을 수 있도록 하기 위해 static 메소드로 만듬 (전역변수처럼 사용하기 위해서)
	public static PageInfo getPageInfo(int currentPage, int listCount) {
		PageInfo pi = new PageInfo();
		int pageLimit = 10; // 한 페이지에서 보여줄 네비게이션 수
		int boardLimit = 10;	// 한 페이지에서 보여줄 게시글의 수
		int maxPage; 		// 전체 페이지 중 가장 마지막 페이지
		int startPage;		// 현재 페이지에서 시작하는 첫번째 페이지
		int endPage;		// 현재 페이지에서 끝나는 마지막 페이지
		
		
		maxPage = (int)((double)listCount/boardLimit + 0.9); // 0.1로 값이 나오는 걸 방지하기 위해서 0.9를 더해줌 (짜피 int니까 소수점 짤림)
		startPage = (((int)((double)currentPage/pageLimit + 0.9)) - 1) * pageLimit + 1;
		endPage = startPage + pageLimit -1;	 // 끝페이지가 1이되면 안되니까
		
		// 오류방지용
		if(maxPage < endPage) {
			endPage = maxPage;
		}
		
		pi = new PageInfo(currentPage, boardLimit, pageLimit, startPage, endPage, listCount, maxPage);
		return pi;
	}
}
