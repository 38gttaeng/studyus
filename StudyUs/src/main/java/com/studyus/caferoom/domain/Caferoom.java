package com.studyus.caferoom.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Caferoom {

	private int crNo;
	private int caNo;
	private int crTopPx;
	private int crLeftPx;
	private String crName;
	private String crInfo;
	private String crFilename;
	private int crMax;
	private int crPrice;

	public Caferoom() {}

}
