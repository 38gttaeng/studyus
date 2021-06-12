package com.studyus.file.service;

import java.util.ArrayList;

import com.studyus.file.domain.FileList;
import com.studyus.file.domain.FileVO;
 
public interface FileService {
	
	public int uploadFile(FileVO fileVO);
	
	public int removeFile(FileVO fileVO);

	ArrayList<FileVO> selectList(FileVO fileVO);
	
	public FileVO selectOne(int fiNO);
	
	public int removeFileByFiNo(int fiNo);

	public String selectOriginName(String fiStoredName);
	
	/**
	 * 과제 / 과제제출 파일함 
	 * @param grNo
	 * @return
	 */
	public ArrayList<FileList> printAllAssign(int grNo);
	
}
