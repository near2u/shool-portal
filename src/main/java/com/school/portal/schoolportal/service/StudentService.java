package com.school.portal.schoolportal.service;

import java.util.List;

import com.school.portal.schoolportal.dto.StudentDto;

public interface StudentService {

	public StudentDto addStudent(StudentDto studentDto);

	public StudentDto updateStudent(StudentDto studentDto);

	public void deleteStudentById(Integer studentId);

	public StudentDto getStudentById(Integer studentId);

	public List<StudentDto> getAllStudents();

}
