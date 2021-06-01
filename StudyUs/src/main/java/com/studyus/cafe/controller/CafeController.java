package com.studyus.cafe.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.studyus.common.RedirectWithMsg;
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
	public ModelAndView cafeList(ModelAndView mv) {
		ArrayList<Cafe> caList = cService.printAll();
		if(!caList.isEmpty()) {
			// 디비에서 가져온 데이터(카페리스트)를 카페리스트뷰 페이지에다가 전송 
			mv.addObject("caList", caList);
			mv.setViewName("cafe/cafeListView"); 
//			for (Cafe c : caList) {
//				System.out.println(c.toString());
//			}
		} else {
			mv.addObject("msg", "카페 리스트 조회 실패");
			mv.setViewName("common/errorPage");
		}
		return mv;
	}

	// 스터디카페 상세
	@RequestMapping(value = "/cafe/detail", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView cafeDetail(ModelAndView mv, @RequestParam("caNo") int caNo) {
		Cafe cafe = cService.printOne(caNo);
		if (cafe != null) {
			mv.addObject("cafe", cafe).setViewName("cafe/cafeDetailView");
		} else {
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
	@RequestMapping(value = "/cafe/register", method = RequestMethod.POST)
	public ModelAndView cafeRegister(ModelAndView mv, @ModelAttribute Cafe cafe,
//									@RequestParam("admCd") String admCd,
//									@RequestParam("rnMgtSn") String rnMgtSn,
//									@RequestParam("udrtYn") String udrtYn,
//									@RequestParam("buldMnnm") String buldMnnm,
//									@RequestParam("buldSlno") String buldSlno,
//									@RequestParam("confmKey") String confmKey,
									@RequestParam(value = "uploadFile", required = false) MultipartFile uploadFile,
									HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 주소 검색
		// OPEN API 호출 URL 정보 설정
//		String apiUrl = "https://www.juso.go.kr/addrlink/addrCoordApi.do?admCd ="+admCd+"&rnMgtSn="+rnMgtSn+"&udrtYn="+udrtYn+"&buldMnnm="+buldMnnm+"&buldSlno="+buldSlno+"&confmKey="+confmKey;
//		URL url = new URL(apiUrl);
//    	BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(),"UTF-8"));
//    	StringBuffer sb = new StringBuffer();
//    	String tempStr = null;
//
//    	while(true){
//    		tempStr = br.readLine();
//    		if(tempStr == null) break;
//    		sb.append(tempStr);		// 응답결과 XML 저장
//    	}
//    	br.close();
//    	response.setCharacterEncoding("UTF-8");
//		response.setContentType("text/xml");
//		response.getWriter().write(sb.toString());
		
		// 파일 등록
		if (!uploadFile.getOriginalFilename().equals("")) {
			FileVO fileVO = saveFile(uploadFile, request);
			if (fileVO.getFiStoredName() != null) {
				FileVO file = new FileVO(cafe.getCaNo(), uploadFile.getOriginalFilename(), fileVO.getFiStoredName(),
						fileVO.getFiDirectory(), 4, cafe.getCaNo());
				fiService.uploadFile(file);

				cafe.setCaFiName(fileVO.getFiStoredName());
			}
		}

		// 디비에 데이터를 저장하는 작업
		int result = 0;
		String path = "";
		result = cService.registerCafe(cafe);
		if (result > 0) {
			path = "redirect:detail?caNo=" + cafe.getCaNo();
		} else {
			mv.addObject("msg", "게시글 등록 실패");
			path = "common/errorPage";
		}
		mv.setViewName(path);
		return mv;
	}

	// 파일 업로드
	public FileVO saveFile(MultipartFile file, HttpServletRequest request) {
		String savePath = request.getSession().getServletContext().getRealPath("resources") + "\\cuploadFiles";
		File folder = new File(savePath);
		if (!folder.exists()) {
			folder.mkdir();
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String originalFilename = file.getOriginalFilename();
		String renameFilename = sdf.format(new Date(System.currentTimeMillis())) + "."
				+ originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
		String filePath = folder + "\\" + renameFilename;
		try {
			file.transferTo(new File(filePath));
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		FileVO fileVO = new FileVO();
		fileVO.setFiStoredName(renameFilename);
		fileVO.setFiDirectory(filePath);
		return fileVO;
	}

	// 주소 팝업창
	@RequestMapping(value = "/cafe/caAddrPop")
	public String cafeAddrPop() {
		return "cafe/caAddrPop";
	}
	
	// 스터디 카페 수정화면
	@RequestMapping(value = "/cafe/modifyView")
	public ModelAndView cafeModifyView(ModelAndView mv, @RequestParam("caNo") int caNo) {
		Cafe cafe = cService.printOne(caNo);
		if (cafe != null) {
			mv.addObject("cafe", cafe).setViewName("cafe/cafeUpdateView");
		} else {
			mv.addObject("msg", "게시글 상세 조회 실패").setViewName("common/errorPage");
		}
		return mv;

	}

	// 스터디 카페 수정
	@RequestMapping(value = "/cafe/modify", method = RequestMethod.POST)
	public ModelAndView cafeUpdate(ModelAndView mv, HttpServletRequest request, @ModelAttribute Cafe cafe,
			@RequestParam(value = "reloadFile", required = false) MultipartFile reloadFile) {
		int fiResult = 0;
		if (reloadFile != null && !reloadFile.isEmpty()) {
			// 1. 파일 수정

			// 기존 파일 삭제
			FileVO fileVO = saveFile(reloadFile, request);
			deleteFile(cafe.getCaFiName(), request); // 기존 파일 삭제

			FileVO file = new FileVO();
			file.setFiBoardType(4);
			file.setFiMotherNo(cafe.getCaNo());
			fiResult = fiService.removeFile(file);

			// 새파일 추가
			FileVO newFile = new FileVO(1, reloadFile.getOriginalFilename(), fileVO.getFiStoredName(),
					fileVO.getFiDirectory(), 4, cafe.getCaNo());
			fiResult = fiService.uploadFile(newFile);

			cafe.setCaFiName(fileVO.getFiStoredName());
		} else {
			// 2. 파일 안 수정
			fiResult = 1;
		}

		// DB 수정
		int result = 0;
		if (fiResult > 0) {
			result = cService.modifyCafe(cafe);
			if (result > 0) {
				mv.addObject("cafe", cafe).setViewName("redirect:/cafe/detail?caNo=" + cafe.getCaNo());
			} else {
				mv.addObject("msg", "게시글 수정 실패").setViewName("common/errorPage");
			}
		}
		return mv;
	}

	// 파일 삭제
	private void deleteFile(String caFiName, HttpServletRequest request) {
		String root = request.getSession().getServletContext().getRealPath("resources");
		String savePath = root + "\\cuploadFiles";
		File file = new File(savePath + "\\" + caFiName);
		if (file.exists()) {
			file.delete();
		}
	}

	// 스터디카페 삭제
	@RequestMapping(value = "/cafe/delete", method = RequestMethod.GET)
	public String cafeDelete(HttpServletRequest request, @ModelAttribute Cafe cafe) {
 
		// 파일 삭제
		int fiResult = 0;
		if (!cafe.getCaFiName().equals("")) {
			deleteFile(cafe.getCaFiName(), request); // 기존 파일 삭제
			FileVO file = new FileVO();
			file.setFiBoardType(4);
			file.setFiMotherNo(cafe.getCaNo());
			fiResult = fiService.removeFile(file);
			cafe.setCaFiName("");
		} else {
			fiResult = 1;
		}

		if (fiResult > 0) {
			// 댓글과 게시물 삭제
			int caResult = cService.removeCafe(cafe.getCaNo()); 
			if(caResult > 0) {
				
				return new RedirectWithMsg().redirect(request, "카페 삭제 성공!", "/cafe/list");
			}else {
				
				return new RedirectWithMsg().redirect(request, "게시물 삭제 실패!", "/cafe/list");
			}
		}else {
			return new RedirectWithMsg().redirect(request, "파일 삭제 실패!", "/cafe/list");
		}
	}
}
