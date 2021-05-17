package com.studyus.enrollment.store.logic;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.studyus.enrollment.domain.Enrollment;
import com.studyus.enrollment.store.EnrollmentStore;

@Repository
public class EnrollmentStoreLogic implements EnrollmentStore {
	
	@Autowired
	SqlSession session;

	@Override
	public int insertEnrollment(Enrollment enrollment) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateEnrollment(Enrollment enrollment) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Enrollment selectOne(int enrollmentNo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Enrollment> selectListByStudyNo(int studyNo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
