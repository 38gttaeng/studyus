package com.studyus.file.store;

import com.studyus.file.domain.File;

public interface FileStore {

	public int insertFile(File file);
	
	public int updateFile(File file);
	
	public int deleteFile(String fiStoredName);
}
