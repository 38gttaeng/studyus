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
	
	// insert
	public int insertEnrollment(Enrollment enrollment, String url) throws Exception;
	
	// update status
	public int updateStatus(Enrollment enrollment) throws Exception;
	
	// select one by No
	public Enrollment selectOne(int enrollmentNo) throws Exception;
	
	// select list by StudyNo
	public ArrayList<EnrollmentWithMember> selectListByStudyNo(int studyNo) throws Exception;
	
	// 가입여부 확인
	public int checkEnrollment(Enrollment enrollment) throws Exception;
	
	//추방
	public int banishMember(int memberNo);
}
