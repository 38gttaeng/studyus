package com.studyus.enrollment.service;

import java.util.ArrayList;

import com.studyus.enrollment.domain.Enrollment;
import com.studyus.enrollment.domain.EnrollmentWithMember;

public interface EnrollmentService {
	
	// 스터디 가입신청
	// 수정
	// selectOne
	// 스터디 번호로 리스트 출력
	
	////////////////////////////////////
	
	// 스터디 가입신청
	public int apply (Enrollment enrollment, String url) throws Exception;

	public int insertOne(Enrollment enrollment) throws Exception;
	
	// 수정
	public int modifyStatus (Enrollment enrollment) throws Exception;
	
	// 1개 출력
	public Enrollment printOne (int enrollmentNo) throws Exception;
	
	// 스터디 번호로 리스트 출력
	public ArrayList<EnrollmentWithMember> printAllByStudyNo (int studyNo) throws Exception;

	// 이미 가입한 회원여부 확인
	public int checkEnrollment(Enrollment enrollment) throws Exception;
	
	// 추방
	public int banishMember(Enrollment enrollment); 
}
