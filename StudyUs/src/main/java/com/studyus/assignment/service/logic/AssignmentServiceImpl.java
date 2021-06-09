package com.studyus.assignment.service.logic;

import java.util.ArrayList;
import java.util.HashMap; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studyus.assignment.domain.Assign;
import com.studyus.assignment.domain.Assignment;
import com.studyus.assignment.domain.AssignmentGroup;
import com.studyus.assignment.service.AssignmentService;
import com.studyus.assignment.store.AssignmentStore;
import com.studyus.common.PageInfo;
import com.studyus.file.domain.FileVO;
import com.studyus.file.store.FileStore;

@Service
public class AssignmentServiceImpl implements AssignmentService {
	
	@Autowired
	private AssignmentStore asStore;
	
	@Autowired
	private FileStore fiStore;

	@Override
	public int getListCount(int grNo, int stNo) {
		return asStore.getListCount(grNo, stNo);
	}

	@Override
	public ArrayList<Assignment> printAll(PageInfo pi, int grNo, int stNo) {
		return asStore.selectAll(pi, grNo, stNo);
	}
	
	public ArrayList<Assignment> printAllAssignment(int grNo) {
		return asStore.selectAllAssignment(grNo);
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
		return asStore.selectAllByStudyNo(stNo);
	}
	
	@Override
	public ArrayList<Integer> printAllAssign(int grNo) {
		return asStore.selectAllAssign(grNo);
	}
	
	/////////////////////////////////////////////////////////////////////////////////
	
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
	
	/////////////////////////////////////////////////////////////////////////////////

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
	public int addAssign(Assign assign) {
		return asStore.addAssign(assign);
	}

	@Override
	public int deleteAssign(Assign assign) {
		return asStore.deleteAssign(assign);
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
	public ArrayList<Assignment> printAllByMbNo(int mbNo) {
		return asStore.selectAllByMbNo(mbNo);
	}
	
	public ArrayList<AssignmentGroup> printGroupChart(int stNo) {
		
		// 스터디에 해당하는 그룹별 과제 개수 가져오기
			// grStatus를 과제 개수 담는 변수로 사용
		ArrayList<AssignmentGroup> grList = asStore.selectGroupChart(stNo);
		
		// 그룹별로 그룹이름과 색상 넣어주기
		for(AssignmentGroup gOne : grList) {
			AssignmentGroup group = asStore.selectOneGroup(gOne.getGrNo());
			gOne.setGrName(group.getGrName());
			
			String grColor = "";
			switch(group.getGrColor()) {
			case 1: grColor = "rgb(196, 178, 234)";
				break;
			case 2: grColor = "rgb(165, 228, 216)";
				break;
			case 3: grColor = "rgb(243, 211, 244)";
				break;
			case 4: grColor = "rgb(188, 209, 241)";
				break;
			}
			gOne.setGrInfo(grColor); // grInfo를 color 정보 담는 변수로 사용
		}
		
		return grList;
	}
	
	/////////////////////////////////////////////////////////////////////////////////

	@Override
	public int printAssignmentRate(int mbNo) {
		// 할당된 전체 과제 개수 가져오기
		int allCount = asStore.selectAssignmentByMbNo(mbNo);
		
		// 실제로 한 개수 가져오기
		int memCount = asStore.mySubmittedAssignment(mbNo);
		
		// 계산
		int rate = 0;
		if( allCount != 0 ) {
			rate = (memCount / allCount) * 100;
		}
		
		// 넘겨주기
		return rate; 
	}

	@Override
	public int printAssignmentRate(HashMap<String, Integer> map) {
		// 할당된 전체 과제 개수 가져오기
		int allCount = asStore.selectAssignmentStNo(map);
		
		// 실제로 한 개수 가져오기
		int memCount = asStore.mySubmittedAssignmentByStNo(map);
		
		// 계산
		int rate = 0;
		if( allCount != 0 ) {
			rate = (memCount / allCount) * 100;
		}
		
		// 넘겨주기
		return rate;
	}

}
