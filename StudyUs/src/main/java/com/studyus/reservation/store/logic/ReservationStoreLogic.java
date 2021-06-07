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
	public ArrayList<Reservation> getReservationCheck(Reservation reservation) {
		return (ArrayList)sqlSession.selectList("reservationMapper.reservationCheck", reservation);
	}

	@Override
	public ArrayList<Reservation> selectAll(int crNo, String rsDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Reservation> selectReservationByStNo(int stNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reservation selectOne(int rsNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertReservation(Reservation reservation) {
		return sqlSession.insert("reservationMapper.insertReservation", reservation);
	}

	@Override
	public int deleteReservation(int rsNo) {
		return sqlSession.update("reservationMapper.deleteReservation", rsNo);
	}

}
