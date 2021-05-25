package com.studyus.member.domain;

public class MyStudyInfo {

	private String studyName;
	private int attRate;
	private int taskRate;
	private int remTask;
	
	public MyStudyInfo() {}

	public String getStudyName() {
		return studyName;
	}

	public void setStudyName(String studyName) {
		this.studyName = studyName;
	}

	public int getAttRate() {
		return attRate;
	}

	public void setAttRate(int attRate) {
		this.attRate = attRate;
	}

	public int getTaskRate() {
		return taskRate;
	}

	public void setTaskRate(int taskRate) {
		this.taskRate = taskRate;
	}

	public int getRemTask() {
		return remTask;
	}

	public void setRemTask(int remTask) {
		this.remTask = remTask;
	}

	@Override
	public String toString() {
		return "MyStudyInfo [studyName=" + studyName + ", attRate=" + attRate + ", taskRate=" + taskRate + ", remTask="
				+ remTask + "]";
	}
	
}
