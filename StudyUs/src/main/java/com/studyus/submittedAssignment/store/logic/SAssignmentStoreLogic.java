package com.studyus.submittedAssignment.store.logic;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.studyus.assignment.domain.Assignment;
import com.studyus.submittedAssignment.domain.SubmittedAssignment;
import com.studyus.submittedAssignment.store.SAssignmentStore;

@Repository
public class SAssignmentStoreLogic implements SAssignmentStore {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public int submittedCheckList(int asNo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int submittedCheck(SubmittedAssignment sAssignment) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<SubmittedAssignment> selectAllSubmittedAssignment(SubmittedAssignment sAssignment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Assignment selectAllSubmittedReply(SubmittedAssignment sAssignment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertSubmittedAssignment(SubmittedAssignment sAssignment) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateSubmittedAssignment(SubmittedAssignment sAssignment) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteSubmittedAssignment(int suNo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int countSubmittedAssignment(SubmittedAssignment sAssignment) {
		// TODO Auto-generated method stub
		return 0;
	}

}
