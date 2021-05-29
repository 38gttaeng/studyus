package com.studyus.assignment.service.logic;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studyus.assignment.domain.Assignment;
import com.studyus.assignment.domain.AssignmentGroup;
import com.studyus.assignment.service.AssignmentService;
import com.studyus.assignment.store.AssignmentStore;
import com.studyus.common.PageInfo;
import com.studyus.submittedAssignment.domain.SubmittedAssignment;

@Service
public class AssignmentServiceImpl implements AssignmentService {
	
	@Autowired
	private AssignmentStore asStore;

	@Override
	public int getListCount(int grNo) {
		return asStore.getListCount(grNo);
	}

	@Override
	public ArrayList<Assignment> printAll(PageInfo pi, int grNo) {
		return asStore.selectAll(pi, grNo);
	}
	
	@Override
	public ArrayList<AssignmentGroup> printAllGroup(int stNo) {
		return asStore.selectAllGroup(stNo);
	}
	
	@Override
	public AssignmentGroup printOneGroup(int grNo) {
		return asStore.selectOneGroup(grNo);
	}
	
	@Override
	public ArrayList<Assignment> printAllByStudyNo(int stNo) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Assignment printOne(int asNo) {
		return asStore.selectOne(asNo);
	}

	@Override
	public ArrayList<Assignment> printAllReply(int asNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int registerGroup(AssignmentGroup asGroup) {
		return asStore.insertGroup(asGroup);
	}

	@Override
	public int modifyGroup(AssignmentGroup asGroup) {
		return asStore.updateGroup(asGroup);
	}

	@Override
	public int removeGroup(int grNo) {
		return asStore.deleteGroup(grNo);
	}

	@Override
	public int registerAssignment(Assignment assignment) {
		return asStore.insertAssignment(assignment);
	}

	@Override
	public int modifyAssignment(Assignment assignment) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int removeAssignment(int asNo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int countAssignment(int stNo) {
		// TODO Auto-generated method stub
		return 0;
	}

}
