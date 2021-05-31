package com.studyus.study.domain;

import java.util.ArrayList;

import com.studyus.enrollment.domain.Enrollment;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StudyWithEnrollmentList {
	private Study study;
	private ArrayList<Enrollment> enrollmentList;
}
