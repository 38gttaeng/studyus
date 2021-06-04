package com.studyus.assignment.domain;

import lombok.Getter;
import lombok.ToString;
import lombok.Setter;

@Getter
@Setter
@ToString
public class Assign {
	
	private int grNo;
	private int mbNo;
	
	public Assign() {}

	public Assign(int grNo, int mbNo) {
		super();
		this.grNo = grNo;
		this.mbNo = mbNo;
	}
}
