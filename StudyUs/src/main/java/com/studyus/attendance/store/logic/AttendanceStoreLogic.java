package com.studyus.attendance.store.logic;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.studyus.attendance.domain.Attendance;
import com.studyus.attendance.store.AttendanceStore;

@Repository
public class AttendanceStoreLogic implements AttendanceStore{ 
	
	@Autowired
	public SqlSession sqlSession;

	@Override
	public ArrayList<HashMap<String, Object>> printAll(int studyNo) { 
		return (ArrayList)sqlSession.selectList("attendanceMapper.printAllAtt", studyNo);
	}

	@Override
	public int insertAttendance(Attendance attendance) {
		return sqlSession.insert("attendanceMapper.insertAttendance", attendance);
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

	@Override
	public boolean checkTodayAttendedAlready(Attendance attendance) {
		return 0 != (Integer)sqlSession.selectOne("attendanceMapper.checkTodayAttendedAlready", attendance);
	}
	
	@Override
	public boolean checkAttendanceToday (int studyNo, int memberNo) {
		Attendance attendance = new Attendance();
		attendance.setStudyNo(studyNo);
		attendance.setMemberNo(memberNo);
		return 0 != (Integer)sqlSession.selectOne("attendanceMapper.checkAttendanceToday", attendance);
	}
	
	@Override
	public boolean checkAttendanceTime (int studyNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
		Date now = new Date(System.currentTimeMillis());
		
		map.put("now", format.format(now));
		map.put("studyNo", studyNo);
		
		return 0 != (Integer)sqlSession.selectOne("attendanceMapper.checkAttendanceTime", map);
	}

}
