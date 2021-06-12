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
import com.studyus.member.domain.Member;

@Repository
public class AttendanceStoreLogic implements AttendanceStore{ 
	
	@Autowired
	public SqlSession sqlSession;

	@Override
	public ArrayList<Attendance> printAll(int studyNo) {  
		return (ArrayList)sqlSession.selectList("attendanceMapper.printAllAtt", studyNo);
	}

	@Override
	public int insertAttendance(Attendance attendance) {
		return sqlSession.insert("attendanceMapper.insertAttendance", attendance);
	}
	
	@Override
	public float selectPersonalAttendanceRate(int memberNo, int studyNo, int recentDays) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		map.put("memberNo", memberNo);
		map.put("studyNo", studyNo);
		map.put("recentDays", recentDays);
		
		return sqlSession.selectOne("attendanceMapper.selectPersonalAttendanceRate", map);
	}
	
	@Override
	public float selectStudyAttendanceRate(int studyNo, int recentDays) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		map.put("studyNo", studyNo);
		map.put("recentDays", recentDays);
		
		return sqlSession.selectOne("attendanceMapper.selectStudyTotalAttendanceRate", map);
	}
	
	@Override
	public ArrayList<Member> selectStudyTopAttendanceMember(int studyNo, int memberAmount) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		map.put("studyNo", studyNo);
		map.put("memberAmount", memberAmount);
		
		return (ArrayList)sqlSession.selectList("attendanceMapper.selectStudyTopAttendanceMember", map);
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
	public boolean checkTodayAttendedAlready(int meetingNo, int memberNo) {
		Attendance attendance = new Attendance();
		attendance.setMemberNo(memberNo);
		attendance.setMeetingNo(meetingNo);
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

	@Override
	public int countAttMember(int studyNo) {
		return sqlSession.selectOne("attendanceMapper.countAttMember", studyNo);
	}
}
