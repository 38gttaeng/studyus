package com.studyus.reservation.store;

import java.util.ArrayList;

import com.studyus.reservation.domain.Reservation;

public interface ReservationStore { 
	
	// 예약 보기
	
	/**
	 * 날짜별 예약 체크
	 * (그 날짜, 스터디룸에 예약이 있는 지 체크)
	 * @param reservation(crNo, rsDate)
	 * @return
	 */
	public ArrayList<Reservation> getReservationCheck(Reservation reservation);
	
	/**
	 * 관리자 전체 예약정보 확인
	 * @return
	 */
	public ArrayList<Reservation> selectAll(int crNo, String rsDate);
	
	/**
	 * 스터디 예약 정보 확인 (일정)
	 * @param stNo
	 * @return
	 */
	public ArrayList<Reservation> selectReservationByStNo(int stNo);
	
	/**
	 * 예약 디테일
	 * @param rsNo
	 * @return
	 */
	public Reservation selectOne(int rsNo);
	
	// 예약 등록, 삭제
	
	public int insertReservation(Reservation reservation);
	public int deleteReservation(int rsNo);

}
