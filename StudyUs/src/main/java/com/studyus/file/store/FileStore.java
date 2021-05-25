package com.studyus.file.store;

import com.studyus.file.domain.FileVO;

public interface FileStore {

	public int insertFile(FileVO fileVO);
	
	public int updateFile(FileVO fileVO);
	
	public int deleteFile(String fiStoredName);
	
	public int selectOne(String fiStoredName);
}
