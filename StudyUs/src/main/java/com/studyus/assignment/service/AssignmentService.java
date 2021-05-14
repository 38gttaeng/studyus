package com.studyus.assignment.service;

import java.util.ArrayList;

import com.studyus.assignment.domain.Assignment;
import com.studyus.assignment.domain.PageInfo;
import com.studyus.assignment.domain.SubmittedAssignment;

public interface AssignmentService {
	
	// 과제 보기
	
	/**
	 * 전체 게시물수(원글번호가 null인 것만 가져오기)
	 * @param stNo
	 * @return
	 */
	public int getListCount(int stNo);
	
	/**
	 * 전체보기 (5개씩)
	 * @param pi
	 * @param stNo
	 * @return
	 */
	public ArrayList<Assignment> printAll(PageInfo pi, int stNo);
	
	/**
	 * 한개 보기 (WHERE AS_NO)
	 * @param asNo
	 * @return
	 */
	public Assignment printOne(int asNo);
	
	/**
	 * 한개 댓글 보기 (WHERE AS_MOTHER_NO)
	 * @param asNo
	 * @return
	 */
	public ArrayList<Assignment> printAllReply(int asNo);
	
	// 과제와 댓글 추가, 수정, 삭제
	
	public int registerAssignment(Assignment assignment);
	public int modifyAssignment(Assignment assignment);
	public int removeAssignment(int asNo);
	
	////////////////////////////////////////////////////////////
	
	// 과제제출 보기
	
	/**
	 * 한개 제출 개수 확인
	 * @param asNo
	 * @return
	 */
	public int submittedCheckList(int asNo);
	
	/**
	 * 한개 제출여부 확인
	 * (0이면 미제출 1이면 제출)
	 * @param sAssignment(asNo, mbNo)
	 * @return
	 */
	public int submittedCheck(SubmittedAssignment sAssignment);
	
	/**
	 * 전체보기
	 * @param submmitedAssignment(stNo, asNo)
	 * @return
	 */
	public ArrayList<SubmittedAssignment> printAllSubmittedAssignment(SubmittedAssignment sAssignment);
	
	/**
	 * 댓글 보기
	 * @param sAssignment(suNo, mbNo)
	 * @return
	 */
	public Assignment printAllSubmittedReply(SubmittedAssignment sAssignment);
	
	// 과제제출과 댓글 추가, 수정, 삭제
		// 과제를 제출할 때마다 회원에게 30점 적립
	
	public int registerSubmittedAssignment(SubmittedAssignment sAssignment);
	public int modifySubmittedAssignment(SubmittedAssignment sAssignment);
	public int removeSubmittedAssignment(int suNo);
	
	////////////////////////////////////////////////////////////
	
	// 한달동안 과제 개수
		// 현재날짜 기준으로 그 날이 속한 달!
		
	/**
	 * 한달 총 과제 개수
	 * @param stNo
	 */
	public int countAssignment(int stNo);
	
	/**
	 * 한달 총 과제 제출 개수
	 * @param sAssignment(stNo, mbNo)
	 */
	public int countSubmittedAssignment(SubmittedAssignment sAssignment);
}
