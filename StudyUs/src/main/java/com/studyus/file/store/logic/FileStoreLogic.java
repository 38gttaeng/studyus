package com.studyus.file.store.logic;

import java.util.ArrayList;

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
	public int deleteFile(FileVO fileVO) {
		return sqlSession.update("fileMapper.deleteFile", fileVO);
	}

	@Override
	public ArrayList<FileVO> selectList(FileVO fileVO) {
		return (ArrayList)sqlSession.selectList("fileMapper.selectList", fileVO);
	}

}
