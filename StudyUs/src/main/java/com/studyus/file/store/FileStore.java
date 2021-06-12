package com.studyus.file.store;

import java.util.ArrayList;

import com.studyus.file.domain.FileList;
import com.studyus.file.domain.FileVO;

public interface FileStore {

	public int insertFile(FileVO fileVO);
	 
	public int deleteFile(FileVO fileVO);
	
	public ArrayList<FileVO> selectList(FileVO fileVO);
	
	public FileVO selectOne(int fiNo);
	
	public int deleteFileByFiNo(int fiNo);

	public String selectOriginName(String fiStoredName);
	
	/** 
	 * 과제 파일함
	 * @param grNo  
	 * @return
	 */
	public ArrayList<FileList> selectAllAssignment(int grNo);
	
	/**
	 * 과제제출 파일함
	 * @param grNo
	 * @return
	 */
	public ArrayList<FileList> selectAllSAssignment(int grNo);
}
