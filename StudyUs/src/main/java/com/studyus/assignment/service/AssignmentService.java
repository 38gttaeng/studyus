package com.studyus.assignment.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.studyus.assignment.domain.Assign;
import com.studyus.assignment.domain.Assignment;
import com.studyus.assignment.domain.AssignmentGroup;
import com.studyus.common.PageInfo;

public interface AssignmentService {
	
	// 과제 보기
	
	/**
	 * 전체 게시물수(원글번호가 null인 것만 가져오기)
	 * @param grNo
	 * @param stNo
	 * @return
	 */
	public int getListCount(int grNo, int stNo);
	
	/**
	 * 전체보기 (5개씩)
	 * @param pi
	 * @param grNo
	 * @param stNo
	 * @return
	 */
	public ArrayList<Assignment> printAll(PageInfo pi, int grNo, int stNo);
	
	/**
	 * 전체보기 (삭제용)
	 * @param grNo
	 * @return
	 */
	public ArrayList<Assignment> printAllAssignment(int grNo);
	
	/**
	 * 과제 그룹정보 모두 보기
	 * @param stNo
	 * @return
	 */
	public ArrayList<AssignmentGroup> printAllGroup(int stNo);
	
	/**
	 * 과제 그룹정보 하나 보기
	 * @param grNo
	 * @return
	 */
	public AssignmentGroup printOneGroup(int grNo); 
	
	/**
	 * 스터디에 해당하는 과제 전체보기(일정용)
	 * @param stNo
	 * @return
	 */
	public ArrayList<Assignment> printAllByStudyNo(int stNo);
	
	/**
	 * 그룹에 해당하는 과제 할당정보 모두 가져오기
	 * @param grNo
	 * @return
	 */
	public ArrayList<Integer> printAllAssign(int grNo);
	
	///////////////////////////////////////////////////////////
	
	/**
	 * 한개 보기 (WHERE AS_NO)
	 * @param asNo
	 * @return
	 */
	public Assignment printOne(int asNo);
	
	///////////////////////////////////////////////////////////
	
	// 과제 그룹 추가, 수정, 삭제
	public int registerGroup(AssignmentGroup asGroup);
	public int modifyGroup(AssignmentGroup asGroup);
	public int removeGroup(int grNo);
	
	// 과제 할당 추가, 삭제
	public int addAssign(Assign assign);
	public int deleteAssign(Assign assign);
	
	// 과제와 댓글 추가, 수정, 삭제
	public int registerAssignment(Assignment assignment);
	public int modifyAssignment(Assignment assignment);
	public int removeAssignment(int asNo);
	
	////////////////////////////////////////////////////////////
	
	/**
	 * 마이페이지 과제 리스트
	 * @param mbNo
	 * @return
	 */
	public ArrayList<Assignment> printAllByMbNo(int mbNo);
	
	/**
	 * 과제율(전체 - 스터디 가입 신청 / 마이페이지)
	 * @param mbNo
	 * @return
	 */
	public int printAssignmentRate(int mbNo);
	
	/**
	 * 과제율(스터디 내부)
	 * @param assignment(stNo, mbNo)
	 * @return
	 */
	public int printAssignmentRate(HashMap<String, Integer> map);

}
