package com.studyus.file.store;

import java.util.ArrayList;

import com.studyus.file.domain.FileVO;

public interface FileStore {

	public int insertFile(FileVO fileVO);
	
	public int deleteFile(FileVO fileVO);
	
	public ArrayList<FileVO> selectList(FileVO fileVO);
	
	public FileVO selectOne(int fiNo);
	
	public int deleteFileByFiNo(int fiNo);
}
