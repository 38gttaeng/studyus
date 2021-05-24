package com.studyus.file.store.logic;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.studyus.file.domain.FileVO;
import com.studyus.file.store.FileStore;

@Repository
public class FileStoreLogic implements FileStore {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int insertFile(FileVO fileVO) {
		return sqlSession.insert("fileMapper.insertFile", fileVO);
	}

	@Override
	public int updateFile(FileVO fileVO) {
		return sqlSession.update("fileMapper.updateFile", fileVO);
	}

	@Override
	public int deleteFile(String fiStoredName) {
		return sqlSession.update("fileMapper.deleteFile", fiStoredName);
	}

	@Override
	public int selectOne(String fiStoredName) {
		return sqlSession.selectOne("fileMapper.selectOne", fiStoredName);
	}

}
