package com.studyus.file.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studyus.file.domain.File;
import com.studyus.file.store.FileStore;

@Service
public class FileServiceImpl implements FileService {

	@Autowired
	public FileStore store;
	
	@Override
	public int uploadFile(File file) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int modifyFile(File file) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int removeFile(String fiStoredName) {
		// TODO Auto-generated method stub
		return 0;
	}

}
