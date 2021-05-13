package com.studyus.attendance.domain;

public class Attendance {
	private int attendanceNo;
	private int memberNo;
	private int studyNo;
	private int meetingNo;
	private int meStartTime;
	private int meEndTime;
	private int ReqiredAtt;
	private int mbReputation;
	
	public Attendance() {}

	public int getAttendanceNo() {
		return attendanceNo;
	}

	public void setAttendanceNo(int attendanceNo) {
		this.attendanceNo = attendanceNo;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public int getStudyNo() {
		return studyNo;
	}

	public void setStudyNo(int studyNo) {
		this.studyNo = studyNo;
	}

	public int getMeetingNo() {
		return meetingNo;
	}

	public void setMeetingNo(int meetingNo) {
		this.meetingNo = meetingNo;
	}

	public int getMeStartTime() {
		return meStartTime;
	}

	public void setMeStartTime(int meStartTime) {
		this.meStartTime = meStartTime;
	}

	public int getMeEndTime() {
		return meEndTime;
	}

	public void setMeEndTime(int meEndTime) {
		this.meEndTime = meEndTime;
	}

	public int getReqiredAtt() {
		return ReqiredAtt;
	}

	public void setReqiredAtt(int reqiredAtt) {
		ReqiredAtt = reqiredAtt;
	}
	
	public int getMbReputation() {
		return mbReputation;
	}

	public void setMbReputation(int mbReputation) {
		this.mbReputation = mbReputation;
	}

	@Override
	public String toString() {
		return "Attendance [attendanceNo=" + attendanceNo + ", memberNo=" + memberNo + ", studyNo=" + studyNo
				+ ", meetingNo=" + meetingNo + ", meStartTime=" + meStartTime + ", meEndTime=" + meEndTime
				+ ", ReqiredAtt=" + ReqiredAtt + ", mbReputation=" + mbReputation + "]";
	}
	
}
