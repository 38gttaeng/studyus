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
	public ArrayList<Caferoom> printAll(int caNo) {
		return crStore.selectAll(caNo);
	}

	@Override
	public Caferoom printOne(int crNo) {
		return crStore.selectOne(crNo);
	}

	@Override
	public int registerCaferoom(Caferoom caferoom) {
		return crStore.insertCaferoom(caferoom);
	}
	
	@Override
	public int modifyCaferoom(Caferoom caferoom) {
		return crStore.updateCaferoom(caferoom);
	}

	@Override
	public int removeCaferoom(int crNo) {
		return crStore.deleteCaferoom(crNo);
	}
	
	@Override
	public int updateLocation(Caferoom caferoom) {
		return crStore.updateLocation(caferoom);
	}

}
