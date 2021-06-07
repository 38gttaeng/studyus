package com.studyus.enrollment.service.logic;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studyus.enrollment.domain.Enrollment;
import com.studyus.enrollment.domain.EnrollmentWithMember;
import com.studyus.enrollment.service.EnrollmentService;
import com.studyus.enrollment.store.EnrollmentStore;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {
	
	@Autowired
	EnrollmentStore eStore;

	@Override
	public int apply(Enrollment enrollment, String url) throws Exception {
		return eStore.insertEnrollment(enrollment, url);
	}

	@Override
	public int modifyStatus(Enrollment enrollment) throws Exception {
		return eStore.updateStatus(enrollment);
	}

	@Override
	public Enrollment printOne(int enrollmentNo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<EnrollmentWithMember> printAllByStudyNo(int studyNo) throws Exception {
		return eStore.selectListByStudyNo(studyNo);
	}

	@Override
	public int checkEnrollment(Enrollment enrollment) throws Exception {
		return eStore.checkEnrollment(enrollment);
	}

	@Override
	public int banishMember(int memberNo) {
		return eStore.banishMember(memberNo);
	}

}
