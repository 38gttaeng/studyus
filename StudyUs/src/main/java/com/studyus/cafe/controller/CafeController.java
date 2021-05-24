package com.studyus.cafe.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.studyus.cafe.domain.Cafe;
import com.studyus.cafe.service.CafeService;

@Controller
public class CafeController {

	@Autowired
	private CafeService cService;

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
									//@RequestParam(value = "uploadFile", required = false) MultipartFile uploadFile,
									HttpServletRequest request) {
//		if (!uploadFile.getOriginalFilename().equals("")) {
//			String caReFilename = saveFile(uploadFile, request);
//			if (caReFilename != null) {
//				cafe.setCafeFilename(uploadFile.getOriginalFilename());
//				cafe.setCafeFilename(caReFilename);
//			}
//		}
//
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
//
//	public String saveFile(MultipartFile file, HttpServletRequest request) {
//		String root = request.getSession().getServletContext().getRealPath("resources");
//		String savePath = root + "\\buploadFiles";
//		File folder = new File(savePath);
//		if (!folder.exists()) {
//			folder.mkdir();
//		}
//		
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
//		String caFilename = file.getOriginalFilename();
//		String caReFilename = sdf.format(new Date(System.currentTimeMillis())) + "."
//				+ caFilename.substring(caFilename.lastIndexOf(".") + 1);
//		String filePath = folder + "\\" + caReFilename;
//		try {
//			file.transferTo(new File(filePath));
//		} catch (IllegalStateException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return caReFilename;
//	}

	// 스터디 카페 수정
	@RequestMapping(value = "/cafe/modifyView", method = RequestMethod.GET)
	public String cafeModifyView(@RequestParam("caNo") int caNo, Model model) {
		return "cafe/cafeUpdateView";

	}

	// 스터디카페 삭제
	@RequestMapping(value = "/cafe/delete", method = RequestMethod.GET)
	public String cafeDelete(@RequestParam("caNo") int caNo, Model model, HttpServletRequest request) {
		return "cafe/cafeListView";

	}
}
