package com.studyus.attendance.domain;

import com.studyus.member.domain.Member;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AttendanceAmountWithMemberVO {
	private int memberNo;
	private int attendanceAmount;
	private Member member;
}
