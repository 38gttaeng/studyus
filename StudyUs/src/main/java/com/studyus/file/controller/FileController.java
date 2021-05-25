package com.studyus.file.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.studyus.file.domain.FileVO;
import com.studyus.file.service.FileService;

public class FileController {
	@Autowired
	private FileService fiService;
	
	@ResponseBody
	@RequestMapping(value="/file/upload/image", method=RequestMethod.POST)
	public String saveImage(HttpServletRequest request, @RequestParam("uploadImage") MultipartFile uploadImage) {
		
		FileVO fileVO = saveFile(uploadImage, request);
		System.out.println(uploadImage);/******************************/
		System.out.println(request);/******************************/
		
		// 파일명 리턴
		return fileVO.getFiStoredName();
	}
	
	public FileVO saveFile(MultipartFile file, HttpServletRequest request) {
		// 파일 저장경로 설정
		String savePath = request.getSession().getServletContext().getRealPath("resources") + "\\uploadFiles";
		
		// 저장폴더 선택
		File folder = new File(savePath);
		
		// 폴더가 없을 경우 자동 생성 (한번만 만들면 됨!)
		if(!folder.exists()) {
			folder.mkdir();
		}
		
		// 파일명 변경하기
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmSS");
		String originalFilename = file.getOriginalFilename();
		String renameFilename = sdf.format(new Date(System.currentTimeMillis())) + "." + originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
		
		String filePath = folder + "\\" + renameFilename;
		
		// 파일 저장
		try {
			file.transferTo(new File(filePath));
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// 새로운 파일이름과 경로 리턴
		FileVO fileVO = new FileVO();
		fileVO.setFiStoredName(renameFilename);
		fileVO.setFiDirectory(filePath);
		return fileVO;
	}
}
