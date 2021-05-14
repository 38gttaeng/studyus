package com.studyus.assignment.service.logic;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studyus.assignment.domain.Assignment;
import com.studyus.assignment.domain.PageInfo;
import com.studyus.assignment.domain.SubmittedAssignment;
import com.studyus.assignment.service.AssignmentService;
import com.studyus.assignment.store.AssignmentStore;

@Service
public class AssignmentServiceImpl implements AssignmentService {
	
	@Autowired
	private AssignmentStore store;

	@Override
	public int getListCount(int stNo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Assignment> printAll(PageInfo pi, int stNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Assignment printOne(int asNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Assignment> printAllReply(int asNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int registerAssignment(Assignment assignment) {
		// TODO Auto-generated method stub
		return 0;
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
	public ArrayList<SubmittedAssignment> printAllSubmittedAssignment(SubmittedAssignment sAssignment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Assignment printAllSubmittedReply(SubmittedAssignment sAssignment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int registerSubmittedAssignment(SubmittedAssignment sAssignment) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int modifySubmittedAssignment(SubmittedAssignment sAssignment) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int removeSubmittedAssignment(int suNo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int countAssignment(int stNo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int countSubmittedAssignment(SubmittedAssignment sAssignment) {
		// TODO Auto-generated method stub
		return 0;
	}

}
