package com.studyus.board.store.logic;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.studyus.board.domain.Board;
import com.studyus.board.domain.PageInfo;
import com.studyus.board.domain.Search;
import com.studyus.board.store.BoardStore;

@Repository
public class BoardStoreLogic implements BoardStore{
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public int getListCount(Board board) {
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
		return sqlSession.selectOne("boardMapper.selectOne", boNo);
	}

	@Override
	public ArrayList<Board> selectAllReply(int boMotherNo) {
		return (ArrayList)sqlSession.selectList("boardMapper.selectAllReply", boMotherNo);
	}

	@Override
	public int getListCountByMemberNo(Board board, int selected) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Board> selectAllByMemberNo(PageInfo pi, Board board, int selected) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getReplyCount(int stNo, int selected) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Board> selectAllReply(PageInfo pi, int stNo, int selected) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Board> selectSearchAllReply(Search search, int stNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getReplyCountByMemberNo(Board board, int selected) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Board> selectAllReplyByMemberNo(PageInfo pi, Board board, int selected) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertBoard(Board board) {
		return sqlSession.insert("boardMapper.insertBoard", board);
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
}
