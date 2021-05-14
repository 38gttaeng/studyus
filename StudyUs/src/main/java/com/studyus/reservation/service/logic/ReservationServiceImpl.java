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
	public ArrayList<Reservation> printAll(int crNo, String rsDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Reservation> printMyReservation(int stNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getReservationCheck(int mbNo, String rsDate) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Reservation printOne(int rsNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int registerReservation() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int removeReservation() {
		// TODO Auto-generated method stub
		return 0;
	}

}
