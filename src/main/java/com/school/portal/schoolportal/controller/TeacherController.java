package com.school.portal.schoolportal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.portal.schoolportal.dto.MarksDto;
import com.school.portal.schoolportal.service.TeacherService;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

	@Autowired
	private TeacherService teacherService;

	@PostMapping("/addmarks")
	public void addMarks(@RequestBody List<MarksDto> marksList) {
		teacherService.addMarks(marksList);
		new ResponseEntity<>("MArks saved successfully", HttpStatus.OK);
	}

	public void login() {

	}
}
