package com.studyus.enrollment.store.logic;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.studyus.enrollment.domain.Enrollment;
import com.studyus.enrollment.domain.EnrollmentWithMember;
import com.studyus.enrollment.store.EnrollmentStore;
import com.studyus.study.store.StudyStore;

@Repository
public class EnrollmentStoreLogic implements EnrollmentStore { 
	
	@Autowired
	SqlSession session;
	
	@Autowired
	StudyStore sStore;

	@Override
	public int applyEnrollment(int memberNo, String message, String url) throws Exception {
		int studyNo = sStore.selectStudyNoByUrl(url);
		// 새로 추가하려는 가입신청
		Enrollment newEnrollment = new Enrollment();
		newEnrollment.setMemberNo(memberNo);
		newEnrollment.setMessage(message);
		newEnrollment.setStudyNo(studyNo);
		
		// 새로 추가하려는 가입신청 정보로 이미 가입이 되어있는지 확인 후 가입되어 있으면 2를 반환합니다.
		if (0 < (Integer)session.selectOne("enrollmentMapper.checkEnrollment", newEnrollment)) {
			return 2;
		}
		
		// 새로 추가하려는 가입신청 정보로 이미 가입신청이 되어있는지 확인 후 신청되어 있으면 3을 반환합니다. 가입이 이미 수락된 신청서는 확인하지 않습니다.
		int exist = session.selectOne("enrollmentMapper.selectExistingApplication", newEnrollment);
		if (0 < exist) {
			return 3;
		}
		int result = session.insert("enrollmentMapper.insertEnrollmentApply", newEnrollment);
		return result;
	}
	
	@Override
	public int insertEnrollment(Enrollment enrollment) {
		return session.insert("enrollmentMapper.insertEnrollment", enrollment);
	}

	@Override
	public int updateStatus(Enrollment enrollment) throws Exception {
		return session.update("enrollmentMapper.updateStatus", enrollment);
	}

	@Override
	public Enrollment selectOne(int enrollmentNo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<EnrollmentWithMember> selectListByStudyNo(int studyNo) throws Exception {
		return (ArrayList) session.selectList("enrollmentMapper.selectAllByStudyNo", studyNo);
	}

	@Override
	public int checkEnrollment(Enrollment enrollment) throws Exception {
		return session.selectOne("enrollmentMapper.checkEnrollment", enrollment);
	}

	@Override
	public int banishMember(Enrollment enrollment) {
		return session.update("enrollmentMapper.banishMember", enrollment); 
	}

}
