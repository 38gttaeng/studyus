package com.studyus.assignment.service.logic;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studyus.assignment.domain.Assignment;
import com.studyus.assignment.domain.AssignmentGroup;
import com.studyus.assignment.service.AssignmentService;
import com.studyus.assignment.store.AssignmentStore;
import com.studyus.common.PageInfo;
import com.studyus.file.domain.FileVO;
import com.studyus.file.store.FileStore;
import com.studyus.submittedAssignment.domain.SubmittedAssignment;

@Service
public class AssignmentServiceImpl implements AssignmentService {
	
	@Autowired
	private AssignmentStore asStore;
	
	@Autowired
	private FileStore fiStore;

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
		
		// Assignment 가져오기
		Assignment assignment = asStore.selectOne(asNo);
		
		// 파일 가져오기
		FileVO fileVO = new FileVO(2, asNo);
		ArrayList<FileVO> fList = fiStore.selectList(fileVO);
		if(!fList.isEmpty()) {
			assignment.setAsFiles(fList);
		}
		
		return assignment;
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
		return asStore.updateAssignment(assignment);
	}

	@Override
	public int removeAssignment(int asNo) {
		return asStore.deleteAssignment(asNo);
	}

	@Override
	public int countAssignment(int stNo) {
		// TODO Auto-generated method stub
		return 0;
	}

}
