package com.school.portal.schoolportal.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.school.portal.schoolportal.dto.MarksDto;
import com.school.portal.schoolportal.dto.TeacherDto;
import com.school.portal.schoolportal.model.Marks;
import com.school.portal.schoolportal.model.Student;
import com.school.portal.schoolportal.model.Teacher;
import com.school.portal.schoolportal.repo.MarksRepository;
import com.school.portal.schoolportal.repo.StudentRepository;
import com.school.portal.schoolportal.repo.TeacherRepository;
import com.school.portal.schoolportal.service.TeacherService;

@Service
public class TeacherServiceImpl implements TeacherService {

	@Autowired
	private TeacherRepository teacherRepository;

	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private MarksRepository marksRepository;

	@Override
	public TeacherDto addTeacher(TeacherDto teacherDto) {
		Teacher teacher = mapToTeacher(teacherDto);
		teacher = teacherRepository.save(teacher);
		return mapToTeacherDto(teacher);
	}

	private TeacherDto mapToTeacherDto(Teacher teacher) {
		TeacherDto teacherDto = new TeacherDto();

		teacherDto.setTeacherId(teacher.getTeacherId());
		teacherDto.setDesgn(teacher.getDesgn());
		teacherDto.setName(teacher.getName());
		teacherDto.setSal(teacher.getSal());
		teacherDto.setPassword(teacher.getPassword());
		teacherDto.setUserName(teacher.getUserName());

		return teacherDto;
	}

	private Teacher mapToTeacher(TeacherDto teacherDto) {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Teacher teacher = new Teacher();

		if (teacherDto.getTeacherId() != 0) {
			teacher.setTeacherId(teacherDto.getTeacherId());
		}

		teacher.setDesgn(teacherDto.getDesgn());
		teacher.setName(teacherDto.getName());
		teacher.setSal(teacherDto.getSal());
		teacher.setPassword(encoder.encode(teacherDto.getPassword()));
		teacher.setUserName(teacherDto.getUserName());

		return teacher;
	}

	@Override
	public void addMarks(List<MarksDto> list) {

		List<Marks> marks = list.stream().map(marksDto -> {
				return mapToMarks(marksDto);
		}).collect(Collectors.toList());
		
		marksRepository.saveAll(marks);
	}

	private Marks mapToMarks(MarksDto marksDto) {

		Student student = null;
		if (marksDto.getStudentId() != 0) {
			student = studentRepository.findById(marksDto.getStudentId()).get();
		}

		if (student != null) {

			Marks marks = new Marks();
			marks.setStudent(student);
			marks.setMarksObtained(marksDto.getMarksObtained());
			marks.setMarksOutOf(marksDto.getMarksOutOf());
			marks.setSubject(marksDto.getSubject());
			return marks;
		}

		return null;
	}

}
