package com.studyus.notice.store.logic;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.studyus.common.PageInfo;
import com.studyus.notice.domain.Notice;
import com.studyus.notice.domain.Search;
import com.studyus.notice.store.NoticeStore;

@Repository
public class NoticeStoreLogic implements NoticeStore{
 
	@Autowired
	public SqlSession sqlSession;

	@Override
	public int selectListCount(Notice notice) {
		return sqlSession.selectOne("noticeMapper.selectListCount", notice);
	}

	@Override
	public int selectPageCount(Search search) {
		return sqlSession.selectOne("noticeMapper.selectPageCount", search);
	}

//	@Override
//	public int selectNListCount(Notice notice) {
//		return sqlSession.selectOne("noticeMapper.selectNListCount", notice);
//	}
//
//	@Override
//	public int selectRListCount(Notice notice) {
//		return sqlSession.selectOne("noticeMapper.selectRListCount", notice);
//	}

	@Override
	public ArrayList<Notice> selectList(PageInfo pi, Notice notice) {
		// RowBounds는 쿼리문을 변경하지 않고도 페이징을 처리할 수있게 해주는 클래스
		// RowBounds의 동작은 offset값과 limit값을 이용해서 동작함
		// offset값은 변하는 값이고 limit값은 고정값
		// limit값이 한 페이지당 보여주고 싶은 게시물의 갯수이고
		// offset값은 건너뛰어야 할 값임
		// ex) currentPage가 1일 때 offset은 0, limit는 10이 되어 10개값을 보여줌
		//     currentPage가 2일 때 offset은 10이 되어서 10개를 건너뛰고 11개부터 20까지의 10개의 값을 보여줌

		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		return (ArrayList)sqlSession.selectList("noticeMapper.selectNoticeList", notice, rowBounds);
	}

	@Override
	public ArrayList<Notice> printMainNotice(Notice notice) {
		return (ArrayList)sqlSession.selectList("noticeMapper.selectMainNotice", notice);
	}

	@Override
	public int resetMainNotice(Notice notice) {
		return sqlSession.update("noticeMapper.resetMainNotice", notice);
	}

	@Override
	public int updateMainNotice(int noNo) {
		return sqlSession.update("noticeMapper.updateMainNotice", noNo);
	}

	@Override
	public int updateReplyCount(int noMotherNo) {
		return sqlSession.update("noticeMapper.updateReplyCount", noMotherNo);
	}

	@Override
	public int addReadCount(int noNo) {
		return sqlSession.update("noticeMapper.updateCount", noNo);
	}

	@Override
	public ArrayList<Notice> selectSearchList(PageInfo pi, Search search) {
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		return (ArrayList)sqlSession.selectList("noticeMapper.selectSearchList", search, rowBounds);
	}

	@Override
	public Notice selectOne(int noNo) {
		return sqlSession.selectOne("noticeMapper.selectOne", noNo);
	}

	@Override
	public int insertNotice(Notice notice) {
		sqlSession.insert("noticeMapper.insertNotice", notice);
		return notice.getNoNo();
	}

	@Override
	public int updateNotice(Notice notice) {
		return sqlSession.update("noticeMapper.updateNotice", notice);
	}

	@Override
	public int deleteNotice(int noNo) {
		return sqlSession.update("noticeMapper.deleteNotice", noNo);
	}

	@Override
	public ArrayList<Notice> printAllReply(PageInfo pi, int noMotherNo) {
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		return (ArrayList)sqlSession.selectList("noticeMapper.selectReplyList", noMotherNo, rowBounds);
	} 
	
	@Override
	public Notice selectOneReply(int noMotherNo) {
		return sqlSession.selectOne("noticeMapper.selectOneReply", noMotherNo);
	}

	@Override
	public int insertReply(Notice notice) {
		return sqlSession.insert("noticeMapper.insertReply", notice);
	}

	@Override
	public int updateReply(Notice notice) {
		return sqlSession.update("noticeMapper.updateReply", notice);
	}

	@Override
	public int deleteReply(Notice notice) {
		return sqlSession.update("noticeMapper.deleteReply", notice);
	}

	@Override
	public ArrayList<Notice> printRecentNotice(Notice notice) {
		return (ArrayList)sqlSession.selectList("noticeMapper.printRecentNotice", notice);
	}

	@Override
	public int printRecentCount(int stNo) {
		return sqlSession.selectOne("noticeMapper.printRecentCount", stNo); 
	}

}
