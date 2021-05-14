package com.studyus.cafe.store;

import java.util.ArrayList;

import com.studyus.cafe.domain.Cafe;

public interface CafeStore {
	
	public ArrayList<Cafe> selectList();
	public Cafe selectOne(int caNo);
	public int insertCafe(Cafe cafe);
	public int updateCafe(Cafe cafe);
	public int deleteCafe(int caNo);

}
