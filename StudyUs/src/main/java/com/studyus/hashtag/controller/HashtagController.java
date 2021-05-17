package com.studyus.hashtag.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HashtagController {
	
	@RequestMapping(value="/hashtag/register")
	public String registerHashtag(ArrayList<String> hashtagList, int studyNo) {
		return "";
	}
	
}
