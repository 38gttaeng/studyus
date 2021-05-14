package com.studyus.caferoom.service.logic;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studyus.caferoom.domain.Caferoom;
import com.studyus.caferoom.service.CaferoomService;
import com.studyus.caferoom.store.CaferoomStore;

@Service
public class CaferoomServiceImpl implements CaferoomService{

	@Autowired
	private CaferoomStore crStore;
	
	@Override
	public ArrayList<Caferoom> printAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Caferoom printOne(int crNo) {
		// TODO Auto-generated method stub
		return null;
	}

}
