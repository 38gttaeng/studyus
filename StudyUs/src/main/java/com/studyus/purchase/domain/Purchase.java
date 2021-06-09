package com.studyus.purchase.domain;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Purchase {
	private int puNo;
	private int rnum;
	private int mbNo;
	private int stNo;
	private String stName;
	private Date puInsertDate;
	private int puStatus;
	
	public Purchase() {}
	
}
