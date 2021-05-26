package com.studyus.file.service;

import java.util.ArrayList;

import com.studyus.file.domain.FileVO;

public interface FileService {
	
	public int uploadFile(FileVO fileVO);
	
	public int removeFile(FileVO fileVO);

	public ArrayList<FileVO> selectList(FileVO fileVO);
}
