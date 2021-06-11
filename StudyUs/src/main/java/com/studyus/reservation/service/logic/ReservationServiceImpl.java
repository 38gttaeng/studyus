package com.studyus.reservation.service.logic;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studyus.reservation.domain.Reservation;
import com.studyus.reservation.domain.ReservationMember;
import com.studyus.reservation.service.ReservationService;
import com.studyus.reservation.store.ReservationStore;

@Service
public class ReservationServiceImpl implements ReservationService {
	
	@Autowired
	private ReservationStore rsStore;
	
	@Override
	public ArrayList<Reservation> getReservationCheck(Reservation reservation) {
		return rsStore.getReservationCheck(reservation);
	}

	@Override
	public ArrayList<Reservation> printAll(int caNo) {
		return rsStore.selectAll(caNo);
	}
	
	@Override
	public ArrayList<Integer> printChartByWeek(int caNo) {
		return rsStore.selectChartByWeek(caNo);
	}

	@Override
	public int printChartByMonth(int caNo) {
		return rsStore.selectChartByMonth(caNo); 
	}

	@Override
	public ArrayList<Reservation> printReservationByStNo(int stNo) {
		return rsStore.selectReservationByStNo(stNo);
	}

	@Override
	public Reservation printOne(int rsNo) {
		return rsStore.selectOne(rsNo);
	}

	@Override
	public ArrayList<ReservationMember> printAllMember(int rsNo) {
		return rsStore.selectAllMember(rsNo);
	}
	
	@Override
	public int registerMember(ReservationMember reservMember) {
		return rsStore.insertMember(reservMember);
	}
	
	@Override
	public int removeMember(ReservationMember reservMember) {
		return rsStore.deleteMember(reservMember);
	}
	
	@Override
	public int registerReservation(Reservation reservation) {
		return rsStore.insertReservation(reservation);
	}

	@Override
	public int removeReservation(Reservation reservation) {
		return rsStore.deleteReservation(reservation);
	}

}
