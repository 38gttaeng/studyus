package com.studyus.file.service.iogic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studyus.file.domain.FileVO;
import com.studyus.file.service.FileService;
import com.studyus.file.store.FileStore;

@Service
public class FileServiceImpl implements FileService {

	@Autowired
	public FileStore fiStore;
	
	@Override
	public int uploadFile(FileVO fileVO) {
		return fiStore.insertFile(fileVO);
	}

	@Override
	public int modifyFile(FileVO fileVO) {
		return fiStore.updateFile(fileVO);
	}

	@Override
	public int removeFile(String fiStoredName) {
		return fiStore.deleteFile(fiStoredName);
	}

	@Override
	public int selectOne(String fiStoredName) {
		return fiStore.selectOne(fiStoredName);
	}

}
