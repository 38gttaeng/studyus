package com.studyus.reservation.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReservationMember {
	private int rsNo;
	private int mbNo;
	private String mbNickname;
	
	public ReservationMember() {}

	public ReservationMember(int rsNo, int mbNo) {
		super();
		this.rsNo = rsNo;
		this.mbNo = mbNo;
	}
}
