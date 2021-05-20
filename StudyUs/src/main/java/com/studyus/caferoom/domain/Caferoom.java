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
	private int crMax;
	private int crCol;
	private int crRow;
	private String crInfo;
	private String crPrice;

	public Caferoom() {}

}
