package com.studyus.submittedAssignment.service.logic;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studyus.assignment.domain.Assignment;
import com.studyus.common.PageInfo;
import com.studyus.file.domain.FileVO;
import com.studyus.file.store.FileStore;
import com.studyus.submittedAssignment.domain.SubmittedAssignment;
import com.studyus.submittedAssignment.service.SAssignmentService;
import com.studyus.submittedAssignment.store.SAssignmentStore;

@Service
public class SAssignmentServiceImpl implements SAssignmentService {
	
	@Autowired
	private SAssignmentStore suStore;
	
	@Autowired
	private FileStore fiStore;

	@Override
	public int submittedCheckCount(int asNo) {
		return suStore.submittedCheckCount(asNo);
	}

	@Override
	public int submittedCheck(SubmittedAssignment sAssignment) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<SubmittedAssignment> printAllSubmittedAssignment(int asNo) {
		
		// 1. 모든 suList 가져오기
		ArrayList<SubmittedAssignment> suList = suStore.selectAllSubmittedAssignment(asNo);
		
		// 2. 파일 리스트, 댓글 하나 저장
		for(SubmittedAssignment suOne : suList) {
			FileVO fileVO = new FileVO(3, suOne.getSuNo());
			ArrayList<FileVO> fList = fiStore.selectList(fileVO);
			
			SubmittedAssignment reply = suStore.selectOneReply(suOne.getSuNo());
			
			suOne.setSuFiles(fList);
			suOne.setReply(reply);
		}
		
		return suList;
	}
	
	@Override
	public SubmittedAssignment printOneSubmittedAssignment(int suNo) {
		
		// SubmittedAssignment 가져오기
		SubmittedAssignment sAssignment = suStore.selectOneSubmittedAssignment(suNo);
		
		// 파일 가져오기
		FileVO fileVO = new FileVO(3, suNo);
		ArrayList<FileVO> fList = fiStore.selectList(fileVO);
		if(!fList.isEmpty()) {
			sAssignment.setSuFiles(fList);
		}
		
		return sAssignment;
	}
	
	@Override
	public int countSubmittedReply(int suMotherNo) {
		return suStore.countSubmittedReply(suMotherNo);
	}

	@Override
	public ArrayList<SubmittedAssignment> printAllSubmittedReply(PageInfo pi, int suMotherNo) {
		return suStore.selectAllSubmittedReply(pi, suMotherNo);
	}

	@Override
	public int registerSubmittedAssignment(SubmittedAssignment sAssignment) {
		return suStore.insertSubmittedAssignment(sAssignment);
	}

	@Override
	public int modifySubmittedAssignment(SubmittedAssignment sAssignment) {
		return suStore.updateSubmittedAssignment(sAssignment);
	}

	@Override
	public int removeSubmittedAssignment(int suNo) {
		return suStore.deleteSubmittedAssignment(suNo);
	}

	@Override
	public int mySubmittedAssignment(SubmittedAssignment sAssignment) {
		// TODO Auto-generated method stub
		return 0;
	}

}
