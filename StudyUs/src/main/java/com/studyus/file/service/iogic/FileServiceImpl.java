package com.studyus.file.service.iogic;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studyus.file.domain.FileList;
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
	public int removeFile(FileVO fileVO) {
		return fiStore.deleteFile(fileVO);
	}

	@Override
	public int removeFileByFiNo(int fiNo) {
		return fiStore.deleteFileByFiNo(fiNo);
	}
 
	@Override
	public ArrayList<FileVO> selectList(FileVO fileVO) {
		return fiStore.selectList(fileVO);
	}
	
	@Override
	public FileVO selectOne(int fiNo) {
		return fiStore.selectOne(fiNo);
	}
	
	@Override
	public String selectOriginName(String fiStoredName) {
		return fiStore.selectOriginName(fiStoredName);
	}

	@Override
	public ArrayList<FileList> printAllAssign(int stNo) { 
		ArrayList<FileList> list = new ArrayList<FileList>();
		
		ArrayList<FileList> asList = fiStore.selectAllAssignment(stNo);
		for(FileList asOne : asList) {
			list.add(asOne);
		}
		
		ArrayList<FileList> suList = fiStore.selectAllSAssignment(stNo);
		for(FileList suOne : suList) {
			list.add(suOne);
		}
		
		return list;
	}

}
