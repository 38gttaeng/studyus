package com.studyus.reservation.store.logic;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.studyus.reservation.domain.Reservation;
import com.studyus.reservation.store.ReservationStore;

@Repository
public class ReservationStoreLogic implements ReservationStore {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public ArrayList<Reservation> selectAll(int crNo, String rsDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Reservation> selectMyReservation(int stNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getReservationCheck(int mbNo, String rsDate) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Reservation selectOne(int rsNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertReservation() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteReservation() {
		// TODO Auto-generated method stub
		return 0;
	}

}
