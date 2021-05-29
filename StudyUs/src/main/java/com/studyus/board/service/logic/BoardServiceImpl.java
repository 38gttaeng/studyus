package com.studyus.board.service.logic;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studyus.board.domain.Board;
import com.studyus.board.service.BoardService;
import com.studyus.board.store.BoardStore;
import com.studyus.common.PageInfo;
import com.studyus.file.domain.FileVO;
import com.studyus.file.store.FileStore;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardStore boStore;
	
	@Autowired
	private FileStore fiStore;

	@Override
	public int getListCount(Board board) {
		return boStore.getListCount(board); 
	}

	@Override
	public ArrayList<Board> printAll(PageInfo pi, Board board) {
		
		// 1. 모든 boardList 가져오기
		ArrayList<Board> bList = boStore.selectAll(pi, board);
		
		// 2. 파일 리스트 저장
		for(Board bOne : bList) {
			// 번호에 해당하는 파일 리스트 가져오기
			FileVO fileVO = new FileVO(5, bOne.getBoNo());
			ArrayList<FileVO> fList = fiStore.selectList(fileVO);
			
			// 파일 리스트 추가해주기
			bOne.setBoFiles(fList);
		}
		
		return bList;
	}
	
	@Override
	public Board printOneReply(int boMotherNo) {
		return boStore.selectOneReply(boMotherNo);
	}
	
	@Override
	public int getSearchCount(HashMap<String, Object> map) {
		return boStore.getSearchCount(map);
	}

	@Override
	public ArrayList<Board> printSearchAll(PageInfo pi, HashMap<String, Object> map) {
		
		// 1. 모든 검색어에 해당하는 boardList 가져오기
		ArrayList<Board> bList = boStore.selectSearchAll(pi, map);
		
		// 2. 파일 리스트 저장
		for(Board bOne : bList) {
			// 번호에 해당하는 파일 리스트 가져오기
			FileVO fileVO = new FileVO(5, bOne.getBoNo());
			ArrayList<FileVO> fList = fiStore.selectList(fileVO);
			
			// 파일 리스트 추가해주기
			bOne.setBoFiles(fList);
		}
				
		return bList;
	}

	@Override
	public Board printOne(int boNo) {
		
		// board 가져오기
		Board board = boStore.selectOne(boNo);
		
		// 파일 가져오기
		FileVO fileVO = new FileVO(5, boNo);
		ArrayList<FileVO> fList = fiStore.selectList(fileVO);
		if(!fList.isEmpty()) {
			board.setBoFiles(fList);
		}
		
		return board;
	}

	@Override
	public ArrayList<Board> printAllReply(PageInfo pi, int boMotherNo) {
		return boStore.selectAllReply(pi, boMotherNo);
	}

	/////////////////////////////////////////////////////////////////////////////////
	
	@Override
	public int getListCountByMemberNo(Board board, int selected) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Board> printAllByMemberNo(PageInfo pi, Board board, int selected) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getReplyCount(int stNo, int selected) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Board> printAllReply(PageInfo pi, int stNo, int selected) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Board> printSearchAllReply(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getReplyCountByMemberNo(Board board, int selected) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Board> printAllReplyByMemberNo(PageInfo pi, Board board, int selected) {
		// TODO Auto-generated method stub
		return null;
	}

	/////////////////////////////////////////////////////////////////////////////////
	
	@Override
	public int registerBoard(Board board) {
		return boStore.insertBoard(board);
	}

	@Override
	public int modifyBoard(Board board) {
		return boStore.updateBoard(board);
	}

	@Override
	public int removeBoard(int boNo) {
		return boStore.deleteBoard(boNo);
	}

}
