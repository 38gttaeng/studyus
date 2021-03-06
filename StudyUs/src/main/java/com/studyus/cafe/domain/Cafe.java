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
	private String caAddr;
	private String caTel;
	private String caTime;
	private String caInfo;
	private String caRoute;
	private String caFiName;
	private String caStatus; 
	private String caLat;
	private String caLng;
	
	public Cafe() {}

}
