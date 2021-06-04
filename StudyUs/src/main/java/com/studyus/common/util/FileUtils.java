package com.studyus.common.util;

import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public class FileUtils {
	public static String saveFile(MultipartFile file, String folderName, HttpServletRequest request) {
		// 파일 저장 경로 설정 
		String root = request.getSession().getServletContext().getRealPath("resources");
		String savePath = root + "/" + folderName;
		// 저장 폴더 선택 
		File folder = new File(savePath);
		// 폴더가 없으면 자동으로 생성 
		if(!folder.exists()) {
			folder.mkdir();
		}
		// 파일명 변경하기 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String originalFileName = file.getOriginalFilename();
		String renameFileName = sdf.format(new Date(System.currentTimeMillis())) + "." + originalFileName.substring(originalFileName.lastIndexOf(".")+1);
		String filePath = folder + "/" + renameFileName;
		
		// 파일 저장 
		try {
			file.transferTo(new File(filePath));
		} catch (Exception o_O) {
			o_O.printStackTrace();
		}
		return filePath.substring(filePath.lastIndexOf("/") + 1);
	}
}
