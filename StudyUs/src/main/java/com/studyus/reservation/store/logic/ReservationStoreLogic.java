package com.studyus.reservation.store.logic;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.studyus.reservation.domain.Reservation;
import com.studyus.reservation.domain.ReservationCount;
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
	public ArrayList<Reservation> selectAll(int caNo) {
		return (ArrayList)sqlSession.selectList("reservationMapper.selectAll", caNo);
	}
	
	@Override
	public ArrayList<Integer> selectChartByWeek(int caNo) {
		ArrayList<ReservationCount> cList = (ArrayList)sqlSession.selectList("reservationMapper.selectChartByWeek", caNo);
		System.out.println(cList);////////////////////////////////
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(ReservationCount one : cList) {
			list.add(one.getCount());
		}
		return list;
	}

	@Override
	public int selectChartByMonth(int caNo) {
		return sqlSession.selectOne("reservationMapper.selectChartByMonth", caNo);
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
		sqlSession.insert("reservationMapper.insertReservation", reservation); 
		return reservation.getRsNo();
	}

	@Override
	public int deleteReservation(Reservation reservation) {
		return sqlSession.update("reservationMapper.deleteReservation", reservation);
	}

}
