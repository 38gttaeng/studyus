package com.studyus.study.domain;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MeetingDays_dep {
	
	private boolean monday = false;
	private boolean tuesday = false;
	private boolean wednesday = false;
	private boolean thursday = false;
	private boolean friday = false;
	private boolean saturday = false;
	private boolean sunday = false;
	private String start; // 모임 시작 시간
	private String end; // 모임 종료 시간
	
}
