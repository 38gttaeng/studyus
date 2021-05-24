package com.studyus.file.service;

import com.studyus.file.domain.FileVO;

public interface FileService {
	
	public int uploadFile(FileVO fileVO);
	
	public int modifyFile(FileVO fileVO);
	
	public int removeFile(String fiStoredName);
	
	public int selectOne(String fiStoredName);
}
