package com.studyus.notice.store.logic;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.studyus.notice.domain.Notice;
import com.studyus.notice.domain.PageInfo;
import com.studyus.notice.domain.Search;
import com.studyus.notice.store.NoticeStore;

@Repository
public class NoticeStoreLogic implements NoticeStore{

	@Autowired
	public SqlSession sqlSession;
	
	@Override
	public int selectListCount() {
		return sqlSession.selectOne("noticeMapper.selectListCount");
	}

	@Override
	public ArrayList<Notice> selectList(PageInfo pi, int studyNo) {
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		return (ArrayList)sqlSession.selectList("noticeMapper.selectAllList", null, rowBounds);
	}

	@Override
	public int addReadCount(int noticeNo) {
		return sqlSession.update("noticeMapper.updateCount", noticeNo);
	}

	@Override
	public ArrayList<Notice> selectSearchList(Search search, int studyNo) {
		return (ArrayList)sqlSession.selectList("noticeMapper.selectSearchList", search);
	}

	@Override
	public Notice selectOne(int noticeNo) {
		return sqlSession.selectOne("noticeMapper.selectOne", noticeNo);
	}

	@Override
	public int insertNotice(Notice notice) {
		return sqlSession.insert("noticeMapper.insertNotice", notice);
	}

	@Override
	public int updateNotice(Notice notice) {
		return sqlSession.update("noticeMapper.updateNotice", notice);
	}

	@Override
	public int deleteNotice(int noticeNo) {
		return sqlSession.delete("noticeMapper.deleteNotice", noticeNo);
	}
	
	@Override
	public ArrayList<Notice> printAllComment(int noticeNo) {
		return (ArrayList)sqlSession.selectList("noticeMapper.selectCommentList", noticeNo);
	}

	@Override
	public int insertComment(Notice notice) {
		return sqlSession.insert("noticeMapper.insertComment", notice);
	}
	
	@Override
	public int updateComment(Notice notice) {
		return sqlSession.update("noticeMapper.updateComment", notice);
	}
	
	@Override
	public int deleteComment(Notice notice) {
		return sqlSession.delete("noticeMapper.deleteComment", notice);
	}

}
