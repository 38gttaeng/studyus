package com.studyus.member.domain;

public class MyStudyInfo {

	private String studyName;
	private int attRate;
	private int taskRate;
	private int remTask;
	private String url;
	
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
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "MyStudyInfo [studyName=" + studyName + ", attRate=" + attRate + ", taskRate=" + taskRate + ", remTask="
				+ remTask + ", url=" + url + "]";
	}

	
}
