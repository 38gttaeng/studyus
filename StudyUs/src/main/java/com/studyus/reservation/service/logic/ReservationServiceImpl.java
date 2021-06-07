package com.studyus.reservation.service.logic;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studyus.reservation.domain.Reservation;
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
	public ArrayList<Reservation> printAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Reservation> printReservationByStNo(int stNo) {
		return rsStore.selectReservationByStNo(stNo);
	}

	@Override
	public Reservation printOne(int rsNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int registerReservation(Reservation reservation) {
		return rsStore.insertReservation(reservation);
	}

	@Override
	public int removeReservation(int rsNo) {
		return rsStore.deleteReservation(rsNo);
	}

}
