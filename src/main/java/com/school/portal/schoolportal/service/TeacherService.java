package com.school.portal.schoolportal.service;

import java.util.List;

import com.school.portal.schoolportal.dto.MarksDto;
import com.school.portal.schoolportal.dto.TeacherDto;

public interface TeacherService {
	public TeacherDto addTeacher(TeacherDto teacherDto);

	public void addMarks(List<MarksDto> list);
}
