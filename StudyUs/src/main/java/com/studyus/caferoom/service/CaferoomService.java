package com.studyus.caferoom.service;

import java.util.ArrayList;

import com.studyus.caferoom.domain.Caferoom;

public interface CaferoomService {
	
	// 관리자 룸 보기

	/**
	 * 룸 보기
	 * @param caNo
	 * @return
	 */
	public ArrayList<Caferoom> printAll(int caNo);
	
	/**
	 * 룸 상세정보 가져오기
	 * @param crNo
	 * @return
	 */
	public Caferoom printOne(int crNo);
	
	// 룸 등록, 수정, 삭제
	
	public int registerCaferoom(Caferoom caferoom);
	public int modifyCaferoom(Caferoom caferoom);
	public int removeCaferoom(int crNo);
	
	/**
	 * 룸 위치 수정
	 * @param caferoom
	 * @return
	 */
	int updateLocation(Caferoom caferoom);
	
}
