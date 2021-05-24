package com.studyus.cafe.service.logic;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studyus.cafe.domain.Cafe;
import com.studyus.cafe.service.CafeService;
import com.studyus.cafe.store.CafeStore;

@Service
public class CafeServiceImpl implements CafeService{
	
	@Autowired
	private CafeStore cStore;

	@Override
	public ArrayList<Cafe> printAll() {
		return cStore.selectList();
	}

	@Override
	public Cafe printOne(int caNo) {
		return cStore.selectOne(caNo);
	}

	@Override
	public int registerCafe(Cafe cafe) {
		return cStore.insertCafe(cafe);
	}

	@Override
	public int modifyCafe(Cafe cafe) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int removeCafe(int caNo) {
		// TODO Auto-generated method stub
		return 0;
	}

}
