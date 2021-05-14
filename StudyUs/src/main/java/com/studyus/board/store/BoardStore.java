package com.studyus.board.store;

import java.util.ArrayList;

import com.studyus.board.domain.Board;
import com.studyus.board.domain.PageInfo;
import com.studyus.board.domain.Search;

public interface BoardStore {
	
	// 게시물 보기
	
	/**
	 * 전체 게시물 수(원글번호가 null인 것만 가져오기 / 카테고리별로)
	 * @param board(stNo, boCategory)
	 * @return
	 */
	public int selectListCount(Board board);
	
	/**
	 * 전체보기(10개씩)
	 * @param pi
	 * @param board(stNo, boCategory)
	 * @return
	 */
	public ArrayList<Board> selectAll(PageInfo pi, Board board);
	
	/**
	 * 검색하기 - 전체 / 마이페이지 / 팀장페이지
	 * (마이페이지 검색은 멤버가 null이 아닐 때)
	 * @param search
	 * @param board(stNo, boCategory)
	 * @return
	 */
	public ArrayList<Board> selectSearchAll(Search search, Board board);
	
	/**
	 * 한개보기
	 * @param boardNo
	 * @return
	 */
	public Board selectOne(int boNo);
	
	// 내가 쓴 게시물 보기
	
	/**
	 * 전체 게시물 수(원글번호가 null인 것만 가져오기 / 카테고리별로)
	 * @param board(stNo, mbNo)
	 * @return
	 */
	public int selectListCountByMemberNo(Board board);
	
	/**
	 * 전체보기(10개씩)
	 * @param pi
	 * @param board(mbNo, stNo)
	 * @return
	 */
	public ArrayList<Board> selectAllByMemberNo(PageInfo pi, Board board);
	
	// 댓글 보기
	
	/**
	 * 전체 댓글 수(원글번호가 null이 아닌 것만 가져오기)
	 * @param stNo
	 * @return
	 */
	public int selectReplyCount(int stNo);
	
	/**
	 * 전체보기(10개씩)
	 * @param pi
	 * @param stNo
	 * @return
	 */
	public ArrayList<Board> selectAllReply(PageInfo pi, int stNo);
	
	/**
	 * 검색하기 - 전체 / 마이페이지 / 팀장페이지
	 * (마이페이지 검색은 멤버가 null이 아닐 때)
	 * @param search
	 * @param stNo
	 * @return
	 */
	public ArrayList<Board> selectSearchAllReply(Search search, int stNo);
	
	// 내가 쓴 댓글 보기
	
	/**
	 * 전체 댓글 수(원글번호가 null이 아닌 것만 가져오기 / 카테고리별로)
	 * @param board(stNo, mbNo)
	 * @return
	 */
	public int selectReplyCountByMemberNo(Board board);
	
	/**
	 * 전체보기(10개씩)
	 * @param pi
	 * @param board(mbNo, stNo)
	 * @return
	 */
	public ArrayList<Board> selectAllReplyByMemberNo(PageInfo pi, Board board);
	
	// 게시물 추가, 수정, 삭제
	public int insertBoard(Board board);
	public int updateBoard(Board board);
	public int deleteBoard(int boNo);
	
	// 댓글 추가, 수정, 삭제
	public int insertReply(Board board);
	public int updateReply(Board board);
	public int deleteReply(Board board);
}
