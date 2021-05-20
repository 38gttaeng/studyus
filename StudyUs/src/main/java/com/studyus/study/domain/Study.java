package com.studyus.study.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Study {
	
	private int studyNo; // 스터디 pk
	private int leaderNo; // 모임장 pk
	private String filename; // 사진파일명
	private String studyName; // 스터디명
	private String introduce; // 소개글
	private int maxPersonnel; // 최대인원
	private Date insertDate; // 생성일시
	private int status; // 상태. default = 1
	private String url;
	
	// 활동 요일 및 시간
	private int monday;
	private int tuesday;
	private int wednesday;
	private int thursday;
	private int friday;
	private int saturday;
	private int sunday;
	private String start; // 모임 시작 시간
	private String end; // 모임 종료 시간
	
}
