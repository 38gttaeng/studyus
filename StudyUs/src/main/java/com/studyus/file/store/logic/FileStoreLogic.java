package com.studyus.file.store.logic;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.studyus.file.domain.FileList;
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

	@Override
	public FileVO selectOne(int fiNo) {
		return sqlSession.selectOne("fileMapper.selectOne", fiNo);
	}
	
	@Override
	public int deleteFileByFiNo(int fiNo) {
		return sqlSession.update("fileMapper.deleteFileByFiNo", fiNo);
	}

	@Override
	public String selectOriginName(String fiStoredName) {
		return sqlSession.selectOne("fileMapper.selectOriginName", fiStoredName);
	}
	
	@Override
	public ArrayList<FileList> selectAllAssignment(int stNo) {
		return (ArrayList)sqlSession.selectList("fileMapper.selectAllAssignment", stNo);
	}

	@Override
	public ArrayList<FileList> selectAllSAssignment(int stNo) {
		return (ArrayList)sqlSession.selectList("fileMapper.selectAllSAssignment", stNo);
	}

}
