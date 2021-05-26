package com.studyus.assignment.store.logic;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.studyus.assignment.domain.Assignment;
import com.studyus.assignment.store.AssignmentStore;
import com.studyus.common.PageInfo;
import com.studyus.submittedAssignment.domain.SubmittedAssignment;

@Repository
public class AssignmentStoreLogic implements AssignmentStore {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int getListCount(int stNo) {
		return sqlSession.selectOne("assignmentMapper.selectListCount", stNo);
	}

	@Override
	public ArrayList<Assignment> selectAll(PageInfo pi, int stNo) {
		int offset = (pi.getCurrentPage() - 1) *pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		return (ArrayList)sqlSession.selectList("assignmentMapper.selectAllList", stNo, rowBounds);
	}
	
	@Override
	public ArrayList<Assignment> selectAllByStudyNo(int stNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Assignment selectOne(int asNo) {
		return sqlSession.selectOne("assignmentMapper.selectOne", asNo);
	}

	@Override
	public ArrayList<Assignment> selectAllReply(int asNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertAssignment(Assignment assignment) {
		return sqlSession.insert("assignmentMapper.insertAssignment", assignment);
	}

	@Override
	public int updateAssignment(Assignment assignment) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteAssignment(int asNo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int countAssignment(int stNo) {
		// TODO Auto-generated method stub
		return 0;
	}

}
