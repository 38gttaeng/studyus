package com.studyus.file.service;

import java.util.ArrayList;

import com.studyus.file.domain.FileVO;
 
public interface FileService {
	
	public int uploadFile(FileVO fileVO);
	
	public int removeFile(FileVO fileVO);

	ArrayList<FileVO> selectList(FileVO fileVO);
	
	public FileVO selectOne(int fiNO);
	
	public int removeFileByFiNo(int fiNo);

	public String selectOriginName(String fiStoredName);
}
