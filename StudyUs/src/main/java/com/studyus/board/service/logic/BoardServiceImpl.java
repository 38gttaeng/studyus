package com.studyus.board.service.logic;

import java.util.ArrayList;

import com.studyus.board.domain.Board;
import com.studyus.board.domain.PageInfo;
import com.studyus.board.domain.Search;
import com.studyus.board.service.BoardService;

public class BoardServiceImpl implements BoardService {

	@Override
	public int getListCount(Board board) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Board> printAll(PageInfo pi, Board board) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Board> printSearchAll(Search search, Board board) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Board printOne(int boNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int printListCountByMemberNo(Board board) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Board> printAllByMemberNo(PageInfo pi, Board board) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getReplyCount(int stNo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Board> printAllReply(PageInfo pi, int stNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Board> printSearchAllReply(Search search, int stNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int printReplyCountByMemberNo(Board board) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Board> printAllReplyByMemberNo(PageInfo pi, Board board) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int registerBoard(Board board) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int modifyBoard(Board board) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int removeBoard(int boNo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int registerReply(Board board) {
		// TODO Auto-generated method stub
		return 0;
	}

}
