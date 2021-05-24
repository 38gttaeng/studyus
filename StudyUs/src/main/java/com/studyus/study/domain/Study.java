package com.studyus.study.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
	private int monday = 0;
	private int tuesday = 0;
	private int wednesday = 0;
	private int thursday = 0;
	private int friday = 0;
	private int saturday = 0;
	private int sunday = 0;
	private String start; // 모임 시작 시간
	private String end; // 모임 종료 시간
	public int getStudyNo() {
		return studyNo;
	}
	public void setStudyNo(int studyNo) {
		this.studyNo = studyNo;
	}
	public int getLeaderNo() {
		return leaderNo;
	}
	public void setLeaderNo(int leaderNo) {
		this.leaderNo = leaderNo;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getStudyName() {
		return studyName;
	}
	public void setStudyName(String studyName) {
		this.studyName = studyName;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public int getMaxPersonnel() {
		return maxPersonnel;
	}
	public void setMaxPersonnel(int maxPersonnel) {
		this.maxPersonnel = maxPersonnel;
	}
	public Date getInsertDate() {
		return insertDate;
	}
	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getMonday() {
		return monday;
	}
	public void setMonday(int monday) {
		this.monday = monday;
	}
	public int getTuesday() {
		return tuesday;
	}
	public void setTuesday(int tuesday) {
		this.tuesday = tuesday;
	}
	public int getWednesday() {
		return wednesday;
	}
	public void setWednesday(int wednesday) {
		this.wednesday = wednesday;
	}
	public int getThursday() {
		return thursday;
	}
	public void setThursday(int thursday) {
		this.thursday = thursday;
	}
	public int getFriday() {
		return friday;
	}
	public void setFriday(int friday) {
		this.friday = friday;
	}
	public int getSaturday() {
		return saturday;
	}
	public void setSaturday(int saturday) {
		this.saturday = saturday;
	}
	public int getSunday() {
		return sunday;
	}
	public void setSunday(int sunday) {
		this.sunday = sunday;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	@Override
	public String toString() {
		return "Study [studyNo=" + studyNo + ", leaderNo=" + leaderNo + ", filename=" + filename + ", studyName="
				+ studyName + ", introduce=" + introduce + ", maxPersonnel=" + maxPersonnel + ", insertDate="
				+ insertDate + ", status=" + status + ", url=" + url + ", monday=" + monday + ", tuesday=" + tuesday
				+ ", wednesday=" + wednesday + ", thursday=" + thursday + ", friday=" + friday + ", saturday="
				+ saturday + ", sunday=" + sunday + ", start=" + start + ", end=" + end + "]";
	}
}
