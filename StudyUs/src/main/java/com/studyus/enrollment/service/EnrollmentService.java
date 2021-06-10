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
	public int applyEnrollment (int memberNo, String message, String url) throws Exception;

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
