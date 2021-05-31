package com.studyus.submittedAssignment.store.logic;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.studyus.assignment.domain.Assignment;
import com.studyus.common.PageInfo;
import com.studyus.submittedAssignment.domain.SubmittedAssignment;
import com.studyus.submittedAssignment.store.SAssignmentStore;

@Repository
public class SAssignmentStoreLogic implements SAssignmentStore {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public int submittedCheckCount(int asNo) {
		return sqlSession.selectOne("sAssignmentMapper.selectListCount", asNo);
	}

	@Override
	public int submittedCheck(SubmittedAssignment sAssignment) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<SubmittedAssignment> selectAllSubmittedAssignment(int asNo) {
		return (ArrayList)sqlSession.selectList("sAssignmentMapper.selectAllList", asNo);
	}
	
	@Override
	public SubmittedAssignment selectOneReply(int suMotherNo) {
		return sqlSession.selectOne("sAssignmentMapper.selectOneReply", suMotherNo);
	}
	
	@Override
	public SubmittedAssignment selectOneSubmittedAssignment(int suNo) {
		return sqlSession.selectOne("sAssignmentMapper.selectOne", suNo);
	}
	
	@Override
	public int countSubmittedReply(int suMotherNo) {
		return sqlSession.selectOne("sAssignmentMapper.selectReplyCount", suMotherNo);
	}

	@Override
	public ArrayList<SubmittedAssignment> selectAllSubmittedReply(PageInfo pi, int suMotherNo) {
		int offset = (pi.getCurrentPage() - 1) *pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		return (ArrayList)sqlSession.selectList("sAssignmentMapper.selectAllReply", suMotherNo, rowBounds);
	}

	@Override
	public int insertSubmittedAssignment(SubmittedAssignment sAssignment) {
		sqlSession.insert("sAssignmentMapper.insertSAssignment", sAssignment);
		return sAssignment.getSuNo();
	}

	@Override
	public int updateSubmittedAssignment(SubmittedAssignment sAssignment) {
		return sqlSession.update("sAssignmentMapper.updateSAssignment", sAssignment);
	}

	@Override
	public int deleteSubmittedAssignment(int suNo) {
		int reResult = sqlSession.update("sAssignmentMapper.deleteReply", suNo);
		int suResult = sqlSession.update("sAssignmentMapper.deleteSAssignment", suNo);
		return reResult + suResult;
	}

	@Override
	public int mySubmittedAssignment(SubmittedAssignment sAssignment) {
		// TODO Auto-generated method stub
		return 0;
	}

}
