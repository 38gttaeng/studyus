package com.studyus.reservation.service;

import java.util.ArrayList;

import com.studyus.reservation.domain.Reservation;

public interface ReservationService {
	
	// 예약 보기
	
	/**
	 * 날짜별 예약 체크
	 * (그 날짜, 스터디룸에 예약이 있는 지 체크)
	 * @param crNo, rsDate
	 * @return
	 */
	public ArrayList<Reservation> printAll(int crNo, String rsDate);
	
	/**
	 * 스터디 예약 정보 확인 (일정)
	 * @param rsNo
	 * @return
	 */
	public ArrayList<Reservation> printMyReservation(int stNo);
	
	/**
	 * 개인이 해당 날짜에 예약정보가 있는 지 확인
	 * (없으면 0, 있으면 1 - count 함수 사용)
	 * @param mbNo, rsDate
	 * @return
	 */
	public int getReservationCheck(int mbNo, String rsDate);
	
	/**
	 * 예약 디테일
	 * @param rsNo
	 * @return
	 */
	public Reservation printOne(int rsNo);
	
	// 예약 등록, 삭제
	
	public int registerReservation();
	public int removeReservation();
}
