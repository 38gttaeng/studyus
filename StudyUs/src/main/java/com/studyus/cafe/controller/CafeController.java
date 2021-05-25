package com.studyus.cafe.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.studyus.cafe.domain.Cafe;
import com.studyus.cafe.service.CafeService;
import com.studyus.file.domain.FileVO;
import com.studyus.file.service.FileService;

@Controller
public class CafeController {

	@Autowired
	private CafeService cService;
	
	@Autowired
	private FileService fiService;

	// 스터디카페 목록(지도)
	@RequestMapping(value = "/cafe/list", method = RequestMethod.GET)
	public String cafeList() {
		return "/cafe/cafeListView";
	}
	
	// 스터디카페 상세
	@RequestMapping(value = "/cafe/detail", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView cafeDetail(ModelAndView mv, @RequestParam("caNo") int caNo) {
		Cafe cafe = cService.printOne(caNo);
		if(cafe != null) {
			mv.addObject("cafe", cafe).setViewName("cafe/cafeDetailView");
		}else {
			mv.addObject("msg", "카페 상세조회 실패");
			mv.setViewName("common/errorPage");
		}
		return mv;
	}

	// 스터디카페 등록 화면
	@RequestMapping(value = "/cafe/registerForm", method = RequestMethod.GET)
	public String cafeRegister() {
		return "cafe/cafeRegisterForm"; 
	}
	
	// 스터디카페 등록
	@RequestMapping(value="/cafe/register", method=RequestMethod.POST)
	public ModelAndView cafeRegister(ModelAndView mv, 
									@ModelAttribute Cafe cafe, 
									@RequestParam(value = "uploadFile", required = false) MultipartFile uploadFile,
									HttpServletRequest request) {
//		HttpSession session = request.getSession();
//		cafe.setCaNo(1);
		int fiResult = 1;
		if (!uploadFile.getOriginalFilename().equals("")) {
			FileVO fileVO = saveFile(uploadFile, request);
			if (fileVO.getFiStoredName() != null) {
				FileVO file = new FileVO(cafe.getCaNo(), uploadFile.getOriginalFilename(), fileVO.getFiStoredName(), fileVO.getFiDirectory());
				fiResult = fiService.uploadFile(file);
				
				cafe.setCaFiName(fileVO.getFiStoredName());
			}
		}

		// 디비에 데이터를 저장하는 작업
		int result = 0;
		String path = "";
		result = cService.registerCafe(cafe);
		if (result > 0) {
			path = "redirect:detail?caNo="+cafe.getCaNo();
		} else {
			mv.addObject("msg", "게시글 등록 실패");
			path = "common/errorPage";
		}
		mv.setViewName(path);
		return mv;
	} 

	// 파일 업로드
	public FileVO saveFile(MultipartFile file, HttpServletRequest request) {
		String savePath = request.getSession().getServletContext().getRealPath("resources") + "\\buploadFiles";
		File folder = new File(savePath);
		if (!folder.exists()) {
			folder.mkdir();
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String originalFilename = file.getOriginalFilename();
		String renameFilename = sdf.format(new Date(System.currentTimeMillis())) + "." + originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
		String filePath = folder + "\\" + renameFilename;
		try {
			file.transferTo(new File(filePath));
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		FileVO fileVO = new FileVO();
		fileVO.setFiStoredName(renameFilename);
		fileVO.setFiDirectory(filePath);
		return fileVO;
	}

	// 스터디 카페 수정화면
	@RequestMapping(value = "/cafe/modifyView")
	public ModelAndView cafeModifyView(ModelAndView mv, @RequestParam("caNo") int caNo) {
		Cafe cafe = cService.printOne(caNo);
		if(cafe != null) {
			mv.addObject("cafe", cafe).setViewName("cafe/cafeUpdateView");
		}else {
			mv.addObject("msg", "게시글 상세 조회 실패").setViewName("common/errorPage");
		}
		return mv;

	}
	
	// 스터디 카페 수정
	@RequestMapping(value = "/cafe/modify", method = RequestMethod.POST)
	public ModelAndView cafeUpdate(ModelAndView mv, HttpServletRequest request, @ModelAttribute Cafe cafe) {
//								@RequestParam(value="reloadFile", required = false) MultipartFile reloadFile
//		int fiResult = 0;
//		// 파일 삭제 후 업로드(수정)
//				if (reloadFile != null && !reloadFile.isEmpty()) {
//					FileVO fileVO = saveFile(reloadFile, request);
//					
//					// 기존 파일 삭제
//					if (cafe.getCaFiName() != "") {
//						deleteFile(cafe.getCaFiName(), request);
//						int fiNo = fiService.selectOne(cafe.getCaFiName());
//						FileVO file = new FileVO(fiNo, reloadFile.getOriginalFilename(), fileVO.getFiStoredName(), fileVO.getFiDirectory());
//						fiResult = fiService.uploadFile(file);
//					}else {
//					// 새 파일 업로드
//					FileVO file = new FileVO(cafe.getCaNo(), reloadFile.getOriginalFilename(), fileVO.getFiStoredName(), fileVO.getFiDirectory());
//					fiResult = fiService.uploadFile(file);
//					}
//					cafe.setCaFiName(fileVO.getFiStoredName());
//				}else {
//					if(!cafe.getCaFiName().equals("")){
//						deleteFile(cafe.getCaFiName(), request);
//						fiResult = fiService.removeFile(cafe.getCaFiName());
//						cafe.setCaFiName("");
//					}else {
//						fiResult = 1;
//					}
//				}
				// DB 수정
				int result = cService.modifyCafe(cafe);
				if (result > 0) {
					mv.setViewName("redirect:/detail?caNo="+cafe.getCaNo());
				} else {
					mv.addObject("msg", "게시글 수정 실패").setViewName("common/errorPage");
				}
				return mv;
			}

	// 파일 삭제
//	private void deleteFile(String caFiName, HttpServletRequest request) {
//		String root = request.getSession().getServletContext().getRealPath("resources");
//		String savePath = root + "\\buploadFiles";
//		File file = new File(savePath + "\\" + caFiName);
//		if (file.exists()) {
//			file.delete();
//		}		
//	}

	// 스터디카페 삭제
	@RequestMapping(value = "/cafe/delete", method = RequestMethod.GET)
	public String cafeDelete(@RequestParam("caNo") int caNo, Model model, HttpServletRequest request) {
		return "cafe/cafeListView";

	}
}
