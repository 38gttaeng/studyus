package com.studyus.submittedAssignment.service.logic;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studyus.assignment.domain.Assignment;
import com.studyus.submittedAssignment.domain.SubmittedAssignment;
import com.studyus.submittedAssignment.service.SAssignmentService;
import com.studyus.submittedAssignment.store.SAssignmentStore;

@Service
public class SAssignmentServiceImpl implements SAssignmentService {
	
	@Autowired
	private SAssignmentStore suStore;

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
	public int countSubmittedAssignment(SubmittedAssignment sAssignment) {
		// TODO Auto-generated method stub
		return 0;
	}

}
