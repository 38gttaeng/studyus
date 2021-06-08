package com.studyus.reservation.store.logic;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.studyus.reservation.domain.Reservation;
import com.studyus.reservation.domain.ReservationMember;
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
	public ArrayList<Reservation> selectAll() {
		return (ArrayList)sqlSession.selectList("reservationMapper.selectAll");
	}

	@Override
	public ArrayList<Reservation> selectReservationByStNo(int stNo) {
		return (ArrayList)sqlSession.selectList("reservationMapper.selectAllByStNo", stNo);
	}

	@Override
	public Reservation selectOne(int rsNo) {
		return sqlSession.selectOne("reservationMapper.selectOne", rsNo);
	}

	@Override
	public ArrayList<ReservationMember> selectAllMember(int rsNo) {
		return (ArrayList)sqlSession.selectList("reservationMapper.selectAllMember", rsNo);
	}
	
	@Override
	public int insertMember(ReservationMember reservMember) {
		return sqlSession.insert("reservationMapper.insertMember", reservMember);
	}
	
	@Override
	public int deleteMember(ReservationMember reservMember) {
		return sqlSession.delete("reservationMapper.deleteMember", reservMember);
	}
	
	@Override
	public int insertReservation(Reservation reservation) {
		return sqlSession.insert("reservationMapper.insertReservation", reservation);
	}

	@Override
	public int deleteReservation(Reservation reservation) {
		return sqlSession.update("reservationMapper.deleteReservation", reservation);
	}

}
