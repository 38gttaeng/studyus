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
	 * @param enrollment
	 * @param url
	 * @return insert 성공시 1, 실패시 0
	 * @throws Exception
	 */
	public int insertEnrollment(Enrollment enrollment, String url) throws Exception;
	
	public int insertEnrollment(Enrollment enrollment);
	
	// update status
	public int updateStatus(Enrollment enrollment) throws Exception;
	
	// select one by No
	public Enrollment selectOne(int enrollmentNo) throws Exception;
	
	// select list by StudyNo
	public ArrayList<EnrollmentWithMember> selectListByStudyNo(int studyNo) throws Exception;
	
	// 가입여부 확인
	public int checkEnrollment(Enrollment enrollment) throws Exception;

}
