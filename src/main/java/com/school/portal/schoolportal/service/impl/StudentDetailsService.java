package com.school.portal.schoolportal.service.impl;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.school.portal.schoolportal.model.Student;
import com.school.portal.schoolportal.model.UserPrinciple;
import com.school.portal.schoolportal.repo.StudentRepository;

@Component("studentDetails")
public class StudentDetailsService implements UserDetailsService {

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		Student student = studentRepository.findByUserName(userName);

		GrantedAuthority authority = new SimpleGrantedAuthority("USER");

		User user = new User(student.getUserName(), student.getPassword(), Arrays.asList(authority));

		return UserPrinciple.build(user);
	}

}
