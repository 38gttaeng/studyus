package com.studyus.reservation.service;

import java.util.ArrayList;

import com.studyus.reservation.domain.Reservation;
import com.studyus.reservation.domain.ReservationMember;

public interface ReservationService {
	
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
	public ArrayList<Reservation> printAll();
	
	/**
	 * 스터디 예약 정보 확인 (일정)
	 * @param rsNo
	 * @return
	 */
	public ArrayList<Reservation> printReservationByStNo(int stNo);
	
	/**
	 * 예약 디테일
	 * @param rsNo
	 * @return
	 */
	public Reservation printOne(int rsNo);
	
	/**
	 * 예약 참여자 리스트
	 * @param rsNo
	 * @return
	 */
	public ArrayList<ReservationMember> printAllMember(int rsNo);
	
	/**
	 * 예약 참여자 추가
	 * @param reservMember(rsNo, mbNo)
	 * @return
	 */
	public int registerMember(ReservationMember reservMember);
	
	/**
	 * 예약 참여자 삭제
	 * @param reservMember(rsNo, mbNo)
	 * @return
	 */
	public int removeMember(ReservationMember reservMember);
	
	// 예약 등록, 삭제
	
	public int registerReservation(Reservation reservation);
	public int removeReservation(Reservation reservation);

}
