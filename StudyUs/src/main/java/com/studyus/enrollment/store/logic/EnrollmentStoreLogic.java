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
	public int insertEnrollment(Enrollment enrollment, String url) throws Exception {
		int studyNo = sStore.selectStudyNoByUrl(url);
		enrollment.setStudyNo(studyNo);
		return session.insert("enrollmentMapper.insertEnrollmentApply", enrollment);
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
