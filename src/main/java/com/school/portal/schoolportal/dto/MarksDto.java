package com.school.portal.schoolportal.dto;

public class MarksDto {

	private int id;
	private String subject;
	private int marksOutOf;
	private int marksObtained;

	private int studentId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public int getMarksOutOf() {
		return marksOutOf;
	}

	public void setMarksOutOf(int marksOutOf) {
		this.marksOutOf = marksOutOf;
	}

	public int getMarksObtained() {
		return marksObtained;
	}

	public void setMarksObtained(int marksObtained) {
		this.marksObtained = marksObtained;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

}
