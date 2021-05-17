package com.studyus.enrollment.service.logic;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studyus.enrollment.domain.Enrollment;
import com.studyus.enrollment.store.EnrollmentStore;

@Service
public class EnrollmentServiceLogic implements EnrollmentStore {
	
	@Autowired
	EnrollmentStore eStore;

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
