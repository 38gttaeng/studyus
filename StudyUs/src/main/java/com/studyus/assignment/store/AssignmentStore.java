package com.studyus.assignment.store;

import java.util.ArrayList;

import com.studyus.assignment.domain.Assign;
import com.studyus.assignment.domain.Assignment;
import com.studyus.assignment.domain.AssignmentGroup;
import com.studyus.common.PageInfo;

public interface AssignmentStore {
	
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
	public ArrayList<Assignment> selectAll(PageInfo pi, int grNo, int stNo);
	
	/**
	 * 전체보기 (삭제용)
	 * @param grNo
	 * @return
	 */
	public ArrayList<Assignment> selectAllAssignment(int grNo);
	
	/**
	 * 과제 그룹정보 모두 보기
	 * @param stNo
	 * @return
	 */
	public ArrayList<AssignmentGroup> selectAllGroup(int stNo);
	
	/**
	 * 과제 그룹정보 하나 보기
	 * @param grNo
	 * @return
	 */
	public AssignmentGroup selectOneGroup(int grNo);
	
	/**
	 * 그룹에 해당하는 과제 할당정보 모두 가져오기
	 * @param grNo
	 * @return
	 */
	public ArrayList<Integer> selectAllAssign(int grNo);
	
	///////////////////////////////////////////////////////////
	
	/**
	 * 스터디에 해당하는 과제 전체보기(일정용)
	 * @param stNo
	 * @return
	 */
	public ArrayList<Assignment> selectAllByStudyNo(int stNo);
	
	////////////////////////////////////////////////////////////////////
	
	/**
	 * 한개 보기 (WHERE AS_NO)
	 * @param asNo
	 * @return
	 */
	public Assignment selectOne(int asNo);
	
	///////////////////////////////////////////////////////////
	
	// 과제 그룹 추가, 수정, 삭제
	public int insertGroup(AssignmentGroup asGroup);
	public int updateGroup(AssignmentGroup asGroup);
	public int deleteGroup(int grNo);
	
	// 과제 할당 추가, 삭제
	public int addAssign(Assign assign);
	public int deleteAssign(Assign assign);
	
	// 과제와 댓글 추가, 수정, 삭제
	public int insertAssignment(Assignment assignment);
	public int updateAssignment(Assignment assignment);
	public int deleteAssignment(int asNo);
	
	///////////////////////////////////////////////////////////
	
	/**
	 * 마이페이지 과제 리스트
	 * @param mbNo
	 * @return
	 */
	public ArrayList<Assignment> selectAllByMbNo(int mbNo);
	
	/**
	 * 과제율(전체 - 스터디 가입 신청 / 마이페이지)
	 * @param mbNo
	 * @return
	 */
	public int selectAssignmentByMbNo(int mbNo);
	
	/**
	 * 과제율(스터디 내부)
	 * @param assignment(stNo, mbNo)
	 * @return
	 */
	public int selectAssignmentStNo(Assignment assignment);
	
	/**
	 * 한달 총 과제 제출 개수
	 * @param mbNo
	 */
	public int mySubmittedAssignment(int mbNo);
	
	/**
	 * 한달 총 과제 제출 개수(스터디 내부)
	 * @param sAssignment(stNo, mbNo)
	 */
	public int mySubmittedAssignmentByStNo(Assignment assignment);
	
}
