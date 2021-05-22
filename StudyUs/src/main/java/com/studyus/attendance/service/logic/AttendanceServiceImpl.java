package com.studyus.attendance.service.logic;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studyus.attendance.domain.Attendance;
import com.studyus.attendance.service.AttendanceService;
import com.studyus.attendance.store.AttendanceStore;

@Service
public class AttendanceServiceImpl implements AttendanceService {
	
	@Autowired
	public AttendanceStore  attStore;

	@Override
	public ArrayList<Attendance> printAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int attCheck(Attendance attendance) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int addPoint(Attendance attendance) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int attRate(Attendance attendance) {
		// TODO Auto-generated method stub
		return 0;
	}

}
