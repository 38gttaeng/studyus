package com.studyus.enrollment.service.logic;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studyus.enrollment.domain.Enrollment;
import com.studyus.enrollment.service.EnrollmentService;
import com.studyus.enrollment.store.EnrollmentStore;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {
	
	@Autowired
	EnrollmentStore eStore;

	@Override
	public int apply(Enrollment enrollment) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int modifyEnrollment(Enrollment enrollment) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Enrollment printOne(int enrollmentNo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Enrollment> printAllByStudyNo(int studyNo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


}
