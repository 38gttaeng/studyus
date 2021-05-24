package com.studyus.board.service.logic;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studyus.board.domain.Board;
import com.studyus.board.domain.Search;
import com.studyus.board.service.BoardService;
import com.studyus.board.store.BoardStore;
import com.studyus.common.PageInfo;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardStore boStore;

	@Override
	public int getListCount(Board board) {
		return boStore.getListCount(board); 
	}

	@Override
	public ArrayList<Board> printAll(PageInfo pi, Board board) {
		return boStore.selectAll(pi, board);
	}
	
	@Override
	public Board printOneReply(int boMotherNo) {
		return boStore.selectOneReply(boMotherNo);
	}

	@Override
	public ArrayList<Board> printSearchAll(Search search, Board board) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Board printOne(int boNo) {
		return boStore.selectOne(boNo);
	}

	@Override
	public ArrayList<Board> printAllReply(PageInfo pi, int boMotherNo) {
		return boStore.selectAllReply(pi, boMotherNo);
	}

	@Override
	public int getListCountByMemberNo(Board board, int selected) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Board> printAllByMemberNo(PageInfo pi, Board board, int selected) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getReplyCount(int stNo, int selected) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Board> printAllReply(PageInfo pi, int stNo, int selected) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Board> printSearchAllReply(Search search, int stNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getReplyCountByMemberNo(Board board, int selected) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Board> printAllReplyByMemberNo(PageInfo pi, Board board, int selected) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int registerBoard(Board board) {
		return boStore.insertBoard(board);
	}

	@Override
	public int modifyBoard(Board board) {
		return boStore.updateBoard(board);
	}

	@Override
	public int removeBoard(int boNo) {
		return boStore.deleteBoard(boNo);
	}

}
