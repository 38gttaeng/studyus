package com.studyus.board.store.logic;

import java.util.ArrayList;

import com.studyus.board.domain.Board;
import com.studyus.board.domain.PageInfo;
import com.studyus.board.domain.Search;
import com.studyus.board.store.BoardStore;

public class BoardStoreLogic implements BoardStore{

	@Override
	public int selectListCount(Board board) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Board> selectAll(PageInfo pi, Board board) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Board> selectSearchAll(Search search, Board board) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Board selectOne(int boNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int selectListCountByMemberNo(Board board) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Board> selectAllByMemberNo(PageInfo pi, Board board) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int selectReplyCount(int stNo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Board> selectAllReply(PageInfo pi, int stNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Board> selectSearchAllReply(Search search, int stNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int selectReplyCountByMemberNo(Board board) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Board> selectAllReplyByMemberNo(PageInfo pi, Board board) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertBoard(Board board) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateBoard(Board board) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteBoard(int boNo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertReply(Board board) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateReply(Board board) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteReply(Board board) {
		// TODO Auto-generated method stub
		return 0;
	}

}
