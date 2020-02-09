package com.school.portal.schoolportal.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.portal.schoolportal.dto.StudentDto;
import com.school.portal.schoolportal.mapper.DtoToObjectMapper;
import com.school.portal.schoolportal.model.Student;
import com.school.portal.schoolportal.repo.StudentRepository;
import com.school.portal.schoolportal.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private DtoToObjectMapper mapper;

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public StudentDto addStudent(StudentDto studentDto) {

		Student student = studentRepository.save(mapper.mapToStudent(studentDto));

		return mapper.mapToStudentDto(student);
	}
 
	@Override
	public StudentDto updateStudent(StudentDto studentDto) {
		Student student;
		if (studentDto.getStudentId() != 0) {
			student = studentRepository.findById(studentDto.getStudentId()).get();
			if (student != null) {
				student = mapper.mapToStudent(studentDto);
				student.setStudentId(studentDto.getStudentId());
				studentRepository.save(student);
				return mapper.mapToStudentDto(student);
			}

		}
		return null;
	}

	@Override
	public void deleteStudentById(Integer studentId) {

		Student student = studentRepository.findById(studentId).get();
		if (student != null) {
			studentRepository.delete(student);
		}

	}

	@Override
	public StudentDto getStudentById(Integer studentId) {

		Student student = studentRepository.findById(studentId).get();

		return mapper.mapToStudentDto(student);

	}

	@Override
	public List<StudentDto> getAllStudents() {
		List<Student> all = studentRepository.findAll();
		maptoStudentDts(all);
		return all.stream().map(student -> {
			return mapper.mapToStudentDto(student);
		}).collect(Collectors.toList());

	}

	private void maptoStudentDts(List<Student> all) {

	}

}
