package com.studyus.file.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.studyus.file.domain.FileVO;
import com.studyus.file.service.FileService;

@Controller
public class FileController {
	
	@Autowired
	private FileService fiService;
	
	// 파일 다운로드
	@RequestMapping(value="/file/download", method=RequestMethod.GET)
	public void fileDownload(HttpServletRequest request, HttpServletResponse response, @RequestParam("fiNo") int fiNo) throws Exception {
		
		FileVO fileVO = fiService.selectOne(fiNo);
		
		String folderName = null;
		switch(fileVO.getFiBoardType()) {
		case 1: folderName = "\\nuploadFiles\\";
			break;
		case 2: folderName = "\\auploadFiles\\";
			break;
		case 3: folderName = "\\suploadFiles\\";
			break;
		case 4: folderName = "\\cuploadFiles\\";
			break;
		case 5: folderName = "\\buploadFiles\\";
			break;
		}
		String filePath = request.getSession().getServletContext().getRealPath("resources") + folderName + fileVO.getFiStoredName();
		
		File file = new File(filePath);
		
		if(file.isFile()) {
			String userAgent = request.getHeader("User-Agent");
			
			String fileNameOrg = null;
			boolean ie = userAgent.indexOf("MSIE") > -1;
			if (ie) {
				fileNameOrg = URLEncoder.encode(fileVO.getFiRealName(),"UTF-8").replaceAll("\\+", "%20");
				response.setHeader("Content-Disposition", "attachment; filename=" + fileNameOrg + ";");
			} else {
				fileNameOrg = new String(fileVO.getFiRealName().getBytes("UTF-8"), "ISO-8859-1");
				response.setHeader("Content-Disposition", "attachment; filename=\"" + fileNameOrg + "\"");
			}
			
			response.setContentType("application/octet-stream");                
			response.setContentLength((int)file.length());
			response.setHeader("Content-Transfer-Encoding", "binary;");
			response.setHeader("Pragma", "no-cache;");
			response.setHeader("Expires", "-1;");
			
			FileInputStream fileIn = new FileInputStream(file);
			OutputStream output = response.getOutputStream();
			
			byte [] outputByte = new byte[4096];
			while(fileIn.read(outputByte, 0, 4096) != -1) {
				// 읽은 것을 다운로드 되도록 함
				output.write(outputByte, 0, 4096);
			}
			
			fileIn.close();
			output.flush();
			output.close();
		}
    }
	
	// 다중파일 저장
	public ArrayList<FileVO> saveFile(List<MultipartFile> fList, int fiBoardType, HttpServletRequest request) {

		// 저장폴더 선택
		String folderName = null;
		switch(fiBoardType) {
		case 1: folderName = "\\nuploadFiles";
			break;
		case 2: folderName = "\\auploadFiles";
			break;
		case 3: folderName = "\\suploadFiles";
			break;
		case 4: folderName = "\\cuploadFiles";
			break;
		case 5: folderName = "\\buploadFiles";
			break;
		}
		String savePath = request.getSession().getServletContext().getRealPath("resources") + folderName;
		File folder = new File(savePath);
		
		// 폴더가 없을 경우 자동 생성 (한번만 만들면 됨!)
		if(!folder.exists()) {
			folder.mkdir();
		}
		
		// 정보 저장해서 넘겨줄 파일 어레이리스트 생성
		ArrayList<FileVO> files = new ArrayList<FileVO>();
		
		for(MultipartFile mf : fList) {
			String fiRealName = mf.getOriginalFilename();
			
			// 파일명 변경하기
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmSS");
			String fiStoredName =  sdf.format(new Date(System.currentTimeMillis())) + "." + fiRealName.substring(fiRealName.lastIndexOf(".") + 1);
			
			// 파일 저장
			String filePath = folder + "\\" + fiStoredName;
			try {
				mf.transferTo(new File(filePath));
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			// DB 저장을 위해서 정보 넘기기
			FileVO fileVO = new FileVO(fiRealName, fiStoredName, filePath, fiBoardType);
			files.add(fileVO);
		}
		
		return files;
	}
	
	// 파일 삭제
	public void deleteFile(String folder, String fileName, HttpServletRequest request) {
		
		// 실제 파일 경로를 만들어서 실제 파일 삭제
		String root = request.getSession().getServletContext().getRealPath("resources");
		String savePath = root + folder;
		File file = new File(savePath + "\\" + fileName);
		if(file.exists()) {
			file.delete();
		}
	}
	
	/*** 텍스트 에디터 ***************************************************************************/
	
	// 등록시 파일 저장
	@RequestMapping(value="/file/upload/image", method=RequestMethod.POST)
	public void saveImage(HttpServletRequest request, HttpServletResponse response, @RequestParam("uploadImage") MultipartFile uploadImage) throws Exception {
		
		String fiStoredName = saveImageFile(uploadImage, request);
		
		// 텍스트 에디터에 저장정보 보내주기
		String image = "/resources/uploadFiles/" + fiStoredName;
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		gson.toJson(image, response.getWriter());
	}
	
	// 파일 하나 저장
	public String saveImageFile(MultipartFile file, HttpServletRequest request) {
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
		
		return renameFilename;
	}
	
	// 등록 취소시 업로드된 파일 전체 삭제
	@ResponseBody
	@RequestMapping(value="/file/reset/image", method=RequestMethod.GET)
	public void resetFile(HttpServletRequest request, @RequestParam("picList") List<String> picList) {
		
		// 실제 파일 경로를 만들어서 실제 파일 삭제
		String savePath = request.getSession().getServletContext().getRealPath("resources");
		
		for(String fileName : picList) {
			File file = new File(savePath + "\\" + fileName);
			if(file.exists()) {
				file.delete();
			}
		}
	}
}
