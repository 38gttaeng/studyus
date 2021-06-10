package com.studyus.enrollment.store;

import java.util.ArrayList;

import com.studyus.enrollment.domain.Enrollment;
import com.studyus.enrollment.domain.EnrollmentWithMember;

public interface EnrollmentStore {
	
	// insert
	// update
	// select one by No
	// select list by StudyNo
	
	//////////////////////////////
	
	/**
	 * 입력한 String url에 해당하는 스터디를 찾아 enrollment를 추가합니다.
	 * 이미 enrollment가 추가된 상태라면 추가하지 않습니다.
	 * @author 김동현
	 * @param enrollment
	 * @param url
	 * @return 
	 * DB오류시 0<br>
	 * 신청 성공시 1<br>
	 * 이미 가입되어 있으면 2<br>
	 * 이미 가입신청 되어있으면 3<br>
	 * @throws Exception
	 */
	public int applyEnrollment(int memberNo, String message, String url) throws Exception;
	
	/**
	 * Enrollment의 값을 그대로 테이블에 추가합니다.
	 * @author 김동현
	 * @param enrollment
	 * @return
	 * 실패시 0, 성공시 1
	 */
	public int insertEnrollment(Enrollment enrollment);
	
	// update status
	public int updateStatus(Enrollment enrollment) throws Exception;
	
	// select one by No
	public Enrollment selectOne(int enrollmentNo) throws Exception;
	
	// select list by StudyNo
	public ArrayList<EnrollmentWithMember> selectListByStudyNo(int studyNo) throws Exception;
	
	// 가입여부 확인
	public int checkEnrollment(Enrollment enrollment) throws Exception;

	public int banishMember(Enrollment enrollment); 

}
