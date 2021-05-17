package com.studyus.enrollment.store;

import java.util.ArrayList;

import com.studyus.enrollment.domain.Enrollment;

public interface EnrollmentStore {
	
	// insert
	// update
	// select one by No
	// select list by StudyNo
	
	//////////////////////////////
	
	// insert
	public int insertEnrollment(Enrollment enrollment) throws Exception;
	
	// update
	public int updateEnrollment(Enrollment enrollment) throws Exception;
	
	// select one by No
	public Enrollment selectOne(int enrollmentNo) throws Exception;
	
	// select list by StudyNo
	public ArrayList<Enrollment> selectListByStudyNo(int studyNo) throws Exception;
	
	
}
