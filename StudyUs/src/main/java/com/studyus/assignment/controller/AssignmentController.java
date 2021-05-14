package com.studyus.assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.studyus.assignment.service.AssignmentService;

@Controller
public class AssignmentController {
	
	@Autowired
	private AssignmentService service;
	
	// 과제 리스트 보기
}
