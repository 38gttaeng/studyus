package com.studyus.file.service;

import com.studyus.file.domain.File;

public interface FileService {

	public int uploadFile(File file);
	
	public int modifyFile(File file);
	
	public int removeFile(String fiStoredName);
}
