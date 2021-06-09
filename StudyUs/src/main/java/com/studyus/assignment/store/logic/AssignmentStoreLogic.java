package com.studyus.assignment.store.logic;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.studyus.assignment.domain.Assign;
import com.studyus.assignment.domain.Assignment;
import com.studyus.assignment.domain.AssignmentGroup;
import com.studyus.assignment.store.AssignmentStore;
import com.studyus.common.PageInfo;

@Repository
public class AssignmentStoreLogic implements AssignmentStore {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int getListCount(int grNo, int stNo) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("grNo", grNo);
		map.put("stNo", stNo);
		return sqlSession.selectOne("assignmentMapper.selectListCount", map);
	}

	@Override
	public ArrayList<Assignment> selectAll(PageInfo pi, int grNo, int stNo) {
		int offset = (pi.getCurrentPage() - 1) *pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("grNo", grNo);
		map.put("stNo", stNo);
		
		return (ArrayList)sqlSession.selectList("assignmentMapper.selectAllList", map, rowBounds);
	}
	
	@Override
	public ArrayList<Assignment> selectAllAssignment(int grNo) {
		return (ArrayList)sqlSession.selectList("assignmentMapper.selectAllList", grNo);
	}
	
	@Override
	public ArrayList<AssignmentGroup> selectAllGroup(int stNo) {
		return (ArrayList)sqlSession.selectList("assignmentMapper.selectAllGroup", stNo);
	}
	
	@Override
	public AssignmentGroup selectOneGroup(int grNo) {
		return sqlSession.selectOne("assignmentMapper.selectOneGroup", grNo);
	}
	
	@Override
	public Assignment selectOne(int asNo) {
		return sqlSession.selectOne("assignmentMapper.selectOne", asNo);
	}
	
	/////////////////////////////////////////////////////////////////////////////////
	
	@Override
	public ArrayList<Assignment> selectAllByStudyNo(int stNo) { 
		return (ArrayList)sqlSession.selectList("assignmentMapper.selectAllByStNo", stNo);
	}
	
	/////////////////////////////////////////////////////////////////////////////////
	
	@Override
	public ArrayList<Integer> selectAllAssign(int grNo) {
		return (ArrayList)sqlSession.selectList("assignmentMapper.selectAllAssign", grNo);
	}
	
	/////////////////////////////////////////////////////////////////////////////////
	
	@Override
	public int insertGroup(AssignmentGroup asGroup) {
		sqlSession.insert("assignmentMapper.insertGroup", asGroup);
		return asGroup.getGrNo();
	}

	@Override
	public int updateGroup(AssignmentGroup asGroup) {
		return sqlSession.update("assignmentMapper.updateGroup", asGroup);
	}

	@Override
	public int deleteGroup(int grNo) {
		return sqlSession.update("assignmentMapper.deleteGroup", grNo);
	}
	
	/////////////////////////////////////////////////////////////////////////////////
	
	@Override
	public int addAssign(Assign assign) {
		return sqlSession.insert("assignmentMapper.insertAssign", assign);
	}

	@Override
	public int deleteAssign(Assign assign) {
		return sqlSession.delete("assignmentMapper.deleteAssign", assign);
	}
	
	/////////////////////////////////////////////////////////////////////////////////
	
	@Override
	public int insertAssignment(Assignment assignment) {
		sqlSession.insert("assignmentMapper.insertAssignment", assignment);
		return assignment.getAsNo();
	}
	
	@Override
	public int updateAssignment(Assignment assignment) {
		return sqlSession.update("assignmentMapper.updateAssignment", assignment);
	}
	
	@Override
	public int deleteAssignment(int asNo) {
		int boResult = sqlSession.update("assignmentMapper.deleteAssignment", asNo);
		/////// 제출 리스트도 전부 삭제
		return boResult;
	}

	/////////////////////////////////////////////////////////////////////////////////
	
	@Override
	public ArrayList<Assignment> selectAllByMbNo(int mbNo) {
		return (ArrayList)sqlSession.selectList("assignmentMapper.selectAllByMbNo", mbNo);
	}
	
	@Override
	public ArrayList<AssignmentGroup> selectGroupChart(int stNo) {
		return (ArrayList)sqlSession.selectList("assignmentMapper.selectGroupChart", stNo);
	}

	@Override
	public int selectAssignmentByMbNo(int mbNo) {
		return sqlSession.selectOne("assignmentMapper.selectCountByMbNo", mbNo);
	}

	@Override
	public int selectAssignmentStNo(HashMap<String, Integer> map) {
		return sqlSession.selectOne("assignmentMapper.selectCountByStNo", map);
	}
	
	@Override
	public int mySubmittedAssignment(int mbNo) {
		return sqlSession.selectOne("sAssignmentMapper.selectCountByMbNo", mbNo);
	}

	@Override
	public int mySubmittedAssignmentByStNo(HashMap<String, Integer> map) {
		return sqlSession.selectOne("sAssignmentMapper.selectCountByStNo", map);
	}

}
