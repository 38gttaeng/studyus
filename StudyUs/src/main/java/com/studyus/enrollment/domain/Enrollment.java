package com.studyus.enrollment.domain;

import java.sql.Date;

public class Enrollment {
	private int enrollmentNo;
	private int memberNo;
	private int studyNo;
	private Date insertdate;
	private String message;
	private int status;
	
	public Enrollment() {}

	public int getEnrollmentNo() {
		return enrollmentNo;
	}

	public void setEnrollmentNo(int enrollmentNo) {
		this.enrollmentNo = enrollmentNo;
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

	public Date getInsertdate() {
		return insertdate;
	}

	public void setInsertdate(Date insertdate) {
		this.insertdate = insertdate;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Enrollment [enrollmentNo=" + enrollmentNo + ", memberNo=" + memberNo + ", studyNo=" + studyNo
				+ ", insertdate=" + insertdate + ", message=" + message + ", status=" + status + "]";
	}
}
