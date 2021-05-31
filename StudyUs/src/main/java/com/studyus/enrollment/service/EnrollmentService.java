package com.studyus.enrollment.service;

import java.util.ArrayList;

import com.studyus.enrollment.domain.Enrollment;

public interface EnrollmentService {
	
	// 스터디 가입신청
	// 수정
	// selectOne
	// 스터디 번호로 리스트 출력
	
	////////////////////////////////////
	
	// 스터디 가입신청
	public int apply (Enrollment enrollment, String url) throws Exception;
	
	// 수정
	public int modifyEnrollment (Enrollment enrollment) throws Exception;
	
	// 1개 출력
	public Enrollment printOne (int enrollmentNo) throws Exception;
	
	// 스터디 번호로 리스트 출력
	public ArrayList<Enrollment> printAllByStudyNo (int studyNo) throws Exception;

}
