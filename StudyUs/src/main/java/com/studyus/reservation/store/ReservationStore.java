package com.studyus.reservation.store;

import java.util.ArrayList;

import com.studyus.reservation.domain.Reservation;
import com.studyus.reservation.domain.ReservationMember;

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
	 * 관리자 전체 예약정보 확인(카페별로 출력)
	 * @param caNo
	 * @return
	 */
	public ArrayList<Reservation> selectAll(int caNo);
	
	/**
	 * 일주일 카페별 예약수 (차트용) 
	 * @param caNo
	 * @return
	 */
	public ArrayList<Integer> selectChartByWeek(int caNo);
	
	/**
	 * 한달 카페별 예약수 (차트용)
	 * @param caNo
	 * @return
	 */
	public int selectChartByMonth(int caNo);
	
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
	
	/**
	 * 예약 참여자 리스트
	 * @param rsNo
	 * @return
	 */
	public ArrayList<ReservationMember> selectAllMember(int rsNo);
	
	/**
	 * 예약 참여자 추가
	 * @param reservMember(rsNo, mbNo)
	 * @return
	 */
	public int insertMember(ReservationMember reservMember);
	
	/**
	 * 예약 참여자 삭제
	 * @param reservMember(rsNo, mbNo)
	 * @return
	 */
	public int deleteMember(ReservationMember reservMember);
	
	// 예약 등록, 삭제
	
	public int insertReservation(Reservation reservation);
	public int deleteReservation(Reservation reservation);

}
