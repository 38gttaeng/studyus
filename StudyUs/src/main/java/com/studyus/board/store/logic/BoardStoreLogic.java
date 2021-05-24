package com.studyus.board.store.logic;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.studyus.board.domain.Board;
import com.studyus.board.domain.Search;
import com.studyus.board.store.BoardStore;
import com.studyus.common.PageInfo;

@Repository
public class BoardStoreLogic implements BoardStore{
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public int getListCount(Board board) {
		return sqlSession.selectOne("boardMapper.selectListCount", board);
	}

	@Override
	public ArrayList<Board> selectAll(PageInfo pi, Board board) {
		int offset = (pi.getCurrentPage() - 1) *pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		return (ArrayList)sqlSession.selectList("boardMapper.selectAllList", board, rowBounds);
	}
	
	@Override
	public Board selectOneReply(int boMotherNo) {
		return sqlSession.selectOne("boardMapper.selectOneReply", boMotherNo);
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
	public ArrayList<Board> selectAllReply(PageInfo pi, int boMotherNo) {
		int offset = (pi.getCurrentPage() - 1) *pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		return (ArrayList)sqlSession.selectList("boardMapper.selectAllReply", boMotherNo, rowBounds);
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
		return sqlSession.update("boardMapper.updateBoard", board);
	}

	@Override
	public int deleteBoard(int boNo) {
		return sqlSession.update("boardMapper.deleteBoard", boNo);
	}

}
