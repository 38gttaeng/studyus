package com.studyus.hashtag.domain;

public class Hashtag {
	
	private String name;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Hashtag [name=" + name + "]";
	}
}
