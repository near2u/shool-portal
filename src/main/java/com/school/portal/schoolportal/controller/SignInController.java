package com.school.portal.schoolportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.portal.schoolportal.dto.LoginDto;
import com.school.portal.schoolportal.dto.TeacherDto;
import com.school.portal.schoolportal.jwttoken.auth.JwtTokenProvider;
import com.school.portal.schoolportal.service.TeacherService;

@RestController
@RequestMapping("/signin")
public class SignInController {

	@Autowired
	//@Qualifier("teacherAuth")
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private TeacherService teacherService;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	//@Autowired
	//@Qualifier("studentAuth")
	//private AuthenticationManager studentAuthenticationManager;
	

	@PostMapping("/teacher")
	public ResponseEntity<String> studentSignIn(@RequestBody LoginDto loginDto) {

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUserName(), loginDto.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String generateJwtToken = jwtTokenProvider.generateJwtToken(authentication);
		return ResponseEntity.ok(generateJwtToken);

	}

	@PostMapping("/addteacher")
	public ResponseEntity<TeacherDto> addTeacher(@RequestBody TeacherDto teacherDto) {

		TeacherDto addedTeacher = teacherService.addTeacher(teacherDto);
		return new ResponseEntity<TeacherDto>(addedTeacher, new HttpHeaders(),
				addedTeacher != null ? HttpStatus.CREATED : HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PostMapping("/student")
	public ResponseEntity<String> teachertSignIn(@RequestBody LoginDto loginDto) {
		
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUserName(), loginDto.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String generateJwtToken = jwtTokenProvider.generateJwtToken(authentication);
		return ResponseEntity.ok(generateJwtToken);

	}
}
