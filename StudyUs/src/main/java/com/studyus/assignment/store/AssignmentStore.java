package com.studyus.assignment.store;

import java.util.ArrayList;

import com.studyus.assignment.domain.Assignment;
import com.studyus.assignment.domain.AssignmentGroup;
import com.studyus.common.PageInfo;
import com.studyus.submittedAssignment.domain.SubmittedAssignment; 

public interface AssignmentStore {
	
	// 과제 보기
	
	/**
	 * 전체 게시물수(원글번호가 null인 것만 가져오기)
	 * @param grNo
	 * @return
	 */
	public int getListCount(int grNo);
	
	/**
	 * 전체보기 (5개씩)
	 * @param pi
	 * @param grNo
	 * @return
	 */
	public ArrayList<Assignment> selectAll(PageInfo pi, int grNo);
	
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
	 * 스터디에 해당하는 과제 전체보기(일정용)
	 * @param stNo
	 * @return
	 */
	public ArrayList<Assignment> selectAllByStudyNo(int stNo);
	
	/**
	 * 한개 보기 (WHERE AS_NO)
	 * @param asNo
	 * @return
	 */
	public Assignment selectOne(int asNo);
	
	/**
	 * 한개 댓글 보기 (WHERE AS_MOTHER_NO)
	 * @param asNo
	 * @return
	 */
	public ArrayList<Assignment> selectAllReply(int asNo);
	
	// 과제 그룹 추가, 수정, 삭제
	public int insertGroup(AssignmentGroup asGroup);
	public int updateGroup(AssignmentGroup asGroup);
	public int deleteGroup(int grNo);
	
	// 과제와 댓글 추가, 수정, 삭제
	
	public int insertAssignment(Assignment assignment);
	public int updateAssignment(Assignment assignment);
	public int deleteAssignment(int asNo);
	
	////////////////////////////////////////////////////////////
	// 현재날짜 기준으로 그 날이 속한 달!
		
	/**
	 * 한달 총 과제 개수
	 * @param stNo
	 */
	public int countAssignment(int stNo);
	
}
