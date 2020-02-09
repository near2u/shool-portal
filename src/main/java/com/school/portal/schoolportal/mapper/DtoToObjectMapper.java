package com.school.portal.schoolportal.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.school.portal.schoolportal.dto.AddressDto;
import com.school.portal.schoolportal.dto.MarksDto;
import com.school.portal.schoolportal.dto.StudentDto;
import com.school.portal.schoolportal.model.Address;
import com.school.portal.schoolportal.model.Marks;
import com.school.portal.schoolportal.model.Student;
import com.school.portal.schoolportal.repo.AddressRepository;
import com.school.portal.schoolportal.repo.MarksRepository;

@Component
public class DtoToObjectMapper {

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private MarksRepository marksRepository;

	public Student mapToStudent(StudentDto studentDto) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		
		Student student = new Student();
		student.setAge(studentDto.getAge());
		student.setContactNumber(studentDto.getContactNumber());
		student.setEmail(studentDto.getEmail());
		student.setFirstName(studentDto.getFirstName());
		student.setLastName(studentDto.getLastName());
		student.setUserName(studentDto.getUserName());
		student.setPassword(encoder.encode(studentDto.getPassword()));
		student.setMarks(mapToMarks(studentDto.getMarks(), student));
		student.setAddress(mapToAddress(studentDto.getAddress(), student));

		return student;
	}

	private List<Marks> mapToMarks(List<MarksDto> marksDto, Student student) {

		return marksDto.stream().map(markDto -> {

			Marks mark;
			if (markDto.getId() != 0) {
				mark = marksRepository.findById(markDto.getId()).get();
				if (mark == null) {
					mark = new Marks();
				}
			} else {
				mark = new Marks();
			}

			mark.setMarksObtained(markDto.getMarksObtained());
			mark.setMarksOutOf(markDto.getMarksOutOf());
			mark.setSubject(markDto.getSubject());
			mark.setStudent(student);

			return mark;
		}).collect(Collectors.toList());

	}

	private Address mapToAddress(AddressDto addressDto, Student student) {

		Address address;
		if (addressDto.getAddressId() != 0) {
			address = addressRepository.findById(addressDto.getAddressId()).get();
			if (address == null) {
				address = new Address();
			}
		} else {
			address = new Address();
		}

		address.setAddressLine1(addressDto.getAddressLine1());
		address.setAddressLine2(addressDto.getAddressLine2());
		address.setCity(addressDto.getCity());
		address.setCountry(addressDto.getCountry());
		address.setStreet(addressDto.getStreet());
		address.setStudent(student);

		return address;
	}

	void updateStudentMapper(StudentDto studentDto) {

	}

	public StudentDto mapToStudentDto(Student student) {

		StudentDto studentDto = new StudentDto();
		studentDto.setStudentId(student.getStudentId());
		studentDto.setAge(student.getAge());
		studentDto.setContactNumber(student.getContactNumber());
		studentDto.setEmail(student.getEmail());
		studentDto.setFirstName(student.getFirstName());
		studentDto.setLastName(student.getLastName());
		studentDto.setMarks(mapToMarksDto(student.getMarks()));
		studentDto.setAddress(mapToAddressDto(student.getAddress()));

		return studentDto;
	}

	private AddressDto mapToAddressDto(Address address) {
		AddressDto addressDto = new AddressDto();
		addressDto.setAddressId(address.getAddressId());
		addressDto.setAddressLine1(address.getAddressLine1());
		addressDto.setAddressLine2(address.getAddressLine2());
		addressDto.setCity(address.getCity());
		addressDto.setCountry(address.getCountry());
		addressDto.setStreet(address.getStreet());
		return addressDto;
	}

	private List<MarksDto> mapToMarksDto(List<Marks> marksList) {

		return marksList.stream().map(marks -> {
			MarksDto marksDto = new MarksDto();
			marksDto.setId(marks.getId());
			marksDto.setMarksObtained(marks.getMarksObtained());
			marksDto.setMarksOutOf(marks.getMarksOutOf());
			marksDto.setSubject(marks.getSubject());
			// marksDto.setStudent(student);
			return marksDto;
		}).collect(Collectors.toList());
	}

}
