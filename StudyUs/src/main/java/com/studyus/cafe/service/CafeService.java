package com.studyus.cafe.service;

import java.util.ArrayList;

import com.studyus.cafe.domain.Cafe;

public interface CafeService {

	public ArrayList<Cafe> printAll();
	public Cafe printOne(int caNo);
	public int registerCafe(Cafe cafe);
	public int modifyCage(Cafe cafe);
	public int removeCafe(int caNo);
	
}
