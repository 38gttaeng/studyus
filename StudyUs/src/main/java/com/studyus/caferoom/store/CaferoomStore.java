package com.studyus.caferoom.store;

import java.util.ArrayList;

import com.studyus.caferoom.domain.Caferoom;

public interface CaferoomStore {
	
	// 관리자 룸 보기

	/**
	 * 룸 보기
	 * @param caNo
	 * @return
	 */
	public ArrayList<Caferoom> selectAll(int caNo);
	
	/**
	 * 룸 상세정보 가져오기
	 * @param crNo
	 * @return
	 */
	public Caferoom selectOne(int crNo);
	
	// 룸 등록, 삭제
	
	public int insertCaferoom(Caferoom caferoom);
	public int updateCaferoom(Caferoom caferoom);
	public int deleteCaferoom(int crNo);
	
	/**
	 * 룸 위치 변경
	 * @param caferoom
	 * @return
	 */
	int updateLocation(Caferoom caferoom);
	
}
