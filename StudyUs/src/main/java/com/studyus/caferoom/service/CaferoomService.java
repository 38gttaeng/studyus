package com.studyus.caferoom.service;

import java.util.ArrayList;

import com.studyus.caferoom.domain.Caferoom;

public interface CaferoomService {

	public ArrayList<Caferoom> printAll();
	public Caferoom printOne(int crNo);
}
