package com.studyus.attendance.store.logic;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.studyus.attendance.domain.Attendance;
import com.studyus.attendance.store.AttendanceStore;

@Repository
public class AttendanceStoreLogic implements AttendanceStore{
	
	@Autowired
	public SqlSession sqlSeesion;

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
