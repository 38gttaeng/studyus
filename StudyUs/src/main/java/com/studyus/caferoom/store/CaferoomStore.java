package com.studyus.caferoom.store;

import java.util.ArrayList;

import com.studyus.caferoom.domain.Caferoom;

public interface CaferoomStore {

	public ArrayList<Caferoom> selectList();
	public Caferoom selectOne(int crNo);
}
