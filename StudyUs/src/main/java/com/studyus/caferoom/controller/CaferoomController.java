package com.studyus.caferoom.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.studyus.cafe.domain.Cafe;
import com.studyus.cafe.service.CafeService;
import com.studyus.caferoom.domain.Caferoom;
import com.studyus.caferoom.service.CaferoomService;
import com.studyus.file.controller.FileController;
import com.studyus.file.domain.FileVO;
import com.studyus.file.service.FileService;

@Controller
public class CaferoomController {
	
	@Autowired
	private CaferoomService crService;
	
	@Autowired
	private CafeService cfService;
	
	@Autowired
	private FileController fiController;
	
	@Autowired
	private FileService fiService;
	
	/************************** 정보 불러오기 **************************/
	
	// 수정페이지
	@RequestMapping(value="/cafe/room-modifyView", method=RequestMethod.GET)
	public ModelAndView caferoomListView(ModelAndView mv, @RequestParam("caNo") int caNo) {
		Cafe cafe = cfService.printOne(caNo);
		
		mv.addObject("cafe", cafe);
		mv.setViewName("cafe/roomUpdateView");
		return mv;
	}
	
	// 리스트
	@RequestMapping(value="/cafe/room-list", method=RequestMethod.GET)
	public void getCaferoomList(HttpServletResponse response, @RequestParam("caNo") int caNo) throws Exception {
		ArrayList<Caferoom> crList = crService.printAll(caNo);
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		gson.toJson(crList, response.getWriter());
	}
	
	// 디테일 (하나 더블클릭시)
	@RequestMapping(value="/cafe/room-detail", method=RequestMethod.GET)
	public void getOneCaferoom(HttpServletResponse response, @RequestParam("crNo") int crNo) throws Exception {
		Caferoom caferoom = crService.printOne(crNo);
		String originFilename = fiService.selectOriginName(caferoom.getCrFilename());
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("caferoom", caferoom);
		map.put("originFilename", originFilename);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		gson.toJson(map, response.getWriter());
	}

	/************************** 카페룸 등록, 수정, 삭제 **************************/
	
	// 등록
	@RequestMapping(value="/cafe/register-room", method=RequestMethod.POST)
	public void addCaferoom(HttpServletRequest request, HttpServletResponse response, @ModelAttribute Caferoom caferoom
			, @RequestParam(value="uploadFile", required=false) MultipartFile uploadFile) throws Exception {
		
		String savePath = "";
		String fiStoredName = "";
		if (uploadFile != null) {
			savePath = request.getSession().getServletContext().getRealPath("resources") + "\\cuploadImages";
			fiStoredName = fiController.saveImageFile(uploadFile, savePath);
			caferoom.setCrFilename(fiStoredName);
		}
		
		int crNo = crService.registerCaferoom(caferoom);
		
		if(crNo > 0) {
			if(fiStoredName != "") {
				FileVO file = new FileVO(1, uploadFile.getOriginalFilename(), fiStoredName,
						savePath + "\\" + fiStoredName, 5, crNo);
				fiService.uploadFile(file);
			}
			
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			gson.toJson(crNo, response.getWriter());
		} else {
			System.out.println("카페 스터디룸 등록 실패!");
		}
	}
	
	// 수정
	@ResponseBody
	@RequestMapping(value="/cafe/modify-room", method=RequestMethod.POST)
	public String modifyCaferoom(MultipartHttpServletRequest request, @ModelAttribute Caferoom caferoom,
			@RequestParam(value="uploadFile", required=false) MultipartFile uploadFile) {
		
		String savePath = "";
		String fiStoredName = "";
		if (uploadFile != null) {
			// 기존 파일 삭제
			Caferoom origin = crService.printOne(caferoom.getCrNo());
			fiController.deleteFile("\\cuploadImages", origin.getCrFilename(), request);
			FileVO fileVO = new FileVO(5, caferoom.getCrNo());
			fiService.removeFile(fileVO);
			
			// 새파일 업로드
			savePath = request.getSession().getServletContext().getRealPath("resources") + "\\cuploadImages";
			fiStoredName = fiController.saveImageFile(uploadFile, savePath);
			FileVO file = new FileVO(1, uploadFile.getOriginalFilename(), fiStoredName,
					savePath + "\\" + fiStoredName, 5, caferoom.getCrNo());
			fiService.uploadFile(file);
			
			caferoom.setCrFilename(fiStoredName);
		}
		
		int result = crService.modifyCaferoom(caferoom);
		
		if(result > 0) {
			return caferoom.getCrNo() + "";
		} else {
			return "fail";
		}
	}
	
	// 삭제
	@ResponseBody
	@RequestMapping(value="/cafe/delete-room", method=RequestMethod.GET)
	public String deleteCaferoom(@RequestParam("crNo") int crNo) {
		int result = crService.removeCaferoom(crNo);
		FileVO fileVO = new FileVO(5, crNo);
		fiService.removeFile(fileVO);
		
		if(result > 0) {
			return "success";
		} else {
			return "fail";
		}
	}
	
	// 전체 스터디룸 위치 수정
	@ResponseBody
	@RequestMapping(value="/cafe/modify-rooms", method=RequestMethod.POST)
	public String modifyCaferooms(@RequestParam String data) {
		String result = "";
		
		ArrayList<Caferoom> crList = new ArrayList<Caferoom>();
		try {
			List<Map<String, Integer>> locations = new Gson().fromJson(String.valueOf(data),
					new TypeToken<List<Map<String, Integer>>>(){}.getType());
			
			for (Map<String, Integer> one : locations) {
				Caferoom caferoom = new Caferoom();
				caferoom.setCrNo(one.get("id"));
				caferoom.setCrLeftPx(one.get("left"));
				caferoom.setCrTopPx(one.get("top"));
				
				crList.add(caferoom);
			}
		} catch (Exception e) {
			result = "json transition fail";
		}
		
		int crResult = 0;
		for(Caferoom crOne : crList) {
			int crResultOne = crService.updateLocation(crOne);
			crResult += crResultOne;
		}
		
		if(crResult == crList.size()) {
			result = "success";
		} else {
			result = "database storing fail";
		}
		
		return result; 
	}
	
}
