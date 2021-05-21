package com.studyus.cafe.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Cafe {

	private int caNo;
	private String caName;
	private String caInfo;
	private String caAddr;
	private String caRealName;
	private String caStatus;
	
	public Cafe() {}

}
