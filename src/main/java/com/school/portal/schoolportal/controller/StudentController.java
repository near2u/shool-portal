package com.school.portal.schoolportal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.portal.schoolportal.dto.StudentDto;
import com.school.portal.schoolportal.service.StudentService;

@RestController

@RequestMapping("/students")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@PostMapping
	public ResponseEntity<StudentDto> addStudent(@RequestBody StudentDto studentDto) {
		StudentDto addedStudent = studentService.addStudent(studentDto);

		return new ResponseEntity<StudentDto>(addedStudent, new HttpHeaders(),
				addedStudent != null ? HttpStatus.CREATED : HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PutMapping
	public ResponseEntity<StudentDto> editStudentDetails(@RequestBody StudentDto studentDto) {
		StudentDto updatedStudent = studentService.updateStudent(studentDto);
		return new ResponseEntity<StudentDto>(updatedStudent, new HttpHeaders(),
				updatedStudent != null ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@DeleteMapping("/{studentId}")
	public ResponseEntity<Object> deleteStudent(@PathVariable("studentId") Integer studentId) {
		studentService.deleteStudentById(studentId);

		return new ResponseEntity<>(HttpStatus.OK);

	}

	@GetMapping("/{studentId}")
	public ResponseEntity<StudentDto> getStudentById(@PathVariable("studentId") Integer studentId) {
		StudentDto student = studentService.getStudentById(studentId);
		return new ResponseEntity<StudentDto>(student, new HttpHeaders(),
				student != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
	}

	@GetMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<List<StudentDto>> getAllStudents() {
		List<StudentDto> students = studentService.getAllStudents();
		return new ResponseEntity<List<StudentDto>>(students, new HttpHeaders(),
				students != null ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public void login() {

	}
}
