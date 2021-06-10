package com.studyus.submittedAssignment.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.studyus.assignment.domain.Assignment;
import com.studyus.board.domain.Board;
import com.studyus.common.PageInfo;
import com.studyus.submittedAssignment.domain.SubmittedAssignment;

public interface SAssignmentService {
	
	// 과제제출 보기
	
	/**
	 * 한개 제출 개수 확인
	 * @param asNo
	 * @return
	 */
	public int submittedCheckCount(int asNo);
	
	/**
	 * 전체보기
	 * @param asNo
	 * @return
	 */
	public ArrayList<SubmittedAssignment> printAllSubmittedAssignment(int asNo);
	
	/**
	 * 한개 보기
	 * @param suNo
	 * @return
	 */
	public SubmittedAssignment printOneSubmittedAssignment(int suNo);
	
	/**
	 * 댓글 총 개수
	 * @param suMotherNo
	 * @return
	 */
	public int countSubmittedReply(int suMotherNo);
	
	/**
	 * 댓글 보기
	 * @param pi
	 * @param suMotherNo
	 * @return
	 */
	public ArrayList<SubmittedAssignment> printAllSubmittedReply(PageInfo pi, int suMotherNo);
	
	// 과제제출과 댓글 추가, 수정, 삭제
		// 과제를 제출할 때마다 회원에게 30점 적립
	
	public int registerSubmittedAssignment(SubmittedAssignment sAssignment);
	public int modifySubmittedAssignment(SubmittedAssignment sAssignment);
	public int removeSubmittedAssignment(int suNo);
	
	////////////////////////////////////////////////////////////
	// 현재날짜 기준으로 그 날이 속한 달!
	
	/**
	 * 남은 과제 수(제출한 과제수)
	 * @param stNo
	 * @param mbNo
	 * @return
	 */
	public int printRemainByMbNo(int stNo, int mbNo);
	
	////////////////////////////////////////////////////////////
	
	/**
	 * 파일함 (게시물 + 댓글 전부 가져오기)
	 * @param stNo
	 * @return
	 */
	public ArrayList<SubmittedAssignment> printAllContents(int stNo);
	
	/**
	 * 게시물, 댓글 관리
	 * @param stNo
	 * @return
	 */
	public ArrayList<HashMap<String, Object>> printAllByStNo(int stNo);
	public ArrayList<HashMap<String, Object>> printAllByMemberNo(HashMap<String, Integer> map);
	public ArrayList<HashMap<String, Object>> printAllReplyByStNo(int stNo);
	public ArrayList<HashMap<String, Object>> printAllReplyByMemberNo(HashMap<String, Integer> map);
}
