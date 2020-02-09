package com.school.portal.schoolportal.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

public class StudentDto {
	private int studentId;
	private String firstName;
	private String lastName;
	private int age;
	private String email;
	private String contactNumber;

	@JsonIgnoreProperties(allowSetters = true)
	private String userName;
	@JsonIgnoreProperties(allowSetters = true)
	private String password;

	private AddressDto address;

	private List<MarksDto> marks;

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public AddressDto getAddress() {
		return address;
	}

	public void setAddress(AddressDto address) {
		this.address = address;
	}

	public List<MarksDto> getMarks() {
		return marks;
	}

	public void setMarks(List<MarksDto> marks) {
		this.marks = marks;
	}

	@JsonIgnore
	public String getUserName() {
		return userName;
	}

	@JsonProperty
	public void setUserName(String userName) {
		this.userName = userName;
	}

	@JsonIgnore
	public String getPassword() {
		return password;
	}

	@JsonProperty
	public void setPassword(String password) {
		this.password = password;
	}

}
