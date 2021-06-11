package com.studyus.submittedAssignment.store.logic;

import java.util.ArrayList;
import java.util.HashMap;

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
	
	public int selectRemainByMbNo(int stNo, int mbNo) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("stNo", stNo);
		map.put("mbNo", mbNo);
		return sqlSession.selectOne("sAssignmentMapper.selectRemainByMbNo", map); 
	}

	@Override
	public ArrayList<SubmittedAssignment> selectAllContents(int stNo) {
		return (ArrayList)sqlSession.selectList("sAssignmentMapper.selectAllContents", stNo);
	}
	
	@Override
	public ArrayList<HashMap<String, Object>> selectAllByStNo(int stNo) {
		return (ArrayList)sqlSession.selectList("sAssignmentMapper.selectAllByStNo", stNo);
	}

	@Override
	public ArrayList<HashMap<String, Object>> selectAllByMemberNo(HashMap<String, Integer> map) {
		return (ArrayList)sqlSession.selectList("sAssignmentMapper.selectAllByMemberNo", map);
	}

	@Override
	public ArrayList<HashMap<String, Object>> selectAllReplyByStNo(int stNo) {
		return (ArrayList)sqlSession.selectList("sAssignmentMapper.selectAllReplyByStNo", stNo);
	}

	@Override
	public ArrayList<HashMap<String, Object>> selectAllReplyByMemberNo(HashMap<String, Integer> map) {
		return (ArrayList)sqlSession.selectList("sAssignmentMapper.selectAllReplyByMemberNo", map);
	}

}
